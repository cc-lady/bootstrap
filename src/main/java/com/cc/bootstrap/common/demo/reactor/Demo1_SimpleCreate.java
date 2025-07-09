package com.cc.bootstrap.common.demo.reactor;

import org.reactivestreams.Subscription;
import reactor.core.Disposable;
import reactor.core.Disposables;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 简单的创建
 * @author: ChenChen
 * @date: 2024-11-11 13:50
 */
public class Demo1_SimpleCreate {
    public static void main(String[] args) {
        // 1.工厂方法创建
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);
        //工厂方法的其他示例包括:
        Mono<String> noData = Mono.empty();
        Mono<String> data = Mono.just("foo");
        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);

        //2.订阅方法示例
        Flux<Integer> ints = Flux.range(1, 3);//设置一个流量，在用户连接时产生三个值。
        ints.subscribe(); //用最简单的方式订阅。

        Flux<Integer> ints2 = Flux.range(1, 3);
        ints2.subscribe(i -> System.out.println(i));

        Flux<Integer> ints3 = Flux.range(1, 4)
                .map(i -> { if (i <= 3) return i;
                    throw new RuntimeException("Got to 4"); });
        ints3.subscribe(i -> System.out.println(i), error -> System.err.println("Error: " + error));


        //3.Cancelling a subscribe() with Its Disposable
        Disposable disposable3 = ints3.subscribe();
        disposable3.dispose();//取消订阅，但是可能内部元素很快来不及取消
        Disposables.composite()//.add(new 0)
                .dispose();//一起取消订阅

        //4.Lambdas的替代方案:BaseSubscriber
//        SampleSubscriber<Integer> ss = new SampleSubscriber<Integer>();
//        Flux<Integer> ints4 = Flux.range(1, 4);
//        ints4.subscribe(ss);
//        Subscribed
//        1
//        2
//        3
//        4

        //5.论背压和重塑请求的方法
        Flux.range(1, 10)
                .doOnRequest(r -> System.out.println("request of " + r))
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    public void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }
                    @Override
                    public void hookOnNext(Integer integer) {
                        System.out.println("Cancelling after having received " + integer);
                        cancel();
                    }
                });

        //6.以编程方式创建序列
        //6.1 同步生成
        /**
         * @Description 以编程方式创建通量的最简单形式是通过generate方法，该方法采用一个生成器函数。
         * 这是针对同步和逐个发射的，这意味着接收器是一个SynchronousSink，它的next()方法在每次回调调用中最多只能调用一次。
         * 然后，您可以另外调用error(Throwable)或complete()，但这是可选的。 最有用的变体可能是这样一种变体，它还允许您保存一个状态，
         * 您可以在接收器使用中引用该状态来决定接下来要发出什么。然后，生成器函数变成双函数< S，
         *         SynchronousSink<T>，S >，其中< S >是状态对象的类型。您必须为初始状态提供一个供应商< S >,
         *         现在您的生成器函数会在每一轮返回一个新状态。
         *
         * @param args
         * @author ChenChen
         * @return void
         * @date 2024-11-11 16:42
         */
        //例如，您可以使用int作为状态:
        Flux<String> flux = Flux.generate( () -> 0, (state, sink) -> {
            sink.next("3 x " + state + " = " + 3*state);
            if (state == 10) {
                sink.complete();
            }
            return state + 1;
        });
//        我们提供初始状态值0。
//        我们使用状态来选择发射什么(3的乘法表中的一行)。
//        我们也用它来选择何时停止。
//        我们返回一个在下一次调用中使用的新状态(除非序列在这次调用中终止)。

//        The preceding code generates the table of 3, as the following sequence:
//        3 x 0 = 0
//        3 x 1 = 3
//        3 x 2 = 6
//        3 x 3 = 9
//        3 x 4 = 12
//        3 x 5 = 15
//        3 x 6 = 18
//        3 x 7 = 21
//        3 x 8 = 24
//        3 x 9 = 27
//        3 x 10 = 30

        //您也可以使用可变的< S >。例如，可以使用单个AtomicLong作为状态来重写上面的示例，在每一轮中对其进行变异:
        //例12。可变状态变量
        Flux<String> flux2 = Flux.generate( AtomicLong::new, (state, sink) -> {
            long i = state.getAndIncrement();
            sink.next("3 x " + i + " = " + 3*i);
            if (i == 10) sink.complete();
            return state;
        });
        //这一次，我们生成一个可变对象作为状态。 我们改变了这里的状态。 我们返回与新状态相同的实例。

        //6.2 异步和多线程:创建

    }
}
