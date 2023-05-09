package com.cc.bootstrap.intl.demo.lambda.test_lambda;

/**
 * @Description: 测试
 * 现在你的代码中已经充溢着Lambda表达式，看起来不错，也很简洁。但是，大多数时候，
 * 我们受雇进行的程序开发工作的要求并不是编写优美的代码，而是编写正确的代码。
 * 通常而言，好的软件工程实践一定少不了单元测试，借此保证程序的行为与预期一致。你编
 * 写测试用例，通过这些测试用例确保你代码中的每个组成部分都实现预期的结果。
 * @author: ChenChen
 * @date: 2023/2/6 16:46
 */
public class Study1_Test {
    public static void main(String[] args) {
        //1.测试可见 Lambda 函数的行为
//        我们假设你在Point类中添加了静态字段
//        compareByXAndThenY，通过该字段，使用方法引用你可以访问Comparator对象：
//        public class Point{
//            public final static Comparator<Point> compareByXAndThenY =
//                    comparing(Point::getX).thenComparing(Point::getY);
// …
//        }
//        还记得吗，Lambda表达式会生成函数接口的一个实例。由此，你可以测试该实例的行为。
//        这个例子中，我们可以使用不同的参数，对Comparator对象类型实例compareByXAndThenY
//        的compare方法进行调用，验证它们的行为是否符合预期：
//        @Test
//        public void testComparingTwoPoints() throws Exception {
//            Point p1 = new Point(10, 15);
//            Point p2 = new Point(10, 20);
//            int result = Point.compareByXAndThenY.compare(p1 , p2);
//            assertEquals(-1, result);
//        }

        //2.测试使用 Lambda 的方法的行为
//        但是Lambda的初衷是将一部分逻辑封装起来给另一个方法使用。从这个角度出发，你不应
//        该将Lambda表达式声明为public，它们仅是具体的实现细节。相反，我们需要对使用Lambda表达
//        式的方法进行测试。比如下面这个方法moveAllPointsRightBy：
//        public static List<Point> moveAllPointsRightBy(List<Point> points, int x){
//            return points.stream()
//                    .map(p -> new Point(p.getX() + x, p.getY()))
//                    .collect(toList());
//        }
//        我们没必要对Lambda表达式p -> new Point(p.getX() + x,p.getY())进行测试，它
//        只是moveAllPointsRightBy内部的实现细节。我们更应该关注的是方法moveAllPointsRightBy的行为：
//        @Test
//        public void testMoveAllPointsRightBy() throws Exception{
//            List<Point> points =
//                    Arrays.asList(new Point(5, 5), new Point(10, 5));
//            List<Point> expectedPoints =
//                    Arrays.asList(new Point(15, 5), new Point(20, 5));
//            List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);
//            assertEquals(expectedPoints, newPoints);
//        }
//        注意，上面的单元测试中，Point类恰当地实现equals方法非常重要，否则该测试的结果
//        就取决于Object类的默认实现。

        //3.将复杂的 Lambda 表达式分到不同的方法
//        可能你会碰到非常复杂的Lambda表达式，包含大量的业务逻辑，比如需要处理复杂情况的
//        定价算法。你无法在测试程序中引用Lambda表达式，这种情况该如何处理呢？一种策略是将
//        Lambda表达式转换为方法引用（这时你往往需要声明一个新的常规方法），我们在8.1.3节详细讨
//        论过这种情况。这之后，你可以用常规的方式对新的方法进行测试。

        //4.高阶函数的测试
//        接受函数作为参数的方法或者返回一个函数的方法（所谓的“高阶函数”，higher-order
//        function，我们在第14章会深入展开介绍）更难测试。如果一个方法接受Lambda表达式作为参数，
//        你可以采用的一个方案是使用不同的Lambda表达式对它进行测试。比如，你可以使用不同的谓
//        词对第2章中创建的filter方法进行测试。
//        @Test
//        public void testFilter() throws Exception{
//            List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
//            List<Integer> even = filter(numbers, i -> i % 2 == 0);
//            List<Integer> smallerThanThree = filter(numbers, i -> i < 3);
//            assertEquals(Arrays.asList(2, 4), even);
//            assertEquals(Arrays.asList(1, 2), smallerThanThree);
//        }
//        如果被测试方法的返回值是另一个方法，该如何处理呢？你可以仿照我们之前处理
//        Comparator的方法，把它当成一个函数接口，对它的功能进行测试。
    }
}
