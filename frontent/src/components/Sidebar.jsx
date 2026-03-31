import { NavLink } from "react-router-dom";
import { BsArchive, BsBell, BsLightbulb, BsPencil, BsTrash3 } from "react-icons/bs";
import { MdLabelImportantOutline } from "react-icons/md";

function Sidebar({ sidebarActive }) {

  const getClass = ({ isActive }) =>
    "fs-5 my-2 d-flex align-items-center cursor-pointer px-3 py-2 text-decoration-none fw-bold " +
    (isActive ? "rounded-pill rounded-start bg-warning-local text-white" : "text-white");

  return (
    <div
      className={
        "sidebar-wrapper d-flex flex-column justify-content-between vh-100-50 p-3 pb-0 ps-0 " +
        (sidebarActive ? "sidebar-open" : "sidebar-close")
      }
    >
      <div className="d-flex flex-column">

        {/* Notes */}
        <NavLink to="/home" end className={getClass}>
          <BsLightbulb className="ms-1 sidebar-circle-icon text-white" />
          {sidebarActive && <div className="ms-3 w-100">Notes</div>}
        </NavLink>

        {/* Reminder */}
        <NavLink to="/home/reminder" className={getClass}>
          <BsBell className="ms-1 sidebar-circle-icon text-white" />
          {sidebarActive && <div className="ms-3 w-100">Reminder</div>}
        </NavLink>

        {/* Important */}
        <NavLink to="/home/important" className={getClass}>
          <MdLabelImportantOutline className="ms-1 sidebar-circle-icon text-white" />
          {sidebarActive && <div className="ms-3 w-100">Important</div>}
        </NavLink>

        {/* Edit Labels */}
        <NavLink to="/home/edit-labels" className={getClass}>
          <BsPencil className="ms-1 sidebar-circle-icon text-white" />
          {sidebarActive && <div className="ms-3 w-100">Edit labels</div>}
        </NavLink>

        {/* Archive */}
        <NavLink to="/home/archive" className={getClass}>
          <BsArchive className="ms-1 sidebar-circle-icon text-white" />
          {sidebarActive && <div className="ms-3 w-100">Archive</div>}
        </NavLink>

        {/* Bin */}
        <NavLink to="/home/bin" className={getClass}>
          <BsTrash3 className="ms-1 sidebar-circle-icon text-white" />
          {sidebarActive && <div className="ms-3 w-100">Bin</div>}
        </NavLink>

      </div>

      {sidebarActive && (
        <div className="fw-bold pb-3 px-2 text-white">
          Open source licences
        </div>
      )}
    </div>
  );
}

export default Sidebar;