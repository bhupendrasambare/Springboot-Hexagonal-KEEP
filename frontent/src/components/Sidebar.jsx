import { BsArchive, BsBell, BsLightbulb, BsPencil, BsTrash3 } from 'react-icons/bs'
import { MdLabelImportantOutline } from 'react-icons/md'
import { ActivePage } from './Constants'

function Sidebar({activePage, setActivePage, sidebarActive}) {
  return (
    <div>
        <div className="d-flex flex-column justify-content-between vh-100-50 max-250 p-3 pb-0 ps-0 text-light">
            <div className="d-flex flex-column">
                <div 
                className={("my-2 d-flex justify-conent-around" + ((activePage == ActivePage.NOTES)?" rounded-pill rounded-start bg-warning-local":""))}
                onClick={()=>setActivePage(ActivePage.NOTES)}>
                    <BsLightbulb className={("ms-1 sidebar-circle-icon fw-bold")} />
                    <div className={("ms-1 sidebar-circle-icon fw-bold w-100 ")}>Notes</div>
                </div>
                <div className={("my-2 d-flex justify-conent-around" + ((activePage == ActivePage.REMINDER)?" rounded-pill rounded-start bg-warning-local":""))}
                onClick={()=>setActivePage(ActivePage.REMINDER)}>
                    <BsBell  className={("ms-1 sidebar-circle-icon fw-bold")} />
                    <div className={("ms-1 sidebar-circle-icon fw-bold w-100 ")}>Reminder</div>
                </div>
                <div className={("my-2 d-flex justify-conent-around" + ((activePage == ActivePage.IMPORTANT)?" rounded-pill rounded-start bg-warning-local":""))}
                onClick={()=>setActivePage(ActivePage.IMPORTANT)}>
                    <MdLabelImportantOutline  className={("ms-1 sidebar-circle-icon fw-bold")} />
                    <div className={("ms-1 sidebar-circle-icon fw-bold w-100 ")}>Important</div>
                </div>
                <div className={("my-2 d-flex justify-conent-around" + ((activePage == ActivePage.EDITLABELS)?" rounded-pill rounded-start bg-warning-local":""))}
                onClick={()=>setActivePage(ActivePage.EDITLABELS)}>
                    <BsPencil  className={("ms-1 sidebar-circle-icon fw-bold")} />
                    <div className={("ms-1 sidebar-circle-icon fw-bold w-100 ")}>Edit labels</div>
                </div>
                <div className={("my-2 d-flex justify-conent-around" + ((activePage == ActivePage.ARCHIVE)?" rounded-pill rounded-start bg-warning-local":""))}
                onClick={()=>setActivePage(ActivePage.ARCHIVE)}>
                    <BsArchive  className={("ms-1 sidebar-circle-icon fw-bold")} />
                    <div className={("ms-1 sidebar-circle-icon fw-bold w-100 ")}>Archive</div>
                </div>
                <div className={("my-2 d-flex justify-conent-around" + ((activePage == ActivePage.BIN)?" rounded-pill rounded-start bg-warning-local":""))}
                onClick={()=>setActivePage(ActivePage.BIN)}>
                    <BsTrash3  className={("ms-1 sidebar-circle-icon fw-bold")} />
                    <div className={("ms-1 sidebar-circle-icon fw-bold w-100 ")}>Bin</div>
                </div>
            </div>
            <div className="fw-bold pb-3 px-2">
                Open source licences
            </div>
        </div>
    </div>
  )
}

export default Sidebar