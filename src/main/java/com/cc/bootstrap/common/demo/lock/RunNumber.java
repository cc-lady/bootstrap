package com.cc.bootstrap.common.demo.lock;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: number运行测试
 * @author: ChenChen
 * @date: 2025-01-10 16:23
 */
public class RunNumber {

    private Queue<NumberVo> numberVoQueue = new ConcurrentLinkedDeque<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private int count = 0;
    private int newNumber = 0;
    private boolean runFlag = true;


    public void addNumber(NumberVo numberVo) {
        try {
            lock.tryLock(1, TimeUnit.MINUTES);
            while(!(numberVoQueue.size() <= 100)) {
                condition.await();
            }
            numberVoQueue.add(numberVo);
            newNumber = numberVo.getNumber();
            count = count + newNumber;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            condition.signalAll();
        }
    }

    public void runNumber() {
        while(runFlag) {
            try {
                lock.tryLock(1, TimeUnit.MINUTES);
                while ((numberVoQueue.size() > 0)) {
                    condition.signalAll();
                }
                NumberVo numberVo = numberVoQueue.poll();
                System.out.println("numer="+ numberVo.getNumber());
                System.out.println("newNumber="+ newNumber);
                System.out.println("count="+ count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                condition.signalAll();
            }
        }
    }

    public void setRunFlagFalse() {
        this.runFlag = false;
    }

    public Queue<NumberVo> getNumberVoQueue() {
        return numberVoQueue;
    }

    public static void main(String[] args) throws InterruptedException {
        RunNumber number = new RunNumber();
        Thread myThread = new Thread(() -> {
            for(int i = 1;i<100;i++) {
                System.out.println("开始添加i="+i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                number.getNumberVoQueue().add(new NumberVo(1));
                Thread.yield();
            }
            number.setRunFlagFalse();
        });
        Thread myThread2 = new Thread(() -> {
            number.runNumber();
        });

        myThread.start();
        myThread2.start();
        myThread.join();
        myThread2.join();
        System.out.println("测试结束");
    }
}
