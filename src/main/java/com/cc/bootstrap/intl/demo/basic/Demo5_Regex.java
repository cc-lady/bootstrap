package com.cc.bootstrap.intl.demo.basic;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 正则表达式
 * @author: ChenChen
 * @date: 2023/5/18 15:04
 */
public class Demo5_Regex {
    public static void main(String[] args) {
        /*正则表达式是一个强大的字符串处理工具，可以对字符串进行查找、提取、分割、替换等操作。 String类中也提供了如下几个特殊的方法。
➢boolean matches(String regex)：判断该字符串是否匹配指定的正则表达式。
➢String replaceAll(String regex, String replacement)：将该字符串中所有匹配regex的子串替换成replacement。
➢String replaceFirst(String regex, String replacement)：将该字符串中第一个匹配regex的子串替换成replacement。
➢String[] split(String regex)：以regex作为分隔符，把该字符串分割成多个子串。
        上面这些特殊的方法都依赖于Java提供的正则表达式支持。此外，Java还提供了Pattern和Matcher两个类专门用于提供正则表达式支持。*/
        //1.创建正则表达式
        /*前面已经介绍了，正则表达式就是一个用于匹配字符串的模板，其可以匹配一批字符串，所以创建正则表达式就是创建一个特殊的字符串。正则表达式所支持的合法字符如表7.1所示。
        表7.1 正则表达式所支持的合法字符
        x \t \n \r \f \c \cx

        此外，正则表达式中有一些特殊字符，这些特殊字符在正则表达式中有其特殊的用途，比如前面介绍的反斜线（\）。如果需要匹配这些特殊字符，
        就必须先将这些字符转义，也就是在其前面添加一个反斜线（\）。正则表达式中的特殊字符如表7.2所示。
        $ 匹配一行的结尾，要匹配本身，请使用\$
        ^ 匹配一行的结尾，要匹配本身，请使用\^
        () [] {}
        * 零次或多次
        + 一次或多次
        ? 零次或一次
        . 匹配除换行符\n外的所有单字符
        \ 用于转义下一个字符，或指定八进制，十六进制，匹配本身，请用\\
        | 用于在指定两项之间任选一项，要匹配本身，请使用\|

        "\u0041\\\\"  // 匹配A\
        "\u0061\t"  // 匹配a<制表符>
        "\\? \\["  // 匹配？[
        可能有读者会提出，第一个正则表达式中怎么有那么多反斜线啊？这是由于Java 字符串中的反斜线本身需要转义，因此两个反斜线（\\）实际上相当于一个（前一个用于转义）。

        面的正则表达式依然只能匹配单个字符，这是因为还未在正则表达式中使用“通配符”, “通配符”是可以匹配多个字符的特殊字符。
        正则表达式中的“通配符”远远超出了普通通配符的功能，它被称为预定义字符，正则表达式支持的预定义字符如表7.3所示。
        . 匹配任何字符
        \d 匹配0~9所有数字
        \D 匹配非数字
        \s 匹配所有空白字符：空格，制表符，回车符，换行符，换页符等
        \S 匹配所有非空白字符
        \w 匹配所有的单词字符，包括0~9所有数字、26个英文字母和下划线_
        \W 匹配所有非单词字符

        上面的7个预定义字符其实很容易记忆—d是digit的意思，代表数字；s是space的意思，代表空白；w是word的意思，代表单词。d、s、w的大写形式恰好匹配与之相反的字符。
        有了上面的预定义字符后，接下来就可以创建更强大的正则表达式了。例如：
        c\\wt  // 可以匹配cat、cbt、cct、c0t、c9t等一批字符串
\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d  // 匹配如000-000-0000形式的电话号码
        在一些特殊情况下，例如，若只想匹配a~f的字母，或者匹配除ab之外的所有小写字母，或者匹配中文字符，上面这些预定义字符就无能为力了，此时就需要使用方括号表达式，方括号表达式有如表7.4所示的几种形式。
        表示枚举      例如[abc]，表示a,b,c其中任意一个字符
        表示范围：-    例如[a-f]，表示a~f任意范围内的任意字符。范围可以和枚举结合使用，如[a-cx-z]表示a~c,x~z范围内的任意字符
        表示求否：^    例如[^abc]，表示非 a,b,c任意一个字符。
        表示“与”运算：&&  例如[a-z&&[def]],求a~z和[def]的交集，表示d,e或f; [a-z&&[^bc]]表示a~z范围内除b和c之外的所有字符,即[ad-z]
        表示“并”运算  与枚举类似，例如[a-d[m-p]]表示[a-dm-p]。
        方括号表达式比预定义字符灵活多了，几乎可以匹配任何字符。例如，若需要匹配所有的中文字符，就可以利用[\\u0041-\\u0056]形式—因为所有中文字符的
        Unicode值是连续的，只要找出所有中文字符中最小、最大的Unicode值，就可以利用上面的形式来匹配所有的中文字符。

        正则表示还支持圆括号表达式，用于将多个表达式组成一个子表达式，在圆括号中可以使用“或”运算符（|）。例如，正则表达式((public)|(protected)|(private))
        用于匹配Java的三个访问控制符其中之一。
        ^ 行的开头。
        $ 行的结尾。
        \b 单词的边界。
        \B 非单词的边界。
        \A 输入的开头。
        \G 前一个匹配的结尾。
        \Z 输入的结尾，仅用作最后的结束符。
        \z 输入的结尾。

        在前面的例子中，当需要建立一个匹配000-000-0000形式的电话号码时，使用了\\d\\d\\d-\\d\\d\\d-\\d\\d\\d\\d正则表达式，这看起来比较烦琐。
        实际上，正则表达式还提供了数量表示符，正则表达式支持的数量表示符有如下匹配模式。
        ➢Greedy（贪婪模式）：数量表示符默认采用贪婪模式，除非另有表示。贪婪模式的表达式会一直匹配下去，直到无法匹配为止。如果你发现表达式匹配的结果与预期的不符，
            则很有可能是因为—你以为表达式只会匹配前面几个字符，而实际上它是贪婪模式，所以会一直匹配下去。
        ➢Reluctant（勉强模式）：用问号（? ）后缀表示，它只会匹配最少的字符。这也被称为最小匹配模式。
        ➢Possessive（占有模式）：用加号（+）后缀表示，目前只有Java支持占有模式，通常比较少用。
        关于贪婪模式和勉强模式的对比，看如下代码：*/
        String str = "hello , java! ";
        // 贪婪模式的正则表达式
        System.out.println(str.replaceFirst("\\w*", "■"));      // 输出■ , java!
        // 勉强模式的正则表达式 - 0个字符
        System.out.println(str.replaceFirst("\\w*? ", "■"));     // 输出■hello , java!

        //2.使用正则表达式
        /*一旦在程序中定义了正则表达式，就可以通过Pattern和Matcher来使用正则表达式。
        Pattern 对象是正则表达式编译后在内存中的表示形式，因此，正则表达式字符串必须先被编译为Pattern 对象，然后再利用该Pattern 对象创建对应的Matcher 对象。
        执行匹配所涉及的状态被保留在Matcher对象中，多个Matcher对象可共享同一个Pattern对象。
        因此，典型的调用顺序如下：*/
        // 将一个字符串编译成Pattern对象
        Pattern p = Pattern.compile("a*b");
        // 使用Pattern对象创建Matcher对象
        Matcher m = p.matcher("aaaaab");
        boolean b = m.matches(); // 返回true
//        上面定义的Pattern 对象可以多次重复使用。如果某个正则表达式仅需要使用一次，则可直接使用Pattern 类的静态 matches()方法，此方法自动把指定的字符串编译成匿名的Pattern 对象，并执行匹配，如下所示。
        boolean b1 = Pattern.matches("a＊b", "aaaaab");  // 返回true

        /*上面的语句等效于前面的三条语句。但采用这种语句每次都需要重新编译新的Pattern 对象，不能重复利用已编译的Pattern对象，所以效率不高。
        Pattern是不可变类，可供多个并发线程安全使用。
        Matcher类提供了如下几个常用的方法。
        ➢find()：返回目标字符串中是否包含与Pattern匹配的子串。
        ➢group()：返回上一次与Pattern匹配的子串。
        ➢start()：返回上一次与Pattern匹配的子串在目标字符串中的开始位置。
        end()：返回上一次与Pattern匹配的子串在目标字符串中的结束位置加1。
        ➢lookingAt()：返回目标字符串前面的部分与Pattern是否匹配。
        ➢matches()：返回整个目标字符串与Pattern是否匹配。
        ➢reset()：将现有的Matcher对象应用于一个新的字符序列。

        通过Matcher类的find()和group()方法可以从目标字符串中依次取出特定的子串（匹配正则表达式的子串），例如互联网的网络爬虫，它们可以自动从网页中识别出所有的电话号码。
        下面的程序示范了如何从大段的字符串中找出电话号码。*/
        // 使用字符串模拟从网络上得到的网页源码
        String str1 = "我想求购一本《疯狂Java讲义》，尽快联系我13500006666" + "交朋友，电话号码是13611125565"
                + "出售二手电脑，联系方式15899903312";
        // 创建一个Pattern对象，并用它建立一个Matcher对象
        // 该正则表达式只抓取13X段和15X段的手机号
        // 实际要抓取哪些电话号码，只要修改正则表达式即可
        Matcher m1 = Pattern.compile("((13\\d)|(15\\d))\\d{8}")
                .matcher(str1);
        // 将所有符合正则表达式的子串（电话号码）全部输出
        while (m1.find()) {
            System.out.println(m1.group());
        }
//        13500006666
//        13611125565
//        15899903312

        /*find()方法还可以传入一个int类型的参数，带int类型参数的find()方法将从该int索引处向下搜索。
        start()和end()方法主要用于确定子串在目标字符串中的位置，如下面的程序所示。*/
        // 创建一个Pattern对象，并用它建立一个Matcher对象
        String regStr = "Java is very easy! ";
        System.out.println("目标字符串是：" + regStr);
        Matcher m2 = Pattern.compile("\\w+")
                .matcher(regStr);
        while (m2.find()) {
            System.out.println(m2.group() + "子串的起始位置："
                    + m2.start() + "，其结束位置：" + m2.end());
        }
//        目标字符串是：Java is very easy!
//                Java子串的起始位置：0，其结束位置：4
//        is子串的起始位置：5，其结束位置：7
//        very子串的起始位置：8，其结束位置：12
//        easy子串的起始位置：13，其结束位置：17

       /* matches()和lookingAt()方法有点相似，只是matches()方法要求整个字符串和Pattern完全匹配时才返回true，而lookingAt()
    只要字符串以Pattern开头就会返回true。reset()方法可将现有的Matcher对象应用于新的字符序列。看如下例子程序。*/
        String[] mails =
                {
                        "kongyeeku@163.com",
                        "kongyeeku@gmail.com",
                        "ligang@crazyit.org",
                        "wawa@abc.xx"
                };
        String mailRegEx = "\\w{3,20}@\\w+\\.(com|org|cn|net|gov)";
        Pattern mailPattern = Pattern.compile(mailRegEx);
        Matcher matcher = null;
        for (String mail : mails) {
            if (matcher == null) {
                matcher = mailPattern.matcher(mail);
            } else {
                matcher.reset(mail);
            }
            String result = mail + (matcher.matches() ? "是" : "不是")
                    + "一个有效的邮件地址！";
            System.out.println(result);
        }

//        事实上，String类中也提供了matches()方法，该方法返回该字符串是否匹配指定的正则表达式。例如：
        "kongyeeku@163.com".matches("\\w{3,20}@\\w+\\.(com|org|cn|net|gov)"); // 返回true

//        此外，还可以利用正则表达式对目标字符串进行分割、查找、替换等操作。看如下例子程序。
        String[] msgs =
                {
                        "Java has regular expressions in 1.4",
                        "regular expressions now expressing in Java",
                        "Java represses oracular expressions"
                };
        Pattern p11 = Pattern.compile("re\\w*");
        Matcher matcher11 = null;
        for (int i = 0; i < msgs.length; i++) {
            if (matcher11 == null) {
                matcher11 = p11.matcher(msgs[i]);
            } else {
                matcher11.reset(msgs[i]);
            }
            System.out.println(matcher11.replaceAll("哈哈：)"));
        }

        System.out.println("------------------------------");

        /*实际上，Matcher类还提供了一个replaceFirst()方法，该方法只替换第一个匹配的子串。运行上面的程序，会看到字符串中所有以“re”开头的单词都会被替换成“哈哈：)”。
        实际上，String类中也提供了replaceAll()、replaceFirst()、split()等方法。下面的例子程序直接使用String类提供的正则表达式功能来进行替换和分割。*/
        String[] msgs1 =
                {
                        "Java has regular expressions in 1.4",
                        "regular expressions now expressing in Java",
                        "Java represses oracular expressions"
                };
        for (String msg : msgs)
        {
            System.out.println(msg.replaceFirst("re\\w*", "哈哈：)"));
            System.out.println(Arrays.toString(msg.split(" ")));
        }
    }
}
