import Col from "react-bootstrap/Col";
import Nav from "react-bootstrap/Nav";
import Row from "react-bootstrap/Row";
import Tab from "react-bootstrap/Tab";
import Card from "react-bootstrap/Card";
import { useAuth } from "../store/AuthContext";
import { useState } from "react";
import { changePassword } from "../api/notesService";

function Settings() {
  const { user } = useAuth();

  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [loading, setLoading] = useState(false);

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const [passwordError, setPasswordError] =
    useState("");

  const [confirmError, setConfirmError] =
    useState("");

  const validatePassword = (password) => {
    return /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&#^()_+\-=[\]{};':"\\|,.<>/?]).{8,}$/.test(
      password
    );
  };

  const handleNewPasswordChange = (value) => {
    setNewPassword(value);

    if (!value) {
      setPasswordError("");
    } else if (!validatePassword(value)) {
      setPasswordError(
        "Password must contain at least 8 characters, one uppercase letter, one lowercase letter, one number and one special character."
      );
    } else {
      setPasswordError("");
    }

    if (confirmPassword) {
      if (value !== confirmPassword) {
        setConfirmError(
          "Passwords do not match."
        );
      } else {
        setConfirmError("");
      }
    }
  };

  const handleConfirmPasswordChange = (
    value
  ) => {
    setConfirmPassword(value);

    if (!value) {
      setConfirmError("");
      return;
    }

    if (value !== newPassword) {
      setConfirmError(
        "Passwords do not match."
      );
    } else {
      setConfirmError("");
    }
  };

  const handleChangePassword = async (e) => {
    e.preventDefault();

    setError("");
    setSuccess("");

    if (!oldPassword.trim()) {
      setError(
        "Current password is required."
      );
      return;
    }

    if (!validatePassword(newPassword)) {
      setError(
        "Please enter a valid new password."
      );
      return;
    }

    if (newPassword !== confirmPassword) {
      setError(
        "Passwords do not match."
      );
      return;
    }

    try {
      setLoading(true);

      await changePassword(
        oldPassword,
        newPassword
      );

      setSuccess(
        "Password updated successfully."
      );

      setOldPassword("");
      setNewPassword("");
      setConfirmPassword("");

      setPasswordError("");
      setConfirmError("");
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

          <Col lg={9}>
            <div className="settings-content">
              <Tab.Content>
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
                        noValidate
                      >
                        <div className="mb-3">
                          <label className="form-label">
                            Current Password
                          </label>

                          <input
                            type="password"
                            className="form-control custom-dark-input"
                            value={oldPassword}
                            placeholder="Enter current password"
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
                            value={newPassword}
                            placeholder="Enter new password"
                            onChange={(e) =>
                              handleNewPasswordChange(
                                e.target.value
                              )
                            }
                          />

                          {passwordError ? (
                            <small className="text-danger">
                              {
                                passwordError
                              }
                            </small>
                          ) : newPassword ? (
                            <small className="text-success">
                              ✓ Strong
                              password
                            </small>
                          ) : (
                            <small className="text-secondary">
                              Minimum 8
                              characters,
                              uppercase,
                              lowercase,
                              number &
                              special
                              character.
                            </small>
                          )}
                        </div>

                        <div className="mb-3">
                          <label className="form-label">
                            Confirm
                            Password
                          </label>

                          <input
                            type="password"
                            className="form-control custom-dark-input"
                            value={
                              confirmPassword
                            }
                            placeholder="Confirm new password"
                            onChange={(e) =>
                              handleConfirmPasswordChange(
                                e.target.value
                              )
                            }
                          />

                          {confirmError ? (
                            <small className="text-danger">
                              {
                                confirmError
                              }
                            </small>
                          ) : confirmPassword ? (
                            <small className="text-success">
                              ✓ Passwords
                              match
                            </small>
                          ) : null}
                        </div>

                        {error && (
                          <div className="alert alert-danger">
                            {error}
                          </div>
                        )}

                        {success && (
                          <div className="alert alert-success">
                            {success}
                          </div>
                        )}

                        <button
                          type="submit"
                          className="btn btn-primary"
                          disabled={
                            loading ||
                            passwordError ||
                            confirmError
                          }
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