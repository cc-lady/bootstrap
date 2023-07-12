package com.cc.bootstrap.common.util;

import org.apache.commons.lang.StringUtils;
import org.bouncycastle.operator.RuntimeOperatorException;
import org.mozilla.universalchardet.UniversalDetector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import java.util.Set;

/**
 * @Description: 文件工具类
 * @author: ChenChen
 * @date: 2022/3/2 17:18
 */
public class FileUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

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
            }
        } catch (Exception e) {
            LOGGER.warn("探测文件编码格式失败，path {}", path, e);
        }
        return encoding;
    }
}
