import { useState } from 'react'
import LocalNav from '../components/LocalNav'

function HomePage() {
    const[sidebarActive, setSidebarActive] = useState(false)
    const[showRow, setShowRow] = useState(false)

  return (
    <>
    <LocalNav
     sidebarActive={sidebarActive}
     setSidebarActive={setSidebarActive}
     showRow={showRow}
     setShowRow={setShowRow} />
    {sidebarActive? "Active":"in_actiove"}
    </>
  )
}

export default HomePage