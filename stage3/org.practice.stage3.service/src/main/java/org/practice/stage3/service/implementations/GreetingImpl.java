package org.practice.stage3.service.implementations;

import org.apache.felix.scr.annotations.*;
import org.practice.stage3.service.interfaces.Greeting;

@SuppressWarnings("deprecation")
@Component(immediate = true)
@Service(value = Greeting.class)
public class GreetingImpl implements Greeting {

    public GreetingImpl(){
        System.out.println("ARTYOM GENII");
    }
    private String greetingObject = "OSGi";

    public void sayHello() {
        System.out.println("Hello " + greetingObject + "!");
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
