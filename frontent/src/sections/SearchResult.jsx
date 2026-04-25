import { useEffect, useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { MdLabelImportantOutline } from "react-icons/md";
import { getPinnedNotesApi } from "../api/notesService";
import NotesCard from "../components/NotesCard";

export const SearchResult = ({refresh, searchString}) => {
  const [pinnedNotesList, setPinnedNotesList] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(false);

  const pageSize = 8;

  useEffect(() => {
    loadSearchNotes();
  }, [refresh]);

  useEffect(() => {
    const handleScroll = () => {
      if (loading) return;
      if (page >= totalPages) return;

      const bottom =
        window.innerHeight + document.documentElement.scrollTop >=
        document.documentElement.offsetHeight - 120;

      if (bottom) {
        loadMoreNotes();
      }
    };

    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, [page, totalPages, loading]);

  const loadSearchNotes = async () => {
    try {
      setLoading(true);

      const pageData = await getPinnedNotesApi(0, pageSize);

      setPinnedNotesList(pageData?.content ?? []);
      setPage(1);
      setTotalPages(pageData?.totalPages ?? 0);
    } catch (error) {
      console.error("Error fetching pinned notes:", error);
      setPinnedNotesList([]);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container fluid className="notes-wrapper">
      {pinnedNotesList.length > 0 && (
        <h2 className="text-secondary fw-bold mb-5">Search results Notes</h2>
      )}

      {pinnedNotesList.length === 0 && !loading ? (
        <div className="empty-fullpage-wrapper">
          <MdLabelImportantOutline className="empty-fullpage-icon" />
          <p className="empty-fullpage-text">No Notes Found</p>
        </div>
      ) : (
        <>
          <Row className="g-4">
            {pinnedNotesList.map((note) => (
              <Col key={note.id} xs={12} sm={6} md={4} lg={3}>
                <NotesCard noteData={note} refreshNotes={loadPinnedNotes} />
              </Col>
            ))}
          </Row>

          {loading && (
            <div className="text-center mt-4 mb-5">
              <span className="text-muted">Loading more notes...</span>
            </div>
          )}
        </>
      )}
    </Container>
  );
};

export default SearchResult;