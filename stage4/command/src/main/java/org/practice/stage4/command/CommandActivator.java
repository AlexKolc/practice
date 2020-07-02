package org.practice.stage4.command;

import org.osgi.framework.*;
import org.practice.stage4.command.implementations.GreetingImpl;
import org.practice.stage4.command.interfaces.Greeting;

import java.util.Hashtable;

public class CommandActivator implements BundleActivator {

    public void start(BundleContext ctx) throws Exception {
        Hashtable<String, Object> properties = new Hashtable<String, Object>();
        properties.put("osgi.command.scope", "practice");
        properties.put("osgi.command.function", new String[]{"hello"});
        ctx.registerService(Greeting.class.getName(), new GreetingImpl(), properties);
    }

    public void stop(BundleContext ctx) throws Exception {
    }
}
