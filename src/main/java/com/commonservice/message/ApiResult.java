package com.commonservice.message;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResult {
    private String code;
    private String message;

    public ApiResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResult() {
    }
}
