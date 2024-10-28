package com.cc.bootstrap.intl.demo.web.io;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyListener;

/**
 * @Description:  使用WatchService监听文件的变化 -- 不支持nfs，需改为使用JNotify库。
 * JNotify是一个Java库，它封装了Windows和Linux的文件通知API，允许Java程序监听文件系统的变化。
 * @author: ChenChen
 * @date: 2024-06-20 14:42
 */
public class Demo9_Jnotify {
        public static void main(String[] args) {
            try {
                String path = "/nfs/shared/directory"; // 要监听的NFS目录路径
                JNotify.addWatch(path, JNotify.FILE_ANY, true, new MyJNotifyListener());

                // 这里可以加入你的程序逻辑，比如一个循环等待文件变化
                // 你可以在MyJNotifyListener中处理文件变化事件
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static class MyJNotifyListener implements JNotifyListener {
//            @Override
//            public void onEvent(int i, String s, Object o) {
//                // 处理文件变化事件
//                switch (i) {
//                    case JNotify.FILE_CREATED:
//                        System.out.println("File created: " + s);
//                        break;
//                    case JNotify.FILE_DELETED:
//                        System.out.println("File deleted: " + s);
//                        break;
//                    case JNotify.FILE_MODIFIED:
//                        System.out.println("File modified: " + s);
//                        break;
//                    case JNotify.FILE_RENAMED:
//                        System.out.println("File renamed: " + s);
//                        break;
//                }
//            }

            @Override
            public void fileCreated(int i, String s, String s1) {
                System.out.println("File created: " + s);
            }

            @Override
            public void fileDeleted(int i, String s, String s1) {
                System.out.println("File deleted: " + s);
            }

            @Override
            public void fileModified(int i, String s, String s1) {
                System.out.println("File modified: " + s);
            }

            @Override
            public void fileRenamed(int i, String s, String s1, String s2) {
                System.out.println("File renamed: " + s);
            }
        }

        //=======================下面是官网的案例====================
    public void sample() throws Exception {
        // path to watch
        String path = System.getProperty("user.home");

        // watch mask, specify events you care about,
        // or JNotify.FILE_ANY for all events.
        int mask = JNotify.FILE_CREATED  |
                JNotify.FILE_DELETED  |
                JNotify.FILE_MODIFIED |
                JNotify.FILE_RENAMED;

        // watch subtree?
        boolean watchSubtree = true;

        // add actual watch
        int watchID = JNotify.addWatch(path, mask, watchSubtree, new Listener());

        // sleep a little, the application will exit if you
        // don't (watching is asynchronous), depending on your
        // application, this may not be required
        Thread.sleep(1000000);

        // to remove watch the watch
        boolean res = JNotify.removeWatch(watchID);
        if (!res) {
            // invalid watch ID specified.
        }
    }
    class Listener implements JNotifyListener {
        public void fileRenamed(int wd, String rootPath, String oldName,
                                String newName) {
            print("renamed " + rootPath + " : " + oldName + " -> " + newName);
        }
        public void fileModified(int wd, String rootPath, String name) {
            print("modified " + rootPath + " : " + name);
        }
        public void fileDeleted(int wd, String rootPath, String name) {
            print("deleted " + rootPath + " : " + name);
        }
        public void fileCreated(int wd, String rootPath, String name) {
            print("created " + rootPath + " : " + name);
        }
        void print(String msg) {
            System.err.println(msg);
        }
    }
}
