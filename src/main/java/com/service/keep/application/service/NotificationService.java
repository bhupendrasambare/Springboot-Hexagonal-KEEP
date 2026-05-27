/**
 * author @bhupendrasambare
 * Date   :27/05/26
 * Time   :10:11 pm
 * Project:Keep
 **/
package com.service.keep.application.service;

import com.service.keep.domain.port.inbound.NotificationUseCase;
import com.service.keep.domain.port.outbound.EmailSenderPort;
import com.service.keep.domain.port.outbound.SmsSenderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService
        implements NotificationUseCase {

    private final EmailSenderPort emailSenderPort;
    private final SmsSenderPort smsSenderPort;

    @Override
    public void sendEmail(
            String to,
            String subject,
            String body
    ) {

        emailSenderPort.sendEmail(
                to,
                subject,
                body
        );
    }

    @Override
    public void sendSms(
            String phoneNumber,
            String message
    ) {

        smsSenderPort.sendSms(
                phoneNumber,
                message
        );
    }
}
