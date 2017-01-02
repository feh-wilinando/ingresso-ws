package br.com.caelum.ingresso_ws;

import br.com.caelum.ingresso_ws.controller.FilmesController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;

/**
 * Created by nando on 02/01/17.
 */
@SpringBootApplication
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Autowired
    private FilmesController filmesController;

    @PostConstruct
    public void onResume(){
        filmesController.getFilmes();
    }

}
