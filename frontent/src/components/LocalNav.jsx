import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import { BsGrid3X3Gap } from 'react-icons/bs';
import { CiGrid2H } from 'react-icons/ci';
import { GiHamburgerMenu } from 'react-icons/gi';
import { MdOutlineRefresh, MdOutlineSettings, MdOutlineSupervisedUserCircle } from 'react-icons/md';

function LocalNav() {
  return (
    <Navbar className="bg-body-tertiary">
      <Container>
        <Navbar.Brand href="#home">
          <GiHamburgerMenu size={25} className='me-4'/>
          Keep
        </Navbar.Brand>
        <form action="" className="form-control w-50 p-0">
          <input type="text" className="form-control" />
        </form>
        <Navbar.Toggle />
        <Navbar.Collapse className="justify-content-end gap-4">
          <MdOutlineRefresh size={25} />
          <CiGrid2H size={25} />
          <MdOutlineSettings size={25} />
          <BsGrid3X3Gap size={25} />
          <MdOutlineSupervisedUserCircle size={25} />
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default LocalNav;