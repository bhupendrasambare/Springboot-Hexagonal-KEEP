import { useEffect, useState } from "react";
import axiosInstance from "../api/axiosInstance";
import { useAuth } from "../store/AuthContext";
import { Modal } from "react-bootstrap";

export const Notes = () => {
  const [notesList, setNotesList] = useState([]);
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [showModel, setShowModel] = useState(false);
  const { accessToken } = useAuth();

  useEffect(() => {
    getNotes();
  }, [showModel]);

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

  const openModal = () => {
    const modal = new Modal(document.getElementById("noteModal"));
    modal.show();
  };

  const handleSaveNote = async () => {
    try {
      await axiosInstance.post(
        "/notes",
        { title, content },
        {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );

      setTitle("");
      setContent("");
      getNotes();

      const modalEl = document.getElementById("noteModal");
      const modal = Modal.getInstance(modalEl);
      modal.hide();
    } catch (error) {
      console.error("Error saving note:", error);
    }
  };

  return (
    <div className="w-100">
      <h1 className="text-secondary">Notes</h1>
      <p>Recent notes</p>

      {/* Input box */}
      <div className="mb-3 d-flex justify-content-center">
        <input
          type="text"
          className="form-control w-50"
          placeholder="Take a note..."
          onClick={()=> setShowModel(true)}
          readOnly
        />
      </div>

      {/* Notes List */}
      <div className="container">
        {notesList.map((note) => (
          <div key={note.id} className="card p-3 mb-2">
            <h5>{note.title}</h5>
            <p>{note.content}</p>
          </div>
        ))}
      </div>

      {/* Bootstrap Modal (Medium Size) */}
      <Modal show={showModel} onHide= {()=> setShowModel(!showModel)}
        className="fade"
        id="noteModal"
        tabIndex="-1"
        aria-hidden="true"
      >
          <div className="modal-content">

            <div className="modal-body">
              <input
                type="text"
                className="form-control mb-3"
                placeholder="Title"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
              />

              <textarea
                className="form-control"
                rows="4"
                placeholder="Write your note..."
                value={content}
                onChange={(e) => setContent(e.target.value)}
              />

              <div className="mt-3 d-flex justify-content-end">

              <button
                className="mx-2 btn btn-secondary"
                 onHide= {()=> setShowModel(!showModel)}
              >
                Cancel
              </button>
              <button
                className="mx-2 btn btn-primary"
                onClick={handleSaveNote}
              >
                Save
              </button>
              </div>
            </div>
          </div>
      </Modal>
    </div>
  );
};

export default Notes;