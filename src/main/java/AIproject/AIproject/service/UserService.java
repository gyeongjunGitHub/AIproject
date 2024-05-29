package AIproject.AIproject.service;

import AIproject.AIproject.dto.UserDTO;
import AIproject.AIproject.entity.UserEntity;
import AIproject.AIproject.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public int loginProc(UserDTO userDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserEntity user = new UserEntity(userDTO);

        Optional<UserEntity> byId = userRepository.findById(user.getId());
        if(byId.isPresent()){
            if(user.getPassword().equals(byId.get().getPassword())){
                UserDTO result = new UserDTO(byId.get());

                // 로그인 성공 시 세션에 userInfo 등록
                session.setAttribute("Id", result.getId());
                session.setAttribute("role", result.getRole());
                System.out.println(result.getId() + " -> login");

                return 1; // 로그인 성공
            }else {
                System.out.println("비밀번호가 일치하지 않습니다.");
                return 0; // 비밀번호 불일치
            }
        }else {
            System.out.println("존재하지 않는 아이디.");
            return -1; // 존재하지 않는 아이디
        }
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("Id") + " -> logout");
        session.removeAttribute("Id");
    }

    @Transactional
    public void joinProc(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRole("ROLE_USER");
        userRepository.join(userEntity);
    }
}
