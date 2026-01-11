/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:06 am
 * Project:Keep
 **/
package com.service.keep.adapter.inbound.rest.note;


import com.service.keep.application.dto.request.NoteCreateRequest;
import com.service.keep.application.dto.request.NoteUpdateRequest;
import com.service.keep.application.dto.response.NoteResponse;
import com.service.keep.application.mapper.NoteMapper;
import com.service.keep.domain.port.inbound.NoteUseCase;
import com.service.keep.domain.port.outbound.AuthenticatedUserPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        noteUseCase.delete(getUserId(), id);
        return ResponseEntity.noContent().build();
    }
}
