/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:07 am
 * Project:Keep
 **/
package com.service.keep.adapter.inbound.rest.tags;

import com.service.keep.application.dto.request.TagCreateRequest;
import com.service.keep.application.dto.response.TagResponse;
import com.service.keep.application.service.TagsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagsController {

    private final TagsService tagsService;

    @PostMapping
    public TagResponse create(@RequestBody TagCreateRequest request) {
        return tagsService.create(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        tagsService.delete(id);
    }
}
