/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:07 am
 * Project:Keep
 **/
package com.service.keep.adapter.inbound.rest.tags;

import com.service.keep.application.dto.request.TagCreateRequest;
import com.service.keep.application.dto.response.TagResponse;
import com.service.keep.application.mapper.NoteMapper;
import com.service.keep.application.mapper.TagMapper;
import com.service.keep.domain.port.inbound.TagsUseCase;
import com.service.keep.domain.port.outbound.AuthenticatedUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagsController {

    private final TagsUseCase tagsService;

    private final AuthenticatedUserPort authenticatedUserPort;

    private String getUserId() {
        return authenticatedUserPort.getCurrentUserId().getValue();
    }

    @PostMapping
    public ResponseEntity<TagResponse> create(@RequestBody TagCreateRequest request) {
        return ResponseEntity.ok(
                TagMapper.toResponse(
                        tagsService.create(
                                this.getUserId(),
                                request.getName(),
                                request.getName(),
                                request.getImageUri())
                )
        );
    }

    @GetMapping("/")
    public ResponseEntity<?> getTags() {
        return ResponseEntity.ok(tagsService.getAllTags(getUserId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntity.ok(tagsService.getAllTags(
                        getUserId()
                ).stream()
                .map(TagMapper::toResponse)
                .toList());
    }
}
