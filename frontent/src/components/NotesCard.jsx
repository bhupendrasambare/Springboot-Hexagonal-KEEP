import { useState } from "react";
import { IoTrashBinOutline } from "react-icons/io5";

import {
  MdOutlineArchive,
  MdOutlinePushPin,
  MdPushPin,
  MdOutlineAlarm,
} from "react-icons/md";

import {
  Modal,
  Button,
  Form,
} from "react-bootstrap";

import {
  pinNoteApi,
  unPinNoteApi,
  archiveNoteApi,
  unArchiveNoteApi,
  trashNoteApi,
} from "../api/notesService";

import { createReminder } from "../api/reminderService";

function NotesCard({ noteData, refreshNotes }) {

  const [showModal, setShowModal] =
    useState(false);

  const [showReminderModal,
    setShowReminderModal] =
    useState(false);

  const [loading, setLoading] =
    useState(false);

  const [reminderData,
    setReminderData] = useState({
      title: noteData.title || "",
      description:
        noteData.description || "",
      reminderTime: "",
    });

  const trimTitle = (text) => {

    if (!text) return "";

    return text.length > 15
      ? text.substring(0, 15) + "..."
      : text;
  };

  const trimDescription = (
    text
  ) => {

    if (!text) return "";

    return text.length > 60
      ? text.substring(0, 60) + "..."
      : text;
  };

  const handleArchiveToggle =
    async () => {

      try {

        if (noteData.archived) {

          await unArchiveNoteApi(
            noteData.id
          );

        } else {

          await archiveNoteApi(
            noteData.id
          );
        }

        await refreshNotes();

      } catch (error) {

        console.error(
          "Archive error:",
          error
        );
      }
    };

  const handlePinToggle =
    async () => {

      try {

        if (noteData.pinned) {

          await unPinNoteApi(
            noteData.id
          );

        } else {

          await pinNoteApi(
            noteData.id
          );
        }

        await refreshNotes();

      } catch (error) {

        console.error(
          "Pin error:",
          error
        );
      }
    };

  const handleTrash =
    async () => {

      try {

        await trashNoteApi(
          noteData.id
        );

        await refreshNotes();

      } catch (error) {

        console.error(
          "Trash error:",
          error
        );
      }
    };

  const handleCreateReminder =
    async (e) => {

      e.preventDefault();

      try {

        setLoading(true);

        await createReminder({
          noteId: noteData.id,
          title:
            reminderData.title,
          description:
            reminderData.description,
          reminderTime:
            reminderData.reminderTime,
        });

        setShowReminderModal(false);

        setReminderData({
          title:
            noteData.title || "",
          description:
            noteData.description || "",
          reminderTime: "",
        });

      } catch (error) {

        console.error(
          "Reminder create error:",
          error
        );

      } finally {

        setLoading(false);

      }
    };

  return (
    <>

      <div
        className="bg-dark text-light border border-secondary d-flex flex-column"
        style={{
          minWidth: "250px",
          maxWidth: "320px",
          width: "100%",
          borderRadius: "16px",
          padding: "18px",
          minHeight: "220px",
        }}
      >

        <div
          className="flex-grow-1"
          style={{ cursor: "pointer" }}
          onClick={() => setShowModal(true)}
        >

          <div className="d-flex justify-content-between align-items-start mb-3">

            <h5
              className="fw-bold mb-0"
              style={{
                wordBreak: "break-word",
              }}
            >
              {trimTitle(noteData.title)}
            </h5>

            {noteData.pinned && (
              <MdPushPin
                size={22}
                className="text-warning flex-shrink-0"
              />
            )}

          </div>

          <p
            className="text-secondary"
            style={{
              whiteSpace: "pre-wrap",
              wordBreak: "break-word",
            }}
          >
            {trimDescription(noteData.description)}
          </p>

        </div>

        <div className="d-flex justify-content-between align-items-center mt-3">

          <small className="text-muted">
            {noteData.archived
              ? "Archived"
              : "Active"}
          </small>

          <div className="d-flex align-items-center gap-2">

            {noteData.trashed === false && (

              <Button
                variant="outline-warning"
                size="sm"
                onClick={() =>
                  setShowReminderModal(true)
                }
              >
                <MdOutlineAlarm size={18} />
              </Button>

            )}

            {noteData.trashed === false && (

              <Button
                variant="outline-danger"
                size="sm"
                onClick={handleTrash}
              >
                <IoTrashBinOutline size={18} />
              </Button>

            )}

            {noteData.trashed === false && (

              <Button
                variant="outline-info"
                size="sm"
                onClick={
                  handleArchiveToggle
                }
              >
                <MdOutlineArchive size={18} />
              </Button>

            )}

            {noteData.archived === false &&
              noteData.trashed === false && (

              <Button
                variant={
                  noteData.pinned
                    ? "warning"
                    : "outline-warning"
                }
                size="sm"
                onClick={
                  handlePinToggle
                }
              >
                {noteData.pinned ? (
                  <MdPushPin size={18} />
                ) : (
                  <MdOutlinePushPin size={18} />
                )}
              </Button>

            )}

          </div>

        </div>

      </div>

      <Modal
        show={showModal}
        onHide={() =>
          setShowModal(false)
        }
        centered
      >

        <Modal.Header
          closeButton
          className="bg-dark text-light border-secondary"
        >

          <Modal.Title>
            {noteData.title}
          </Modal.Title>

        </Modal.Header>

        <Modal.Body className="bg-dark text-light">

          <p
            style={{
              whiteSpace: "pre-wrap",
            }}
          >
            {noteData.description}
          </p>

        </Modal.Body>

      </Modal>

      <Modal
        show={showReminderModal}
        onHide={() =>
          setShowReminderModal(
            false
          )
        }
        centered
      >

        <Form
          onSubmit={
            handleCreateReminder
          }
        >

          <Modal.Body className="bg-dark text-light">

            <Form.Group className="mb-3">

              <Form.Label>
                Title
              </Form.Label>

              <Form.Control
                type="text"
                required
                value={
                  reminderData.title
                }
                onChange={(e) =>
                  setReminderData({
                    ...reminderData,
                    title:
                      e.target.value,
                  })
                }
              />

            </Form.Group>

            <Form.Group className="mb-3">

              <Form.Label>
                Reminder Time
              </Form.Label>

              <Form.Control
                type="datetime-local"
                required
                value={
                  reminderData.reminderTime
                }
                onChange={(e) =>
                  setReminderData({
                    ...reminderData,
                    reminderTime:
                      e.target.value,
                  })
                }
              />

            </Form.Group>

            <Form.Group>

              <Form.Label>
                Description
              </Form.Label>

              <Form.Control
                as="textarea"
                rows={4}
                value={
                  reminderData.description
                }
                onChange={(e) =>
                  setReminderData({
                    ...reminderData,
                    description:
                      e.target.value,
                  })
                }
              />

            </Form.Group>

          </Modal.Body>

          <Modal.Footer className="bg-dark border-secondary">

            <Button
              variant="secondary"
              onClick={() =>
                setShowReminderModal(
                  false
                )
              }
            >
              Cancel
            </Button>

            <Button
              type="submit"
              variant="primary"
              disabled={loading}
            >
              {loading
                ? "Saving..."
                : "Save Reminder"}
            </Button>

          </Modal.Footer>

        </Form>

      </Modal>
    </>
  );
}

export default NotesCard;