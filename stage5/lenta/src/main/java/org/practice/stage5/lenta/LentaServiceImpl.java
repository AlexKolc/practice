package org.practice.stage5.lenta;

import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.practice.stage5.service.AbstractNewsService;
import org.practice.stage5.service.interfaces.NewsService;

import java.io.IOException;
import java.util.*;

@Component(
        service = NewsService.class,
        immediate = true
)
public class LentaServiceImpl extends AbstractNewsService implements NewsService {
    private static final String name = "lenta.ru";
    private static final String url = "https://api.lenta.ru/lists/latest";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getURL() {
        return url;
    }

    @Override
    public List<String> pageToString(String json) {
        List<String> headLines = new ArrayList<>();
        JSONObject obj = new JSONObject(json);
        JSONArray headlines = obj.getJSONArray("headlines");
        for (int i = 0; i < headlines.length(); i++) {
            JSONObject headline = headlines.getJSONObject(i);
            JSONObject info = headline.getJSONObject("info");
            headLines.add(info.getString("title"));
        }
        return headLines;
    }
}
