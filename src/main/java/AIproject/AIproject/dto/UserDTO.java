package AIproject.AIproject.dto;

import AIproject.AIproject.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String Id;
    private String password;
    private String role;
    public UserDTO(){};
    public UserDTO(UserEntity userEntity){
        this.Id = userEntity.getId();
        this.password = userEntity.getPassword();
        this.role = userEntity.getRole();
    }
}
