package com.cc.bootstrap.intl.demo.lambda.completableFuture;

/**
 * @Description: 使用 CompletableFuture 构建异步应用
 * 这一章中，你会了解新的CompletableFuture类（它实现了Future接口）如何利用Java 8
 * 的新特性以更直观的方式将上述需求都变为可能。Stream和CompletableFuture的设计都遵循
 * 了类似的模式：它们都使用了Lambda表达式以及流水线的思想。从这个角度，你可以说
 * CompletableFuture和Future的关系就跟Stream和Collection的关系一样。
 * @author: ChenChen
 * @date: 2023/2/7 15:18
 */
public class Study3_CompletableFuture {
    public static void main(String[] args) {
//        为了展示CompletableFuture的强大特性，我们会创建一个名为“最佳价格查询器”
//        （best-price-finder）的应用，它会查询多个在线商店，依据给定的产品或服务找出最低的价格。这
//        个过程中，你会学到几个重要的技能。
// 首先，你会学到如何为你的客户提供异步API（如果你拥有一间在线商店的话，这是非常
//        有帮助的）。
// 其次，你会掌握如何让你使用了同步API的代码变为非阻塞代码。你会了解如何使用流水
//        线将两个接续的异步操作合并为一个异步计算操作。这种情况肯定会出现，比如，在线
//        商店返回了你想要购买商品的原始价格，并附带着一个折扣代码——最终，要计算出该
//        商品的实际价格，你不得不访问第二个远程折扣服务，查询该折扣代码对应的折扣比率。
// 你还会学到如何以响应式的方式处理异步操作的完成事件，以及随着各个商店返回它的
//        商品价格，最佳价格查询器如何持续地更新每种商品的最佳推荐，而不是等待所有的商
//        店都返回他们各自的价格（这种方式存在着一定的风险，一旦某家商店的服务中断，用
//        户可能遭遇白屏）。

//        同步API与异步API
//        同步API其实只是对传统方法调用的另一种称呼：你调用了某个方法，调用方在被调用方
//        运行的过程中会等待，被调用方运行结束返回，调用方取得被调用方的返回值并继续运行。即
//        使调用方和被调用方在不同的线程中运行，调用方还是需要等待被调用方结束运行，这就是阻
//        塞式调用这个名词的由来。
//        与此相反，异步API会直接返回，或者至少在被调用方计算完成之前，将它剩余的计算任
//        务交给另一个线程去做，该线程和调用方是异步的——这就是非阻塞式调用的由来。执行剩余
//        计算任务的线程会将它的计算结果返回给调用方。返回的方式要么是通过回调函数，要么是由
//        调用方再次执行一个“等待，直到计算完成”的方法调用。这种方式的计算在I/O系统程序设
//        计中非常常见：你发起了一次磁盘访问，这次访问和你的其他计算操作是异步的，你完成其他
//        的任务时，磁盘块的数据可能还没载入到内存，你只需要等待数据的载入完成。

        //1.实现异步 API
        //原同步例子请看vo.Shop类代码

        //1.1 将同步方法转换为异步方法
//        为了实现这个目标，你首先需要将getPrice转换为getPriceAsync方法，并修改它的返
//        回值：
//        public Future<Double> getPriceAsync(String product) { ... }
//        我们在本章开头已经提到，Java 5引入了java.util.concurrent.Future接口表示一个异
//        步计算（即调用线程可以继续运行，不会因为调用方法而阻塞）的结果。这意味着Future是一
//        个暂时还不可知值的处理器，这个值在计算完成后，可以通过调用它的get方法取得。因为这样
//        的设计，getPriceAsync方法才能立刻返回，给调用线程一个机会，能在同一时间去执行其他
//        有价值的计算任务。新的CompletableFuture类提供了大量的方法，让我们有机会以多种可能
//        的方式轻松地实现这个方法，比如下面就是这样一段实现代码。
        //请看Shop2类代码

//        如何正确地管理异步任务执行过程中可能出现的错误。
        //1.2 错误处理
        //(1) CompletableFuture的completeExceptionally方法将导致CompletableFuture内发生问题的异常抛出
//        如果没有意外，我们目前开发的代码工作得很正常。但是，如果价格计算过程中产生了错误
//        会怎样呢？非常不幸，这种情况下你会得到一个相当糟糕的结果：用于提示错误的异常会被限制
//        在试图计算商品价格的当前线程的范围内，最终会杀死该线程，而这会导致等待get方法返回结
//        果的客户端永久地被阻塞。
//        客户端可以使用重载版本的get方法，它使用一个超时参数来避免发生这样的情况。这是一
//        种值得推荐的做法，你应该尽量在你的代码中添加超时判断的逻辑，避免发生类似的问题。使用
//        这种方法至少能防止程序永久地等待下去，超时发生时，程序会得到通知发生了TimeoutException。不过，也因为如此，你不会有机会发现计算商品价格的线程内到底发生了什么问题
//        才引发了这样的失效。为了让客户端能了解商店无法提供请求商品价格的原因，你需要使用
//        CompletableFuture的completeExceptionally方法将导致CompletableFuture内发生问
//        题的异常抛出。

//        public Future<Double> getPriceAsync(String product) {
//            CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//            new Thread( () -> {
//                try {
//                    double price = calculatePrice(product);
//                    futurePrice.complete(price);
//                } catch (Exception ex) {
//                    futurePrice.completeExceptionally(ex);
//                }
//            }).start();
//            return futurePrice;
//        }

        //(2)使用工厂方法supplyAsync创建CompletableFuture
//        目前为止我们已经了解了如何通过编程创建CompletableFuture对象以及如何获取返回
//        值，虽然看起来这些操作已经比较方便，但还有进一步提升的空间，CompletableFuture类自
//        身提供了大量精巧的工厂方法，使用这些方法能更容易地完成整个流程，还不用担心实现的细节。
//        比如，采用supplyAsync方法后，你可以用一行语句重写代码清单11-4中的getPriceAsync方
//        法，如下所示。
//        public Future<Double> getPriceAsync(String product) {
//            return CompletableFuture.supplyAsync(() -> calculatePrice(product));
//        }
//        supplyAsync方法接受一个生产者（Supplier）作为参数，返回一个CompletableFuture
//        对象，该对象完成异步执行后会读取调用生产者方法的返回值。生产者方法会交由ForkJoinPool
//        池中的某个执行线程（Executor）运行，但是你也可以使用supplyAsync方法的重载版本，传
//        递第二个参数指定不同的执行线程执行生产者方法。一般而言，向CompletableFuture的工厂
//        方法传递可选参数，指定生产者方法的执行线程是可行的，在11.3.4节中，你会使用这一能力，我
//        们会在该小节介绍如何使用适合你应用特性的执行线程改善程序的性能。
//        此外，代码清单11-7中getPriceAsync方法返回的CompletableFuture对象和代码清单
//        11-6中你手工创建和完成的CompletableFuture对象是完全等价的，这意味着它提供了同样的
//        错误管理机制，而前者你花费了大量的精力才得以构建。


    }
}
