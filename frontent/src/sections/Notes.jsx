import { useEffect, useState } from "react";
import { Modal, Button, Form } from "react-bootstrap";
import { getNotesApi, createNoteApi, getPinnedNotesApi } from "../api/notesService";
import NotesCard from "../components/NotesCard";

export const Notes = () => {
  const [pinnedNotesList, setPinnedNotesList] = useState([]);
  const [notesList, setNotesList] = useState([]);
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [showModel, setShowModel] = useState(false);

  useEffect(() => {
    loadNotes();
    loadPinedNotes();
  }, []);

  const loadNotes = async () => {
    try {
      const data = await getNotesApi();
      setNotesList(data);
    } catch (error) {
      console.error("Error fetching notes:", error);
    }
  };

  const loadPinedNotes = async () => {
    try {
      const data = await getPinnedNotesApi();
      setPinnedNotesList(data);
    } catch (error) {
      console.error("Error fetching pinned notes:", error);
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
      loadNotes();
    } catch (error) {
      console.error("Error creating note:", error);
    }
  };

  return (
    <div className="w-100">

      <h1 className="text-secondary">Notes</h1>
      <p>Recent notes</p>

      <div className="mb-3 d-flex justify-content-center">
        <input
          type="text"
          className="form-control w-50"
          placeholder="Take a note..."
          onClick={() => setShowModel(true)}
          readOnly
        />
      </div>

      {/* Notes List */}
      <div className="container d-flex justify-content-start woven-container">
        {notesList.map((note) => (
          <NotesCard noteData={note}/>
        ))}
      </div>

      {/* Modal */}
      <Modal show={showModel} onHide={() => setShowModel(false)} centered size="md">
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

    </div>
  );
};

export default Notes;