import axios from 'axios';
import { useState } from 'react';

export const Notes = () => {

  const [notesList, setNotesList] = useState([]);

  return (
    <div>
      <h1>Notes</h1>
      <p>Recent notes</p>
    </div>
  )
}

export default Notes