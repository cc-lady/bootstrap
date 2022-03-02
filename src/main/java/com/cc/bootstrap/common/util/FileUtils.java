package com.cc.bootstrap.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

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
}
