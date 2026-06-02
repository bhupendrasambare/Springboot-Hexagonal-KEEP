/**
 * author @bhupendrasambare
 * Date   :14/05/26
 * Time   :11:43 pm
 * Project:Keep
 **/
package com.service.keep.domain.port.outbound;

import com.service.keep.domain.model.Reminder;
import com.service.keep.domain.valueobject.ReminderId;
import com.service.keep.domain.valueobject.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReminderRepositoryPort {

    Reminder save(Reminder reminder);

    Page<Reminder> findByUserId(
            UserId userId,
            boolean pinned,
            boolean archived,
            boolean trashed,
            String keyword,
            int page,
            int pageSize
    );


    Optional<Reminder> findById(ReminderId id);

    List<Reminder> findAllByUserId(UserId userId);

    Page<Reminder> findAllByIsCompleted(PageRequest request, Boolean completed);

    Page<Reminder> findAllByMetaDataFlag(Boolean metaDataFlag, Pageable pageable);

    List<Reminder> searchByAi(UserId userId, List<String> keywords, List<String> tags, String title);
}
