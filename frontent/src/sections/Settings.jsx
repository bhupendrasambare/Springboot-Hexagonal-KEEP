import Col from "react-bootstrap/Col";
import Nav from "react-bootstrap/Nav";
import Row from "react-bootstrap/Row";
import Tab from "react-bootstrap/Tab";
import Card from "react-bootstrap/Card";
import { useAuth } from "../store/AuthContext";
import { useState } from "react";

function Settings({ refresh }) {

  const { user, logout } = useAuth();
  const[newPassword,setNewPassword] = useState("")
  const[oldPassword,setOldPassword] = useState("")


  const handleChangePassword =
    async () => {

      try {

        if (noteData.pinned) {

          await handleChangePassword(newPassword, oldPassword);

        }

      } catch (error) {
      }
    };


  return (
    <>
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
                  {/* DETAILS */}
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
                        <div className="mb-3">
                          <div className="settings-label">
                            Username
                          </div>

                          <div className="settings-value">
                            {user.userName}
                          </div>
                        </div>
                        <div className="mb-3">
                          <div className="settings-label">
                            Full Name
                          </div>

                          <div className="settings-value">
                            {user.firstName} {user.lastName}
                          </div>
                        </div>
                        <div className="mb-3">
                          <div className="settings-label">
                            Email
                          </div>

                          <div className="settings-value">
                            {user.email}
                          </div>
                        </div>
                      </Card.Body>
                    </Card>
                  </Tab.Pane>

                  {/* PASSWORD */}
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
                        <form>
                          <div className="mb-3">
                            <label className="form-label">
                              Current Password
                            </label>

                            <input
                              type="password"
                              className="form-control custom-dark-input"
                              placeholder="Enter current password"
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
                            />
                          </div>

                          <div className="mb-4">
                            <label className="form-label">
                              Confirm Password
                            </label>

                            <input
                              type="password"
                              className="form-control custom-dark-input"
                              placeholder="Confirm new password"
                            />
                          </div>

                          <button
                            type="submit"
                            className="btn btn-primary"
                          >
                            Update Password
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
    </>
  );
}

export default Settings;