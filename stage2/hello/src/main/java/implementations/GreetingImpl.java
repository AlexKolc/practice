package implementations;

import interfaces.Greeting;

public class GreetingImpl implements Greeting {
    private String greetingObject;
    public GreetingImpl(String greetingObject) {
        this.greetingObject = greetingObject;
    }

    public void sayHello() {
        System.out.println("Hello " + greetingObject + "!");
    }
}