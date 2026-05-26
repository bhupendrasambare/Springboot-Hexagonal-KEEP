import { useEffect, useState } from "react";

import {
  Container,
  Card,
  Button,
  Modal,
  Form,
  Spinner,
  Badge,
} from "react-bootstrap";

import {
  MdAdd,
  MdDelete,
  MdEdit,
  MdCheckCircle,
  MdRadioButtonUnchecked,
} from "react-icons/md";

import {
  getAllReminders,
  createReminder,
  updateReminder,
  deleteReminder,
  markReminderCompleted,
  markReminderIncomplete,
} from "../api/reminderService";

function Reminder({ refresh }) {

  const [reminders, setReminders] = useState([]);
  const [loading, setLoading] = useState(false);

  const [showCreateModal, setShowCreateModal] =
    useState(false);

  const [showEditModal, setShowEditModal] =
    useState(false);

  const [showDeleteModal, setShowDeleteModal] =
    useState(false);

  const [selectedReminder, setSelectedReminder] =
    useState(null);

  const [formData, setFormData] = useState({
    noteId: "",
    title: "",
    description: "",
    reminderTime: "",
  });

  useEffect(() => {
    loadReminders();
  }, [refresh]);

  const loadReminders = async () => {

    try {

      setLoading(true);

      const data = await getAllReminders();

      setReminders(data);

    } catch (error) {

      console.error(
        "Error loading reminders",
        error
      );

      setReminders([]);

    } finally {

      setLoading(false);

    }
  };

  const resetForm = () => {

    setFormData({
      noteId: "",
      title: "",
      description: "",
      reminderTime: "",
    });
  };

  const handleCreate = async (e) => {

    e.preventDefault();

    try {

      await createReminder({
        noteId: formData.noteId,
        title: formData.title,
        description: formData.description,
        reminderTime: formData.reminderTime,
      });

      setShowCreateModal(false);

      resetForm();

      loadReminders();

    } catch (error) {

      console.error(
        "Create reminder error",
        error
      );
    }
  };

  const handleEdit = async (e) => {

    e.preventDefault();

    try {

      await updateReminder({
        reminderId: selectedReminder.id,
        noteId: selectedReminder.noteId,
        title: formData.title,
        description: formData.description,
        reminderTime: formData.reminderTime,
        completed: selectedReminder.completed,
      });

      setShowEditModal(false);

      resetForm();

      setSelectedReminder(null);

      loadReminders();

    } catch (error) {

      console.error(
        "Update reminder error",
        error
      );
    }
  };

  const handleDelete = async () => {

    try {

      await deleteReminder(
        selectedReminder.id
      );

      setShowDeleteModal(false);

      setSelectedReminder(null);

      loadReminders();

    } catch (error) {

      console.error(
        "Delete reminder error",
        error
      );
    }
  };

  const toggleCompleted = async (
    reminder
  ) => {

    try {

      if (reminder.completed) {

        await markReminderIncomplete(
          reminder.id
        );

      } else {

        await markReminderCompleted(
          reminder.id
        );
      }

      loadReminders();

    } catch (error) {

      console.error(
        "Reminder status error",
        error
      );
    }
  };

  const openEditModal = (reminder) => {

    setSelectedReminder(reminder);

    setFormData({
      noteId: reminder.noteId || "",
      title: reminder.title || "",
      description:
        reminder.description || "",
      reminderTime: reminder.reminderTime
        ? reminder.reminderTime.slice(0, 16)
        : "",
    });

    setShowEditModal(true);
  };

  const openDeleteModal = (
    reminder
  ) => {

    setSelectedReminder(reminder);

    setShowDeleteModal(true);
  };

  return (
    <Container
      fluid
      className="notes-wrapper py-4"
    >

      {/* HEADER */}
      <div className="d-flex justify-content-between align-items-center mb-4">

        <h3 className="text-light fw-bold">
          Reminders
        </h3>

        <Button
          variant="primary"
          className="d-flex align-items-center gap-2"
          onClick={() =>
            setShowCreateModal(true)
          }
        >
          <MdAdd size={20} />
          Add Reminder
        </Button>

      </div>

      {/* LOADING */}
      {loading && (
        <div className="empty-fullpage-wrapper">
          <Spinner
            animation="border"
            variant="light"
          />
        </div>
      )}

      {/* EMPTY */}
      {!loading &&
        reminders.length === 0 && (
          <div className="empty-fullpage-wrapper text-center">
            <h4 className="text-secondary">
              No reminders found
            </h4>
          </div>
        )}

      {/* REMINDER LIST */}
      {!loading &&
        reminders.length > 0 && (

          <div
            className="d-flex flex-wrap gap-4"
            style={{
              alignItems: "stretch",
            }}
          >

            {reminders.map((reminder) => (

              <Card
                key={reminder.id}
                className="bg-dark text-light border-secondary"
                style={{
                  minWidth: "250px",
                  maxWidth: "320px",
                  width: "100%",
                  borderRadius: "16px",
                }}
              >

                <Card.Body className="d-flex flex-column">

                  {/* TOP */}
                  <div className="d-flex justify-content-between align-items-start mb-3">

                    <div>

                      <Card.Title className="fw-bold">
                        {reminder.title}
                      </Card.Title>

                      <Badge
                        bg={
                          reminder.completed
                            ? "success"
                            : "warning"
                        }
                      >
                        {reminder.completed
                          ? "Completed"
                          : "Pending"}
                      </Badge>

                    </div>

                    <Button
                      variant="link"
                      className="text-decoration-none p-0"
                      onClick={() =>
                        toggleCompleted(
                          reminder
                        )
                      }
                    >
                      {reminder.completed ? (
                        <MdCheckCircle
                          size={28}
                          color="#4ade80"
                        />
                      ) : (
                        <MdRadioButtonUnchecked
                          size={28}
                          color="#facc15"
                        />
                      )}
                    </Button>

                  </div>

                  {/* DESCRIPTION */}
                  <Card.Text className="text-secondary flex-grow-1">
                    {reminder.description}
                  </Card.Text>

                  {/* REMINDER TIME */}
                  {reminder.reminderTime && (
                    <small className="text-info mb-2">
                      Reminder :
                      {" "}
                      {new Date(
                        reminder.reminderTime
                      ).toLocaleString()}
                    </small>
                  )}

                  {/* DATE */}
                  <small className="text-muted mb-3">
                    Created :
                    {" "}
                    {new Date(
                      reminder.createdAt
                    ).toLocaleString()}
                  </small>

                  {/* ACTIONS */}
                  <div className="d-flex justify-content-end gap-2">

                    <Button
                      variant="outline-info"
                      size="sm"
                      onClick={() =>
                        openEditModal(
                          reminder
                        )
                      }
                    >
                      <MdEdit size={18} />
                    </Button>

                    <Button
                      variant="outline-danger"
                      size="sm"
                      onClick={() =>
                        openDeleteModal(
                          reminder
                        )
                      }
                    >
                      <MdDelete size={18} />
                    </Button>

                  </div>

                </Card.Body>

              </Card>

            ))}

          </div>
        )}

      {/* CREATE MODAL */}
      <Modal
        show={showCreateModal}
        onHide={() =>
          setShowCreateModal(false)
        }
        centered
      >

        <Modal.Header
          closeButton
          className="bg-dark text-light border-secondary"
        >

          <Modal.Title>
            Create Reminder
          </Modal.Title>

        </Modal.Header>

        <Form onSubmit={handleCreate}>

          <Modal.Body className="bg-dark text-light">

            <Form.Group className="mb-3">

              <Form.Label>
                Note Id
              </Form.Label>

              <Form.Control
                type="text"
                required
                value={formData.noteId}
                onChange={(e) =>
                  setFormData({
                    ...formData,
                    noteId:
                      e.target.value,
                  })
                }
              />

            </Form.Group>

            <Form.Group className="mb-3">

              <Form.Label>
                Title
              </Form.Label>

              <Form.Control
                type="text"
                required
                value={formData.title}
                onChange={(e) =>
                  setFormData({
                    ...formData,
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
                value={
                  formData.reminderTime
                }
                onChange={(e) =>
                  setFormData({
                    ...formData,
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
                  formData.description
                }
                onChange={(e) =>
                  setFormData({
                    ...formData,
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
                setShowCreateModal(false)
              }
            >
              Cancel
            </Button>

            <Button
              type="submit"
              variant="primary"
            >
              Save
            </Button>

          </Modal.Footer>

        </Form>

      </Modal>

      {/* EDIT MODAL */}
      <Modal
        show={showEditModal}
        onHide={() =>
          setShowEditModal(false)
        }
        centered
      >

        <Modal.Header
          closeButton
          className="bg-dark text-light border-secondary"
        >

          <Modal.Title>
            Edit Reminder
          </Modal.Title>

        </Modal.Header>

        <Form onSubmit={handleEdit}>

          <Modal.Body className="bg-dark text-light">

            <Form.Group className="mb-3">

              <Form.Label>
                Title
              </Form.Label>

              <Form.Control
                type="text"
                required
                value={formData.title}
                onChange={(e) =>
                  setFormData({
                    ...formData,
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
                value={
                  formData.reminderTime
                }
                onChange={(e) =>
                  setFormData({
                    ...formData,
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
                  formData.description
                }
                onChange={(e) =>
                  setFormData({
                    ...formData,
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
                setShowEditModal(false)
              }
            >
              Cancel
            </Button>

            <Button
              type="submit"
              variant="info"
            >
              Update
            </Button>

          </Modal.Footer>

        </Form>

      </Modal>

      {/* DELETE MODAL */}
      <Modal
        show={showDeleteModal}
        onHide={() =>
          setShowDeleteModal(false)
        }
        centered
      >

        <Modal.Header
          closeButton
          className="bg-dark text-light border-secondary"
        >

          <Modal.Title>
            Delete Reminder
          </Modal.Title>

        </Modal.Header>

        <Modal.Body className="bg-dark text-light">

          Are you sure you want to
          delete this reminder?

        </Modal.Body>

        <Modal.Footer className="bg-dark border-secondary">

          <Button
            variant="secondary"
            onClick={() =>
              setShowDeleteModal(false)
            }
          >
            Cancel
          </Button>

          <Button
            variant="danger"
            onClick={handleDelete}
          >
            Delete
          </Button>

        </Modal.Footer>

      </Modal>

    </Container>
  );
}

export default Reminder;