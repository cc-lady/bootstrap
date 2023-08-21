package com.cc.bootstrap.common.util;

import com.cc.bootstrap.common.exception.FileException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.operator.RuntimeOperatorException;
import org.mozilla.universalchardet.UniversalDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: 文件工具类
 * @author: ChenChen
 * @date: 2022/3/2 17:18
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "file.config")
public class FileUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    private String allowType;
    private Map<String,String> allowContentType;

    public void setAllowType(String allowType) {
        this.allowType = allowType;
    }

    public void setAllowContentType(Map<String, String> allowContentType) {
        this.allowContentType = allowContentType;
    }

    /**
     * @Description 创建文件夹
     * @param dirPath
     * @author ChenChen
     * @return boolean
     * @date 2022/3/2 17:19
     */
    public static boolean mkdir(String dirPath) {
        File dir = new File(dirPath);
        if(dir.isDirectory()) {
            return true;
        } else {
            LOGGER.info("新建目录：" + dir);
            return dir.mkdirs();
        }
    }

    //设置目录或文件为不可执行
    public static void setOnlyReadWriteNotExecute(Path path) throws IOException {
        PosixFileAttributeView posixFileAttributeView = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        if(null == posixFileAttributeView) {
            LOGGER.warn("PosixFileAttributeView is not supported.");
            return;
        }

        Set<PosixFilePermission> permissionSet = new HashSet<>();
        permissionSet.add(PosixFilePermission.OWNER_READ);
        permissionSet.add(PosixFilePermission.OWNER_WRITE);
        permissionSet.add(PosixFilePermission.GROUP_READ);
        permissionSet.add(PosixFilePermission.GROUP_WRITE);
        permissionSet.add(PosixFilePermission.OTHERS_READ);
        permissionSet.add(PosixFilePermission.OTHERS_WRITE);

        posixFileAttributeView.setPermissions(permissionSet);
    }

    /**
     * @Description 根据文件前多少行的内容，判断文件的编码格式
     * 可能探测结果为空，例如csv的ANSI，但是UTF-8可以检测出来，所以可以检测出来的直接返回，检测不出来默认ANSI，这里采用常用的GB18030
     * 使用依赖：com.googlecode.juniversalchardet
     * @param path
     * @param line
     * @author ChenChen
     * @return java.lang.String
     * @date 2023/7/12 14:27
     */
    public static String getFileCharsetName(Path path, int line) {
        File file = path.toFile();

        // 读取文件前N行大小
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            for (int i = 1; i <= line ; i++) {
                stringBuilder.append(bufferedReader.readLine());
            }
        } catch (Exception e) {
            LOGGER.warn("读取文件前{}行失败，path {}", line, path, e);
            throw new RuntimeOperatorException("读取文件前"+line+"行失败，请检查文件是否存在。");
        }
        String content = stringBuilder.toString();
        int length = content.length();
        LOGGER.info("path{} 前{}行 content如下{} 文件大小 {}", path, line, content, length);

        // 根据前五行大小读取字节流内容
        byte[] buf = new byte[length];
        try(BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file))) {
            bufferedInputStream.read(buf,0, buf.length);
        } catch (Exception e) {
            LOGGER.warn("读取文件流失败，path {}", path, e);
        }

        // 根据文件字节流内容探测文件编码格式
        String encoding = "GB18030";
        try{
            UniversalDetector detector = new UniversalDetector(null);
            detector.handleData(buf, 0, buf.length);
            detector.dataEnd();
            String detectorCharset = detector.getDetectedCharset();
            LOGGER.info("path {} detectorCharset {}", path, detectorCharset);
            if(StringUtils.isEmpty(detectorCharset)) {
                return encoding;
            // 探测csv文件仅UTF-8准确，所以其他全部返回默认GB18030
            } else if(!"UTF-8".equalsIgnoreCase(detectorCharset)) {
                return encoding;
            }
            return detectorCharset;
        } catch (Exception e) {
            LOGGER.warn("探测文件编码格式失败，path {}", path, e);
        }
        return encoding;
    }

    /**
     * @Description 校验文件类型和文件头类型
     * @param files
     * @author ChenChen
     * @return void
     * @date 2023/8/21 10:02
     */
    public boolean validateFileTypes(MultipartFile[] files) {
        if(files == null) {
            return true;
        }

        for (MultipartFile file : files) {
            if(file == null) {
                continue;
            }

            String fileName = file.getOriginalFilename();
            if(StringUtils.isEmpty(fileName)) {
                continue;
            }
            String fileType = fileName.substring(fileName.lastIndexOf(".")+1);
            validateFileType(fileType);

            //增加校验文件头
            String contentType = file.getContentType();
            validateFileContentType(fileName, contentType);
        }
        return true;
    }

    //校验文件头类型
    private boolean validateFileContentType(String fileName, String contentType) {
        log.info("校验文件头 fileName {} contentType {}", fileName, contentType);
        if(null == allowContentType || allowContentType.size() == 0 || StringUtils.isEmpty(contentType)) {
            return true;
        }

        boolean exits = allowContentType.values().stream()
                .filter(fileContentType -> fileContentType.contains(contentType))
                .findAny()
                .isPresent();
        if(!exits) {
            throw new FileException("不允许的文件头类型！当前校验文件名为：" + fileName + "，当前文件头为："+contentType
                    +"，允许的文件头类型如下："
                    + allowContentType.keySet().stream().map(key -> key + "=" + allowContentType.get(key))
                        .collect(Collectors.joining("、")));
        }
        return true;
    }

    //校验文件类型
    private boolean validateFileType(String fileType) {
        if(StringUtils.isEmpty(fileType)) {
            return true;
        }

        String[] fileTypeArr = allowType.split(",");
        for (int i = 0;i<fileTypeArr.length;i++) {
            if(fileType.equalsIgnoreCase(fileTypeArr[i])) {
                return true;
            }
        }
        throw new FileException("不允许的文件类型！允许的文件类型如下：" + allowType + "，请检查！");
    }
}
