import React from 'react'
import { BsArchive, BsBell, BsLightbulb, BsPencil, BsTrash3 } from 'react-icons/bs'
import { MdLabelImportantOutline } from 'react-icons/md'
import { ActivePage } from './Constants'

function Sidebar({activePage, setActivePage}) {
  return (
    <div>
        <div className="d-flex flex-column justify-content-between bg-secondary vh-100-50 max-200">
            <div className="d-flex flex-column">
                <div 
                className={("d-flex justify-conent-around" + ((activePage == ActivePage.NOTES)?" bg-danger":""))}
                onClick={()=>setActivePage(ActivePage.NOTES)}>
                    <BsLightbulb size={20} />
                    <div className="fw-bold">Notes</div>
                </div>
                <div className={("d-flex justify-conent-around" + ((activePage == ActivePage.REMINDER)?" bg-danger":""))}
                onClick={()=>setActivePage(ActivePage.REMINDER)}>
                    <BsBell size={20} />
                    <div className="fw-bold">Reminder</div>
                </div>
                <div className={("d-flex justify-conent-around" + ((activePage == ActivePage.IMPORTANT)?" bg-danger":""))}
                onClick={()=>setActivePage(ActivePage.IMPORTANT)}>
                    <MdLabelImportantOutline size={20} />
                    <div className="fw-bold">Important</div>
                </div>
                <div className={("d-flex justify-conent-around" + ((activePage == ActivePage.EDITLABELS)?" bg-danger":""))}
                onClick={()=>setActivePage(ActivePage.EDITLABELS)}>
                    <BsPencil size={20} />
                    <div className="fw-bold">Edit labels</div>
                </div>
                <div className={("d-flex justify-conent-around" + ((activePage == ActivePage.ARCHIVE)?" bg-danger":""))}
                onClick={()=>setActivePage(ActivePage.ARCHIVE)}>
                    <BsArchive size={20} />
                    <div className="fw-bold">Archive</div>
                </div>
                <div className={("d-flex justify-conent-around" + ((activePage == ActivePage.BIN)?" bg-danger":""))}
                onClick={()=>setActivePage(ActivePage.BIN)}>
                    <BsTrash3 size={20} />
                    <div className="fw-bold">Bin</div>
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