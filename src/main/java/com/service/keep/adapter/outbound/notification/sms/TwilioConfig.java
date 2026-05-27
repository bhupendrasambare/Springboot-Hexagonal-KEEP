/**
 * author @bhupendrasambare
 * Date   :27/05/26
 * Time   :10:12 pm
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.notification.sms;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TwilioConfig {

    private final SmsProperties properties;

    @PostConstruct
    public void init() {

        Twilio.init(
                properties.getAccountSid(),
                properties.getAuthToken()
        );
    }
}
