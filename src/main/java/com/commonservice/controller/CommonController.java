package com.commonservice.controller;

import com.commonservice.dto.CardRequestDto;
import com.commonservice.message.ApiResult;
import com.commonservice.service.CommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/common-service")
public class CommonController {

    private final CommonService commonService;

    @GetMapping("/card/{terminalKey}/{amount}")
    public ResponseEntity<ApiResult> verify(@PathVariable(value = "terminalKey") String terminalKey,
                                            @PathVariable(value = "amount") Long amount) {

        return commonService.verifyCard(terminalKey, amount);
    }
}
