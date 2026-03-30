import { useEffect, useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import { MdOutlineArchive } from "react-icons/md";
import { getArchiveNotesApi } from "../api/notesService";
import NotesCard from "../components/NotesCard";

export const Archived = () => {
  const [archiveNotesList, setArchiveNotes] = useState([]);

  useEffect(() => {
    loadArchiveNotes();
  }, []);

  const loadArchiveNotes = async () => {
    try {
      const data = await getArchiveNotesApi();
      setArchiveNotes([...data]);
    } catch (error) {
      console.error("Error fetching archive notes:", error);
    }
  };

  return (
    <Container fluid className="notes-wrapper">
      {(archiveNotesList.length > 0)?(<h2 className="text-secondary fw-bold mb-5">Archived Notes</h2>):(<></>)}

      {archiveNotesList.length === 0 ? (
        <div className="empty-fullpage-wrapper">
          <MdOutlineArchive className="empty-fullpage-icon" />
          <p className="empty-fullpage-text">No archived notes found</p>
        </div>
      ) : (
        <Row className="g-4">
          {archiveNotesList.map((note) => (
            <Col key={note.id} xs={12} sm={6} md={4} lg={3}>
              <NotesCard noteData={note} refreshNotes={loadArchiveNotes} />
            </Col>
          ))}
        </Row>
      )}
    </Container>
  );
};

export default Archived;