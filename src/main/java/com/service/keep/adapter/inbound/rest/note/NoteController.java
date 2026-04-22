/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:06 am
 * Project:Keep
 **/
package com.service.keep.adapter.inbound.rest.note;

import com.service.keep.application.dto.request.*;
import com.service.keep.application.dto.response.NoteResponse;
import com.service.keep.application.dto.response.PageResponse;
import com.service.keep.application.mapper.NoteMapper;
import com.service.keep.application.response.Response;
import com.service.keep.application.response.ResponseUtil;
import com.service.keep.domain.model.Note;
import com.service.keep.domain.port.inbound.NoteUseCase;
import com.service.keep.domain.port.outbound.AuthenticatedUserPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteUseCase noteUseCase;
    private final AuthenticatedUserPort authenticatedUserPort;

    private String getUserId() {
        return authenticatedUserPort.getCurrentUserId().getValue();
    }

    @PostMapping
    public ResponseEntity<Response> create(@Valid @RequestBody NoteCreateRequest request) {

        NoteResponse response = NoteMapper.toNoteResponse(
                noteUseCase.create(
                        getUserId(),
                        request.getTitle(),
                        request.getDescription(),
                        request.getReminder(),
                        request.getTagId()
                )
        );

        return ResponseUtil.success("Note created successfully", response);
    }

    @PostMapping("/find")
    public ResponseEntity<Response> findAll(@Valid @RequestBody NoteSearchRequest request) {

        Page<Note> data = noteUseCase.getAll(
                getUserId(),
                request.getPinned(),
                request.getArchived(),
                request.getTrashed(),
                request.getKeyword(),
                request.getPage(),
                request.getPageSize()
        );

        List<NoteResponse> notes = data.stream()
                .map(NoteMapper::toNoteResponse)
                .toList();

        PageResponse<NoteResponse> pageResponse = new PageResponse<>(notes, data);

        return ResponseUtil.success("Notes searched successfully", pageResponse);
    }

    @GetMapping("/search-notes")
    public ResponseEntity<Response> searchNotes(@RequestParam String request) {

        List<Note> data = noteUseCase.findByAi(request, getUserId());

        List<NoteResponse> notes = data.stream()
                .map(NoteMapper::toNoteResponse)
                .toList();

        return ResponseUtil.success("Notes fetched successfully", notes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(
            @PathVariable String id,
            @Valid @RequestBody NoteUpdateRequest request) {

        NoteResponse response = NoteMapper.toNoteResponse(
                noteUseCase.update(
                        getUserId(),
                        id,
                        request.getTitle(),
                        request.getDescription(),
                        request.getTagId()
                )
        );

        return ResponseUtil.success("Note updated successfully", response);
    }

    @PostMapping("/pin/{id}")
    public ResponseEntity<Response> pin(@PathVariable String id) {
        noteUseCase.pin(getUserId(), id);
        return ResponseUtil.success("Note pinned successfully");
    }

    @PostMapping("/un-pin/{id}")
    public ResponseEntity<Response> unPin(@PathVariable String id) {
        noteUseCase.unPin(getUserId(), id);
        return ResponseUtil.success("Note unpinned successfully");
    }

    @PostMapping("/archive/{id}")
    public ResponseEntity<Response> archive(@PathVariable String id) {
        noteUseCase.archive(getUserId(), id);
        return ResponseUtil.success("Note archived successfully");
    }

    @PostMapping("/un-archive/{id}")
    public ResponseEntity<Response> unArchive(@PathVariable String id) {
        noteUseCase.unArchive(getUserId(), id);
        return ResponseUtil.success("Note unarchived successfully");
    }

    @PostMapping("/trash/{id}")
    public ResponseEntity<Response> trash(@PathVariable String id) {
        noteUseCase.moveToTrash(getUserId(), id);
        return ResponseUtil.success("Note moved to trash");
    }

    @PostMapping("/un-trash/{id}")
    public ResponseEntity<Response> unTrash(@PathVariable String id) {
        noteUseCase.restore(getUserId(), id);
        return ResponseUtil.success("Note restored successfully");
    }
}
