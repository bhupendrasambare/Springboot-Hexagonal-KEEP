package com.service.keep.domain.port.outbound;

import com.service.keep.domain.valueobject.UserId;

public interface AuthenticatedUserPort {

    UserId getCurrentUserId();
}
