package org.practice.stage5.command.implementations;

import org.osgi.service.component.annotations.*;
import org.apache.felix.service.command.CommandProcessor;
import org.practice.stage5.service.interfaces.NewsService;
import org.practice.stage5.command.interfaces.CommandService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

@Component(
        service = CommandService.class,
        immediate = true,
        property = {
                CommandProcessor.COMMAND_SCOPE + "=news",
                CommandProcessor.COMMAND_FUNCTION + "=stats",
        }
)
public class CommandServiceImpl implements CommandService {
    private final static int COUNT_WORDS = 10;
    private Map<String, NewsService> newsServices = new HashMap<>();

    @Reference(
            name = "NewsService",
            service = NewsService.class,
            cardinality = ReferenceCardinality.MULTIPLE,
            policy = ReferencePolicy.DYNAMIC,
            bind = "bind",
            unbind = "unbind"
    )
    protected void bind(NewsService service) {
        newsServices.put(service.getName(), service);
    }

    protected void unbind(NewsService service) {
        newsServices.remove(service.getName());
    }

    @Override
    public void stats() {

        if (newsServices.isEmpty()) {
            System.out.println("The list of news portals is empty");
        } else {
            System.out.println("Select a news portal or everything portals from the list \n" +
                    "with the command `all` to select top " + COUNT_WORDS + " words from news headlines:");
            newsServices.forEach((name, service) -> System.out.println(name));
            System.out.println("-------------------------------");
            System.out.print("-> ");
            Scanner scanner = new Scanner(System.in, "UTF8");
            String answer = scanner.nextLine();
            stats(answer);
        }
    }

    @Override
    public void stats(String... arg) {
        if (arg == null || arg[0] == null) {
            System.out.println("Select non-null portal");
            printUsage();
            return;
        }
        if (arg.length > 1) {
            System.out.println("Select one portal");
            printUsage();
            return;
        }
        if (newsServices.isEmpty()) {
            System.out.println("The list of news portals is empty");
        } else {
            if (arg[0].equals("all")) {
                newsServices.forEach(this::printWords);
            } else {
                if (newsServices.containsKey(arg[0])) {
                    printWords(arg[0], newsServices.get(arg[0]));
                    System.out.println("YEP");
                } else {
                    System.out.println("The portal with name: \"" + arg[0] + "\" is not available");
                }
            }
        }
    }

    private void printUsage() {
        System.out.println("Use: news:stats <source_name>");
    }

    private void printWords(String name, NewsService service) {
        try {
            List<String> sortedWordList = service.getSortedWords();

            System.out.println("Top " + COUNT_WORDS + " words of " + name + ":");

            StringJoiner joiner = new StringJoiner(", ");
            int count = 0;
            int i = 0;
            while (count != COUNT_WORDS && i != sortedWordList.size()) {
                joiner.add(sortedWordList.get(i++));
                count++;
            }
            if (count < COUNT_WORDS) {
                System.out.println("Amount of headline words is less than " + COUNT_WORDS + "!");
            }
            System.out.println("--------------------");
            System.out.println(joiner.toString());
        } catch (MalformedURLException e) {
            System.out.println("Wrong URL name: " + service.getURL());
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Unable to get info from " + service.getName() + ": " + service.getURL());
        }
    }

    @Activate
    void activate() {
        System.out.println("Added command news:stats");
    }

    @Deactivate
    void deactivate() {
        System.out.println("Removed command news:stats");
    }
}
