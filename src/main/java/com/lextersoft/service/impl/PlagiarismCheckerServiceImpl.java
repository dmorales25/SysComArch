package com.lextersoft.service.impl;

import com.lextersoft.classes.Coincidence;
import com.lextersoft.classes.TextQuery;
import com.lextersoft.service.PlagiarismCheckerService;
import com.lextersoft.utils.QueryUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlagiarismCheckerServiceImpl implements PlagiarismCheckerService {

    private Logger log = LoggerFactory.getLogger(PlagiarismCheckerServiceImpl.class);

    @Override
    public void compareText(TextQuery textQuery) throws IOException {
        log.debug("Request to comparteText : {}", textQuery);
        Document doc = Jsoup.connect(textQuery.getQuery()).get();
        System.out.println(doc.title());
        Elements newsHeadlines = doc.select(".rc");

        List<Coincidence> coincidences = new ArrayList<>();

        for (Element headline : newsHeadlines) {
            if (QueryUtils.cleanText(headline.text()).contains(textQuery.getText()))
            {
                String link = headline.selectFirst(".r a").attr("href");
                String text = headline.selectFirst(".s").text();
                Coincidence coincidence = new Coincidence(text, link);
                coincidences.add(coincidence);

            }
            textQuery.setCoincidences(coincidences);
        }
    }

    @Override
    public List<TextQuery>  generateQuerys(String text) {
        log.debug("Request to generateQuerys : {}", text);

        String textSearch[] = text.split("\\.");

        List<TextQuery> queries = new ArrayList<>();

        for (int i = 0; i < textSearch.length; i++) {
            String textReplace = QueryUtils.cleanText(textSearch[i]);
            queries.add(new TextQuery(textReplace));

        }
        return queries;
    }

    public List<TextQuery> process(String text) throws IOException {
        List<TextQuery> textQueries = generateQuerys(text);
        for (TextQuery query: textQueries) {
            compareText(query);
        }

        return textQueries;
    }
}
