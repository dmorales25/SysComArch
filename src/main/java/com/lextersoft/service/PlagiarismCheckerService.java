package com.lextersoft.service;

import com.lextersoft.classes.TextQuery;

import java.io.IOException;
import java.util.List;

public interface PlagiarismCheckerService {
    void compareText(TextQuery textQuery) throws IOException;
    List<TextQuery> generateQuerys(String text);
    List<TextQuery> process(String text) throws IOException;
}
