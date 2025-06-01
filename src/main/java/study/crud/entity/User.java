package study.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import study.crud.dto.UserUpdateForm;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString(of = {"username","email"})
public class User extends BaseUpdatableEntity{

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false,unique = true)
    private String password;

    @Column(nullable = false,unique = true)
    private String email;

    public void updateUser(UserUpdateForm form) {
        if(form.getUsername()!=username) {
            this.username = form.getUsername();
        }
        changeUpdatedAt();
    }
}
