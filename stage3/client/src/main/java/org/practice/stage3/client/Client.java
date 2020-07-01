package org.practice.stage3.client;

import org.practice.stage3.service.interfaces.Greeting;
import org.osgi.service.component.annotations.*;

@Component(
        name = "Client",
        service = Client.class,
        immediate = true
)
public class Client {
    @Reference(
            name = "Service",
            service = Greeting.class,
            bind = "bindService",
            unbind = "unbindService"
    )
    private Greeting greeting;

    protected void bindService(Greeting greeting) {
        this.greeting = greeting;
    }

    protected void unbindService(Greeting greeting) {
        this.greeting = null;
    }


    @Activate
    protected void activate() {
        System.out.println("Started Client");
        if (greeting != null) {
            greeting.sayHello();
        } else {
            System.out.println("HelloSCRService not found!");
        }
    }

    @Deactivate
    protected void deactivate() {
        System.out.println("Stopped Client");
    }
}

// C:/Users/Alex/Desktop/studies/6semestr/practice/stage3/org.practice.stage3.service/target/org.practice.stage3.service-1.0.0.jar
// C:/Users/Alex/Desktop/studies/6semestr/practice/stage3/org.practice.stage3.client/target/org.practice.stage3.client-1.0.0.jar