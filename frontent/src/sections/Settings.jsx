import Col from "react-bootstrap/Col";
import Nav from "react-bootstrap/Nav";
import Row from "react-bootstrap/Row";
import Tab from "react-bootstrap/Tab";
import Card from "react-bootstrap/Card";
import { useAuth } from "../store/AuthContext";
import { useState } from "react";
import { changePassword } from "../api/notesService";

function Settings({ refresh }) {
  const { user } = useAuth();

  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const validatePassword = (password) => {
    return /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#^()_+\-=[\]{};':"\\|,.<>/?]).{8,}$/.test(
      password
    );
  };

  const handleChangePassword = async (e) => {
    e.preventDefault();

    setError("");
    setSuccess("");

    if (!oldPassword.trim()) {
      setError("Current password is required.");
      return;
    }

    if (!validatePassword(newPassword)) {
      setError(
        "New password must contain at least 8 characters, one uppercase letter, one lowercase letter, one number and one special character."
      );
      return;
    }

    if (newPassword !== confirmPassword) {
      setError("New password and Confirm password do not match.");
      return;
    }

    try {
      setLoading(true);

      await changePassword(oldPassword, newPassword);

      setSuccess("Password updated successfully.");

      setOldPassword("");
      setNewPassword("");
      setConfirmPassword("");
    } catch (err) {
      setError(
        err.response?.data?.message ||
          "Unable to update password."
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="settings-wrapper container-fluid px-4">
      <Tab.Container
        id="settings-tabs"
        defaultActiveKey="details"
      >
        <Row className="g-4">
          {/* Sidebar */}
          <Col lg={3}>
            <div className="settings-sidebar">
              <Nav
                variant="pills"
                className="flex-column"
              >
                <Nav.Item>
                  <Nav.Link eventKey="details">
                    Profile Details
                  </Nav.Link>
                </Nav.Item>

                <Nav.Item>
                  <Nav.Link eventKey="password">
                    Reset Password
                  </Nav.Link>
                </Nav.Item>
              </Nav>
            </div>
          </Col>

          {/* Content */}
          <Col lg={9}>
            <div className="settings-content">
              <Tab.Content>
                {/* ================= PROFILE ================= */}
                <Tab.Pane eventKey="details">
                  <h3 className="settings-title">
                    Profile Details
                  </h3>

                  <Card
                    bg="dark"
                    border="secondary"
                    text="light"
                  >
                    <Card.Body>
                      <div className="mb-4">
                        <div className="settings-label">
                          Username
                        </div>

                        <div className="settings-value">
                          {user?.userName}
                        </div>
                      </div>

                      <div className="mb-4">
                        <div className="settings-label">
                          Full Name
                        </div>

                        <div className="settings-value">
                          {user?.firstName}{" "}
                          {user?.lastName}
                        </div>
                      </div>

                      <div className="mb-4">
                        <div className="settings-label">
                          Email
                        </div>

                        <div className="settings-value">
                          {user?.email}
                        </div>
                      </div>
                    </Card.Body>
                  </Card>
                </Tab.Pane>

                {/* ================= PASSWORD ================= */}
                <Tab.Pane eventKey="password">
                  <h3 className="settings-title">
                    Reset Password
                  </h3>

                  <Card
                    bg="dark"
                    border="secondary"
                    text="light"
                  >
                    <Card.Body>
                      <form
                        onSubmit={
                          handleChangePassword
                        }
                      >
                        <div className="mb-3">
                          <label className="form-label">
                            Current Password
                          </label>

                          <input
                            type="password"
                            className="form-control custom-dark-input"
                            placeholder="Enter current password"
                            value={oldPassword}
                            onChange={(e) =>
                              setOldPassword(
                                e.target.value
                              )
                            }
                          />
                        </div>

                        <div className="mb-3">
                          <label className="form-label">
                            New Password
                          </label>

                          <input
                            type="password"
                            className="form-control custom-dark-input"
                            placeholder="Enter new password"
                            value={newPassword}
                            onChange={(e) =>
                              setNewPassword(
                                e.target.value
                              )
                            }
                          />

                          <small className="text-secondary">
                            Password must be at
                            least 8 characters
                            long and contain one
                            uppercase letter, one
                            lowercase letter, one
                            number and one special
                            character.
                          </small>
                        </div>

                        <div className="mb-3">
                          <label className="form-label">
                            Confirm Password
                          </label>

                          <input
                            type="password"
                            className="form-control custom-dark-input"
                            placeholder="Confirm new password"
                            value={
                              confirmPassword
                            }
                            onChange={(e) =>
                              setConfirmPassword(
                                e.target.value
                              )
                            }
                          />
                        </div>

                        {error && (
                          <div className="alert alert-danger py-2">
                            {error}
                          </div>
                        )}

                        {success && (
                          <div className="alert alert-success py-2">
                            {success}
                          </div>
                        )}

                        <button
                          type="submit"
                          className="btn btn-primary"
                          disabled={loading}
                        >
                          {loading
                            ? "Updating..."
                            : "Update Password"}
                        </button>
                      </form>
                    </Card.Body>
                  </Card>
                </Tab.Pane>
              </Tab.Content>
            </div>
          </Col>
        </Row>
      </Tab.Container>
    </div>
  );
}

export default Settings;