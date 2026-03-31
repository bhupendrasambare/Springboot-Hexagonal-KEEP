import { useEffect, useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { MdOutlineArchive } from "react-icons/md";
import { getArchiveNotesApi } from "../api/notesService";
import NotesCard from "../components/NotesCard";

export const Archived = () => {
  const [archiveNotesList, setArchiveNotes] = useState([]);
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(false);

  const pageSize = 10;

  useEffect(() => {
    loadArchiveNotes();
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

  /* ========== FIRST LOAD ========== */
  const loadArchiveNotes = async () => {
    try {
      setLoading(true);

      const pageData = await getArchiveNotesApi(0, pageSize);

      setArchiveNotes(pageData?.content ?? []);
      setPage(1);
      setTotalPages(pageData?.totalPages ?? 0);
    } catch (error) {
      console.error("Error fetching archive notes:", error);
      setArchiveNotes([]);
    } finally {
      setLoading(false);
    }
  };

  /* ========== LOAD MORE ========== */
  const loadMoreNotes = async () => {
    try {
      if (loading) return;

      setLoading(true);

      const pageData = await getArchiveNotesApi(page, pageSize);

      const newNotes = pageData?.content ?? [];

      setArchiveNotes((prev) => {
        const existingIds = new Set(prev.map((n) => n.id));
        const filtered = newNotes.filter((n) => !existingIds.has(n.id));
        return [...prev, ...filtered];
      });

      setPage((prev) => prev + 1);
    } catch (error) {
      console.error("Error loading more archive notes:", error);
    } finally {
      setLoading(false);
    }
  };

  /* ========== UI ========== */
  return (
    <Container fluid className="notes-wrapper">
      {archiveNotesList.length > 0 && (
        <h2 className="text-secondary fw-bold mb-5">Archived Notes</h2>
      )}

      {archiveNotesList.length === 0 && !loading ? (
        <div className="empty-fullpage-wrapper">
          <MdOutlineArchive className="empty-fullpage-icon" />
          <p className="empty-fullpage-text">No archived notes found</p>
        </div>
      ) : (
        <>
          <Row className="g-4">
            {archiveNotesList.map((note) => (
              <Col key={note.id} xs={12} sm={6} md={4} lg={3}>
                <NotesCard noteData={note} refreshNotes={loadArchiveNotes} />
              </Col>
            ))}
          </Row>

          {loading && (
            <div className="text-center mt-4 mb-5">
              <span className="text-muted">Loading more archived notes...</span>
            </div>
          )}
        </>
      )}
    </Container>
  );
};

export default Archived;