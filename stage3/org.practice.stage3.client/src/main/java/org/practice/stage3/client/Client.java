package org.practice.stage3.client;

import org.practice.stage3.service.interfaces.Greeting;
import org.apache.felix.scr.annotations.*;

@SuppressWarnings("deprecation")
@Component
public class Client {
    @Reference(policy = ReferencePolicy.DYNAMIC)
    private volatile Greeting greeting;

    public Client() {
    }

    public Client(Greeting greeting) {
        this.greeting = greeting;
    }

    @Activate
    void activate() {
        System.out.println("Started Client");
        greeting.sayHello();
    }

    @Deactivate
    void deactivate() {
        System.out.println("Stopped Client");
    }
}