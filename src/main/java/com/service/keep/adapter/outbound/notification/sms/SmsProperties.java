/**
 * author @bhupendrasambare
 * Date   :27/05/26
 * Time   :10:12 pm
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.notification.sms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "sms.twilio")
public class SmsProperties {

    private String accountSid;

    private String authToken;

    private String fromNumber;
}
