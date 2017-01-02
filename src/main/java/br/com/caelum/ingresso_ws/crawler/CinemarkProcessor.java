package br.com.caelum.ingresso_ws.crawler;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nando on 02/01/17.
 */
@Component
public class CinemarkProcessor implements PageProcessor {

    @Override
    public void process(Page page) {

        List<Selectable> nodes = page.getHtml().xpath("//div[@class='tabs-content']/div[@class='active']/div[@class='row']/div[@class='col-sm-6 col-md-3']").nodes();

        List<String> titulos = new ArrayList<>();

        nodes.forEach(n -> {

            Selectable movieTitle = n.xpath("//h3[@class='movie-title']/a/text()");

            String titulo = movieTitle.toString().trim();

            if(titulo.isEmpty()){

                titulo = n.xpath("//h3[@class='movie-title']/a/span/text()").toString();

            }

            titulos.add(titulo);

        });

        page.putField("titulos", titulos);

    }

    @Override
    public Site getSite() {
        return Site.me();
    }
}
