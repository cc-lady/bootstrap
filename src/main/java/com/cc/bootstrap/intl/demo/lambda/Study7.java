package com.cc.bootstrap.intl.demo.lambda;

/**
 * ֪测试，调试和重构
 * 本章主要探讨如何在代码中使用 Lambda 表达式的技术，也会说明什么情况下不应该（直 接）使用 Lambda 表达式。
 * 本章还讲述了如何调试大量使用 Lambda 表达式和流的程序。
 */
public class Study7 {
    public static void main(String[] args) {
        // ֪7.1 重构候选项 这里有一些要点，可以帮助读者确定什么时候应该 Lambda 化自己的应用或类库。
        // 其中的 每一条都可看作一个局部的反模式或代码异味，借助于 Lambda 化可以修复

        // 7.1.1 进进出出、摇摇晃晃
        /**
         * 如果你发现自己的代码不断地查询和操作某对象，目的只为了 在最后给该对象设个值，那么这段代码就本该属于你所操作的对象。
         */
        // 7.1.2 孤独的覆盖 这个代码异味是使用继承，其目的只是为了覆盖一个方法。
       // 7.1.3 同样的东西写两遍
        /**
         * 什么时候该将 WET 的代码 Lambda 化？这里有一个信号可以参考。
         * 如果有一个整体 上大概相似的模式，只是行为上有所不同，就可以试着加入一个 Lambda 表达式。
         */

        // 7.2 Lambda表达式的单元测试
        //Lambda 表达式给单元测试带来了一些麻烦，Lambda 表达式没有名字，无法直接在测试代 码中调用

        /**解决该问题有两种方式。
         * 一、第一种是将 Lambda 表达式放入一个方法测试，这种方式要测那 个方法，而不是 Lambda 表达式本身。
         * 例如：
         * 例 7-8 将字符串转换为大写形式
         * public static List<String> allToUpperCase(List<String> words) {
         *  return words.stream() .map(string -> string.toUpperCase()) .collect(Collectors.<String>toList());
         * }
         * 如果换我来测试这段代码，我会将重点放在方法的行为上。
         * 例 7-9 测试大写转换 @Test
         * public void multipleWordsToUppercase() {
         *   List<String> input = Arrays.asList("a", "b", "hello");
         *   List<String> result = Testing.allToUpperCase(input);
         *   assertEquals(asList("A", "B", "HELLO"), result);
         * }
         *
         * 有时候 Lambda 表达式实现了复杂的功能，它可能包含多个边界情况、使用了多个属性来 计算一个非常重要的值。你非常想测试该段代码的行为，
         * 但它是一个 Lambda 表达式，无 法引用。
         *二、别用 Lambda 表达式。
         * 例 7-10 将列表中元素的第一个字母转换成大写
         * public static List<String> elementFirstToUpperCaseLambdas(List<String> words) {
         *   return words.stream() .map(value -> {
         *    char firstChar = Character.toUpperCase(value.charAt(0));
         *    return firstChar + value.substring(1);
         *   }).collect(Collectors.<String>toList());
         * }
         * 我知道，在一本介绍如何使用 Lambda 表达式的书里，这个建议 有点奇怪，但是方楔子钉不进圆孔。既然如此，大家一定会问如何测试代码，
         * 同时享有 Lambda 表达式带来的便利？
         * 请用方法引用。任何 Lambda 表达式都能被改写为普通方法，然后使用方法引用直接引用。
         * 例 7-12 将 Lambda 表达式重构为一个方法，然后在主程序中使用，主程序负责转换字符 串。例 7-12 将首字母转换为大写，应用到所有列表元素
         * public static List<String> elementFirstToUppercase(List<String> words) {
         *   return words.stream() .map(Testing::firstToUppercase) .collect(Collectors.<String>toList());
         * }
         * public static String firstToUppercase(String value) {
         *   char firstChar = Character.toUpperCase(value.charAt(0));
         *   return firstChar + value.substring(1);
         * }
         * 把处理字符串的的逻辑抽取成一个方法后，就可以测试该方法，把所有的边界情况都覆盖 到。新的测试用例如例 7-13 所示。
         * 例 7-13 测试单独的方法 @Test
         * public void twoLetterStringConvertedToUppercase() {
         *   String input = "ab";
         *   String result = Testing.firstToUppercase(input);
         *   assertEquals("Ab", result);
         * }
         */

        // 7.3 在测试替身时使用Lambda表达式
        /**
         * 测试代码时，使用 Lambda 表达式的最简单方式是实现轻量级的测试存根。如果交互的类 本身就是一个函数接口，实现这样的存根就非常简单和自然。
         * 例 7-14 使用 Lambda 表达式编写测试替身，传给 countFeature 方法 @Test
         * public void canCountFeatures() {
         *   OrderDomain order = new OrderDomain(
         *     asList(
         *       newAlbum("Exile on Main St."),
         *       newAlbum("Beggars Banquet"),
         *       newAlbum("Aftermath"),
         *       newAlbum("Let it Bleed")
         *     ));
         *   assertEquals(8, order.countFeature(album -> 2));
         * }
         *
         */

        // 7.4 惰性求值和调试
        //在 Java 8 中，你仍然可以使用 IDE 提供的各种调试工具，但有时需要调整 实现方式，以期达到更好的结果。

        // 7.5 日志和打印消息
        /**
         * 现在可以使用 forEach 方法打印出流中的值，这同时会触发求值过程。但是这样的操作有 个缺点：我们无法再继续操作流了，流只能使用一次。
         * 如果我们还想继续，必须重新创建 流。例 7-17 展示了这样的代码会有多难看。
         * 例 7-17 使用 forEach 记录中间值，这种方式有点幼稚
         * album.getMusicians() .filter(artist -> artist.getName().startsWith("The"))
         * .map(artist -> artist.getNationality())
         * .forEach(nationality -> System.out.println("Found: " + nationality));
         *
         * Set<String> nationalities = album.getMusicians() .filter(artist -> artist.getName().startsWith("The"))
         * .map(artist -> artist.getNationality()) .collect(Collectors.<String>toSet());
         *
         * 解决方案：peak
         * 遗憾的是，流有一个方法让你能查看每个值，同时能继续操作流。这就是 peek 方法。
         * 例 7-18 使用 peek 方法重写了前面的例子，输出流中的值，同时避免了重复的流操作。
         * 例 7-18 使用 peek 方法记录中间值
         * Set<String> nationalities = album.getMusicians()
         *   .filter(artist -> artist.getName().startsWith("The"))
         *   .map(artist -> artist.getNationality())
         *   .peek(nation -> System.out.println("Found nationality: " + nation))
         *   .collect(Collectors.<String>toSet());
         * 使用 peek 方法还能以同样的方式，将输出定向到现有的日志系统中，比如 log4j、java. util.logging 或者 slf4j。
         *
         * 7.7 在流中间设置断点 记录日志这是 peek 方法的用途之一。为了像调试循环那样一步一步跟踪，
         * 可在 peek 方法 中加入断点，这样就能逐个调试流中的元素了。 此时，peek 方法可知包含一个空的方法体，
         * 只要能设置断点就行。有一些调试器不允许在 空的方法体中设置断点，此时，我将值简单地映射为其本身，这样就有地方设置断点了，
         * 虽然这样做不够完美，但只要能工作就行。
         */
    }
}
