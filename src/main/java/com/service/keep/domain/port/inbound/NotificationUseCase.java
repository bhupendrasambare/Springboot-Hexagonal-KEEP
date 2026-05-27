/**
 * author @bhupendrasambare
 * Date   :27/05/26
 * Time   :10:10 pm
 * Project:Keep
 **/
package com.service.keep.domain.port.inbound;

public interface NotificationUseCase {

    void sendEmail(
            String to,
            String subject,
            String body
    );

    void sendSms(
            String phoneNumber,
            String message
    );
}