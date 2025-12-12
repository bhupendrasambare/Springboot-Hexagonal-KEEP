/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:26 am
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistance.mongo.note;

import com.service.keep.domain.model.Note;
import com.service.keep.domain.port.outbound.NoteRepositoryPort;
import com.service.keep.domain.valueobject.NoteId;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class NotePersistenceAdapter implements NoteRepositoryPort {
}
