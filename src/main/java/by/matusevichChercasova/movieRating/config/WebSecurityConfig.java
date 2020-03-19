package by.matusevichChercasova.movieRating.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

/*
* Это класс, кот при старте приложения конфигурирует WebSecurity
* система заходит сюда, передает на вход объект(HttpSecurity http) и мы в нем вклчаем: (см в методе configure(HttpSecurity http))
* */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()//авторизация
                    .antMatchers("/", "/registration").permitAll()//на главную страничку и на страничку регистрации зайти могут все
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")//loginPage находится на таком мэпинге
                    .permitAll()//разрешаем этим пользоваться всем
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)//менеджер мог входить в бд и искать роли
                .passwordEncoder(NoOpPasswordEncoder.getInstance())//шифрует пароли
                .usersByUsernameQuery("select username, password, active from user where username=?")//чтоб система нашла пользователя по его имнни
                .authoritiesByUsernameQuery("select user.username, user_role.roles from user inner join user_role on user.id = user_role.user_id " +
                        "where user.username=?;");//помогает спрингу получить список юзеров с их ролями
    }
}
