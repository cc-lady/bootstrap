package com.cc.bootstrap.intl.demo.file.upload;

import com.cc.bootstrap.common.base.restful.ResponseResult;
import com.cc.bootstrap.common.exception.FileException;
import com.cc.bootstrap.common.util.FileUtils;
import com.cc.bootstrap.intl.logic.FileInfoLogic;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @Description: 文件上传服务类
 * @author: ChenChen
 * @date: 2022/3/2 16:59
 */
@Component
public class FileUploadService {
    private static Logger LOGGER = LoggerFactory.getLogger(FileUploadService.class);

    @Value("${fileupload.path}")
    private String fileUploadPath;

    @Autowired
    private FileInfoLogic fileInfoLogic;


    /**
     * @Description 多个文件上传
     * @param userId
     * @param files
     * @author ChenChen
     * @return void
     * @date 2022/3/2 17:12
     */
    @Transactional
    public ResponseResult uploadFiles(String userId, MultipartFile[] files) {

        // 目录创建失败，不上传
        String dirPath = fileUploadPath + File.separator + userId;
        if(!FileUtils.mkdir(dirPath)) {
            throw new UnsupportedOperationException("为上传文件创建目录失败！");
        }

        String fileId = null;
        String fileName = null;
        String fileSize = null;
        Date uploadTime = new Date();
        String filePath = null;
        for (MultipartFile file: files) {
            fileId = UUID.randomUUID().toString();
            fileName = file.getOriginalFilename();
            float fSize = file.getSize() / 1024f;
            if(fSize < 1024) {
                fileSize = String.format("%.2f", fSize) + "KB";
            } else {
                fileSize = String.format("%.2f", fSize/1024) + "MB";
            }

            // 上传D:\\git\\github-repo\\bootstrap-github\\uploadFile\\cc\\f9086ca2-8f99-4466-b91f-43ad1500536d
            // 即不能直接将文件名放在上传路径中，以免文件名../../类似，发生文件路径泄漏安全问题。
            filePath = dirPath + File.separator + fileId;

            // 插入上传文件信息
            try {
                fileInfoLogic.insertUploadFileInfo(fileId, userId, fileName, fileSize, uploadTime);
            } catch (Exception e) {
                LOGGER.error("文件【{}】上传-插入上传文件信息失败！", fileName, e);
                continue;
            }

            // 上传文件
            try {
                Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                LOGGER.error("文件【{}】上传失败！", fileName, e);
                throw new FileException(500, "A006", "上传文件【"+fileName+"】发生异常，请检查！");
            }
        }

        return ResponseResult.success();
    }


//    下面以gitea上传文件为例，演示大文件分块上传
    private JSONPObject uploadFile(String apiUrl, String method, Map<String, Object> params,
                                   String giteaAuthorizationHeaderToken, String localPath) {
        LOGGER.info("开始上传文件{}", localPath);

        File file = org.apache.commons.io.FileUtils.getFile(localPath);
        String base = new Gson().toJson(params);
        byte[] basePrefix = base.substring(0, base.indexOf("content\":\"") + 10).getBytes(StandardCharsets.UTF_8);
        byte[] baseSuffix = base.substring(base.indexOf("content\":\"") + 10).getBytes(StandardCharsets.UTF_8);

        int chunkSize = 3145720;//分块大小

        URL url = null;
        HttpURLConnection httpURLConnection = null;
        try {
            url = new URL(apiUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(method);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            // 设置请求头
            httpURLConnection.setRequestProperty("Authorization", "token " + giteaAuthorizationHeaderToken);
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("fileName", file.getName());
            // 分块上传
            httpURLConnection.setRequestProperty("Transfer-Encoding", "chunked");
            httpURLConnection.setChunkedStreamingMode(0);//直接提交到服务器上

            // 分块写入请求体
            try (BufferedOutputStream boos = new BufferedOutputStream(httpURLConnection.getOutputStream());
                 BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                // 文件开始部分
                boos.write(basePrefix);
                LOGGER.info("开始核心部分文件上传{}", localPath);
                // 文件部分（此部分进行分段上传）
                byte[] buffer = new byte[chunkSize];
                // base64编码完长度会增加约1/3,2倍可以保证不溢出
                byte[] outBuffer = new byte[chunkSize * 2];
                Base64.Encoder encoder = Base64.getEncoder();
                int bytesRead = 0;
                int bytesB64 = 0;
                int count = 1;
                while ((bytesRead = bis.read(buffer)) != -1) {
                    // 最后一部分和分块大小不同
                    if (bytesRead != chunkSize) {
                        byte[] newBuffer = new byte[bytesRead];
                        for (int i = 0; i < bytesRead; i ++) {
                            newBuffer[i] = buffer[i];
                        }
                        buffer = newBuffer;
                    }
                    bytesB64 = encoder.encode(buffer, outBuffer);
                    boos.write(outBuffer, 0, bytesB64);

                    // 计数
                    if ((count++) % 1024 == 0) {
                        boos.flush();
                    }
                }
                LOGGER.info("核心部分文件上传{}结束", localPath);
                // 文件结尾部分
                boos.write(baseSuffix);
                boos.flush();

                int responseCode = httpURLConnection.getResponseCode();
                LOGGER.info("文件上传{}, responseCode", localPath, responseCode);
                if (responseCode == 200 || responseCode == 201) {
                    String responseContent = null;
                    // 读取响应
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
                        String inputLine = "";
                        StringBuilder content = new StringBuilder();
                        while ((inputLine = in.readLine()) != null) {
                            content.append(inputLine);
                        }
                        responseContent = content.toString();
                    }
                    LOGGER.info("文件分块上传{}成功", localPath);
                    return new ObjectMapper().readValue(responseContent, new TypeReference<JSONPObject>() {
                    });
                } else {
                    String errorContent = null;
                    // 读取响应
                    try (BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()))) {
                        String inputLine = "";
                        StringBuilder content = new StringBuilder();
                        while ((inputLine = in.readLine()) != null) {
                            content.append(inputLine);
                        }
                        errorContent = content.toString();
                    }
                    LOGGER.error("文件分块上传{}失败", localPath);
                    throw new FileException(500, "A00701", errorContent);
                }
            }

        } catch (Exception e) {
            LOGGER.error("文件分块上传{}异常", localPath, e);
            throw new FileException(500, "A007", e.getMessage());
        } finally {
            if (null != httpURLConnection) {
                httpURLConnection.disconnect();
            }
        }
    }
}
