import { useEffect, useState } from "react";
import Navbar from "react-bootstrap/Navbar";
import { BsGrid3X3Gap, BsViewStacked, BsGrid } from "react-icons/bs";
import { GiHamburgerMenu } from "react-icons/gi";
import { IoMdCloudOutline } from "react-icons/io";
import {
  MdOutlineRefresh,
  MdOutlineSupervisedUserCircle,
  MdOutlineDarkMode,
  MdOutlineLightMode,
} from "react-icons/md";
import { useTheme } from "../store/ThemeContext";

function LocalNav({ sidebarActive, setSidebarActive, showRow, setShowRow }) {
  const { theme, toggleTheme } = useTheme();
  const [refresh, setRefresh] = useState(false);

  useEffect(() => {
    if (!refresh) return;

    const timerId = setTimeout(() => {
      setRefresh(false);
    }, 1000);

    return () => clearTimeout(timerId);
  }, [refresh]);

  return (
    <Navbar className="navbar-theme">
      <div className="mx-1p d-flex w-100 align-items-center">
        <Navbar.Brand className="d-flex align-items-center">
          <GiHamburgerMenu
            onClick={() => setSidebarActive(!sidebarActive)}
            size={25}
            className="me-3"
            style={{ cursor: "pointer" }}
          />
          Keep
        </Navbar.Brand>

        <form className="form-control w-50 p-0">
          <input type="text" className="form-control" />
        </form>

        <Navbar.Collapse className="justify-content-end gap-4 align-items-center">

          {refresh ? (
            <IoMdCloudOutline size={22} />
          ) : (
            <MdOutlineRefresh
              onClick={() => setRefresh(true)}
              size={22}
              style={{ cursor: "pointer" }}
            />
          )}

          {showRow ? (
            <BsGrid
              onClick={() => setShowRow(false)}
              size={22}
              style={{ cursor: "pointer" }}
            />
          ) : (
            <BsViewStacked
              onClick={() => setShowRow(true)}
              size={22}
              style={{ cursor: "pointer" }}
            />
          )}

          {/* Theme Toggle */}
          {theme === "light" ? (
            <MdOutlineDarkMode
              size={22}
              onClick={toggleTheme}
              style={{ cursor: "pointer" }}
            />
          ) : (
            <MdOutlineLightMode
              size={22}
              onClick={toggleTheme}
              style={{ cursor: "pointer" }}
            />
          )}

          <BsGrid3X3Gap size={22} />
          <MdOutlineSupervisedUserCircle size={22} />
        </Navbar.Collapse>
      </div>
    </Navbar>
  );
}

export default LocalNav;