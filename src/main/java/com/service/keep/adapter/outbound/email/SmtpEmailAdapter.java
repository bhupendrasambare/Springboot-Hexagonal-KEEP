/**
 * author @bhupendrasambare
 * Date   :05/12/25
 * Time   :12:07 am
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.email;

import com.service.keep.domain.port.outbound.EmailSenderPort;
import org.springframework.stereotype.Service;

@Service
public class SmtpEmailAdapter implements EmailSenderPort {
    @Override
    public void sendEmail(String to, String subject, String body) {

    }
}
