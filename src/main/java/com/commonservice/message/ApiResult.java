package com.commonservice.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ApiResult {
    private String code;
    private String message;
    private String terminalId;
    private String van;
    private String vanId;
    private String vanKey;

    public ApiResult(String code, String message, String terminalId, String van, String vanId, String vanKey) {
        this.code = code;
        this.message = message;
        this.terminalId = terminalId;
        this.van = van;
        this.vanId = vanId;
        this.vanKey = vanKey;
    }

    public ApiResult() {
    }
}
