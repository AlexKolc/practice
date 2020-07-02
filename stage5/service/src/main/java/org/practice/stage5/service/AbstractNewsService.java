package org.practice.stage5.service;

import org.practice.stage5.service.interfaces.NewsService;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractNewsService implements NewsService {
    public abstract String getName();

    public abstract String getURL();

    public abstract List<String> pageToString(String string) throws IOException;

    private List<String> getLines() throws IOException {
        List<String> lines;
        URL url = new URL(this.getURL());
        URLConnection connection = url.openConnection();
        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF8");
        BufferedReader in = new BufferedReader(inputStreamReader);
        String json = in.lines().collect(Collectors.joining());
        in.close();
        lines = pageToString(json);
        return lines;
    }

    public List<String> getSortedWords() throws IOException {
        Map<String, Integer> words = new HashMap<>();
        List<String> lines = this.getLines();
        for (String curLine : lines) {
            String[] wordsTitle = curLine
                    .toLowerCase()
                    .replaceAll("[^а-яА-Яa-zA-Z0-9\\-\\s]", " ")
                    .trim()
                    .split("\\s+");
            for (String word : wordsTitle) {
                if (!word.isEmpty()) {
                    Integer count = words.get(word);
                    words.put(word, count != null ? count + 1 : 1);
                }
            }
        }
        List<Map.Entry<String, Integer>> sorted = words
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toList());
        List<String> answer = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sorted) {
            answer.add(entry.getKey());
        }
        return answer;
    }
}
