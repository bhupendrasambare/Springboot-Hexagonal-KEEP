import { useState } from 'react'
import LocalNav from '../components/LocalNav'
import Sidebar from '../components/Sidebar'
import { ActivePage } from '../components/Constants'

function HomePage() {
    const[sidebarActive, setSidebarActive] = useState(false)
    const[showRow, setShowRow] = useState(false)
    const[activePage, setActivePage] = useState(ActivePage.NOTES)

  return (
    <>
    <LocalNav
     sidebarActive={sidebarActive}
     setSidebarActive={setSidebarActive}
     showRow={showRow}
     setShowRow={setShowRow} />
     <Sidebar activePage={activePage} setActivePage={setActivePage}/>
    {sidebarActive? "Active":"in_actiove"}
    </>
  )
}

export default HomePage