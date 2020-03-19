package by.matusevichChercasova.movieRating.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {//не нужно создавать свой контроллер, здесь раздаются странички, которые описаны(шаблоны), на них нет никакой логики
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");// "/login" - это и есть системная логика
                                                                        //страничка нас не устраевает, поэтому мы пишем свою
    }
}
