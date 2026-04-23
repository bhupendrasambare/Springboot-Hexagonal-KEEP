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
import SearchResult from "../sections/SearchResult";

function HomePage() {
  const [sidebarActive, setSidebarActive] = useState(false);
  const [showRow, setShowRow] = useState(false);
  const [refresh, setRefresh] = useState(false);
  const [searchString, setSearchString] = useState("");
  const refreshNotes = () =>{
    setRefresh(!refresh);
  }

  return (
    <div className="bg-dark">
      <LocalNav
        sidebarActive={sidebarActive}
        setSidebarActive={setSidebarActive}
        showRow={showRow}
        setShowRow={setShowRow}
        refreshNotes ={refreshNotes}
      />

      <div className="d-flex w-100">
        <Sidebar sidebarActive={sidebarActive} />

        <div className="ms-6rem flex-grow-1">
          <Routes>
            <Route path="/" element={<Notes refresh={refresh} />} />
            <Route path="/reminder" element={<Reminder refresh={refresh} />} />
            <Route path="/important" element={<Important refresh={refresh} />} />
            <Route path="/edit-labels" element={<EditLabels refresh={refresh} />} />
            <Route path="/archive" element={<Archived refresh={refresh} />} />
            <Route path="/bin" element={<Bin refresh={refresh} />} />
            <Route path="/search" element={<SearchResult refresh={refresh} searchString={searchString} />} />

            {/* default */}
            <Route path="*" element={<Navigate to="/home" />} />
          </Routes>
        </div>
      </div>
    </div>
  );
}

export default HomePage;