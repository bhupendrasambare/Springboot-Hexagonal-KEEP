/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:53 pm
 * Project:Keep
 **/
package com.service.keep.domain.note.service;

import com.service.keep.domain.note.model.Note;

public interface NoteDomainService {

    Note create(Note note);

    Note update(Note updatedNote, String id);

    Boolean archive(String id);

    Boolean pin(String id);

    Boolean trash(String id);

    Boolean restore(String id);

}
