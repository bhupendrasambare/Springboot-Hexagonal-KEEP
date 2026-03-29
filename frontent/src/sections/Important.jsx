import { useEffect, useState } from "react";
import { getPinnedNotesApi, getPinnedNotesApi } from "../api/notesService";
import NotesCard from "../components/NotesCard";

export const Important = () => {
  const [PinnedNotesList, setPinnedNotes] = useState([]);

  useEffect(() => {
    getPinnedNotes();
  }, []);

  const getPinnedNotes = async () => {
    try {
      const data = await getPinnedNotesApi();
      setPinnedNotes(data);
    } catch (error) {
      console.error("Error fetching notes:", error);
    }
  };


  return (
    <div className="w-100">

      <h1 className="text-secondary">Trash Notes</h1>

      <div className="container card">
        {PinnedNotesList.map((note) => (
          <NotesCard noteData={note}/>
        ))}
      </div>

    </div>
  );
};

export default Important;