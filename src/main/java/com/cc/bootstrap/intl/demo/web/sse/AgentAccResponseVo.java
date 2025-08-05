package com.cc.bootstrap.intl.demo.web.sse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description: agent调用返回Vo
 * @author: ChenChen
 * @date: 2025-08-04 17:23
 */
@ToString
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AgentAccResponseVo<T> {
    private T data;
}
