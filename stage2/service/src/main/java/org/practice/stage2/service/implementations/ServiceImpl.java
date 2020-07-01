package org.practice.stage2.service.implementations;

import org.practice.stage2.service.interfaces.Service;

public class ServiceImpl implements Service {

    private String serviceObject;

    public ServiceImpl(String serviceObject) {
        this.serviceObject = serviceObject;
    }

    public void sayHello() {
        System.out.println("Hello " + serviceObject + "!");
    }
}
