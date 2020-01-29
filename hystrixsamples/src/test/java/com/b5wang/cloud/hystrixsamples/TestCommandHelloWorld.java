package com.b5wang.cloud.hystrixsamples;

import org.junit.Assert;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TestCommandHelloWorld {

    String hoStr;
    String hbStr;

    /**
     * Trigger job synchronously
     * */
    @Test
    public void execSynchronously(){
        Assert.assertEquals("Hello World!", new CommandHelloWorld("World").execute());
        Assert.assertEquals("Hello Bob!", new CommandHelloWorld("Bob").execute());
    }

    /**
     * Trigger job asynchronously
     * */
    @Test
    public void execAsynchronously() throws ExecutionException, InterruptedException {
        Future<String> fWorld = new CommandHelloWorld("World").queue();
        Future<String> fBob = new CommandHelloWorld("Bob").queue();

        Assert.assertEquals("Hello World!", fWorld.get());
        Assert.assertEquals("Hello Bob!", fBob.get());
    }

    @Test
    public void execReactiveAsynchronously() throws Exception {

        Observable<String> fWorld = new CommandHelloWorld("World").observe();
        Observable<String> fBob = new CommandHelloWorld("Bob").observe();

        // blocking
        Assert.assertEquals("Hello World!", fWorld.toBlocking().single());
        Assert.assertEquals("Hello Bob!", fBob.toBlocking().single());

        // non-blocking
        // - this is a verbose anonymous inner-class approach and doesn't do assertions
        fWorld.subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {
                // nothing needed here
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                System.out.println("onNext: " + v);
            }

        });

        // non-blocking
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
        fBob.subscribe(new Action1<String>() {

            @Override
            public void call(String v) {
                System.out.println("onNext: " + v);
            }

        });
    }


}
