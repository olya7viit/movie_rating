package by.matusevichChercasova.movieRating.controller;

import by.matusevichChercasova.movieRating.domain.Role;
import by.matusevichChercasova.movieRating.domain.User;
import by.matusevichChercasova.movieRating.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        //если юзера нет
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));//здесь дб коллекция Set, мы заменяем на Collections.singleton(Role.USER), т.е. с единственной записью
        userRepo.save(user);

        return "redirect:/login";
    }
}
