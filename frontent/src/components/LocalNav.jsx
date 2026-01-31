import { useEffect, useState } from 'react';
import { Fade } from 'react-bootstrap';
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import { BsGrid3X3Gap, BsViewStacked, BsGrid } from "react-icons/bs";
import { GiHamburgerMenu } from 'react-icons/gi';
import { IoMdCloudOutline } from 'react-icons/io';
import { MdOutlineRefresh, MdOutlineSettings, MdOutlineSupervisedUserCircle } from 'react-icons/md';

function LocalNav({sidebarActive, setSidebarActive,showRow, setShowRow}) {

  const [refresh, setRefresh] = useState(false);

  useEffect(()=>{
    const timerId = setTimeout(() => {
      setRefresh(false)
    }, 1000)
  }, [refresh])

  return (
    <Navbar className="bg-body-tertiary">
      <div className='mx-1p d-flex w-100'>
        <Navbar.Brand href="#home">
          <GiHamburgerMenu onClick={() =>setSidebarActive(!sidebarActive)} size={25} className='me-4'/>
          Keep
        </Navbar.Brand>
        <form action="" className="form-control w-50 p-0">
          <input type="text" className="form-control" />
        </form>
        <Navbar.Toggle />
        <Navbar.Collapse className="justify-content-end gap-4">
          {
            (refresh != true)? 
            <MdOutlineRefresh onClick={()=> setRefresh(true)} size={25} />:
            <IoMdCloudOutline size={25} />
          }
          
          
          {
            (showRow != true)? 
            <BsViewStacked onClick={()=> setShowRow(true)} size={25} />:
            <BsGrid onClick={()=> setShowRow(false)} size={25} />
          }
          <MdOutlineSettings size={25} />
          <BsGrid3X3Gap size={25} />
          <MdOutlineSupervisedUserCircle size={25} />
        </Navbar.Collapse>
      </div>
    </Navbar>
  );
}

export default LocalNav;