package com.cc.bootstrap.intl.demo.lambda.stream_api.exercises.content;

import com.cc.bootstrap.intl.demo.lambda.stream_api.exercises.vo.Trader;
import com.cc.bootstrap.intl.demo.lambda.stream_api.exercises.vo.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

/**
 * @Description: 执行交易的交易员。--为八个查询找到答案。
 * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
 * (2) 交易员都在哪些不同的城市工作过？
 * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
 * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
 * (5) 有没有交易员是在米兰工作的？
 * (6) 打印生活在剑桥的交易员的所有交易额。
 * (7) 所有交易中，最高的交易额是多少？
 * (8) 找到交易额最小的交易。
 * @author: ChenChen
 * @date: 2023/1/29 9:33
 */
public class Test {

    //执行交易的交易员。
    private static Trader raoul = new Trader("Raoul", "Cambridge");
    private static Trader mario = new Trader("Mario","Milan");
    private static Trader alan = new Trader("Alan","Cambridge");
    private static Trader brian = new Trader("Brian","Cambridge");
    private static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public static void main(String[] args) {
//        (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> transactions1 = transactions.stream().filter(transaction -> 2011 == transaction.getYear())
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(transactions1);//[{Trader:Brian in Cambridge, year: 2011, value:300}, {Trader:Raoul in Cambridge, year: 2011, value:400}]
//        (2) 交易员都在哪些不同的城市工作过？
        List<String> citys = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(citys);//[Cambridge, Milan]
//        (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> traders1 = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> "Cambridge".equals(trader.getCity()))
                .sorted(comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(traders1);//[Trader:Alan in Cambridge, Trader:Brian in Cambridge, Trader:Raoul in Cambridge]
//        (4) 返回所有交易员的姓名字符串，按字母顺序排序。
        List<String> traderNames = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .map(Trader::getName)
                .sorted().collect(Collectors.toList());
        System.out.println(traderNames);//[Alan, Brian, Mario, Raoul]
//        (5) 有没有交易员是在米兰工作的？
        Optional<Trader> trader1 = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> "Milan".equals(trader.getCity()))
                .findAny();
        if(trader1.isPresent()){
            System.out.println("有交易员是在米兰工作的");//有交易员是在米兰工作的
        }else {
            System.out.println("没有交易员是在米兰工作的");
        }
//        (6) 打印生活在剑桥的交易员的所有交易额。
        int total = transactions.stream().filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .reduce(0, (a,b) -> a+b);
        System.out.println("生活在剑桥的交易员的所有交易额为" + total);//2650
//        (7) 所有交易中，最高的交易额是多少？
        int max = transactions.stream().map(Transaction::getValue).reduce(Integer::max).get();
        System.out.println("所有交易中，最高的交易额是" + max);//1000
//        (8) 找到交易额最小的交易。
        Transaction minTransaction = transactions.stream()
                .sorted(Comparator.comparingInt(Transaction::getValue)).findFirst().get();
        System.out.println("交易额最小的交易是" + minTransaction);//{Trader:Brian in Cambridge, year: 2011, value:300}

        Optional<Transaction> smallestTransaction =
                transactions.stream()
                        .min(comparing(Transaction::getValue));

    }


}
