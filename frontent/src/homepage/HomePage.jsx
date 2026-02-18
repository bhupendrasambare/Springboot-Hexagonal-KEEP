import { useState } from 'react'
import LocalNav from '../components/LocalNav'
import Sidebar from '../components/Sidebar'
import { ActivePage } from '../components/Constants'
import Notes from '../sections/Notes'
import Reminder from '../sections/Reminder'
import Important from '../sections/Important'
import EditLabels from '../sections/EditLabels'
import Archive from '../sections/Archive'
import Bin from '../sections/Bin'

function HomePage() {
  const [sidebarActive, setSidebarActive] = useState(false)
  const [showRow, setShowRow] = useState(false)
  const [activePage, setActivePage] = useState(ActivePage.NOTES)

  return (
    <div className='bg-dark'>
      <LocalNav
        sidebarActive={sidebarActive}
        setSidebarActive={setSidebarActive}
        showRow={showRow}
        setShowRow={setShowRow}
      />

      <div className="d-flex w-100">
        <Sidebar
          activePage={activePage}
          setActivePage={setActivePage}
          sidebarActive={sidebarActive}
        />

        {
          (activePage === ActivePage.NOTES) ? <Notes /> :
          (activePage === ActivePage.REMINDER) ? <Reminder /> :
          (activePage === ActivePage.IMPORTANT) ? <Important /> :
          (activePage === ActivePage.EDITLABELS) ? <EditLabels /> :
          (activePage === ActivePage.ARCHIVE) ? <Archive /> :
          (activePage === ActivePage.BIN) ? <Bin /> :
          <></>
        }
      </div>
    </div>
  )
}

export default HomePage