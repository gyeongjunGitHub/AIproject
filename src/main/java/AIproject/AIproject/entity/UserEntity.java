package AIproject.AIproject.entity;

import AIproject.AIproject.dto.UserDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity {
    @Id
    @Column(name = "Id")
    private String Id;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public UserEntity(){};
    public UserEntity(UserDTO userDTO){
        this.Id = userDTO.getId();
        this.password = userDTO.getPassword();
        this.role = userDTO.getRole();
    }
}
