package com.coopfin.backend.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String token;
    private String username;
    private String rol;
    private Long idCooperativa;
    private String nombreCooperativa;
}
