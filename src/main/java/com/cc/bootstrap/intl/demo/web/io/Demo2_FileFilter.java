package com.cc.bootstrap.intl.demo.web.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermissions;
import java.nio.file.attribute.UserPrincipal;
import java.nio.file.attribute.UserPrincipalLookupService;

/**
 * @Description: 文件过滤器
 * @author: ChenChen
 * @date: 2023/5/15 16:51
 */
public class Demo2_FileFilter {
    public static void main(String[] args) {
        File file = new File("D:\\cc_study\\book\\Google_三大论文中文版.pdf");

//        String[] nameList = file.list((dir, name) -> name.startsWith("H"));
//        Arrays.stream(nameList).forEach(name -> System.out.println(name));

        String name = file.getName();
        String absolute = file.getAbsolutePath();
        String path = file.getPath();
        System.out.println("name="+name+";absolutePath="+absolute+";path="+path);
        //name=book;absolutePath=D:\cc_study\book;path=D:\cc_study\book
    }

    /**
     * @Description 为path设置读写权限，类似777
     * @param path
     * @param fileChmodNumber
     * @author ChenChen
     * @return void
     * @date 2024-06-13 14:34
     */
    private void setPermission(Path path, String fileChmodNumber) throws IOException {
        Files.setPosixFilePermissions(path, PosixFilePermissions.fromString(this.authNumberToRWXString(fileChmodNumber)));
    }

    /**
     * @Description 数字权限转为rwx格式，例如777转为rwxrwxrwx
     * @param fileChmodNumber
     * @author ChenChen
     * @return java.lang.String
     * @date 2024-06-13 14:34
     */
    private String authNumberToRWXString(String fileChmodNumber) {
        return fileChmodNumber.replace("7", "rwx").replace("6", "rw-")
                .replace("5", "r-x").replace("4", "r--")
                .replace("3", "-wx").replace("2", "-w-")
                .replace("1", "--x").replace("0", "---");
    }

    /**
     * @Description 设置用户属组
     * @param path
     * @param owner
     * @param group
     * @author ChenChen
     * @return void
     * @date 2024-06-13 14:38
     */
    private void setOwnerAndGroup(Path path, String owner, String group) throws IOException {
        UserPrincipalLookupService lookupService = FileSystems.getDefault().getUserPrincipalLookupService();

        UserPrincipal newOwner = lookupService.lookupPrincipalByName(owner);
        Files.setOwner(path, newOwner);

        GroupPrincipal newGroup = lookupService.lookupPrincipalByGroupName("users");
        Files.getFileAttributeView(path, PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS).setGroup(newGroup);
    }

}
