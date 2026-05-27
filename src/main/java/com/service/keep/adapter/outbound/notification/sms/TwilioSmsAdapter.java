/**
 * author @bhupendrasambare
 * Date   :27/05/26
 * Time   :10:12 pm
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.notification.sms;

import com.service.keep.domain.port.outbound.SmsSenderPort;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TwilioSmsAdapter
        implements SmsSenderPort {

    private final SmsProperties properties;

    @Override
    public void sendSms(
            String phoneNumber,
            String message
    ) {

        Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(
                        properties.getFromNumber()
                ),
                message
        ).create();
    }
}
