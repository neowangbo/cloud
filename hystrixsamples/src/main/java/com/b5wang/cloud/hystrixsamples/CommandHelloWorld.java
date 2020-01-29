package com.b5wang.cloud.hystrixsamples;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * An equivalent Hello World solution that uses HystrixObservableCommand
 * instead of a HystrixCommand would involve overriding the construct method.
 * Go to @see ObservableCommandHelloWorld
 *
 * https://github.com/Netflix/Hystrix/wiki/How-To-Use#Hello-World
 * */
public class CommandHelloWorld extends HystrixCommand<String> {

    private final String name;

    public CommandHelloWorld(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        // a real example would do work like a network call here
        return "Hello " + name + "!";
    }

    public static void main(String[] args){

    }
}
