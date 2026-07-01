import Modal from "react-bootstrap/Modal";
import { useEffect, useState } from "react";
import Navbar from "react-bootstrap/Navbar";
import { BsGrid3X3Gap, BsViewStacked, BsGrid } from "react-icons/bs";
import { GiHamburgerMenu } from "react-icons/gi";
import { IoMdCloudOutline } from "react-icons/io";
import { useAuth } from "../store/AuthContext";
import {
  MdOutlineRefresh,
  MdOutlineSupervisedUserCircle,
  MdOutlineSettings,
  MdOutlineSearch,
} from "react-icons/md";
import { useNavigate } from "react-router-dom";
import { Button } from "react-bootstrap";

function LocalNav({
  sidebarActive,
  setSidebarActive,
  showRow,
  setShowRow,
  refreshNotes,
}) {
  const [refresh, setRefresh] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [searchString, setSearchString] = useState("");

  const { user, logout } = useAuth();
  const navigate = useNavigate();

  useEffect(() => {
    refreshNotes();
    const timerId = setTimeout(() => {
      setRefresh(false);
    }, 1000);

    return () => clearTimeout(timerId);
  }, [refresh]);

  const handleLogout = () => {
    logout();
    setShowModal(false);
    navigate("/");
  };

  return (
    <>
      <Navbar
        className="bg-body-tertiary bg-black"
        fixed="top"
        data-bs-theme="dark"
      >
        <div className="mx-1p d-flex w-100">
          <Navbar.Brand href="#home">
            <GiHamburgerMenu
              onClick={() => setSidebarActive(!sidebarActive)}
              size={25}
              className="me-4"
            />
            Keep
          </Navbar.Brand>

          <Navbar.Toggle />
          <Navbar.Collapse className="justify-content-end gap-4 text-body-emphasis">
            {refresh !== true ? (
              <MdOutlineRefresh
                onClick={() => setRefresh(true)}
                size={25}
              />
            ) : (
              <IoMdCloudOutline size={25} />
            )}

            <MdOutlineSettings size={25} className="cursor-pointer" onClick={()=>{navigate("/home/settings")}} />
            <MdOutlineSupervisedUserCircle
              size={25}
              onClick={() => setShowModal(true)}
            />
          </Navbar.Collapse>
        </div>
      </Navbar>

      <Modal show={showModal} onHide={() => setShowModal(false)} centered>

        <Modal.Body className="bg-dark text-light">
          {user ? (
            <>
              <p><strong>Username:</strong> {user.userName}</p>
              <p><strong>First Name:</strong> {user.firstName}</p>
              <p><strong>Last Name:</strong> {user.lastName}</p>
              <p><strong>Email:</strong> {user.email}</p>
            </>
          ) : (
            <p>No user data found.</p>
          )}
        </Modal.Body>

        <Modal.Footer className="bg-dark text-light b-0">
          <Button variant="secondary" onClick={() => setShowModal(false)}>
            Close
          </Button>
          <Button variant="danger" onClick={handleLogout}>
            Logout
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default LocalNav;