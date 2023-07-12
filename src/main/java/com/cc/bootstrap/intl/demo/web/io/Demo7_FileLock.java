package com.cc.bootstrap.intl.demo.web.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @Description: 文件锁
 * @author: ChenChen
 * @date: 2023/5/17 14:35
 */
public class Demo7_FileLock {
    public static void main(String[] args) {
 /*       使用文件锁在操作系统中是很平常的事情，当多个运行的程序需要并发修改同一个文件时，程序之间需要某种机制来进行通信，使用文件锁可以有效地阻止多个进程并发修改同一个文件，所以现在的大部分操作系统都提供了文件锁的功能。
        文件锁控制对文件的全部或部分字节的访问，但文件锁在不同的操作系统中差别较大，所以早期的JDK版本并未提供文件锁支持。从JDK 1.4的NIO开始，Java开始提供文件锁支持。
        在NIO中，Java提供了FileLock来支持文件锁定功能。在FileChannel中提供的lock()/tryLock()方法可以获得文件锁FileLock对象，从而锁定文件。lock()和tryLock()方法存在区别：
        当lock()试图锁定某个文件时，如果无法得到文件锁，程序将一直被阻塞；而 tryLock()是尝试锁定文件，它将直接返回而不是被阻塞，如果获得了文件锁，该方法将返回该文件锁，否则将返回null。
        如果FileChannel只想锁定文件的部分内容，而不是锁定全部内容，则可以使用如下lock()或tryLock()方法。
➢lock(long position, long size, boolean shared)：对文件从position开始、长度为size的内容加锁，该方法是阻塞式的。
        tryLock(long position, long size, boolean shared)：这是非阻塞式的加锁方法，其参数的作用与上一个方法类似。
        直接使用lock()或tryLock()方法获得的文件锁是排他锁。

        当参数shared为true时，表明该锁是一个共享锁，它将允许多个进程读取该文件，但阻止其他进程获得对该文件的排他锁。当参数shared为false时，表明该锁是一个排他锁，
        它将锁定对该文件的读/写。程序可以通过调用FileLock的isShared()方法来判断它获得的锁是否为共享锁。

        在处理完文件后，通过FileLock的release()方法释放文件锁。下面的程序示范了使用FileLock锁定文件和释放文件锁。*/
        try (
                // 使用FileOutputStream获取FileChannel
            FileChannel channel = new FileOutputStream("a.txt").getChannel())
        {
            // 使用非阻塞式方式对指定的文件加锁
            FileLock lock = channel.tryLock();
            // 程序暂停10s
            Thread.sleep(10000);
            // 释放文件锁
            lock.release();
        } catch (FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

  /*      关于文件锁还需要指出如下几点。
➢在某些平台上，文件锁仅仅是建议性的，并不是强制性的。这意味着，即使一个程序不能获得文件锁，它也可以对该文件进行读/写。
➢在某些平台上，不能同步地锁定一个文件并把它映射到内存中。
➢文件锁是由Java虚拟机所持有的，如果两个Java程序使用同一台Java虚拟机运行，那么它们不能对同一个文件进行加锁。
➢在某些平台上关闭FileChannel时，会释放Java虚拟机在该文件上的所有锁，因此应该避免对同一个被锁定的文件打开多个FileChannel。*/
    }
}
