package org.practice.stage3.client;

import org.practice.stage3.service.interfaces.Service;
import org.osgi.service.component.annotations.*;

@Component(
        name = "Client",
        service = Client.class,
        immediate = true
)
public class Client {
    @Reference(
            name = "Service",
            service = Service.class,
            bind = "bindService",
            unbind = "unbindService"
    )
    private Service service;

    protected void bindService(Service greeting) {
        this.service = service;
    }

    protected void unbindService(Service service) {
        this.service = null;
    }


    @Activate
    protected void activate() {
        System.out.println("Started Client");
        if (service != null) {
            service.sayHello();
        } else {
            System.out.println("Service not found!");
        }
    }

    @Deactivate
    protected void deactivate() {
        System.out.println("Stopped Client");
    }
}

// C:/Users/Alex/Desktop/studies/6semestr/practice/stage3/org.practice.stage3.service/target/org.practice.stage3.service-1.0.0.jar
// C:/Users/Alex/Desktop/studies/6semestr/practice/stage3/org.practice.stage3.client/target/org.practice.stage3.client-1.0.0.jar
