package com.commonservice.service;

import com.commonservice.dto.CardRequestDto;
import com.commonservice.message.ApiResult;
import org.springframework.http.ResponseEntity;

public interface CommonService {

    ResponseEntity<ApiResult> verifyCard(String terminalKey, Long amount);
    ResponseEntity<String> verifyVirtualAccount(String terminalKey);
}
