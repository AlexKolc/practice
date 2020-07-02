package org.practice.stage4.command.implementations;

import org.practice.stage4.command.interfaces.Greeting;

public class GreetingImpl implements Greeting {
    public void hello(String greetingObject) {
        System.out.println("Hello, " + greetingObject + "!");
    }
}
