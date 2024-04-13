package com.commonservice.repository;

import com.commonservice.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TerminalRepository extends JpaRepository<Terminal, Long> {

    Optional<Terminal> findByTerminalKey(String terminalKey);
    Terminal findTerminalByTerminalId(String terminalId);
}
