import { useEffect, useState } from "react";
import { Modal, Button, Form, Container, Row, Col } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import {
  getNotesApi,
  createNoteApi,
  getPinnedNotesApi,
} from "../api/notesService";
import NotesCard from "../components/NotesCard";

export const Notes = ({refresh}) => {
  const [pinnedNotesList, setPinnedNotesList] = useState([]);
  const [notesList, setNotesList] = useState([]);

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [showModel, setShowModel] = useState(false);

  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [loading, setLoading] = useState(false);

  const navigate = useNavigate();
  const pageSize = 10;

  useEffect(() => {
    refreshNotes();
  }, [refresh]);

  useEffect(() => {
    const handleScroll = () => {
      if (loading) return;
      if (page >= totalPages) return;

      const bottom =
        window.innerHeight + document.documentElement.scrollTop >=
        document.documentElement.offsetHeight - 120;

      if (bottom) loadMoreNotes();
    };

    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, [page, totalPages, loading]);

  /* ================= FIRST LOAD ================= */

  const refreshNotes = async () => {
    try {
      setLoading(true);
      setPage(0);

      const [pinnedPage, notesPage] = await Promise.all([
        getPinnedNotesApi(0, 8),
        getNotesApi(0, pageSize),
      ]);

      setPinnedNotesList(pinnedPage?.content ?? []);
      setNotesList(notesPage?.content ?? []);

      setPage(1);
      setTotalPages(notesPage?.totalPages ?? 0);
    } catch (error) {
      console.error("Error fetching notes:", error);
      setPinnedNotesList([]);
      setNotesList([]);
    } finally {
      setLoading(false);
    }
  };

  /* ================= LOAD MORE ================= */

  const loadMoreNotes = async () => {
    try {
      if (loading) return;

      setLoading(true);

      const pageData = await getNotesApi(page, pageSize);
      const newNotes = pageData?.content ?? [];

      setNotesList((prev) => {
        const existingIds = new Set(prev.map((n) => n.id));
        const filtered = newNotes.filter((n) => !existingIds.has(n.id));
        return [...prev, ...filtered];
      });

      setPage((prev) => prev + 1);
    } catch (error) {
      console.error("Error loading more notes:", error);
    } finally {
      setLoading(false);
    }
  };


  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await createNoteApi({
        title,
        description,
        reminder: "",
        tagId: "",
      });

      setTitle("");
      setDescription("");
      setShowModel(false);
      refreshNotes();
    } catch (error) {
      console.error("Error creating note:", error);
    }
  };

  const pinnedToShow = pinnedNotesList.slice(0, 7);
  const showMorePinned = pinnedNotesList.length > 7;


  return (
    <Container fluid className="notes-wrapper">
      <h2 className="text-secondary fw-bold mb-5">Notes</h2>

      {/* Create Note Box */}
      <div className="note-input-box mb-4">
        <input
          type="text"
          className="form-control"
          placeholder="Take a note..."
          onClick={() => setShowModel(true)}
          readOnly
        />
      </div>

      {/* PINNED NOTES */}
      {pinnedNotesList.length > 0 && (
        <>
          <h6 className="section-title">Pinned</h6>

          <Row className="g-4">
            {pinnedToShow.map((note) => (
              <Col key={note.id} xs={12} md={4} lg={3} xl={2}>
                <NotesCard noteData={note} refreshNotes={refreshNotes} />
              </Col>
            ))}

            {showMorePinned && (
              <Col xs={12} md={4} lg={3} xl={2}>
                <div
                  className="note-card"
                  onClick={() => navigate("/important")}
                  style={{ cursor: "pointer" }}
                >
                  <div className="note-content">
                    <h5 className="note-title text-primary text-center mt-4">
                      Show More
                    </h5>
                    <p className="note-description px-5 text-center mt-4">
                      View all important notes that are pinned
                    </p>
                  </div>
                </div>
              </Col>
            )}
          </Row>
        </>
      )}

      {/* NORMAL NOTES */}
      {notesList.length > 0 && (
        <>
          <h6 className="section-title mt-5">Others</h6>

          <Row className="g-4">
            {notesList.map((note) => (
              <Col key={note.id} xs={12} sm={6} md={4} lg={3}>
                <NotesCard noteData={note} refreshNotes={refreshNotes} />
              </Col>
            ))}
          </Row>
        </>
      )}

      {loading && (
        <div className="text-center mt-4 mb-5">
          <span className="text-muted">Loading more notes...</span>
        </div>
      )}

      {/* MODAL */}
      <Modal show={showModel} onHide={() => setShowModel(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>Create Note</Modal.Title>
        </Modal.Header>

        <Form onSubmit={handleSubmit}>
          <Modal.Body>
            <Form.Control
              type="text"
              placeholder="Title"
              className="mb-3"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
            />

            <Form.Control
              as="textarea"
              rows={4}
              placeholder="Write your note..."
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            />
          </Modal.Body>

          <Modal.Footer>
            <Button variant="secondary" onClick={() => setShowModel(false)}>
              Cancel
            </Button>

            <Button variant="primary" type="submit">
              Save
            </Button>
          </Modal.Footer>
        </Form>
      </Modal>
    </Container>
  );
};

export default Notes;