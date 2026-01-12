/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:06 am
 * Project:Keep
 **/
package com.service.keep.adapter.inbound.rest.note;

import com.service.keep.application.dto.request.NoteCreateRequest;
import com.service.keep.application.dto.request.NoteSearchRequest;
import com.service.keep.application.dto.request.NoteUpdateRequest;
import com.service.keep.application.dto.response.NoteResponse;
import com.service.keep.application.mapper.NoteMapper;
import com.service.keep.domain.port.inbound.NoteUseCase;
import com.service.keep.domain.port.outbound.AuthenticatedUserPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteUseCase noteUseCase;

    private final AuthenticatedUserPort authenticatedUserPort;

    // TEMP – replace with SecurityContext later
    private String getUserId() {
        return authenticatedUserPort.getCurrentUserId().getValue();
    }

    @PostMapping
    public ResponseEntity<NoteResponse> create(@Valid @RequestBody NoteCreateRequest request) {

        return ResponseEntity.ok(
                NoteMapper.toNoteResponse(
                        noteUseCase.create(
                                getUserId(),
                                request.getTitle(),
                                request.getDescription(),
                                request.getReminder(),
                                request.getTagIds()
                        )
                )
        );
    }

    @PostMapping
    public ResponseEntity<List<NoteResponse>> findAll(@Valid @RequestBody NoteSearchRequest request) {

        return ResponseEntity.ok(noteUseCase.getAll(
                        getUserId(),
                        request.getPinned(),
                        request.getArchived(),
                        request.getTrashed(),
                        request.getKeyword(),
                        request.getPage(),
                        request.getPageSize()
                ).stream()
                .map(NoteMapper::toNoteResponse)
                .toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> update(
            @PathVariable String id,
            @Valid @RequestBody NoteUpdateRequest request) {

        return ResponseEntity.ok(
                NoteMapper.toNoteResponse(
                        noteUseCase.update(
                                getUserId(),
                                id,
                                request.getTitle(),
                                request.getDescription(),
                                request.getTagId()
                        )
                )
        );
    }

    @PostMapping("/pin/{id}")
    public ResponseEntity<Void> pin(@PathVariable String id) {
        noteUseCase.pin(getUserId(), id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/un-pin/{id}")
    public ResponseEntity<Void> unPin(@PathVariable String id) {
        noteUseCase.unPin(getUserId(), id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/archive/{id}")
    public ResponseEntity<Void> archive(@PathVariable String id) {
        noteUseCase.archive(getUserId(), id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/un-archive/{id}")
    public ResponseEntity<Void> unArchive(@PathVariable String id) {
        noteUseCase.unArchive(getUserId(), id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/trash/{id}")
    public ResponseEntity<Void> trash(@PathVariable String id) {
        noteUseCase.moveToTrash(getUserId(), id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/un-trash/{id}")
    public ResponseEntity<Void> unTrash(@PathVariable String id) {
        noteUseCase.restore(getUserId(), id);
        return ResponseEntity.noContent().build();
    }
}
