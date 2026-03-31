import { useEffect, useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { IoTrashBinOutline } from "react-icons/io5";
import { getTrashNotesApi } from "../api/notesService";
import NotesCard from "../components/NotesCard";

export const Bin = () => {
  const [trashNotesList, setTrashNotes] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(false);

  const pageSize = 10;

  useEffect(() => {
    loadTrashNotes();
  }, []);

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

  const loadTrashNotes = async () => {
    try {
      setLoading(true);

      const pageData = await getTrashNotesApi(0, pageSize);

      setTrashNotes(pageData?.content ?? []);
      setPage(1);
      setTotalPages(pageData?.totalPages ?? 0);
    } catch (error) {
      console.error("Error fetching trash notes:", error);
      setTrashNotes([]);
    } finally {
      setLoading(false);
    }
  };

  const loadMoreNotes = async () => {
    try {
      if (loading) return;

      setLoading(true);

      const pageData = await getTrashNotesApi(page, pageSize);
      const newNotes = pageData?.content ?? [];

      setTrashNotes((prev) => {
        const existingIds = new Set(prev.map((n) => n.id));
        const filtered = newNotes.filter((n) => !existingIds.has(n.id));
        return [...prev, ...filtered];
      });

      setPage((prev) => prev + 1);
    } catch (error) {
      console.error("Error loading more trash notes:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container fluid className="notes-wrapper">
      {trashNotesList.length > 0 && (
        <h2 className="text-secondary fw-bold mb-5">Trash Notes</h2>
      )}

      {trashNotesList.length === 0 && !loading ? (
        <div className="empty-fullpage-wrapper">
          <IoTrashBinOutline className="empty-fullpage-icon" />
          <p className="empty-fullpage-text">No trash notes found</p>
        </div>
      ) : (
        <>
          <Row className="g-4">
            {trashNotesList.map((note) => (
              <Col key={note.id} xs={12} sm={6} md={4} lg={3}>
                <NotesCard noteData={note} refreshNotes={loadTrashNotes} />
              </Col>
            ))}
          </Row>

          {loading && (
            <div className="text-center mt-4 mb-5">
              <span className="text-muted">Loading more trash notes...</span>
            </div>
          )}
        </>
      )}
    </Container>
  );
};

export default Bin;