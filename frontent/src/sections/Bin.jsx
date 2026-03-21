import { useEffect, useState } from "react";
import { getTrashNotesApi } from "../api/notesService";
import NotesCard from "../components/NotesCard";

export const Bin = () => {
  const [trashNotesList, settrashNotes] = useState([]);

  useEffect(() => {
    getTrashNotes();
  }, []);

  const getTrashNotes = async () => {
    try {
      const data = await getTrashNotesApi();
      settrashNotes(data);
    } catch (error) {
      console.error("Error fetching notes:", error);
    }
  };


  return (
    <div className="w-100">

      <h1 className="text-secondary">Trash Notes</h1>

      {/* Notes List */}
      <div className="container card">
        {trashNotesList.map((note) => (
          <NotesCard noteData={note}/>
        ))}
      </div>

    </div>
  );
};

export default Bin;