/**
 * author @bhupendrasambare
 * Date   :22/11/25
 * Time   :10:18 pm
 * Project:Keep
 **/
package com.service.keep.domain.port.inbound;

import com.service.keep.domain.model.Tags;

import java.util.List;

public interface ManageTagsUseCase {

    Tags createTags(String userId, String userName, String color, String imageUrl);

    Tags updateTags(String tagId, String userId, String userName, String color, String imageUrl);

    boolean deleteTags(String userId, String tagId);

    List<Tags> getTags(String userId);

}
