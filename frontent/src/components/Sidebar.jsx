import React from 'react'
import { BsLightbulb } from 'react-icons/bs'

function Sidebar() {
  return (
    <div>
        <div className="d-flex flex-column justify-content-between bg-secondary vh-100-50">
            <div className="d-flex flex-column">
                <div className="d-flex justify-conent-around">
                    <BsLightbulb size={20} />
                    <div className="fw-bold">Notes</div>
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