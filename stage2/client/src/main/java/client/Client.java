package client;

import interfaces.Greeting;
import org.osgi.framework.*;

public class Client implements BundleActivator {

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Started Client");
        ServiceReference reference = bundleContext.getServiceReference(Greeting.class.getName());
        ((Greeting) bundleContext.getService(reference)).sayHello();

    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Stopped Client");
    }
}