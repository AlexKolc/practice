package org.practice.stage3.service.implementations;

import org.osgi.service.component.annotations.*;
import org.practice.stage3.service.interfaces.Service;

@Component(
        name = "Service",
        service = Service.class,
        immediate = true
)
public class ServiceImpl implements Service {
    public void sayHello() {
        System.out.println("Hello, OSGi!");
    }

    @Activate
    void activate() {
        System.out.println("Started Service");
    }

    @Deactivate
    void deactivate() {
        System.out.println("Stopped Service");
    }

}
