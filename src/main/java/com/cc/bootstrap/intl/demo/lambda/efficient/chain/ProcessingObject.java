package com.cc.bootstrap.intl.demo.lambda.efficient.chain;

/**
 * @Description: 责任链模式
 * 这种模式是通过定义一个代表处理对象的抽象类来实现的，在抽象类中会定义一个字段来记录后续对象。
 * 一旦对象完成它的工作，处理对象就会将它的工作转交给它的后继
 * @author: ChenChen
 * @date: 2023/2/6 10:51
 */
public abstract class ProcessingObject<T> {
    protected ProcessingObject<T> successor;
    public void setSuccessor(ProcessingObject<T> successor){
        this.successor = successor;
    }
    public T handle(T input){
        T r = handleWork(input);
        if(successor != null){
            return successor.handle(r);
        }
        return r;
    }
    abstract protected T handleWork(T input);
}
