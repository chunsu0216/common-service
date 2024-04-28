package com.commonservice.service;

import com.commonservice.dto.CardRequestDto;
import com.commonservice.entity.Merchant;
import com.commonservice.entity.Terminal;
import com.commonservice.entity.Van;
import com.commonservice.message.ApiResponseMessage;
import com.commonservice.message.ApiResult;
import com.commonservice.repository.TerminalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{

    private final TerminalRepository terminalRepository;
    @Override
    public ResponseEntity<ApiResult> verifyCard(String terminalKey, Long amount) {

        log.info("옴?");

        // HTTP Header Authorization 셋팅된 터미널 키로 가맹점 검증
        Optional<Terminal> terminal = terminalRepository.findByTerminalKey(terminalKey);

        if (terminal.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiResult.builder()
                    .message(ApiResponseMessage.NOT_FOUND.message())
                    .code(ApiResponseMessage.NOT_FOUND.code()).build());
        }
        // 터미널 1회 한도 검증
        Long onceLimit = terminal.get().getOnceLimit();
        if(amount > onceLimit){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.builder()
                    .message(ApiResponseMessage.ONCE_LIMIT_EXCESS.message())
                    .code(ApiResponseMessage.ONCE_LIMIT_EXCESS.code())
                    .build());
        }
        // 터미널 비활성화일 경우
        if(!terminal.get().getState().equals("Y")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.builder()
                    .message(ApiResponseMessage.UNAUTHORIZED_TERMINAL.message())
                    .code(ApiResponseMessage.UNAUTHORIZED_TERMINAL.code())
                    .build());
        }
        // 가맹점 비활성화일 경우
        Merchant merchant = terminal.get().getMerchant();

        if(!merchant.getState().equals("Y")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiResult.builder()
                    .message(ApiResponseMessage.UNAUTHORIZED_MERCHANT.message())
                    .code(ApiResponseMessage.UNAUTHORIZED_TERMINAL.code())
                    .build());
        }

        // 공통 정보 응답
        Van van = terminal.get().getVan();

        return ResponseEntity.status(HttpStatus.OK).body(ApiResult.builder()
                .message(ApiResponseMessage.STEADY_STATE.message())
                .code(ApiResponseMessage.STEADY_STATE.code())
                .terminalId(terminal.get().getTerminalId())
                .van(van.getVan())
                .vanId(van.getVanId())
                .vanKey(van.getVanKey())
                .build());
    }

    @Override
    public ResponseEntity<String> verifyVirtualAccount(String terminalKey) {
        return null;
    }
}
