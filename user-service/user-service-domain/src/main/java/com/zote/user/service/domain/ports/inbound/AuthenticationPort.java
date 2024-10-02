package com.zote.user.service.domain.ports.inbound;

import com.zote.user.service.domain.model.AuthData;

public interface AuthenticationPort {

    AuthData authenticate(String username, String password);
}
