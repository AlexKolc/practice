package org.practice.stage2.client;

import org.osgi.framework.*;
import org.practice.stage2.service.interfaces.Service;


public class ClientActivator implements BundleActivator {

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Started Client");
        ServiceReference reference = bundleContext.getServiceReference(Service.class.getName());
        ((Service) bundleContext.getService(reference)).sayHello();

    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Stopped Client");
    }
}