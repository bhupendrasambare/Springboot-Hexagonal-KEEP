import React from 'react'
import { BsArchive, BsBell, BsLightbulb, BsPencil, BsTrash3 } from 'react-icons/bs'
import { MdLabelImportantOutline } from 'react-icons/md'

function Sidebar() {
  return (
    <div>
        <div className="d-flex flex-column justify-content-between bg-secondary vh-100-50">
            <div className="d-flex flex-column">
                <div className="d-flex justify-conent-around">
                    <BsLightbulb size={20} />
                    <div className="fw-bold">Notes</div>
                </div>
                <div className="d-flex justify-conent-around">
                    <BsBell size={20} />
                    <div className="fw-bold">Reminder</div>
                </div>
                <div className="d-flex justify-conent-around">
                    <MdLabelImportantOutline size={20} />
                    <div className="fw-bold">Important</div>
                </div>
                <div className="d-flex justify-conent-around">
                    <BsPencil size={20} />
                    <div className="fw-bold">Edit labels</div>
                </div>
                <div className="d-flex justify-conent-around">
                    <BsArchive size={20} />
                    <div className="fw-bold">Archive</div>
                </div>
                <div className="d-flex justify-conent-around">
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