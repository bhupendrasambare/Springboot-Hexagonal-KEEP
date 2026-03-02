import axios from 'axios';
import { useEffect, useState } from 'react';

export const Notes = () => {

  const [notesList, setNotesList] = useState([]);

  useEffect({

  },[])

  const getNotes = () =>{
    
  }

  return (
    <div>
      <h1>Notes</h1>
      <p>Recent notes</p>
      <div className="container">

      </div>
    </div>
  )
}

export default Notes