package org.practice.stage5.aif;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.osgi.service.component.annotations.Component;
import org.practice.stage5.service.AbstractNewsService;
import org.practice.stage5.service.interfaces.NewsService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Component(
        service = NewsService.class,
        immediate = true
)
public class AifServiceImpl extends AbstractNewsService implements NewsService {
    private static final String name = "aif.ru";
    private static final String url = "http://www.aif.ru/rss/news.php";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public List<String> pageToString(String xml) {
        Document document = Jsoup.parse(xml);
        Elements els = document.select("item > title");
        System.out.println(els.stream().map(Element::text).collect(Collectors.toList()));
        return els.stream().map(Element::text).collect(Collectors.toList());
    }
}
