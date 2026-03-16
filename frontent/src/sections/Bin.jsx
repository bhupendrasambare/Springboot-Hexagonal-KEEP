import { useEffect, useState } from "react";
import { getArchiveNotesApi, getTrashNotesApi } from "../api/notesService";

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
          <div key={note.id} className="card p-3 mb-2">
            <h5>{note.title}</h5>
            <p>{note.description}</p>
          </div>
        ))}
      </div>

    </div>
  );
};

export default Bin;