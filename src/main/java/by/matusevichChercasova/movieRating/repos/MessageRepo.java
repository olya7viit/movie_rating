package by.matusevichChercasova.movieRating.repos;

import by.matusevichChercasova.movieRating.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {//нужно смотреть документацию, какие там методы есть
    List<Message> findByTag(String tag); //если нет подходящего метода, то пишем свой
    //имя метода формировать по правилу https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
}
