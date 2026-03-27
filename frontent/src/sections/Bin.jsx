import { useEffect, useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { getTrashNotesApi } from "../api/notesService";
import NotesCard from "../components/NotesCard";

export const Bin = () => {
  const [trashNotesList, setTrashNotes] = useState([]);

  useEffect(() => {
    loadTrashNotes();
  }, []);

  const loadTrashNotes = async () => {
    try {
      const data = await getTrashNotesApi();
      setTrashNotes([...data]);
    } catch (error) {
      console.error("Error fetching trash notes:", error);
    }
  };

  return (
    <Container fluid className="notes-wrapper">
      <h2 className="text-secondary fw-bold">Trash Notes</h2>
      <p className="text-muted">Notes moved to trash</p>

      {trashNotesList.length === 0 ? (
        <div className="empty-notes">No notes in trash</div>
      ) : (
        <Row className="g-4">
          {trashNotesList.map((note) => (
            <Col key={note.id} xs={12} sm={6} md={4} lg={3}>
              <NotesCard noteData={note} refreshNotes={loadTrashNotes} />
            </Col>
          ))}
        </Row>
      )}
    </Container>
  );
};

export default Bin;