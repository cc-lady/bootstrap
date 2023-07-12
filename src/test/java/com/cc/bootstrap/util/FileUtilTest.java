package com.cc.bootstrap.util;

import com.cc.bootstrap.common.util.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @Description: 文件工具方法测试
 * @author: ChenChen
 * @date: 2023/7/12 14:42
 */
public class FileUtilTest {
    private static Logger LOGGER = LoggerFactory.getLogger(FileUtilTest.class);

    /**
     * @Description 测试探测文件编码格式(com.googlecode.juniversalchardet)
     *
     * @author ChenChen
     * @return void
     * @date 2023/7/12 15:07
     */
    @Test
    public void getFileCharsetName_juniversalchardet_test() throws IOException {
        String projectDir = System.getProperty("user.dir");
        LOGGER.info("projectDir {}", projectDir);//D:\git\github-repo\bootstrap-github

        ClassLoader classLoader = this.getClass().getClassLoader();
        URL url = classLoader.getResource("cctest.txt");
//        //classLoader.getResource("cctest.txt");路径中若包含中文，需要进行解码
//        String currentPath = URLDecoder.decode(url.getPath(), "UTF-8").substring(1);
        String currentPath = url.getPath().substring(1);
        LOGGER.info("currentPath {}", currentPath);//D:/git/github-repo/bootstrap-github/target/test-classes/cctest.txt

        Path path = Paths.get(currentPath);
        if(Files.exists(path) && Files.isReadable(path)){
            String charsetName = FileUtils.getFileCharsetName(path, 5);
            LOGGER.info("path {} charsetName {}", path, charsetName);
            String content = Files.lines(path, Charset.forName(charsetName))
                    .limit(5)
                    .collect(Collectors.joining(System.lineSeparator()));
            LOGGER.info("path {} 正确编码解析后，前5行的内容为 {}", path, content);
        }
    }
}
