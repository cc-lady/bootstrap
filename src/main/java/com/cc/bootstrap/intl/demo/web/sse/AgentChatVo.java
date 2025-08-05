package com.cc.bootstrap.intl.demo.web.sse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description: agent对话Vo
 * @author: ChenChen
 * @date: 2025-08-05 16:50
 */
@ToString
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentChatVo {
    private String toolId;
    private String sessionId;
    private String txt;
    private boolean stream = true;
}
