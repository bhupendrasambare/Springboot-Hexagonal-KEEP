import React from 'react'

function NotesCard({noteData}) {
  return (
    <div className='card note-cards p-2 m-3'>
        <h1 className='fw-bold fs-4'>{noteData.title}</h1>
        <p className='fs-6'>{noteData.description}</p>
    </div>
  )
}

export default NotesCard