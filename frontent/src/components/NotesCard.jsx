import { IoTrashBinOutline } from "react-icons/io5";
import { MdOutlineArchive, MdOutlinePushPin, MdPushPin } from "react-icons/md";
import {
  pinNoteApi,
  unPinNoteApi,
  archiveNoteApi,
  unArchiveNoteApi,
  trashNoteApi,
} from "../api/notesService";

function NotesCard({ noteData, refreshNotes }) {

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

  return (
    <div className="note-card">

      <div className="note-content">
        <h5 className="note-title">{noteData.title}</h5>
        <p className="note-description">{noteData.description}</p>
      </div>

      <div className="note-footer">

        

        {noteData.trashed == false ?
          <>
            <IoTrashBinOutline
              className="note-icon"
              onClick={handleTrash}
            />
          </>:<></>
        }
        

        {noteData.trashed == false ?
          <>
            <MdOutlineArchive
              className="note-icon"
              onClick={handleArchiveToggle}
            />
          </>:<></>
        }

        {noteData.archived == false && noteData.trashed == false ?
          <>
            {noteData.pinned ? (
              <MdPushPin className="note-icon active-icon" onClick={handlePinToggle} />
            ) : (
              <MdOutlinePushPin className="note-icon" onClick={handlePinToggle} />
            )}
          </>:<></>
        }

      </div>
    </div>
  );
}

export default NotesCard;