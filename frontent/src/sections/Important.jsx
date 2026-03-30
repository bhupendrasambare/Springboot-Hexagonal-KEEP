import { useEffect, useState } from "react";
import { Container, Row, Col } from "react-bootstrap";
import {
  getPinnedNotesApi,
} from "../api/notesService";
import NotesCard from "../components/NotesCard";
import { MdLabelImportantOutline } from "react-icons/md";

export const Notes = () => {
  const [pinnedNotesList, setPinnedNotesList] = useState([]);

  useEffect(() => {
    refreshNotes();
  }, []);

  const refreshNotes = async () => {
    try {
      const [pinned, notes] = await Promise.all([
        getPinnedNotesApi(),
      ]);

      setPinnedNotesList([...pinned]);
    } catch (error) {
      console.error("Error fetching notes:", error);
    }
  };

  return (
    <Container fluid className="notes-wrapper">

      {(pinnedNotesList.length > 0)?(<h2 className="text-secondary fw-bold mb-5">Important Notes</h2>):(<></>)}


      {/* PINNED NOTES */}
      {(pinnedNotesList.length > 0)?(
        <>

          <Row className="g-4">
            {pinnedNotesList.map((note) => (
              <Col key={note.id} xs={12} sm={6} md={4} lg={3}>
                <NotesCard noteData={note} refreshNotes={refreshNotes} />
              </Col>
            ))}
          </Row>
        </>
      ):<>
        <div className="empty-fullpage-wrapper">
          <MdLabelImportantOutline className="empty-fullpage-icon" />
          <p className="empty-fullpage-text">No Important Notes Found</p>
        </div>
      </>}
    </Container>
  );
};

export default Notes;