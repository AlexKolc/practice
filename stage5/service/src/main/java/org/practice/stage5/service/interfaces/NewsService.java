package org.practice.stage5.service.interfaces;

import java.io.IOException;
import java.util.List;

public interface NewsService {
    String getName();
    String getURL();
    List<String> getSortedWords() throws IOException;
}
