package com.cc.bootstrap.intl.demo.web.sse;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * @Description: agent工具问答服务
 * @author: ChenChen
 * @date: 2025-08-04 15:59
 */
@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin
public class AgentChatController {

    @Autowired
    private AgentService agentService;

    /**
     * @Description 工具健康检查
     * @param toolId
     * @author ChenChen
     * @return com.cc.bootstrap.common.base.restful.ResponseResult<java.util.Map < java.lang.String, java.lang.String>>
     * @date 2025-08-04 17:17
     */
    @GetMapping("/agent/health/check")
    public ResponseResult<Map<String,String>> healthCheck(@RequestParam("toolId") String toolId) {
        log.info("调用[agent工具健康检查]：参数=[{}]", toolId);
        Map<String,String> healthStatusMap = agentService.healthCheck(toolId);
        log.info("调用[agent工具健康检查]，调用成功：参数=[{}]", toolId);
        return ResponseResult.success(healthStatusMap);
    }

    /**
     * @Description 创建sessionId
     * @param toolId
     * @author ChenChen
     * @return com.cc.bootstrap.common.base.restful.ResponseResult<java.util.Map < java.lang.String, java.lang.String>>
     * @date 2025-08-05 14:34
     */
    @PostMapping("/agent/session")
    public ResponseResult<Map<String,String>> createSessionId(@RequestParam("toolId") String toolId) {
        log.info("调用[创建sessionId]：参数=[{}]", toolId);
        Map<String,String> sessionIdMap = agentService.createSessionId(toolId);
        log.info("调用[创建sessionId]，调用成功：参数=[{}]", toolId);
        return ResponseResult.success(sessionIdMap);
    }

    /**
     * @Description 体验对话
     * @param files
     * @param toolId
     * @param sessionId
     * @param txt
     * @author ChenChen
     * @return reactor.core.publisher.Flux<java.lang.String>
     * @date 2025-08-05 16:54
     */
    @PostMapping(value = "/agent/chat", produces = {MediaType.TEXT_EVENT_STREAM_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public Flux<String> chat(@RequestParam(value = "files", required = false) CommonsMultipartFile[] files,
                             @RequestParam("toolId") String toolId,
                             @RequestParam("sessionId") String sessionId,
                             @RequestParam("txt") String txt) {
        AgentChatVo agentChatVo = new AgentChatVo();
        agentChatVo.setToolId(toolId);
        agentChatVo.setSessionId(sessionId);
        agentChatVo.setTxt(txt);

        log.info("调用[体验对话]：参数=[{}]", agentChatVo);
        Flux<String> webFlux = agentService.chat(files, agentChatVo);
        log.info("调用[体验对话]，调用成功：参数=[{}]", agentChatVo);
        return webFlux.doOnNext(data -> log.info("Emitting:{}", data));
    }
}
