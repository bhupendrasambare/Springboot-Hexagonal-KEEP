import { useEffect, useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { MdLabelImportantOutline } from "react-icons/md";
import { useSearchParams } from "react-router-dom";
import { searchNotes } from "../api/notesService";
import NotesCard from "../components/NotesCard";

export const SearchResult = ({ refresh }) => {
  const [notesList, setNotesList] = useState([]);
  const [loading, setLoading] = useState(false);

  const [searchParams] = useSearchParams();
  const searchString = searchParams.get("query") || "";

  useEffect(() => {
    if (searchString) {
      loadSearchNotes();
    } else {
      setNotesList([]);
    }
  }, [refresh, searchString]);

  const loadSearchNotes = async () => {
    try {
      setLoading(true);

      const response = await searchNotes(searchString);

      const notes = response?.data ?? [];

      setNotesList(notes);
    } catch (error) {
      console.error("Error fetching search notes:", error);
      setNotesList([]);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container fluid className="notes-wrapper">
      {notesList.length > 0 && (
        <h2 className="text-secondary fw-bold mb-5">
          Search Results for "{searchString}"
        </h2>
      )}

      {notesList.length === 0 && !loading ? (
        <div className="empty-fullpage-wrapper">
          <MdLabelImportantOutline className="empty-fullpage-icon" />
          <p className="empty-fullpage-text">
            No notes found for "{searchString}"
          </p>
        </div>
      ) : (
        <>
          <Row className="g-4">
            {notesList.map((note) => (
              <Col key={note.id} xs={12} sm={6} md={4} lg={3}>
                <NotesCard noteData={note} refreshNotes={loadSearchNotes} />
              </Col>
            ))}
          </Row>

          {loading && (
            <div className="text-center mt-4 mb-5">
              <span className="text-muted">Searching notes...</span>
            </div>
          )}
        </>
      )}
    </Container>
  );
};

export default SearchResult;