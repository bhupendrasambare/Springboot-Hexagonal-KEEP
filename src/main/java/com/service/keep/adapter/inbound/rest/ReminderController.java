/**
 * author @bhupendrasambare
 * Date   :19/05/26
 * Time   :11:22 pm
 * Project:Keep
 **/
package com.service.keep.adapter.inbound.rest;

import com.service.keep.application.response.Response;
import com.service.keep.application.response.ResponseUtil;
import com.service.keep.domain.model.Reminder;
import com.service.keep.domain.port.inbound.ReminderUseCase;
import com.service.keep.domain.port.outbound.AuthenticatedUserPort;
import com.service.keep.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reminder")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderUseCase reminderUseCase;
    private final AuthenticatedUserPort authenticatedUserPort;

    @PostMapping
    public ResponseEntity<Response> create(
            @RequestParam String noteId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description
    ) {

        UserId userId = authenticatedUserPort.getCurrentUserId();

        Reminder reminder = reminderUseCase.create(
                userId.getValue(),
                noteId,
                title,
                description
        );

        return ResponseUtil.success(
                "Reminder created successfully",
                reminder
        );
    }

    @PutMapping("/{reminderId}")
    public ResponseEntity<Response> update(
            @PathVariable String reminderId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Boolean completed
    ) {

        UserId userId = authenticatedUserPort.getCurrentUserId();

        Reminder reminder = reminderUseCase.update(
                userId.getValue(),
                reminderId,
                title,
                description,
                completed
        );

        return ResponseUtil.success(
                "Reminder updated successfully",
                reminder
        );
    }

    @PatchMapping("/{reminderId}/complete")
    public ResponseEntity<Response> markCompleted(
            @PathVariable String reminderId
    ) {

        UserId userId = authenticatedUserPort.getCurrentUserId();

        reminderUseCase.markCompleted(
                userId.getValue(),
                reminderId
        );

        return ResponseUtil.success(
                "Reminder marked as completed",
                null
        );
    }

    @PatchMapping("/{reminderId}/incomplete")
    public ResponseEntity<Response> markIncomplete(
            @PathVariable String reminderId
    ) {

        UserId userId = authenticatedUserPort.getCurrentUserId();

        reminderUseCase.markIncomplete(
                userId.getValue(),
                reminderId
        );

        return ResponseUtil.success(
                "Reminder marked as incomplete",
                null
        );
    }

    @GetMapping("/{reminderId}")
    public ResponseEntity<Response> getById(
            @PathVariable String reminderId
    ) {

        UserId userId = authenticatedUserPort.getCurrentUserId();

        Reminder reminder = reminderUseCase.getById(
                userId.getValue(),
                reminderId
        );

        return ResponseUtil.success(
                "Reminder fetched successfully",
                reminder
        );
    }

    @GetMapping
    public ResponseEntity<Response> getAllByUser() {

        UserId userId = authenticatedUserPort.getCurrentUserId();

        return ResponseUtil.success(
                "Reminders fetched successfully",
                reminderUseCase.getAllByUser(userId.getValue())
        );
    }

    @GetMapping("/completed")
    public ResponseEntity<Response> getCompletedReminders() {

        UserId userId = authenticatedUserPort.getCurrentUserId();

        return ResponseUtil.success(
                "Completed reminders fetched successfully",
                reminderUseCase.getCompletedReminders(userId.getValue())
        );
    }

    @GetMapping("/pending")
    public ResponseEntity<Response> getPendingReminders() {

        UserId userId = authenticatedUserPort.getCurrentUserId();

        return ResponseUtil.success(
                "Pending reminders fetched successfully",
                reminderUseCase.getPendingReminders(userId.getValue())
        );
    }
}
