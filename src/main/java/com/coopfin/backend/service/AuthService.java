package com.coopfin.backend.service;

import com.coopfin.backend.dto.request.AuthRequest;
import com.coopfin.backend.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse login(AuthRequest request);
}
