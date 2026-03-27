import { useEffect, useState } from "react";
import { Modal, Button, Form, Container, Row, Col } from "react-bootstrap";
import {
  getNotesApi,
  createNoteApi,
  getPinnedNotesApi,
} from "../api/notesService";
import NotesCard from "../components/NotesCard";

export const Notes = () => {
  const [pinnedNotesList, setPinnedNotesList] = useState([]);
  const [notesList, setNotesList] = useState([]);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [showModel, setShowModel] = useState(false);

  useEffect(() => {
    refreshNotes();
  }, []);

  const refreshNotes = async () => {
    try {
      const [pinned, notes] = await Promise.all([
        getPinnedNotesApi(),
        getNotesApi(),
      ]);

      setPinnedNotesList([...pinned]);
      setNotesList(notes.filter((n) => !n.archived));
    } catch (error) {
      console.error("Error fetching notes:", error);
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

  return (
    <Container fluid className="notes-wrapper">

      <h2 className="text-secondary fw-bold">Notes</h2>
      <p className="text-muted">Recent notes</p>

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
            {pinnedNotesList.map((note) => (
              <Col key={note.id} xs={12} sm={6} md={4} lg={3}>
                <NotesCard noteData={note} refreshNotes={refreshNotes} />
              </Col>
            ))}
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

      {/* Modal */}
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