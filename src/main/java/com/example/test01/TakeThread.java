package com.example.test01;

public class TakeThread extends Thread {
    private MyService myService;
    public TakeThread(MyService myService) {
        super();
        this.myService = myService;
    }
    public void run() {
        myService.take();
    }
}
