package by.matusevichChercasova.movieRating.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)//автоматически формирует доп табл для хранения enum
    //роли лежат в отдельной таблице. fetch = FetchType.EAGER - hibernate сразу по запросу пользователя будет подгружать роли
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))//указывает, что данное поле будет храниться в отдельной таблице, для кот мы не описывали мэпинг
    @Enumerated(EnumType.STRING)//Enum хванить в виде строки
    private Set<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
