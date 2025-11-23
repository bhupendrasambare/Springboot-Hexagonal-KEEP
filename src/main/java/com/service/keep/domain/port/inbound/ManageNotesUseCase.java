/**
 * author @bhupendrasambare
 * Date   :22/11/25
 * Time   :10:18 pm
 * Project:Keep
 **/
package com.service.keep.domain.port.inbound;

import com.service.keep.domain.model.Note;

import java.util.List;

public interface ManageNotesUseCase {

    Note createNote(String userId, String title, String content, List<String> tags);

    Note updateNote(String userId, String noteId, String title, String content, List<String> tags);

    boolean deleteNote(String userId, String noteId);

    List<Note> getNotes(String userid);

}
