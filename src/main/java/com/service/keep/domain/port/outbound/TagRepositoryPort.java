/**
 * author @bhupendrasambare
 * Date   :21/11/25
 * Time   :8:56 pm
 * Project:Keep
 **/
package com.service.keep.domain.port.outbound;

import com.service.keep.domain.model.Tags;
import com.service.keep.domain.valueobject.TagsId;
import com.service.keep.domain.valueobject.UserId;

import java.util.List;
import java.util.Optional;

public interface TagRepositoryPort {

    Tags save(Tags user);

    Optional<Tags> findById(TagsId id);

    List<Tags> findAllUserId(UserId userId);

    boolean delete(TagsId id);
}
