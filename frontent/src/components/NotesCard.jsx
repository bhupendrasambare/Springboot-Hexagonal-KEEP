import { useState } from "react";
import { IoTrashBinOutline } from "react-icons/io5";
import { MdOutlineArchive, MdOutlinePushPin, MdPushPin } from "react-icons/md";
import { Modal } from "react-bootstrap";
import {
  pinNoteApi,
  unPinNoteApi,
  archiveNoteApi,
  unArchiveNoteApi,
  trashNoteApi,
} from "../api/notesService";

function NotesCard({ noteData, refreshNotes }) {
  const [showModal, setShowModal] = useState(false);

  /* ================= TEXT LIMIT HANDLING ================= */

  const trimTitle = (text) => {
    if (!text) return "";
    return text.length > 50 ? text.substring(0, 50) + "..." : text;
  };

  const trimDescription = (text) => {
    if (!text) return "";
    return text.length > 200 ? text.substring(0, 200) + "..." : text;
  };

  /* ================= ACTIONS ================= */

  const handleArchiveToggle = async () => {
    try {
      if (noteData.archived) {
        await unArchiveNoteApi(noteData.id);
      } else {
        await archiveNoteApi(noteData.id);
      }

      await refreshNotes();
    } catch (error) {
      console.error("Archive error:", error);
    }
  };

  const handlePinToggle = async () => {
    try {
      if (noteData.pinned) {
        await unPinNoteApi(noteData.id);
      } else {
        await pinNoteApi(noteData.id);
      }

      await refreshNotes();
    } catch (error) {
      console.error("Pin error:", error);
    }
  };

  const handleTrash = async () => {
    try {
      await trashNoteApi(noteData.id);
      await refreshNotes();
    } catch (error) {
      console.error("Trash error:", error);
    }
  };

  /* ================= UI ================= */

  return (
    <>
      <div className="note-card">
        <div className="note-content">
          <h5
            style={{ cursor: "pointer" }}
            onClick={() => setShowModal(true)}
            className="note-title">{trimTitle(noteData.title)}</h5>

          <p
            className="note-description"
            style={{ cursor: "pointer" }}
            onClick={() => setShowModal(true)}
          >
            {trimDescription(noteData.description)}
          </p>
        </div>

        <div className="note-footer">
          {noteData.trashed === false && (
            <IoTrashBinOutline className="note-icon" onClick={handleTrash} />
          )}

          {noteData.trashed === false && (
            <MdOutlineArchive
              className="note-icon"
              onClick={handleArchiveToggle}
            />
          )}

          {noteData.archived === false && noteData.trashed === false && (
            <>
              {noteData.pinned ? (
                <MdPushPin
                  className="note-icon active-icon"
                  onClick={handlePinToggle}
                />
              ) : (
                <MdOutlinePushPin
                  className="note-icon"
                  onClick={handlePinToggle}
                />
              )}
            </>
          )}
        </div>
      </div>

      {/* ================= VIEW NOTE MODAL ================= */}

      <Modal show={showModal} onHide={() => setShowModal(false)} centered>
        <Modal.Header closeButton>
          <Modal.Title>{noteData.title}</Modal.Title>
        </Modal.Header>

        <Modal.Body>
          <p style={{ whiteSpace: "pre-wrap" }}>{noteData.description}</p>
        </Modal.Body>
      </Modal>
    </>
  );
}

export default NotesCard;