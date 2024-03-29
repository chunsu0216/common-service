package com.commonservice.message;

public enum ApiResponseMessage {
    NOT_FOUND("존재하지않는 가맹점 키 입니다.", "0404"),
    UNAUTHORIZED_TERMINAL("비활성화된 가맹점 키입니다.", "0401"),
    UNAUTHORIZED_MERCHANT("사용 중지된 가맹점 입니다.", "0409"),
    ONCE_LIMIT_EXCESS("1회 최대 한도를 초과하였습니다.", "0409"),
    STEADY_STATE("결제 가능한 터미널입니다.", "0200");
    private final String message;
    private final String code;
    ApiResponseMessage(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String message() {
        return message;
    }

    public String code() {
        return code;
    }
}
