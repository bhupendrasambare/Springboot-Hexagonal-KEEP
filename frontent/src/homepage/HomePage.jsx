import { useState } from 'react'
import LocalNav from '../components/LocalNav'

function HomePage() {
    const[sidebarActive, setSidebarActive] = useState(false)

  return (
    <>
    <LocalNav sidebarActive={sidebarActive} setSidebarActive={setSidebarActive} />
    
    </>
  )
}

export default HomePage