package org.practice.stage5.meduza;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.osgi.service.component.annotations.Component;
import org.practice.stage5.service.AbstractNewsService;
import org.practice.stage5.service.interfaces.NewsService;

import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@Component(
        service = NewsService.class,
        immediate = true
)
public class MeduzaServiceImpl extends AbstractNewsService implements NewsService {
    private static final String name = "meduza.io";
    private static final String url = "https://meduza.io/rss/podcasts/meduza-v-kurse";

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
        return els.stream().map(Element::text).collect(Collectors.toList());
    }

}
