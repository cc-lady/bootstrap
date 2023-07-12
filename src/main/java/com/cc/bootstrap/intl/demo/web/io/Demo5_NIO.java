package com.cc.bootstrap.intl.demo.web.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: NIO
 * @author: ChenChen
 * @date: 2023/5/16 14:39
 */
public class Demo5_NIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        /*
            一、Path、Paths和Files核心API
            早期的Java只提供了一个File类来访问文件系统，但File类的功能比较有限，它不能利用特定文件系统的特性，其所提供的方法性能也不高。
            而且，File类的大多数方法在出错时仅返回失败，并不会提供异常信息。
            NIO.2为了弥补这种不足，引入了一个Path 接口，Path 接口代表一个与平台无关的平台路径。此外，NIO.2还提供了Files和Paths两个工具类，
            其中Files包含了大量的静态工具方法来操作文件；Paths则包含了两个返回Path的静态工厂方法。
         */
        // 以当前路径来创建Path对象
        Path path = Paths.get(".");
        System.out.println("path里包含的路径数量："+ path.getNameCount());
        //读者可能会对getNameCount()方法感到有点困惑，此处简要说明一下：
        // 它会返回 Path 路径所包含的路径名的数量，例如 g:\publish\codes，调用该方法就会返回3。
        System.out.println("path的根路径：" + path.getRoot());
        // 获取path对应的绝对路径
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        // 获取绝对路径的根路径
        System.out.println("absolutePath的根路径："+ absolutePath.getRoot());
        // 获取绝对路径所包含的路径数量
        System.out.println("absolutePath里包含的路径数量："+ absolutePath.getNameCount());
        System.out.println(absolutePath.getName(3));
        // 以多个String来构建Path对象
        Path path2 = Paths.get("g:", "publish", "codes");
        System.out.println(path2);


//        Files是一个操作文件的工具类，它提供了大量便捷的工具方法。下面的程序简单示范了Files类的用法。
        // 复制文件
        Files.copy(Paths.get("FilesTest.java"), new FileOutputStream("a.txt"));
        // 判断FilesTest.java文件是否为隐藏文件
        System.out.println("FilesTest.java是否为隐藏文件："+ Files.isHidden(Paths.get("FilesTest.java")));
        // 一次性读取FilesTest.java文件的所有行
        List<String> lines = Files.readAllLines(Paths.get("FilesTest.java"), Charset.forName("gbk"));
        System.out.println(lines);
        // 判断指定文件的大小
        System.out.println("FilesTest.java的大小为："+ Files.size(Paths.get("FilesTest.java")));
        List<String> poem = new ArrayList<>();
        poem.add("水晶潭底银鱼跃");
        poem.add("清徐风中碧竿横");
        // 直接将多个字符串内容写入指定的文件中
        Files.write(Paths.get("pome.txt"), poem, Charset.forName("gbk"));


        // 一次性读取FilesTest.java文件的所有行
        List<String> lines1 = Files.readAllLines(Paths
                .get("C:\\Users\\chenc\\Desktop\\linux_hadoop.txt"), Charset.forName("utf-8"));
        lines1.stream().forEach(line -> System.out.println(line));


        // 使用Java 8新增的Stream API读取文件内容
        Files.lines(Paths.get("C:\\Users\\chenc\\Desktop\\linux_hadoop.txt"), Charset.forName("utf-8"))
                .forEach(line -> System.out.println(line));    // 使用了Stream API来读取文件内容。


        FileStore cStore = Files.getFileStore(Paths.get("C:"));
        // 判断C盘的总空间、可用空间
        System.out.println("C：共有空间：" + cStore.getTotalSpace());
        System.out.println("C：可用空间：" + cStore.getUsableSpace());




        // 使用FileVisitor遍历文件和目录
        /*
        在以前的 Java 版本中，如果程序要遍历指定目录下的所有文件和子目录，则只能使用递归进行遍历。但这种方式不仅复杂，而且灵活性也不高。
现在有了Files工具类的帮助，可以用更优雅的方式来遍历文件和子目录了。Files类提供了如下两个方法来遍历文件和子目录。
➢walkFileTree(Path start, FileVisitor<? super Path> visitor)：遍历start路径下的所有文件和子目录。
➢walkFileTree(Path start, Set<FileVisitOption> options, int maxDepth, FileVisitor<? super Path>visitor)：该方法的功能与上一个方法的功能类似。该方法最多遍历maxDepth深度的文件。
上面两个方法都需要FileVisitor参数，FileVisitor代表一个文件访问器，walkFileTree()方法会自动遍历 start 路径下的所有文件和子目录，遍历文件和子目录都会“触发”FileVisitor 中相应的方法。FileVisitor中定义了如下4个方法。
➢FileVisitResult postVisitDirectory(T dir, IOException exc)：在访问子目录之后触发该方法。
➢FileVisitResult preVisitDirectory(T dir, BasicFileAttributes attrs)：在访问子目录之前触发该方法。
➢FileVisitResult visitFile(T file, BasicFileAttributes attrs)：在访问file文件时触发该方法。
➢FileVisitResult visitFileFailed(T file, IOException exc)：当访问file文件失败时触发该方法。
上面4个方法都返回一个 FileVisitResult 对象，它是一个枚举类，代表了访问之后的后续行为。FileVisitReFileVisitResult定义了如下几种后续行为。
➢CONTINUE：代表“继续访问”的后续行为。
➢SKIP_SIBLINGS：代表“继续访问”的后续行为，但不访问该文件或目录的兄弟文件或目录。
➢SKIP_SUBTREE：代表“继续访问”的后续行为，但不访问该文件或目录的子目录树。
➢TERMINATE：代表“中止访问”的后续行为。
在实际编程时没必要为 FileVisitor 的4个方法都提供实现，可以通过继承 SimpleFileVisitor （FileVisitor 的实现类）来实现自己的“文件访问器”，这样就可以根据需要额选择性地重写指定的方法了。
如下程序示范了使用FileVisitor来遍历文件和子目录。
         */
        Files.walkFileTree(Paths.get("D:", "cc_study", "book"), new SimpleFileVisitor<Path>(){
                // 在访问文件时触发该方法
                @Override
                public FileVisitResult visitFile(Path file,
                                                 BasicFileAttributes attrs) throws IOException
                {
                    System.out.println("正在访问" + file + "文件");
                    // 找到了FileVisitorTest.java文件
                    if (file.endsWith(".pdf"))
                    {
                        System.out.println("--已经找到目标文件: .pdf文件 --");
                        return FileVisitResult.TERMINATE;
                    }
                    return FileVisitResult.CONTINUE;
                }
                // 当开始访问目录时触发该方法
                @Override
                public FileVisitResult preVisitDirectory(Path dir,
                                                         BasicFileAttributes attrs) throws IOException
                {
                    System.out.println("正在访问：" + dir + " 路径");
                    return FileVisitResult.CONTINUE;
                }

            });



        // 使用WatchService监听文件的变化
        /**
         在以前的 Java 版本中，如果程序需要监听文件的变化，则可以考虑启动一条后台线程，这条后台线程每隔一段时间去“遍历”一次指定目录下的文件；如果发现此次遍历的结果与上次遍历的结果不同，则认为文件发生了变化。但这种方式不仅十分烦琐，而且性能也不好。
         NIO.2的Path类提供了如下方法来监听文件的变化。
         ➢register(WatchService watcher, WatchEvent.Kind<? >... events)：用watcher监听该path代表的目录下文件的变化。events参数指定要监听哪些类型的事件。
         在这个方法中，WatchService代表一个文件系统监听服务，它负责监听path代表的目录下文件的变化。一旦使用register()方法完成了注册，接下来就可调用WatchService的如下三个方法来获取被监听目录的文件变化事件。
         ➢WatchKey poll()：获取下一个WatchKey，如果没有WatchKey发生，就立即返回null。
         ➢WatchKey poll(long timeout, TimeUnit unit)：尝试等待 timeout时间去获取下一个WatchKey。
         ➢WatchKey take()：获取下一个WatchKey，如果没有WatchKey发生，就一直等待。
         如果程序需要一直监听，则应该选择使用take()方法；如果程序只需要监听指定的时间，则可考虑使用poll()方法。下面的程序示范了使用WatchService来监听C：盘根路径下文件的变化。
         */
        // 获取文件系统的WatchService对象
        WatchService watchService = FileSystems.getDefault().newWatchService();
        // 为C：盘根路径注册监听
        Paths.get("C:/").register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);
        while (true) {
            // 获取下一个文件变化事件
            WatchKey key = watchService.take();   // ①试图获取下一个WatchKey，如果没有发生就等待。因此，C：盘根路径下每次文件的变化都会被该程序监听到。
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println(event.context() + " 文件发生了 "
                        + event.kind() + "事件！");
            }
            // 重设WatchKey
            boolean valid = key.reset();
            // 如果重设失败，则退出监听
            if (!valid) {
                break;
            }
        }


        // 访问文件属性
        /*
        早期的Java提供的File类可以访问一些简单的文件属性，比如文件大小、文件的修改时间、文件是否隐藏、是文件还是目录等。如果程序需要获取或修改更多的文件属性，
        则必须利用运行所在平台的特定代码来实现，这是一件非常困难的事情。
        Java 7的NIO.2在java.nio.file.attribute包下提供了大量的工具类，通过这些工具类，开发者可以非常简单地读取、修改文件属性。这些工具类主要分为如下两类。
➢XxxAttributeView：代表某种文件属性的“视图”。
➢XxxAttributes：代表某种文件属性的“集合”，程序一般通过 XxxAttributeView 对象来获取XxxAttributes。
在这些工具类中，FileAttributeView 是其他 XxxAttributeView 的父接口。下面简单介绍一下这些XxxAttributeView。
➢AclFileAttributeView：
    通过 AclFileAttributeView，开发者可以为特定文件设置 ACL（Access Control List）及文件所有者属性。
它的getAcl()方法返回List<AclEntry>对象，该返回值代表了该文件的权限集。通过setAcl(List)方法可以修改该文件
的ACL。
➢BasicFileAttributeView：
    通过它可以获取或修改文件的基本属性，包括文件的最后修改时间、文件的最后访问时间、文件的创建时间、文件大小、是否为目录、是否为符号链接等。
    它的readAttributes()方法返回一个 BasicFileAttributes 对象，对文件夹基本属性的修改是通过BasicFileAttributes对象完成的。
➢DosFileAttributeView：
    它主要用于获取或修改文件DOS相关属性，比如文件是否只读、文件是否隐藏、是否为系统文件、是否为存档文件等。
    它的readAttributes()方法返回一个DosFileAttributes对象，对这些属性的修改其实是由DosFileAttributes对象来完成的。
➢FileOwnerAttributeView：
    它主要用于获取或修改文件的所有者。它的 getOwner()方法返回一个UserPrincipal对象来代表文件的所有者；
    也可调用setOwner(UserPrincipal owner)方法来改变文件的所有者。
➢PosixFileAttributeView：
    它主要用于获取或修改POSIX（Portable Operating System Interface of INIX）属性。它的readAttributes()方法返回一个PosixFileAttributes对象，
    该对象可用于获取或修改文件的所有者、组所有者、访问权限信息（就是 UNIX 的 chmod 命令负责干的事情）。这个工具类只在UNIX、Linux等系统上有用。
➢UserDefinedFileAttributeView：
    它可以让开发者为文件设置一些自定义属性。*/
        // 获取将要操作的文件
        Path testPath = Paths.get("C:\\Users\\chenc\\Desktop\\linux_hadoop.txt");
        // 获取访问基本属性的BasicFileAttributeView
        BasicFileAttributeView basicView = Files.getFileAttributeView(testPath, BasicFileAttributeView.class);
        // 获取访问基本属性的BasicFileAttributes
        BasicFileAttributes basicAttribs = basicView.readAttributes();
        // 访问文件的基本属性
        System.out.println("创建时间：" + new Date(basicAttribs.creationTime().toMillis()));
        System.out.println("最后访问时间：" + new Date(basicAttribs.lastAccessTime().toMillis()));
        System.out.println("最后修改时间：" + new Date(basicAttribs.lastModifiedTime().toMillis()));
        System.out.println("文件大小：" + basicAttribs.size());
        // 获取访问文件所有者信息的UserDefinedFileAttributeView
        FileOwnerAttributeView ownerView = Files.getFileAttributeView(testPath, FileOwnerAttributeView.class);
        // 获取该文件所属的用户
        System.out.println(ownerView.getOwner());
        // 获取系统中guest对应的用户
        UserPrincipal user = FileSystems.getDefault()
                .getUserPrincipalLookupService()
                .lookupPrincipalByName("guest");
        // 修改用户
        ownerView.setOwner(user);
        // 获取访问自定义属性的FileOwnerAttributeView
        UserDefinedFileAttributeView userView = Files.getFileAttributeView(testPath, UserDefinedFileAttributeView.class);
        List<String> attrNames = userView.list();
        // 遍历所有的自定义属性
        for (String name : attrNames)
        {
            ByteBuffer buf = ByteBuffer.allocate(userView.size(name));
            userView.read(name, buf);
            buf.flip();
            String value = Charset.defaultCharset().decode(buf).toString();
            System.out.println(name + "--->" + value);
        }
        // 添加一个自定义属性
        userView.write("发行者", Charset.defaultCharset().encode("疯狂Java联盟"));
        // 获取访问DOS属性的DosFileAttributeView
        DosFileAttributeView dosView = Files.getFileAttributeView(testPath, DosFileAttributeView.class);
        // 将文件设置为隐藏、只读
        dosView.setHidden(true);
        dosView.setReadOnly(true);

/*
        前面在介绍BufferedReader时提到它的一个特征——当BufferedReader读取输入流中的数据时，如果没有读取到有效数据，程序将在此处阻塞该线程的执行
    （使用 InputStream 的 read()方法从流中读取数据时，如果数据源中没有数据，它也会阻塞该线程），也就是前面介绍的输入流和输出流都是阻塞式的输入和输出。
        不仅如此，传统的输入流和输出流都是通过字节的移动来处理的（即使不直接去处理字节流，底层的实现也是依赖于字节处理的）。也就是说，
        面向流的输入/输出系统一次只能处理一个字节，因此面向流的输入/输出系统通常效率不高。
        从JDK 1.4开始，Java提供了一系列改进的输入/输出处理的新功能，这些新功能被统称为新IO（New IO，简称NIO）。其新增了许多用于处理输入/输出的类，
        这些类都被放在java.nio包及其子包下，并且对原java.io包中的很多类都以NIO为基础进行了改写，新增了满足NIO的功能。
        15.9.1 Java NIO概述
        NIO和传统的IO有相同的目的，都用于进行输入/输出，但NIO使用了不同的方式来处理输入/输出——NIO 采用内存映射文件的方式来处理输入/输出，
        NIO 将文件或文件的一段区域映射到内存中，这样就可以像访问内存一样来访问文件了（这种方式模拟了操作系统上的虚拟内存的概念），通过这种方式来进行输入/输出比传统
        的输入/输出要快得多。
Java中与NIO相关的包如下。
➢java.nio包：主要包含各种与Buffer相关的类。
➢java.nio.channels包：主要包含与Channel和Selector相关的类。
➢java.nio.charset包：主要包含与字符集相关的类。
➢java.nio.channels.spi包：主要包含与Channel相关的服务提供者编程接口。
➢java.nio.charset.spi包：包含与字符集相关的服务提供者编程接口。
Channel（通道）和Buffer（缓冲）是NIO中的两个核心对象。Channel是对传统的输入/输出系统的模拟，在NIO系统中所有的数据都需要通过通道传输；Channel与传统的InputStream、OutputStream最大的区别在于，
它提供了一个map()方法，通过该map()方法可以直接将“一块数据”映射到内存中。如果说传统的输入/输出系统是面向流的处理，那么NIO就是面向块的处理。
Buffer可以被理解成一个容器，它的本质是一个数组，发送到Channel中的所有对象都必须先放到Buffer中，而从Channel中读取的数据也必须先放到Buffer中。此处的Buffer有点类似于前面介绍的“竹筒”，
但该Buffer既可以像“竹筒”那样一次次去Channel中取“水”，也允许使用Channel直接将文件的某块数据映射成Buffer。
除Channel和Buffer之外，NIO还提供了用于将Unicode字符串映射成字节序列以及逆映射操作的Charset类，也提供了用于支持非阻塞式输入/输出的Selector类。

*/

        // 使用Buffer
        /*
        从内部结构来看，Buffer 就像一个数组，它可以保存多个类型相同的数据。Buffer 是一个抽象类，其最常用的子类是ByteBuffer，它可以在底层的字节数组上进行get/set操作。
        除ByteBuffer之外，对应于其他基本数据类型（boolean除外）都有相应的Buffer类：CharBuffer、ShortBuffer、IntBuffer、LongBuffer、FloatBuffer、DoubleBuffer。
上面这些Buffer类，除ByteBuffer之外，它们都采用相同或相似的方法来管理数据，只是各自管理的数据类型不同而已。这些Buffer类都没有提供构造器，通过使用如下方法来得到一个Buffer对象。
➢static XxxBuffer allocate(int capacity)：创建一个容量为capacity的XxxBuffer对象。
但实际使用较多的是ByteBuffer和CharBuffer，其他Buffer类则较少用到。其中ByteBuffer类还有一个子类：MappedByteBuffer，它用于表示Channel将磁盘文件的部分或全部内容映射到内存中后得到的结果，
通常MappedByteBuffer对象由Channel的map()方法返回。
在Buffer中有三个重要的概念：容量、界限和位置。
➢容量（capacity）：缓冲区的容量表示该 Buffer 的最大数据容量，即最多可以存储多少数据。缓冲区的容量不可能为负值，其创建后不能改变。
➢界限（limit）：第一个不应该被读出或者写入的缓冲区位置索引。也就是说，位于 limit 后的数据既不可被读，也不可被写。
➢位置（position）：用于指明下一个可以被读出或者写入的缓冲区位置索引（类似于IO流中的记录指针）。当使用Buffer从Channel中读取数据时，position的值恰好等于已经读取了多少个数据。
当刚刚新建一个Buffer对象时，其position为0；如果从Channel中读取了2个数据到该Buffer中，则position为2，指向Buffer中第3个位置（第1个位置的索引为0）。
此外，Buffer还支持一个可选的标记（mark，类似于传统IO流中的mark）, Buffer允许直接将position定位到该mark处。它们满足如下关系：
0≤mark≤position≤limit≤capacity
图15.15 Buffer读入一些数据后的示意图
图15.15显示了某个Buffer读入一些数据后的示意图。
Buffer 的主要作用就是装入数据，然后输出数据（其类似于前面介绍的取水的“竹筒”）。开始时Buffer的position为0, limit为capacity，
程序可通过put()方法向Buffer中放入一些数据（或者从Channel中获取一些数据），每放入一些数据，Buffer的position都相应地向后移动一些位置。
当Buffer装入数据结束后，调用Buffer的flip()方法，该方法将limit设置为position所在的位置，并将position设为0，这就使得Buffer的读/写指针又移到了开始位置。
也就是说，Buffer调用flip()方法之后，Buffer为输出数据做好了准备；当Buffer输出数据结束后，Buffer调用clear()方法，clear()方法不是清空Buffer的数据，
它仅仅将position置为0，将limit置为capacity，这样就为再次向 Buffer中装入数据做好了准备。

Buffer中包含两个重要的方法，即flip()和clear()，其中flip()为从Buffer中取出数据做好准备，clear()则为再次向Buffer中装入数据做好准备。
        int capacity()：返回Buffer的容量大小。
➢boolean hasRemaining()：判断当前位置和界限之间是否还有元素可供处理。
➢int limit()：返回Buffer的界限的位置。
➢Buffer limit(int newLt)：重新设置limit的值，并返回一个具有新的界限的缓冲区对象。
➢Buffer mark()：设置Buffer的mark位置，它只能在0和position之间做标记。
➢int position()：返回Buffer中position的值。
➢Buffer position(int newPs)：设置Buffer的position，并返回position被修改后的Buffer对象。
➢int remaining()：返回当前位置和界限之间的元素个数。
        Buffer reset()：将position转到mark所在的位置。
➢Buffer rewind()：将position设置成0，取消设置的mark。
        除这些移动position、limit、mark的方法之外，Buffer的所有子类还提供了两个重要的方法：put()和get()方法，用于向Buffer中放入数据和从Buffer中取出数据。当使用put()和get()方法放入、取出数据时，Buffer既支持对单个数据的访问，也支持对批量数据的访问（以数组作为参数）。
        当使用put()和get()方法访问Buffer中的数据时，分为相对和绝对两种方式。
➢相对（Relative）：从Buffer的当前position处开始读取或写入数据，然后将position的值按处理元素的个数增加。
➢绝对（Absolute）：直接根据索引向Buffer中读取或写入数据。使用绝对方式访问Buffer中的数据时，并不会影响position的值。*/
        // 创建Buffer
        CharBuffer buff = CharBuffer.allocate(8);   // ①通过CharBuffer对象的静态方法allocate()创建了一个capacity为8的CharBuffer，此时该Buffer的limit和capacity均为8, position为0
        System.out.println("capacity: "   + buff.capacity());
        System.out.println("limit: " + buff.limit());
        System.out.println("position: " + buff.position());
        // 放入元素
        buff.put('a');
        buff.put('b');
        buff.put('c');     // ②
        System.out.println("加入三个元素后，position = "+ buff.position());
        // 调用flip()方法
        buff.flip();     // ③该方法将把 limit 设为 position，把 position设为0，这样就相当于把Buffer中没有数据的存储空间“封印”起来，从而避免在读取Buffer数据时读到null值。
        System.out.println("执行flip()后，limit = " + buff.limit());
        System.out.println("position = " + buff.position());
        // 取出第一个元素
        System.out.println("第一个元素(position=0):" + buff.get());   // ④取出一个元素后position向后移动一位，也就是该Buffer的position等于1
        System.out.println("取出一个元素后，position = "+ buff.position());
        // 调用clear()方法
        buff.clear();    // ⑤clear()方法将position设为0，将limit设为与capacity相同。对Buffer执行clear()方法后，该Buffer对象中的数据依然存在
        System.out.println("执行clear()后，limit = " + buff.limit());
        System.out.println("执行clear()后，position = "
                + buff.position());
        System.out.println("执行clear()后，缓冲区内容并没有被清除："+ "第三个元素为：" + buff.get(2));   // ⑥
        System.out.println("执行绝对读取后，position = "+ buff.position());

/*        通过allocate()方法创建的Buffer对象是普通Buffer, ByteBuffer还提供了一个allocateDirect()方法来创建直接Buffer。直接Buffer的创建成本比普通Buffer的创建成本高，但直接Buffer的读取效率更高。
        由于直接Buffer的创建成本很高，所以直接Buffer只适合做长生存期的Buffer，而不适合做短生存期、一次用完就丢弃的Buffer。而且只有ByteBuffer才提供了allocateDirect()方法，所以只能在ByteBuffer级别上创建直接Buffer。如果希望使用其他类型，则应该将该Buffer转换成其他类型的Buffer。
        */


        // 使用Channel
        /*Channel类似于传统的流对象，但与传统的流对象有两个主要区别。Channel可以直接将指定文件的部分或全部直接映射成Buffer。
➢程序不能直接访问Channel中的数据，包括读取、写入都不行，Channel只能与Buffer进行交互。也就是说，如果要从Channel中取得数据，
        必须先用Buffer从Channel中取出一些数据，然后再让程序从Buffer中取出这些数据；如果要将程序中的数据写入Channel中，
        一样先让程序将数据放入Buffer中，然后再将Buffer中的数据写入Channel中。

        所有的Channel都不应该通过构造器来直接创建，而是要通过传统的节点流InputStream、OutputStream的getChannel()方法来返回对应的Channel，不同的节点流获得的Channel不一样。例如，
        FileInputStream、FileOutputStream的 getChannel()方法返回的是 FileChannel, PipedInputStream和 PipedOutputStream的getChannel()方法返回的是Pipe.SinkChannel、
        Pipe.SourceChannel。

        Channel中最常用的三类方法是map()、read()和write()，其中map()方法用于将Channel对应的部分或全部数据映射成ByteBuffer；
        而read()和write()方法都有一系列重载形式，这些方法用于从Buffer中读取数据或向Buffer中写入数据。
        map()方法的方法签名为：MappedByteBuffer map(FileChannel.MapMode mode, long position, long size)，
        其中第一个参数是执行映射时的模式，分别有只读、读/写等模式；第二个和第三个参数用于控制将Channel的哪些数据映射成ByteBuffer。*/
        File f = new File("C:\\Users\\chenc\\Desktop\\linux_hadoop.txt");
        try (
                //虽然FileChannel既可以读，也可以写，但FileInputStream获取的FileChannel只能读，而FileOutputStream获取的FileChannel只能写。
                // 创建FileInputStream，以该文件输入流创建FileChannel
                FileChannel inChannel = new FileInputStream(f).getChannel();
                // 以文件输出流创建FileChannel，用于控制输出
                FileChannel outChannel = new FileOutputStream("a.txt").getChannel())
        {
            // 将FileChannel中的全部数据映射成ByteBuffer
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, f.length());   // ①
            // 使用GBK字符集来创建解码器
            Charset charset = Charset.forName("GBK");
            // 直接将buffer中的数据全部输出
            outChannel.write(buffer);    // ②将整个ByteBuffer中的数据全部写入一个输出FileChannel中，这样就完成了文件的复制。
            // 再次调用buffer的clear()方法，复原limit、position的位置
            buffer.clear();
            // 创建解码器（CharsetDecoder）对象
            CharsetDecoder decoder = charset.newDecoder();
            // 使用解码器将ByteBuffer转换成CharBuffer
            CharBuffer charBuffer = decoder.decode(buffer);
            // 使用CharBuffer的toString方法可以获取对应的字符串
            System.out.println(charBuffer);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        /*不仅InputStream、OutputStream中包含了getChannel()方法，而且RandomAccessFile中也包含了一个getChannel()方法，
        RandomAccessFile返回的FileChannel是只读的还是读/写的，则取决于RandomAccessFile打开文件的模式。例如，下面的程序将会对a.txt文件的内容进行复制，并追加在该文件的后面。
        每次运行下面的程序，都会把a.txt文件的内容复制一份，并将全部内容追加到该文件的后面。*/
        File file = new File("C:\\Users\\chenc\\Desktop\\linux_hadoop.txt");
        try (
                // 创建一个RandomAccessFile对象
                RandomAccessFile raf = new RandomAccessFile(f, "rw");
                // 获取RandomAccessFile对应的Channel
                FileChannel randomChannel = raf.getChannel())
        {
            // 将Channel中的所有数据映射成ByteBuffer
            ByteBuffer buffer = randomChannel.map(FileChannel
                    .MapMode.READ_ONLY, 0, f.length());
            // 把Channel的记录指针移动到最后
            randomChannel.position(f.length());
            // 将buffer中的所有数据输出
            randomChannel.write(buffer);//以将Channel的记录指针移动到该Channel的最后，从而让程序将指定ByteBuffer的数据追加到该Channel的后面。
        }


/*        如果读者习惯了传统IO的“用竹筒多次重复取水”的过程，或者担心Channel对应的文件过大，使用map()方法一次将所有的文件内容映射到内存中引起性能下降，
        则也可以采用Channel和Buffer传统的“用竹筒多次重复取水”的方式。程序如下。
        程序虽然使用FileChannel和Buffer来读取文件，但其处理方式和使用InputStream、byte[]来读取文件的方式几乎一样，都是采用“用竹筒多次重复取水”的方式。
        但因为Buffer提供了flip()和clear()两个方法，所以程序处理起来比较方便，每次读取数据后都调用flip()方法将没有数据的区域“封印”起来，避免程序从Buffer中取出null值；
        数据被取出后立即调用clear()方法将Buffer的position设0，为下一次读取数据做好准备。*/
        try (
                // 创建文件输入流
                FileInputStream fis = new FileInputStream("ReadFile.java");
                // 创建一个FileChannel
                FileChannel fcin = fis.getChannel())
        {
            // 定义一个ByteBuffer对象，用于重复取水
            ByteBuffer bbuff = ByteBuffer.allocate(1024);
            // 将FileChannel中的数据放入ByteBuffer中
            while (fcin.read(bbuff)!= -1)
            {
                // 锁定Buffer的空白区
                bbuff.flip();
                // 创建Charset对象
                Charset charset = Charset.forName("GBK");
                // 创建解码器（CharsetDecoder）对象
                CharsetDecoder decoder = charset.newDecoder();
                // 将ByteBuffer的内容转码
                CharBuffer cbuff = decoder.decode(bbuff);
                System.out.print(cbuff);
                // 将Buffer初始化，为下一次读取数据做好准备
                bbuff.clear();
            }
        }

    }
}
