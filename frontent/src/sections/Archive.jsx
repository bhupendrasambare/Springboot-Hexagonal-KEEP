import { useEffect, useState } from "react";
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
      setArchiveNotes(data);
    } catch (error) {
      console.error("Error fetching notes:", error);
    }
  };


  return (
    <div className="w-100">

      <h1 className="text-secondary">Archive Notes</h1>

      {/* Notes List */}
      <div className="container d-flex justify-content-start">
        {archiveNotesList.map((note) => (
          <NotesCard noteData={note}/>
        ))}
      </div>

    </div>
  );
};

export default Archived;