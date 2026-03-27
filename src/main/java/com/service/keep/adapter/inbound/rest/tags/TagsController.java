/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:07 am
 * Project:Keep
 **/
package com.service.keep.adapter.inbound.rest.tags;

import com.service.keep.application.dto.request.TagCreateRequest;
import com.service.keep.application.dto.response.TagResponse;
import com.service.keep.application.mapper.TagMapper;
import com.service.keep.application.response.Response;
import com.service.keep.application.response.ResponseUtil;
import com.service.keep.domain.port.inbound.TagsUseCase;
import com.service.keep.domain.port.outbound.AuthenticatedUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Response> create(@RequestBody TagCreateRequest request) {

        TagResponse response = TagMapper.toResponse(
                tagsService.create(
                        getUserId(),
                        request.getName(),
                        request.getName(),
                        request.getImageUri()
                )
        );

        return ResponseUtil.success("Tag created successfully", response);
    }

    @GetMapping
    public ResponseEntity<Response> getTags() {

        List<TagResponse> tags = tagsService.getAllTags(getUserId())
                .stream()
                .map(TagMapper::toResponse)
                .toList();

        return ResponseUtil.success("Tags fetched successfully", tags);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {

        tagsService.delete(getUserId(), id);

        return ResponseUtil.success("Tag deleted successfully");
    }
}
