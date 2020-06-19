package main;

import implementations.GreetingImpl;
import interfaces.Greeting;
import org.osgi.framework.*;

public class Activator implements BundleActivator {

    public void start(BundleContext ctx) throws Exception {
        ctx.registerService(Greeting.class.getName(), new GreetingImpl("OSGi World"), null);
        System.out.println("Started Activator");
    }

    public void stop(BundleContext ctx) throws Exception {
        System.out.println("Stopped Activator");
    }
}