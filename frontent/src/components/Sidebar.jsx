import React from 'react'
import { BsArchive, BsBell, BsLightbulb, BsPencil, BsTrash3 } from 'react-icons/bs'
import { MdLabelImportantOutline } from 'react-icons/md'
import { ActivePage } from './Constants'

function Sidebar({activePage, setActivePage, sidebarActive}) {
  return (
    <div>
        <div className="d-flex flex-column justify-content-between vh-100-50 max-250 p-3 pb-0">
            <div className="d-flex flex-column">
                <div 
                className="my-2 d-flex justify-conent-around"
                onClick={()=>setActivePage(ActivePage.NOTES)}>
                    <BsLightbulb size={40} className={("ms-1 sidebar-circle-icon fw-bold" + ((activePage == ActivePage.NOTES)?" rounded-circle bg-warning-local":""))} />
                    <div className={("ms-1 sidebar-circle-icon fw-bold w-100 " + ((activePage == ActivePage.NOTES)?" rounded-pill p-2 bg-warning-local":""))}>Notes</div>
                </div>
                <div className="my-2 d-flex justify-conent-around"
                onClick={()=>setActivePage(ActivePage.REMINDER)}>
                    <BsBell size={20} className={("ms-1 sidebar-circle-icon fw-bold" + ((activePage == ActivePage.REMINDER)?" rounded-pill p-2 bg-warning-local":""))} />
                    <div className={("ms-1 sidebar-circle-icon fw-bold w-100 " + ((activePage == ActivePage.REMINDER)?" rounded-pill p-2 bg-warning-local":""))}>Reminder</div>
                </div>
                <div className="my-2 d-flex justify-conent-around"
                onClick={()=>setActivePage(ActivePage.IMPORTANT)}>
                    <MdLabelImportantOutline size={20} className={("ms-1 sidebar-circle-icon fw-bold" + ((activePage == ActivePage.IMPORTANT)?" rounded-pill p-2 bg-warning-local":""))} />
                    <div className={("ms-1 sidebar-circle-icon fw-bold w-100 " + ((activePage == ActivePage.IMPORTANT)?" rounded-pill p-2 bg-warning-local":""))}>Important</div>
                </div>
                <div className="my-2 d-flex justify-conent-around"
                onClick={()=>setActivePage(ActivePage.EDITLABELS)}>
                    <BsPencil size={20} className={("ms-1 sidebar-circle-icon fw-bold" + ((activePage == ActivePage.EDITLABELS)?" rounded-pill p-2 bg-warning-local":""))} />
                    <div className={("ms-1 sidebar-circle-icon fw-bold w-100 " + ((activePage == ActivePage.EDITLABELS)?" rounded-pill p-2 bg-warning-local":""))}>Edit labels</div>
                </div>
                <div className="my-2 d-flex justify-conent-around"
                onClick={()=>setActivePage(ActivePage.ARCHIVE)}>
                    <BsArchive size={20} className={("ms-1 sidebar-circle-icon fw-bold" + ((activePage == ActivePage.ARCHIVE)?" rounded-pill p-2 bg-warning-local":""))} />
                    <div className={("ms-1 sidebar-circle-icon fw-bold w-100 " + ((activePage == ActivePage.ARCHIVE)?" rounded-pill p-2 bg-warning-local":""))}>Archive</div>
                </div>
                <div className="my-2 d-flex justify-conent-around"
                onClick={()=>setActivePage(ActivePage.BIN)}>
                    <BsTrash3 size={20} className={("ms-1 sidebar-circle-icon fw-bold" + ((activePage == ActivePage.BIN)?" rounded-pill p-2 bg-warning-local":""))} />
                    <div className={("ms-1 sidebar-circle-icon fw-bold w-100 " + ((activePage == ActivePage.BIN)?" rounded-pill p-2 bg-warning-local":""))}>Bin</div>
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