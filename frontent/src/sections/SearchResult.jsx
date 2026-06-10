import { useEffect, useState } from "react";
import { Container, Spinner } from "react-bootstrap";
import { MdLabelImportantOutline } from "react-icons/md";
import { useNavigate, useSearchParams } from "react-router-dom";
import { searchNotes } from "../api/notesService";
import NotesCard from "../components/NotesCard";

export const SearchResult = ({ refresh }) => {
  const [notesList, setNotesList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [searchString, setSearchString] = useState("");

  const navigate = useNavigate();

  const [searchParams] = useSearchParams();

  useEffect(() => {
    const query = searchParams.get("query");

    if (query && query.trim() !== "") {
      setSearchString(query);
    } else {
      setSearchString("");
      setNotesList([]);
    }
  }, [searchParams]);

  useEffect(() => {
    if (searchString.trim() !== "") {
      loadSearchNotes(searchString);
    }
  }, [refresh]);

  const loadSearchNotes = async (query) => {
    try {
      setLoading(true);

      const response = await searchNotes(query);

      const notes = response?.data ?? [];

      setNotesList(notes);
    } catch (error) {
      console.error("Error fetching search notes:", error);
      setNotesList([]);
    } finally {
      setLoading(false);
    }
  };

  const handleSearchSubmit = async (e) => {
    e.preventDefault();

    if (searchString.trim() === "") return;

    navigate(`/home/search?query=${encodeURIComponent(searchString)}`);
  };

  return (
    <Container fluid className="notes-wrapper">
      {/* SEARCH INPUT */}
      <form
        onSubmit={handleSearchSubmit}
        className="note-input-box mb-4 mt-5"
      >
        <input
          type="text"
          className="form-control"
          placeholder="Search notes..."
          value={searchString}
          onChange={(e) => setSearchString(e.target.value)}
        />
      </form>

      {/* LOADING */}
      {loading && (
        <div className="empty-fullpage-wrapper">
          <Spinner animation="border" variant="secondary" />
          <p className="empty-fullpage-text mt-3">
            Searching notes...
          </p>
        </div>
      )}

      {/* NOTES */}
      {!loading && notesList.length > 0 && (
        <>
          <h2 className="text-secondary fw-bold mb-5">
            Search Results for "{searchString}"
          </h2>

          <div className="notes-flex-container mx-auto">
            {notesList.map((note) => (
              <div key={note.id} className="notes-flex-item">
                <NotesCard noteData={note} refreshNotes={refreshNotes} />
              </div>
            ))}
          </div>
        </>
      )}

      {/* EMPTY */}
      {!loading &&
        notesList.length === 0 &&
        searchString.trim() !== "" && (
          <div className="empty-fullpage-wrapper">
            <MdLabelImportantOutline className="empty-fullpage-icon" />
            <p className="empty-fullpage-text">
              No notes found for "{searchString}"
            </p>
          </div>
        )}
    </Container>
  );
};

export default SearchResult;