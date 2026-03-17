import { useEffect, useState } from "react";
import { getArchiveNotesApi, getTrashNotesApi } from "../api/notesService";
import NotesCard from "../components/NotesCard";

export const Bin = () => {
  const [archiveNotesList, setArchiveNotes] = useState([]);

  useEffect(() => {
    loadArchiveNotes();
  }, []);

  const loadArchiveNotes = async () => {
    try {
      const data = await getTrashNotesApi();
      setArchiveNotes(data);
    } catch (error) {
      console.error("Error fetching notes:", error);
    }
  };


  return (
    <div className="w-100">

      <h1 className="text-secondary">Archive Notes</h1>

      {/* Notes List */}
      <div className="container card">
        {archiveNotesList.map((note) => (
          <NotesCard noteData={note}/>
        ))}
      </div>

    </div>
  );
};

export default Bin;