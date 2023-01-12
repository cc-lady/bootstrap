package com.cc.bootstrap.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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
}
