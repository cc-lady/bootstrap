package com.cc.bootstrap.intl.demo.basic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @Description: 系统相关类
 * @author: ChenChen
 * @date: 2023/5/17 16:40
 */
public class Demo2_System {
    public static void main(String[] args) throws IOException, InterruptedException {
        //Java 17增强的System类
        //System类代表当前Java程序的运行平台，程序不能创建System类的对象，System类提供了一些类变量和类方法，允许直接通过System类来调用这些类变量和类方法。
        //System 类提供了代表标准输入、标准输出和错误输出的类变量，并提供了一些静态方法用于访问环境变量、系统属性的方法，Java 17为System新增了一个native.encoding系统属性，用于获取操作系统的字符集。
        //System还提供了加载文件和动态链接库的方法。

        //下面的程序通过System类来访问操作的环境变量和系统属性。
        // 输出操作系统的字符集
        System.out.println(System.getProperty("native.encoding"));
        // 获取系统所有的环境变量
        Map<String, String> env = System.getenv();
        for (String name : env.keySet())
        {
            System.out.println(name + " ---> " + env.get(name));
        }
        // 获取指定环境变量的值
        System.out.println(System.getenv("JAVA_HOME"));
        // 获取所有的系统属性
        Properties props = System.getProperties();
        // 将所有的系统属性保存到props.txt文件中
        props.store(new FileOutputStream("props.txt"),
                "System Properties");
        // 输出特定的系统属性
        System.out.println(System.getProperty("os.name"));

/*        System 类还有两个获取系统当前时间的方法：currentTimeMillis()和nanoTime()，它们都返回一个long类型整数。实际上，
        它们都返回当前时间与UTC 1970年1月1日午夜的时间差，前者以毫秒作为单位，后者以纳秒作为单位。必须指出的是，
        这两个方法返回的时间粒度取决于底层操作系统—可能当前操作系统根本不支持以毫秒、纳秒作为计时单位。例如，许多操作系统都以几十毫秒为单位
    测量时间，currentTimeMillis()方法不可能返回精确的毫秒数；而nanoTime()方法很少用，因为大部分操作系统都不支持使用纳秒作为计时单位。*/


      /*  此外，System类的in、out和err分别代表系统的标准输入（通常是键盘）、标准输出（通常是显示器）和错误输出流，并提供了setIn()、
        setOut()和setErr()方法来改变系统的标准输入、标准输出和错误输出流。*/

/*        System类还提供了一个identityHashCode(Object x)方法，该方法返回指定对象的精确hashCode值，也就是根据该对象的地址计算得到的hashCode值。
        当某个类的hashCode()方法被重写后，该类实例的hashCode()方法就不能唯一地标识该对象；但通过identityHashCode()方法返回的hashCode 值，
        依然是根据该对象的地址计算得到的hashCode值。所以，如果两个对象的identityHashCode值相同，那么这两个对象绝对是同一个对象。
        因为identityHashCode 值是根据对象的地址计算得到的，所以任何两个对象的identityHashCode值总是不相同。*/
        // 下面程序中的s1和s2是两个不同的对象
        String s1 = new String("Hello");
        String s2 = new String("Hello");
        // String重写了hashCode()方法—改为根据字符序列计算hashCode值
        // 因为s1和s2的字符序列相同，所以它们的hashCode()方法的返回值相同
        System.out.println(s1.hashCode()
                + "----" + s2.hashCode());
// s1和s2是不同的字符串对象，所以它们的identityHashCode值不同
        System.out.println(System.identityHashCode(s1)
                + "----" + System.identityHashCode(s2));
        String s3 = "Java";
        String s4 = "Java";
        // s3和s4是相同的字符串对象，所以它们的identityHashCode值相同
        System.out.println(System.identityHashCode(s3)
                + "----" + System.identityHashCode(s4));

        //Runtime类与ProcessHandle
       /* Runtime类代表Java程序的运行时环境，每个Java程序都有一个与之对应的Runtime实例，应用程序通过该对象与其运行时环境相关联。
        应用程序不能创建自己的Runtime 实例，但可以通过getRuntime()方法获取与之关联的Runtime对象。
        与System类似的是，Runtime类也提供了gc()和runFinalization()方法来通知系统进行垃圾回收和清理系统资源，并提供了load(String filename)
    和loadLibrary(String libname)方法来加载文件和动态链接库。
        Runtime类代表Java程序的运行时环境，可以访问JVM的相关信息，如处理器数量、内存信息等。程序如下。*/
        // 获取与Java程序关联的运行时对象
        Runtime rt = Runtime.getRuntime();
        System.out.println("处理器数量："+ rt.availableProcessors());
        System.out.println("空闲内存数："+ rt.freeMemory());
        System.out.println("总内存数："+ rt.totalMemory());
        System.out.println("可用最大内存数："+ rt.maxMemory());

        //Runtime类还有一个功能—它可以直接单独启动一个进程来运行操作系统命令，如下面的程序所示。
        Runtime rt1 = Runtime.getRuntime();
        // 运行记事本程序
        rt1.exec("notepad.exe");

        /*通过exec启动平台上的命令之后，它就变成了一个进程，Java使用Process来代表进程。Java 9还新增了一个ProcessHandle 接口，
        通过该接口可以获取进程的ID、父进程和后代进程；通过该接口的onExit()方法可以在进程结束时完成某些行为。
        ProcessHandle还提供了一个ProcessHandle.Info类，用于获取进程的命令、参数、启动时间、累计运行时间、用户等信息。
        下面的程序示范了通过ProcessHandle获取进程相关信息。*/
/*        Runtime rt = Runtime.getRuntime();
        // 运行记事本程序
        Process p = rt.exec("notepad.exe");
        ProcessHandle ph = p.toHandle();  ----------------------- java9提供
        System.out.println("进程是否运行： " + ph.isAlive());
        System.out.println("进程ID: " + ph.pid());
        System.out.println("父进程： " + ph.parent());
        // 获取ProcessHandle.Info信息
        ProcessHandle.Info info = ph.info();
        // 通过ProcessHandle.Info信息获取进程相关信息
        System.out.println("进程命令： " + info.command());
        System.out.println("进程参数： " + info.arguments());
        System.out.println("进程启动时间： " + info.startInstant());
        System.out.println("进程累计运行时间： " + info.totalCputotalCpuDuration());
        // 通过CompletableFuture在进程结束时运行某个任务
        CompletableFuture<ProcessHandle> cf = ph.onExit();
        cf.thenRunAsync(()->{
            System.out.println("程序退出");
        });
        Thread.sleep(5000);*/

    }
}
