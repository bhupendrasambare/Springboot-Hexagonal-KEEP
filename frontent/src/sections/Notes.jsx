import { useEffect, useState } from "react";
import axiosInstance from "../api/axiosInstance";
import { useAuth } from "../store/AuthContext";

export const Notes = () => {
  const [notesList, setNotesList] = useState([]);
  const { accessToken } = useAuth();

  useEffect(() => {
    getNotes();
  }, []);

  const getNotes = async () => {
    try {
      const response = await axiosInstance.post(
        "/notes/find",
        {
          pinned: true,
          archived: true,
          trashed: true,
          keyword: "",
          page: 0,
          pageSize: 10,
        },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );

      setNotesList(response.data.content || response.data);
    } catch (error) {
      console.error("Error fetching notes:", error);
    }
  };

  return (
    <div>
      <h1>Notes</h1>
      <p>Recent notes</p>

      <div className="container">
        {notesList.map((note) => (
          <div key={note.id} className="card p-3 mb-2">
            <h5>{note.title}</h5>
            <p>{note.content}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Notes;