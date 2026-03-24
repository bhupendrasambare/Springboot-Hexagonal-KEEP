import React from 'react'
import { IoTrashBinOutline } from 'react-icons/io5'
import { MdOutlineArchive, MdOutlinePushPin } from 'react-icons/md'

function NotesCard({noteData}) {
  return (
    <div className='card note-cards p-2 m-3'>
        <h1 className='fw-bold fs-4'>{noteData.title}</h1>
        <p className='fs-6'>{noteData.description}</p>

        <div className="footer">
          <MdOutlineArchive className='card-circle-icon' />
          <IoTrashBinOutline className='card-circle-icon' />
          <MdOutlinePushPin className='card-circle-icon' />
        </div>
    </div>
  )
}

export default NotesCard