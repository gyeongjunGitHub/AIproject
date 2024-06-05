package AIproject.AIproject.controller;

import AIproject.AIproject.dto.BoardDTO;
import AIproject.AIproject.dto.ValueDTO;
import AIproject.AIproject.entity.CommentEntity;
import AIproject.AIproject.service.BoardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/sendtext")
    @ResponseBody
    public ResponseEntity<BoardDTO> send(@RequestBody ValueDTO valueDTO, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("Id") == null){

            return ResponseEntity.ok(new BoardDTO());
        }

        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setCategory(valueDTO.getCategory());
        boardDTO.setContent(valueDTO.getContent());
        String url = "http://localhost:5000/text";
        String ram = "";

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod("POST");
            // 요청 본문 데이터 설정
            String postData = "text=" + boardDTO.getContent();
            conn.setDoOutput(true); // POST 요청으로 설정
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = postData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            BufferedReader buf = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            while ((line = buf.readLine()) != null) {
                ram = ram + line + "\n";
                if (ram.toString().contains("ok")) {
                    System.out.println("test!");
                }
            }
            buf.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ram);
        boardDTO.setContent(ram);
        BoardDTO result = boardService.save(boardDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/board")
    public String boardList(Model model, @RequestParam String category) {
        model.addAttribute("boardList", boardService.findByCategory(category));
        return "boardList";
    }

    @GetMapping("/board/detail")
    public String boardDetail(@RequestParam(name = "board_Id")Long board_Id, Model model) throws JsonProcessingException {
        BoardDTO result = boardService.getBoardDetail(board_Id);
        List<CommentEntity> commentList1 = boardService.getCommentList1(String.valueOf(board_Id));
        model.addAttribute("commentList1", commentList1);
        model.addAttribute("result", result);
        return "detail";
    }

    @PostMapping("/commentContent")
    @ResponseBody
    public ResponseEntity<List<CommentEntity>> commentSave(@RequestBody String value) throws JsonProcessingException {
        boardService.saveComment(value);
        List<CommentEntity> commentList = boardService.getCommentList(value);

        return ResponseEntity.ok(commentList);
    }

}
