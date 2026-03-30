import { useEffect, useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { getTrashNotesApi } from "../api/notesService";
import NotesCard from "../components/NotesCard";
import { IoTrashBinOutline } from "react-icons/io5";

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
      {(trashNotesList.length > 0)?(<h2 className="text-secondary fw-bold mb-5">Archived Notes</h2>):(<></>)}

      {trashNotesList.length === 0 ? (
        <div className="empty-fullpage-wrapper">
          <IoTrashBinOutline className="empty-fullpage-icon" />
          <p className="empty-fullpage-text">No archived notes found</p>
        </div>
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