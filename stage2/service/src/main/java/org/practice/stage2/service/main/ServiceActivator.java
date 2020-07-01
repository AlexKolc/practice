package org.practice.stage2.service.main;

import org.osgi.framework.*;
import org.practice.stage2.service.implementations.ServiceImpl;
import org.practice.stage2.service.interfaces.Service;

public class ServiceActivator implements BundleActivator {

    public void start(BundleContext ctx) throws Exception {
        ctx.registerService(Service.class.getName(), new ServiceImpl("OSGi World"), null);
        System.out.println("Started Activator");
    }

    public void stop(BundleContext ctx) throws Exception {
        System.out.println("Stopped Activator");
    }
}
