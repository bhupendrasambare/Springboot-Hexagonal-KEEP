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
import com.service.keep.application.service.NoteApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteApplicationService noteUseCase;

    @PostMapping
    public ResponseEntity<NoteResponse> create(
            @RequestBody @Valid NoteCreateRequest request) {
        return ResponseEntity.ok(noteUseCase.createNote(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponse> update(
            @PathVariable String id,
            @RequestBody @Valid NoteUpdateRequest request) {
        return ResponseEntity.ok(noteUseCase.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        noteUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
