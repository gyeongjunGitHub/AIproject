package AIproject.AIproject.controller;

import AIproject.AIproject.dto.UserDTO;
import AIproject.AIproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public String goLoginForm(){
        return "loginForm";
    }

    @PostMapping("/loginProc")
    public String loginProc(@ModelAttribute UserDTO userDTO, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        int result = userService.loginProc(userDTO, request);
        model.addAttribute("Id", session.getAttribute("Id"));
        return "main";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        userService.logout(request);
        return "main";
    }

    @GetMapping("/join")
    public String goJoinForm(){
        return "joinForm";
    }

    @PostMapping("/joinProc")
    public String joinProc(@ModelAttribute UserDTO userDTO){
        userService.joinProc(userDTO);
        return "main";
    }
}
