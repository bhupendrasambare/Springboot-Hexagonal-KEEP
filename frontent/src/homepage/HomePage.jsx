import { useState } from "react";
import { Route, Routes, Navigate } from "react-router-dom";

import LocalNav from "../components/LocalNav";
import Sidebar from "../components/Sidebar";

import Notes from "../sections/Notes";
import Reminder from "../sections/Reminder";
import Important from "../sections/Important";
import EditLabels from "../sections/EditLabels";
import Archived from "../sections/Archive";
import Bin from "../sections/Bin";

function HomePage() {
  const [sidebarActive, setSidebarActive] = useState(false);
  const [showRow, setShowRow] = useState(false);

  return (
    <div className="bg-dark">
      <LocalNav
        sidebarActive={sidebarActive}
        setSidebarActive={setSidebarActive}
        showRow={showRow}
        setShowRow={setShowRow}
      />

      <div className="d-flex w-100">
        <Sidebar sidebarActive={sidebarActive} />

        <div className="ms-6rem flex-grow-1">
          <Routes>
            <Route path="/" element={<Notes />} />
            <Route path="/reminder" element={<Reminder />} />
            <Route path="/important" element={<Important />} />
            <Route path="/edit-labels" element={<EditLabels />} />
            <Route path="/archive" element={<Archived />} />
            <Route path="/bin" element={<Bin />} />

            {/* default */}
            <Route path="*" element={<Navigate to="/home" />} />
          </Routes>
        </div>
      </div>
    </div>
  );
}

export default HomePage;