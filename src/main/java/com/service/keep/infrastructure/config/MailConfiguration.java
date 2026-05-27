/**
 * author @bhupendrasambare
 * Date   :27/05/26
 * Time   :10:13 pm
 * Project:Keep
 **/
package com.service.keep.infrastructure.config;

import com.service.keep.adapter.outbound.notification.sms.SmsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        SmsProperties.class
})
public class MailConfiguration {
}
