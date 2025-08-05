package com.cc.bootstrap.intl.demo.web.sse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 逻辑服务层
 * @author: ChenChen
 * @date: 2025-08-04 17:14
 */
@Slf4j
@Component
public class AgentService {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * @Description 工具健康检查
     * @param toolId
     * @author ChenChen
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @date 2025-08-04 17:18
     */
    public Map<String, String> healthCheck(String toolId) {
        String url = "";
        String host = "";// ip 绕过域名解析，为空时直接连接域名，不为空时直接连接ip

        String requestUrl = String.format("%s/chatabc/health_check", url);

        ResponseEntity<AgentAccResponseVo<Map<String,String>>> response = null;
        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
        URI uri = UriComponentsBuilder.fromUriString(requestUrl).build().toUri();
        RequestEntity<Void> requestEntity = RequestEntity.get(uri)
                .header("Host", host)
                .build();
        try {
            response = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<AgentAccResponseVo<Map<String, String>>>() {
            });
        } catch (Exception e) {
            log.error("工具健康检查失败！", e);
            throw new RuntimeException("工具健康检查失败！", e);
        }

        return response.getBody().getData();
    }

    /**
     * @Description 创建sessionId
     * @param toolId
     * @author ChenChen
     * @return java.util.Map<java.lang.String, java.lang.String>
     * @date 2025-08-05 14:34
     */
    public Map<String, String> createSessionId(String toolId) {
        String url = "";
        String host = "";// ip 绕过域名解析，为空时直接连接域名，不为空时直接连接ip

        String requestUrl = String.format("%s/chatabc/init_session", url);
        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Host", host);
            headers.setContentType(MediaType.APPLICATION_JSON);

            AccRequestEntity<Map<String,Object>> accRequestEntityMap = new AccRequestEntity();
            Map<String,Object> variables = new HashMap<>();
            variables.put("promp_variables", new ArrayList<String>());
            accRequestEntityMap.setData(variables);

            HttpEntity<AccRequestEntity<Map<String,Object>>> request = new HttpEntity<>(accRequestEntityMap, headers);
            ResponseEntity<AgentAccResponseVo> response = restTemplate.exchange(requestUrl, HttpMethod.POST,
                    request, AgentAccResponseVo.class);
            return objectMapper.readValue(objectMapper.writeValueAsString(response.getBody()),
                    new TypeReference<Map<String, String>>() {
                    });
        } catch (Exception e) {
            log.error("创建sessionId发生异常！", e);
            throw new RuntimeException("创建sessionId发生异常！", e);
        }
    }

    /**
     * @Description 体验对话
     * @param files
     * @param agentChatVo
     * @author ChenChen
     * @return reactor.core.publisher.Flux<java.lang.String>
     * @date 2025-08-05 16:54
     */
    public Flux<String> chat(CommonsMultipartFile[] files, AgentChatVo agentChatVo) {
        String sessionId = agentChatVo.getSessionId();
        Preconditions.checkArgument(StringUtils.isNotBlank(sessionId), "参数sessionId不能为空，请检查。");
        String toolId = agentChatVo.getToolId();
        Preconditions.checkArgument(StringUtils.isNotBlank(toolId), "参数toolId不能为空，请检查。");
        String txt = agentChatVo.getTxt();
        Preconditions.checkArgument(StringUtils.isNotBlank(txt), "参数txt不能为空，请检查。");

        String isSupportFile = "Y";
        if ("Y".equals(isSupportFile) && null != files) {
            Preconditions.checkArgument(files.length <= 3, "单次对话图片数量不能超过3个，请检查。");

            for (MultipartFile file : files) {
                if (null == file) {
                    continue;
                }
                float fsize = file.getSize() / 1024;
                Assert.isTrue(fsize <= 1024 * 2, "文件大小不得超过2M，请检查。");
            }
        }

        String url = "";
        String host = "";
        // 参数
        Map<String,Object> variables = new HashMap<>();
        variables.put("sessionId", sessionId);
        variables.put("txt", txt);
        variables.put("stream", agentChatVo.isStream());

        List<Map<String,Object>> fileMapVariables = new ArrayList<>();
        variables.put("files", fileMapVariables);
        if ("Y".equals(isSupportFile) && null != files) {
            for (CommonsMultipartFile file : files) {
                if (null == file) {
                    continue;
                }
                String fileName = file.getOriginalFilename();
                String name = fileName.substring(0, fileName.lastIndexOf(".")).trim();
                String fileSuffix = fileName.substring(fileName.lastIndexOf(".")).trim();
                String type = null;
                if (".jpeg".equalsIgnoreCase(fileSuffix) || ".jpg".equalsIgnoreCase(fileSuffix)) {
                    type = "image/jpeg";
                } else if ((".png".equalsIgnoreCase(fileSuffix))) {
                    type = "image/png";
                } else if ((".webp".equalsIgnoreCase(fileSuffix))) {
                    type = "image/webp";
                }
                Preconditions.checkArgument(null != type, "暂仅支持jpeg,png,webp格式的图片文件，请检查。");
                Map<String,Object> fileMap = new HashMap<>();
                fileMap.put("file_id", name);
                fileMap.put("url", "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(file.getBytes()));
                fileMap.put("content_type", "image");
                fileMapVariables.add(fileMap);
            }
        }

        AccRequestEntity<Map<String,Object>> accRequestEntityMap = new AccRequestEntity();
        accRequestEntityMap.setData(variables);


        /*
         * 返回结果两行为一组，event == chat_started 表示当前会话启动，输出本次对话id
         * Event == chunk 大模型正在流式输出，content为输出字段
         * Event == message 大模型输出完成，content为完整结果（之前所有chunk的信息）
         * Event == failed 当前会话异常，输出异常信息
         * Event == down 当前会话结束
         */
        WebClient webClient = WebClient.create(url);
        return webClient.post()
                .uri(url)
                .header("host", host)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(accRequestEntityMap)
                .retrieve()
                .bodyToFlux(Map.class)
                .map(each -> null != each && null != each.get("content") ? (String)each.get("content") : "");
    }
}
