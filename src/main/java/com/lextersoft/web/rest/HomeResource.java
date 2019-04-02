package com.lextersoft.web.rest;

import com.lextersoft.classes.TextQuery;
import com.lextersoft.service.PlagiarismCheckerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeResource {

    private final PlagiarismCheckerService plagiarismCheckerService;

    public HomeResource(PlagiarismCheckerService plagiarismCheckerService) {
        this.plagiarismCheckerService = plagiarismCheckerService;
    }

    @RequestMapping("/")
    public String index() {
        return "Esto funciona!!";
    }

    @GetMapping("/test")
    public List<TextQuery> textQuery() throws IOException {
        List<TextQuery> textQueries = plagiarismCheckerService.generateQuerys("El amor es un concepto universal relativo a la afinidad entre seres, definido de diversas formas según las diferentes ideologías y puntos de vista (artístico, científico, filosófico, religioso). De manera habitual, y fundamentalmente en Occidente, se interpreta como un sentimiento relacionado con el afecto y el apego, y resultante y productor de una serie de actitudes, emociones y experiencias. En el contexto filosófico, el amor es una virtud que representa todo el afecto, la bondad y la compasión del ser humano. También puede describirse como acciones dirigidas hacia otros y basadas en la compasión, o bien como acciones dirigidas hacia otros (o hacia uno mismo) y basadas en el afecto.");
        for (TextQuery query: textQueries) {
            plagiarismCheckerService.compareText(query);
        }

        return textQueries;
    }
}
