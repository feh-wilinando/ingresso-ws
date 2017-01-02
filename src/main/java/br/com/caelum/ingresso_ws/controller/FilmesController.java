package br.com.caelum.ingresso_ws.controller;

import br.com.caelum.ingresso_ws.crawler.CinemarkProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ResultItemsCollectorPipeline;

import java.util.List;

/**
 * Created by nando on 02/01/17.
 */
@RestController
public class FilmesController {

    @Autowired
    private CinemarkProcessor cinemarkProcessor;

    @GetMapping("/filmes")
    @Cacheable("Default")
    public List<String> getFilmes(){

        ResultItemsCollectorPipeline pipeline = new ResultItemsCollectorPipeline();

        Spider.create(cinemarkProcessor).addUrl("https://www3.cinemark.com.br/sao-paulo/filmes/em-cartaz")
                .addPipeline(pipeline)
                .thread(1).run();

        return pipeline.getCollected().get(0).get("titulos");

    }


}
