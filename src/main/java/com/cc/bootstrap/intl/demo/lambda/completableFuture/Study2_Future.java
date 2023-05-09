package com.cc.bootstrap.intl.demo.lambda.completableFuture;

/**
 * @Description: Future接口
 * @author: ChenChen
 * @date: 2023/2/7 14:50
 */
public class Study2_Future {
    public static void main(String[] args) {
//        Future接口在Java 5中被引入，设计初衷是对将来某个时刻会发生的结果进行建模。它建模
//        了一种异步计算，返回一个执行运算结果的引用，当运算结束后，这个引用被返回给调用方。在
//        Future中触发那些潜在耗时的操作把调用线程解放出来，让它能继续执行其他有价值的工作，
//        不再需要呆呆等待耗时的操作完成。打个比方，你可以把它想象成这样的场景：你拿了一袋子衣
//        服到你中意的干洗店去洗。干洗店的员工会给你张发票，告诉你什么时候你的衣服会洗好（这就
//        是一个Future事件）。衣服干洗的同时，你可以去做其他的事情。Future的另一个优点是它比
//        更底层的Thread更易用。要使用Future，通常你只需要将耗时的操作封装在一个Callable对
//        象中，再将它提交给ExecutorService，就万事大吉了。下面这段代码展示了Java 8之前使用
//        Future的一个例子。

        //创建ExecutorService，通过它你可以向线程池提交任务
//        ExecutorService executor = Executors.newCachedThreadPool();
        //向ExecutorService提交一个Callable对象
//        Future<Double> future = executor.submit(new Callable<Double>() {
//            public Double call() {
//                return doSomeLongComputation();//以异步方式在新的线程中执行耗时的操作
//            }});
//        doSomethingElse();//异步操作进行的同时，你可以做其他的事情
//        try {
              //获取异步操作的结果，如果最终被阻塞，无法得到结果，那么在最多等待1秒钟之后退出
//            Double result = future.get(1, TimeUnit.SECONDS);
//        } catch (ExecutionException ee) {
//            // 计算抛出一个异常
//        } catch (InterruptedException ie) {
//            // 当前线程在等待过程中被中断
//        } catch (TimeoutException te) {
//            // 在Future对象完成之前超过已过期
//        }

//        这种编程方式让你的线程可以在ExecutorService以并发方式调
//        用另一个线程执行耗时操作的同时，去执行一些其他的任务。接着，如果你已经运行到没有异步
//        操作的结果就无法继续任何有意义的工作时，可以调用它的get方法去获取操作的结果。如果操
//        作已经完成，该方法会立刻返回操作的结果，否则它会阻塞你的线程，直到操作完成，返回相应
//        的结果。
//        你能想象这种场景存在怎样的问题吗？如果该长时间运行的操作永远不返回了会怎样？为
//        了处理这种可能性，虽然Future提供了一个无需任何参数的get方法，我们还是推荐大家使用重
//        载版本的get方法，它接受一个超时的参数，通过它，你可以定义你的线程等待Future结果的最
//        长时间，而不是像代码清单11-1中那样永无止境地等待下去。

        //1.Future 接口的局限性
//        通过第一个例子，我们知道Future接口提供了方法来检测异步计算是否已经结束（使用
//        isDone方法），等待异步操作结束，以及获取计算的结果。但是这些特性还不足以让你编写简洁
//        的并发代码。比如，我们很难表述Future结果之间的依赖性；从文字描述上这很简单，“当长时
//        间计算任务完成时，请将该计算的结果通知到另一个长时间运行的计算任务，这两个计算任务都
//        完成后，将计算的结果与另一个查询操作结果合并”。但是，使用Future中提供的方法完成这样
//        的操作又是另外一回事。这也是我们需要更具描述能力的特性的原因，比如下面这些。
// 将两个异步计算合并为一个——这两个异步计算之间相互独立，同时第二个又依赖于第
//        一个的结果。
// 等待Future集合中的所有任务都完成。
// 仅等待Future集合中最快结束的任务完成（有可能因为它们试图通过不同的方式计算同
//        一个值），并返回它的结果。
// 通过编程方式完成一个Future任务的执行（即以手工设定异步操作结果的方式）。
// 应对Future的完成事件（即当Future的完成事件发生时会收到通知，并能使用Future
//        计算的结果进行下一步的操作，不只是简单地阻塞等待操作的结果）。
//        这一章中，你会了解新的CompletableFuture类（它实现了Future接口）如何利用Java 8
//        的新特性以更直观的方式将上述需求都变为可能。Stream和CompletableFuture的设计都遵循
//        了类似的模式：它们都使用了Lambda表达式以及流水线的思想。从这个角度，你可以说
//        CompletableFuture和Future的关系就跟Stream和Collection的关系一样。
    }
}
