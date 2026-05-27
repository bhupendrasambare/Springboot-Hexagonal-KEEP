/**
 * author @bhupendrasambare
 * Date   :27/05/26
 * Time   :10:10 pm
 * Project:Keep
 **/
package com.service.keep.domain.port.outbound;

public interface SmsSenderPort {

    void sendSms(
            String phoneNumber,
            String message
    );
}