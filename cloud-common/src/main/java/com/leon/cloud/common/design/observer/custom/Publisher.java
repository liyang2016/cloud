package com.leon.cloud.common.design.observer.custom;

import java.util.Objects;
import java.util.Vector;

/**
 * 通知者
 */
public class Publisher {

    //观察者
    private Vector<ISubscriber> subscribers;

    public Publisher(){
        subscribers=new Vector<>();
    }

    public void addSubscriber(ISubscriber subscriber){
        subscribers.add(subscriber);
    }

    public void removeSubscriber(ISubscriber subscriber){
        subscribers.remove(subscriber);
    }

    public void notifySubscriber(){
        subscribers.stream().filter(Objects::nonNull).forEach(ISubscriber::update);
        subscribers.stream().filter(Objects::isNull).distinct().forEach(System.out::println);
    }

    public static void main(String[] args) {
        Publisher publisher=new Publisher();
        Subscriber subscriber=new Subscriber();
        publisher.addSubscriber(subscriber);
        publisher.addSubscriber(null);
        publisher.notifySubscriber();
        publisher.removeSubscriber(subscriber);
        publisher.notifySubscriber();
    }
}
