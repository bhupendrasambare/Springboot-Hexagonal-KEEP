import {
  BsArchive,
  BsBell,
  BsLightbulb,
  BsPencil,
  BsTrash3,
} from "react-icons/bs";
import { MdLabelImportantOutline } from "react-icons/md";
import { ActivePage } from "./Constants";

function Sidebar({ activePage, setActivePage, sidebarActive }) {
  const getClass = (page) =>
    "my-2 d-flex align-items-center sidebar-item" +
    (activePage === page ? " active-sidebar" : "");

  return (
    <div
      className={`sidebar-theme d-flex flex-column justify-content-between vh-100-50 max-250 p-3 pb-0 ps-0 ${
        sidebarActive ? "d-block" : "d-none d-md-flex"
      }`}
    >
      <div className="d-flex flex-column">
        <div
          className={getClass(ActivePage.NOTES)}
          onClick={() => setActivePage(ActivePage.NOTES)}
        >
          <BsLightbulb className="sidebar-circle-icon" />
          <div className="ms-2">Notes</div>
        </div>

        <div
          className={getClass(ActivePage.REMINDER)}
          onClick={() => setActivePage(ActivePage.REMINDER)}
        >
          <BsBell className="sidebar-circle-icon" />
          <div className="ms-2">Reminder</div>
        </div>

        <div
          className={getClass(ActivePage.IMPORTANT)}
          onClick={() => setActivePage(ActivePage.IMPORTANT)}
        >
          <MdLabelImportantOutline className="sidebar-circle-icon" />
          <div className="ms-2">Important</div>
        </div>

        <div
          className={getClass(ActivePage.EDITLABELS)}
          onClick={() => setActivePage(ActivePage.EDITLABELS)}
        >
          <BsPencil className="sidebar-circle-icon" />
          <div className="ms-2">Edit labels</div>
        </div>

        <div
          className={getClass(ActivePage.ARCHIVE)}
          onClick={() => setActivePage(ActivePage.ARCHIVE)}
        >
          <BsArchive className="sidebar-circle-icon" />
          <div className="ms-2">Archive</div>
        </div>

        <div
          className={getClass(ActivePage.BIN)}
          onClick={() => setActivePage(ActivePage.BIN)}
        >
          <BsTrash3 className="sidebar-circle-icon" />
          <div className="ms-2">Bin</div>
        </div>
      </div>

      <div className="fw-bold pb-3 px-2">
        Open source licences
      </div>
    </div>
  );
}

export default Sidebar;