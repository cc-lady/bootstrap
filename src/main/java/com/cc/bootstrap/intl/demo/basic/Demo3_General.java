package com.cc.bootstrap.intl.demo.basic;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description: 常用类
 * @author: ChenChen
 * @date: 2023/5/17 17:13
 */
public class Demo3_General {

    // 定义一个obj变量，它的默认值是null
    static Demo3_General obj;


    public static void main(String[] args) {
        //1.Object类
        /*Object类是所有类、数组、枚举类的父类，也就是说，Java 允许把任何类型的对象赋给Object类型的变量。在定义一个类时，如果没有使用extends关键字为它显式指定父类，则该类默认继承Object父类。
        因为所有的Java类都是Object类的子类，所以任何Java对象都可以调用Object类的方法。Object类提供了如下几个常用方法。
➢boolean equals(Object obj)：判断指定对象与该对象是否相等。此处相等的标准是，两个对象是同一个对象，因此该equals()方法通常没有太大的实用价值。
➢protected void finalize()：当系统中没有引用变量引用到该对象时，垃圾回收器调用此方法来清理该对象的资源。
➢Class<? > getClass()：返回该对象的运行时类，该方法在本书第18章中还有更详细的介绍。
➢int hashCode()：返回该对象的hashCode值。在默认情况下，Object类的hashCode()方法根据该对象的地址来计算（即与System.identityHashCode(Object x)
    方法的计算结果相同）。但很多类都重写了Object类的hashCode()方法，不再根据地址来计算其hashCode()方法值。
    String toString()：返回该对象的字符串表示，当程序使用System.out.println()方法输出一个对象，或者把某个对象和字符串进行连接运算时，
    系统会自动调用该对象的toString()方法返回该对象的字符串表示。Object类的toString()方法返回“运行时类名@十六进制hashCode值”格式的字符串，
    但很多类都重写了Object类的toString()方法，用于返回可以表示该对象信息的字符串。
    Object类还提供了wait()、notify()、notifyAll()方法，通过这几个方法可以控制线程的暂停和运行。
    Java还提供了一个protected修饰的clone()方法，该方法用于帮助其他对象来实现“自我克隆”。所谓“自我克隆”就是得到一个当前对象的副本，而且二者之间完全隔离。
    由于Object类提供的clone()方法使用了protected修饰，因此该方法只能被子类重写或调用。*/

/*        自定义类实现“克隆”的步骤如下。
        自定义类实现Cloneable 接口。这是一个标记性的接口，实现该接口的对象可以实现“自我克隆”，接口里没有定义任何方法。
        自定义类实现自己的clone()方法。 通过调用super.clone()来实现clone()方法；调用Object实现的clone()方法得到该对象的副本，并返回该副本。如下程序示范了如何实现“自我克隆”。
        见CloneTest.java

        Object类的clone()方法虽然简单、易用，但它只是一种“浅克隆”—它只克隆该对象的所有成员变量值，不会对引用类型的成员变量值所引用的对象进行克隆。
        如果开发者需要对对象进行“深克隆”，则需要开发者自己进行“递归”克隆，保证所有引用类型的成员变量值所引用的对象都被复制了。
        */

        //2.操作对象的Objects工具类
        /*从Java 7开始引入的Objects工具类提供了一些工具方法来操作对象，这些工具方法大多是“空指针”安全的。比如你不能确定一个引用变量是否为null，
        如果贸然地调用该变量的toString()方法，则可能引发NullPointerExcetpion异常；但如果使用Objects类提供的toString(Object o)方法，
        就不会引发空指针异常，当o为null时，程序将返回一个"null"字符串。*/
        // 输出一个null对象的hashCode值，输出0
        System.out.println(Objects.hashCode(obj));
        // 输出一个null对象的toString，输出null
        System.out.println(Objects.toString(obj));
        // 要求obj不能为null，如果obj为null，则引发异常
        System.out.println(Objects.requireNonNull(obj, "obj参数不能是null! "));
        //Objects.requireNonNull 该方法主要用来对方法形参进行输入校验，例如如下代码：
        /*public Foo(Bar bar)
        {
            // 校验bar参数，如果bar参数为null，则将引发异常；否则，this.bar被赋值为bar参数
            this.bar = Objects.requireNonNull(bar);
        }*/

        //3.使用Optional操作可空值
        /*Optional相当于一个容器，它所盛装的对象可能为null，也可能不为null，它的主要作用就是结合Lambda表达式来更优雅地处理是否为null的判断。
        例如，以前要对某个可能为null的变量进行处理，可能要使用类似于如下的代码：
        类型 myVar = …; // myVar是一个可能为null的变量
        if (myVar ! = null) {
            // 接下来可调用myVar变量
        }
        与Optional结合后，则可使用类似于如下的代码：
        类型 myVar = …; // myVar是一个可能为null的变量
        Optional.ofNullable(myVar).ifPresent(s -> {
            // 接下来可调用myVar变量
        });*/
        //Optional还包含了不少专门用于处理null判断的方法，例如如下方法。
        test("fkjava");
        System.out.println("-------");
        test(null);

        //4.String、StringBuffer和StringBuilder类
        /*字符串就是一连串的字符序列，Java提供了String、StringBuffer和StringBuilder三个类来封装字符串，并提供了一系列方法来操作字符串对象。
        String类是不可变类，即一旦一个String对象被创建以后，包含在这个对象中的字符序列就是不可改变的，直至这个对象被销毁。
        StringBuffer 对象则代表一个字符序列可变的字符串，当一个StringBuffer 对象被创建以后，通过StringBuffer提供的append()、insert()、reverse()、setCharAt()、setLength()等方法
    可以改变这个字符串对象的字符序列。一旦通过StringBuffer生成了最终想要的字符串，就可以调用它的toString()方法将其转换为一个String对象。
        StringBuilder类是JDK 1.5新增的类，它也代表可变字符串对象。实际上，StringBuilder和StringBuffer基本相似，两个类的构造器和方法也基本相同。不同的
        是，StringBuffer是线程安全的，而StringBuilder则没有实现线程安全功能，所以性能略高。在通常情况下，如果需要创建一个内容可变的字符串对象，则应该优先考虑使用StringBuilder类。
        Java 9改进了字符串（包括String、StringBuffer、StringBuilder）的实现。在Java 9以前，字符串采用char[]数组来保存字符，因此字符串的每个字符占2字节；
        而Java 9及更新版本的JDK的字符串采用byte[]数组再加一个encoding-flag字段来保存字符，因此字符串的每个字符只占1字节。可见，Java 9及更新版本的JDK的字符串更加节省空间，
        但字符串的功能方法没有受到任何影响。
        String类提供了大量构造器来创建String对象，其中如下几个有特殊用途。
➢String()：创建一个包含0个字符串序列的String 对象（并不是返回null）。
➢String(byte[] bytes, Charset charset)：使用指定的字符集将指定的byte[]数组解码成一个新的String对象。
➢String(byte[] bytes, int offset, int length)：使用平台的默认字符集将指定的byte[]数组从offset开始、长度为length的子数组解码成一个新的String对象。
        String(byte[] bytes, int offset, int length, String charsetName)：使用指定的字符集将指定的byte[]数组从offset开始、长度为length的子数组解码成一个新的String对象。
➢String(byte[] bytes, String charsetName)：使用指定的字符集将指定的byte[]数组解码成一个新的String对象。
➢String(char[] value, int offset, int count)：将指定的字符数组从offset开始、长度为count的字符元素连缀成字符串。
➢String(String original)：根据字符串直接量来创建一个String 对象。也就是说，新创建的String对象是该参数字符串的副本。
➢String(StringBuffer buffer)：根据StringBuffer对象来创建对应的String对象。
➢String(StringBuilder builder)：根据StringBuilder对象来创建对应的String对象。
        String类也提供了大量方法来操作字符串对象，下面详细介绍常用方法。*/

        //char charAt(int index)：获取字符串中指定位置的字符。其中，index参数指的是字符串的序数，字符串的序数从0开始到length()-1。
        String s = "fkit.org";
        System.out.println("s.charAt(5): " + s.charAt(5));//s.charAt(5): o
        //int compareTo(String anotherString)：比较两个字符串的大小。如果两个字符串的字符序列相同，则返回0；否则，从两个字符串的第0个字符开始比较，返回第一个不相同的字符差。
        // 另一种情况是，较长字符串的前面部分恰好是较短的字符串，则返回它们的长度差。
        String s1 = "abcdefghijklmn";
        String s2 = "abcdefghij";
        String s3 = "abcdefghijalmn";
        System.out.println("s1.compareTo(s2): " + s1.compareTo(s2));      // 返回长度差 4
        System.out.println("s1.compareTo(s3): " + s1.compareTo(s3));      // 返回’k'-'a’的差 10
        /*String concat(String str)：将该String对象与str连接在一起。与Java提供的字符串连接运算符“+”的功能相同。
        ➢boolean contentEquals(StringBuffer sb)：将该String对象与StringBuffer对象sb进行比较，当它们包含的字符序列相同时返回true。
        ➢static String copyValueOf(char[] data)：将字符数组连缀成字符串，与String(char[] content)构造器的功能相同。
        static String copyValueOf(char[] data, int offset, int count)：将char[]数组的子数组中的元素连缀成字符串，
        与String(char[] value, int offset, int count)构造器的功能相同。*/

        //boolean endsWith(String suffix)：返回该String对象是否以suffix结尾。
        String s4 = "fkit.org"; String s5 = ".org";
        System.out.println("s4.endsWith(s5): " + s4.endsWith(s5));

        /*boolean equals(Object anObject)：将该字符串与指定对象进行比较，如果二者包含的字符序列相同，则返回true；否则返回false。
        ➢boolean equalsIgnoreCase(String str)：与前一个方法基本相似，只是忽略字符的大小写。
        ➢byte[] getBytes()：将该String对象转换成byte[]数组。*/
        //➢void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin)：该方法将字符串中从srcBegin开始，
        //到srcEnd结束的字符复制到dst字符数组中，其中dstBegin指定放入目标字符数组的起始位置。
        char[] s6 = {'I', ' ', 'l', 'o', 'v', 'e', ' ', 'j', 'a', 'v', 'a'}; // s6=I love java
        String s7 = "ejb";
        s7.getChars(0, 3, s6, 7);
        System.out.println(s6); // s6=I love ejba

//        int indexOf(int ch)：找出ch字符在该字符串中第一次出现的位置。
//        ➢int indexOf(int ch, int fromIndex)：找出ch字符在该字符串中从fromIndex开始后第一次出现的位置。
//        ➢int indexOf(String str)：找出str子字符串在该字符串中第一次出现的位置。
//        ➢int indexOf(String str, int fromIndex)：找出str子字符串在该字符串中从fromIndex开始后第一次出现的位置。
        String sa = "www.fkit.org";
        String ss = "it";
        System.out.println("sa.indexOf('r'): " + sa.indexOf('r'));//10
        System.out.println("sa.indexOf('r',2): " + sa.indexOf('r',2));//10
        System.out.println("sa.indexOf(ss): " + sa.indexOf(ss));//6

//        int lastIndexOf(int ch)：找出ch字符在该字符串中最后一次出现的位置。
//        ➢int lastIndexOf(int ch, int fromIndex)：找出ch字符在该字符串中从fromIndex开始后最后一次出现的位置。
//        ➢int lastIndexOf(String str)：找出str子字符串在该字符串中最后一次出现的位置。
//        ➢int lastIndexOf(String str, int fromIndex)：找出str子字符串在该字符串中从fromIndex开始后最后一次出现的位置。
//        ➢int length()：返回当前字符串长度。
//        ➢String replace(char oldChar, char newChar)：将字符串中的第一个oldChar替换成newChar。
//        ➢boolean startsWith(String prefix)：该String对象是否以prefix开始。
//        ➢boolean startsWith(String prefix, int toffset)：该String对象从toffset位置算起，是否以prefix开始。
        String sa1 = "www.fkit.org";
        String ss1 = "www";
        String sss = "fkit";
        System.out.println("sa1.startsWith(ss1): " + sa1.startsWith(ss1));//true
        System.out.println("sa1.startsWith(sss, 4): " + sa1.startsWith(sss, 4));//true

//        String substring(int beginIndex)：获取从beginIndex位置开始到结束的子字符串。
//        ➢String substring(int beginIndex, int endIndex)：获取从beginIndex位置开始到endIndex位置的子字符串。
//        ➢char[] toCharArray()：将该String对象转换成char[]数组。
//        ➢String toLowerCase()：将字符串转换成小写。
//        ➢String toUpperCase()：将字符串转换成大写。
        String st = "fkjava.org";
        System.out.println("st.toUpperCase(): " + st.toUpperCase());//FKJAVA.ORG
        System.out.println("st.toLowerCase(): " + st.toLowerCase());//fkjava.org
        //static String valueOf(X x)：一系列用于将基本类型值转换为String对象的方法。

        //String类是不可变的，String的实例一旦生成就不会再改变了。例如如下代码。
        String str1 = "java";
        str1 = str1 + "struts";
        str1 = str1 + "spring";
        /*上面的程序除了使用3个字符串直接量，还会额外生成2个字符串直接量—"java"和"struts"连接生成的"javastruts"，接着"javastruts"与"spring"连接生成的"javastrutsspring"，
        程序中的str1依次指向3个不同的字符串对象。
        因为String类是不可变的，所以会额外产生很多临时变量，使用StringBuffer或StringBuilder就可以避免这个问题。
        StringBuilder 提供了一系列插入、追加、改变该字符串中包含的字符序列的方法。而 StringBuffer与其用法完全相同，只是StringBuffer是线程安全的。
        StringBuilder、StringBuffer有两个属性：length和capacity，其中length属性表示其包含的字符序列的长度。与String对象的length不同的是，StringBuilder、
        StringBuffer的length是可以改变的，可以通过length()、setLength(int len)方法来访问和修改其字符序列的长度。capacity 属性表示StringBuilder的容量，
        capacity通常比length大，程序通常无须关心capacity属性。如下程序示范了StringBuilder类的用法。*/
        StringBuilder sb = new StringBuilder();
        // 追加字符串
        sb.append("java"); // sb = "java"
        // 插入
        sb.insert(0, "hello "); // sb="hello java"
        // 替换
        sb.replace(5, 6, ", "); // sb="hello, java"
        // 删除
        sb.delete(5, 6); // sb="hellojava"
        System.out.println(sb);
        // 反转
        sb.reverse(); // sb="avajolleh"
        System.out.println(sb);
        System.out.println(sb.length()); // 输出9
        System.out.println(sb.capacity()); // 输出16
        // 改变StringBuilder的长度，只保留前面部分
        sb.setLength(5); // sb="avajo"
        System.out.println(sb);

        //5.Math类
        /*Java提供了基本的+、-、*、/、%等算术运算符，但对于更复杂的数学运算，例如三角函数、对数运算、指数运算等则无能为力。
        Java提供了Math工具类来完成这些复杂的运算。Math类是一个工具类，它的构造器被定义成private的，因此无法创建Math类的对象；
        Math类中的所有方法都是类方法，可以直接通过类名来调用它们。Math类除了提供大量的静态方法，还提供了两个类变量：PI和E，正如它们的名字所暗示的，它们的值分别等于π和e。
        Math类的所有方法名都明确标识了该方法的作用，读者可自行查阅API来了解Math类各方法的说明。下面的程序示范了Math类的用法。*/
        /*---------下面是三角函数运算---------*/
        // 将弧度转换成角度
        System.out.println("Math.toDegrees(1.57):"
                + Math.toDegrees(1.57));
        // 将角度转换为弧度
        System.out.println("Math.toRadians(90):"
                + Math.toRadians(90));
        // 计算反余弦，返回的角度范围在0.0和pi之间
        System.out.println("Math.acos(1.2):" + Math.acos(1.2));
        // 计算反正弦，返回的角度范围在-pi/2和pi/2之间
        System.out.println("Math.asin(0.8):" + Math.asin(0.8));
        // 计算反正切，返回的角度范围在-pi/2和pi/2 之间
        System.out.println("Math.atan(2.3):" + Math.atan(2.3));
        // 计算三角余弦
        System.out.println("Math.cos(1.57):" + Math.cos(1.57));
        // 计算双曲余弦
        System.out.println("Math.cosh(1.2 ):" + Math.cosh(1.2));
        // 计算正弦
        System.out.println("Math.sin(1.57 ):" + Math.sin(1.57));
        // 计算双曲正弦
        System.out.println("Math.sinh(1.2 ):" + Math.sinh(1.2));
        // 计算三角正切
        System.out.println("Math.tan(0.8 ):" + Math.tan(0.8));
        // 计算双曲正切
        System.out.println("Math.tanh(2.1 ):" + Math.tanh(2.1));
        // 将矩形坐标 (x, y) 转换成极坐标 (r, thet))
        System.out.println("Math.atan2(0.1, 0.2):" + Math.atan2(0.1, 0.2));

        /*---------下面是取整运算---------*/
        // 取整，返回小于目标数的最大整数
        System.out.println("Math.floor(-1.2 ):" + Math.floor(-1.2 ));
        // 取整，返回大于目标数的最小整数
        System.out.println("Math.ceil(1.2):" + Math.ceil(1.2));
        // 四舍五入取整
        System.out.println("Math.round(2.3 ):" + Math.round(2.3 ));

        /*---------下面是乘方、开方、指数运算---------*/
        // 计算平方根
        System.out.println("Math.sqrt(2.3 ):" + Math.sqrt(2.3 ));
        // 计算立方根
        System.out.println("Math.cbrt(9):" + Math.cbrt(9));
        // 返回欧拉数 e 的n次幂
        System.out.println("Math.exp(2):" + Math.exp(2));
        // 返回sqrt(x2 +y2)，没有中间溢出或下溢
        System.out.println("Math.hypot(4, 4):" + Math.hypot(4, 4));
        // 按照 IEEE 754 标准的规定，对两个参数进行余数运算
        System.out.println("Math.IEEEremainder(5, 2):"+ Math.IEEEremainder(5, 2));
        // 计算乘方
        System.out.println("Math.pow(3, 2):" + Math.pow(3, 2));
        // 计算自然对数
        System.out.println("Math.log(12):" + Math.log(12));
        // 计算底数为10的对数
        System.out.println("Math.log10(9):" + Math.log10(9));
        // 返回参数与1之和的自然对数
        System.out.println("Math.log1p(9):" + Math.log1p(9));

        /*---------下面是符号相关运算---------*/
        // 计算绝对值
        System.out.println("Math.abs(-4.5):" + Math.abs(-4.5));
        // 符号赋值，返回带有第二个浮点数符号的第一个浮点参数
        System.out.println("Math.copySign(1.2, -1.0):"
                + Math.copySign(1.2, -1.0));
        // 符号函数，如果参数为0，则返回0；如果参数大于 0，则返回1.0；
        // 如果参数小于 0，则返回-1.0
        System.out.println("Math.signum(2.3):" + Math.signum(2.3));

        /*---------下面是大小相关运算---------*/
        // 计算最大值
        System.out.println("Math.max(2.3, 4.5):" + Math.max(2.3, 4.5));
        // 计算最小值
        System.out.println("Math.min(1.2, 3.4):" + Math.min(1.2, 3.4));
        // 返回第一个参数和第二个参数之间与第一个参数相邻的浮点数
        System.out.println("Math.nextAfter(1.2, 1.0):"
                + Math.nextAfter(1.2, 1.0));
        // 返回比目标数略大的浮点数
        System.out.println("Math.nextUp(1.2 ):" + Math.nextUp(1.2 ));
        // 返回一个伪随机数，该值大于或等于 0.0 且小于 1.0
        System.out.println("Math.random():" + Math.random());

        //6.ThreadLocalRandom与Random
        /*Random类专门用于生成一个伪随机数，它有两个构造器：一个构造器使用默认的种子（以当前时间作为种子），另一个构造器需要程序员显式传入一个long类型整数的种子。
        ThreadLocalRandom类是Java 7新增的一个类，它是Random的增强版。在并发访问的环境下，使用ThreadLocalRandom来代替Random可以减少多线程资源竞争，最终保证系统具有更好的线程安全性。
        ThreadLocalRandom类的用法与Random类的用法基本相似，它提供了一个静态的current()方法来获取ThreadLocalRandom对象，在获取该对象之后，即可调用各种nextXxx()方法来获取伪随机数了。
        ThreadLocalRandom与Random都比Math的random()方法提供了更多的方式来生成各种伪随机数，比如可以生成浮点类型的伪随机数，也可以生成整数类型的伪随机数，还可以指定生成随机数的范围。关于Random类的用法如下面的程序所示。*/
        Random rand = new Random();
        System.out.println("rand.nextBoolean():"
                + rand.nextBoolean());
        byte[] buffer = new byte[16];
        rand.nextBytes(buffer);
        System.out.println(Arrays.toString(buffer));
        // 生成0.0~1.0之间的伪随机double数
        System.out.println("rand.nextDouble():"
                + rand.nextDouble());
        // 生成0.0~1.0之间的伪随机float数
        System.out.println("rand.nextFloat():"
                + rand.nextFloat());
        // 生成平均值是0.0、标准差是1.0的伪高斯数
        System.out.println("rand.nextGaussian():"
                + rand.nextGaussian());
        // 生成一个处于int类型整数取值范围的伪随机整数
        System.out.println("rand.nextInt():" + rand.nextInt());
        // 生成0~26之间的伪随机整数
        System.out.println("rand.nextInt(26):" + rand.nextInt(26));
        // 生成一个处于long类型整数取值范围的伪随机整数
        System.out.println("rand.nextLong():" + rand.nextLong());
        /*从上面的程序中可以看出，Random可以提供很多选项来生成伪随机数。
        Random 使用一个48位的种子，如果这个类的两个实例是用同一个种子创建的，那么对它们以同样的顺序调用方法，它们会产生相同的数字序列。
        下面就对上面的介绍做一个实验，可以看到当两个Random对象的种子相同时，它们会产生相同的数字序列。值得指出的，当使用默认的种子构造Random对象时，它们属于同一个种子。*/
        Random r1 = new Random(50);
        System.out.println("第一个种子为50的Random对象");
        System.out.println("r1.nextBoolean():\t" + r1.nextBoolean());
        System.out.println("r1.nextInt():\t\t" + r1.nextInt());
        System.out.println("r1.nextDouble():\t" + r1.nextDouble());
        System.out.println("r1.nextGaussian():\t" + r1.nextGaussian());
        System.out.println("---------------------------");
        Random r2 = new Random(50);
        System.out.println("第二个种子为50的Random对象");
        System.out.println("r2.nextBoolean():\t" + r2.nextBoolean());
        System.out.println("r2.nextInt():\t\t" + r2.nextInt());
        System.out.println("r2.nextDouble():\t" + r2.nextDouble());
        System.out.println("r2.nextGaussian():\t" + r2.nextGaussian());
        System.out.println("---------------------------");
        Random r3 = new Random(100);
        System.out.println("种子为100的Random对象");
        System.out.println("r3.nextBoolean():\t" + r3.nextBoolean());
        System.out.println("r3.nextInt():\t\t" + r3.nextInt());
        System.out.println("r3.nextDouble():\t" + r3.nextDouble());
        System.out.println("r3.nextGaussian():\t" + r3.nextGaussian());
        /*第一个种子为50的Random对象
        r1.nextBoolean():      true
        r1.nextInt():  -1727040520
        r1.nextDouble():       0.6141579720626675
        r1.nextGaussian():     2.377650302287946
                ---------------------------
                第二个种子为50的Random对象
        r2.nextBoolean():      true
        r2.nextInt():  -1727040520
        r2.nextDouble():       0.6141579720626675
        r2.nextGaussian():     2.377650302287946
                ---------------------------
                种子为100的Random对象
        r3.nextBoolean():      true
        r3.nextInt():  -1139614796
        r3.nextDouble():       0.19497605734770518
        r3.nextGaussian():     0.6762208162903859*/
        /*只要两个Random对象的种子相同，而且方法的调用顺序也相同，它们就会产生相同的数字序列。也就是说，Random产生的数字并不是真正随机的，而是一种伪随机。
        为了避免两个Random对象产生相同的数字序列，通常推荐使用当前时间作为Random对象的种子，如下面的代码所示。*/
//        Random rand = new Random(System.currentTimeMillis());

//        在多线程环境下，使用ThreadLocalRandom的方式与使用Random基本类似，如下程序片段示范了ThreadLocalRandom的用法。
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        // 生成一个4~20之间的伪随机整数
        int val1 = threadLocalRandom.nextInt(4, 20);
        // 生成一个2.0~10.0之间的伪随机浮点数
        double val2 = threadLocalRandom.nextDouble(2.0, 10.0);

        //7.BigDecimal
//        前面在介绍float和double两种基本浮点类型时已经指出，这两种基本类型的浮点数容易引起精度丢失。先看如下程序。
        System.out.println("0.05 + 0.01 = " + (0.05 + 0.01));
        System.out.println("1.0 - 0.42 = " + (1.0 - 0.42));
        System.out.println("4.015 ＊ 100 = " + (4.015 * 100));
        System.out.println("123.3 / 100 = " + (123.3 / 100));
        //结果如下
//        0.05 + 0.01 = 0.060000000000000005
//        1.0 - 0.42 = 0.5800000000000001
//        4.015 ＊ 100 = 401.49999999999994
//        123.3 / 100 = 1.2329999999999999
        /*Java 的double 类型会发生精度丢失，尤其在进行算术运算时更容易发生这种情况。不仅是Java，很多编程语言也存在这样的问题（只要这门语言采用IEEE 754规则存储浮点数，就会存在这个问题）。
        为了能精确表示、计算浮点数，Java 提供了BigDecimal 类，该类提供了大量的构造器用于创建BigDecimal对象，包括把所有的基本数值型变量转换成BigDecimal对象，也包括利用数字字符串、数字字符数组来创建BigDecimal对象。
        当查看BigDecimal类的BigDecimal(double val)构造器的详细说明时，可以看到不推荐使用该构造器的说明，主要是因为使用该构造器有一定的不可预知性。当程序使用new BigDecimal(0.1)来创建一个BigDecimal对象时，它的值并不是0.1，它实际上等于一个近似0.1的数。这是因为0.1无法准确地表示为double浮点数，所以传入BigDecimal构造器的值不会正好等于0.1（虽然表面上等于该值）。
        如果使用BigDecimal(String val)构造器的结果是可预知的—写入new BigDecimal("0.1")将创建一个BigDecimal对象，它正好等于预期的0.1，则通常建议优先使用基于String的构造器。
        如果必须使用double浮点数作为BigDecimal构造器的参数，则不要直接将该double浮点数作为构造器参数创建BigDecimal 对象，而是应该通过BigDecimal.valueOf(double value)静态方法来创建BigDecimal对
        象。BigDecimal类提供了add()、subtract()、multiply()、divide()、pow()等方法对精确浮点数进行常规算术运算。下面的程序示范了BigDecimal的基本运算。*/
        BigDecimal f1 = new BigDecimal("0.05");
        BigDecimal f2 = BigDecimal.valueOf(0.01);
        BigDecimal f3 = new BigDecimal(0.05);
        System.out.println("使用String作为BigDecimal构造器参数：");
        System.out.println("0.05 + 0.01 = " + f1.add(f2));
        System.out.println("0.05 - 0.01 = " + f1.subtract(f2));
        System.out.println("0.05 ＊ 0.01 = " + f1.multiply(f2));
        System.out.println("0.05 / 0.01 = " + f1.divide(f2));
        System.out.println("使用double作为BigDecimal构造器参数：");
        System.out.println("0.05 + 0.01 = " + f3.add(f2));
        System.out.println("0.05 - 0.01 = " + f3.subtract(f2));
        System.out.println("0.05 ＊ 0.01 = " + f3.multiply(f2));
        System.out.println("0.05 / 0.01 = " + f3.divide(f2));

        /*使用String作为BigDecimal构造器参数：
        0.05 + 0.01 = 0.06
        0.05 - 0.01 = 0.04
        0.05 ＊ 0.01 = 0.0005
        0.05 / 0.01 = 5
        使用double作为BigDecimal构造器参数：
        0.05 + 0.01 = 0.06000000000000000277555756156289135105907917022705078125
        0.05 - 0.01 = 0.04000000000000000277555756156289135105907917022705078125
        0.05 ＊ 0.01 = 0.0005000000000000000277555756156289135105907917022705078125
        0.05 / 0.01 = 5.000000000000000277555756156289135105907917022705078125
        在创建BigDecimal 对象时，不要直接使用double 浮点数作为构造器参数来调用BigDecimal构造器，否则同样会发生精度丢失的问题。    */

       /* 在BigDecimal的使用过程中存在一个陷阱：如果要比较两个BigDecimal所代表的数值是否相等，不能用equals()方法进行比较，因为equals()
    判断相等的规则不仅要求具有相等的数值，而且要求具有相同的小数位数。这意味着代表2的BigDecimal不等于代表2.0的BigDecimal。
        如果仅想比较两个BigDecimal的数值是否相等，则应该使用compareTo()方法，当两个BigDecimal的数值相等时，compareTo()方法会返回0*/
        BigDecimal f11 = new BigDecimal("2.0");
        BigDecimal f21 = new BigDecimal("2");
        System.out.println(f11.equals(f21)); // 输出false
        BigDecimal f31 = new BigDecimal("0.00");
        System.out.println(f31.equals(BigDecimal.ZERO)); // 输出false
        System.out.println(f11.compareTo(f21));  // 输出0
        System.out.println(f31.compareTo(BigDecimal.ZERO));  // 输出0



    }

    public static void test(String st) {
//        Optional<String> op = Optional.ofNullable(st);
//        // 只有当被包装的变量不为null时才执行Lambda表达式
//        op.ifPresent(s -> System.out.println(s.length()));
//        // 当被包装的变量不为null时，执行第1个Lambda表达式
//        // 否则执行第2个Lambda表达式
//        op.ifPresentOrElse(s -> System.out.println(s.length()),
//                () -> System.out.println("为空"));
//        // 如果被包装的变量不为null，则返回被包装的变量；否则返回默认值
//        System.out.println(op.orElse("默认值"));
//        // 如果被包装的变量不为null，则返回true
//        System.out.println(op.isPresent());
//        // 如果被包装的变量为null，则返回true
//        System.out.println(op.isEmpty());

    }
}
