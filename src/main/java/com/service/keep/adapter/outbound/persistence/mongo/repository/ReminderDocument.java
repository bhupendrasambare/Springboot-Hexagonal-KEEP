/**
 * author @bhupendrasambare
 * Date   :16/05/26
 * Time   :11:07 pm
 * Project:Keep
 **/
package com.service.keep.adapter.outbound.persistence.mongo.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collation = "reminder")
public class ReminderDocument {
}
