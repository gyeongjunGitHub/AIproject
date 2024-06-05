package AIproject.AIproject.service;

import AIproject.AIproject.dto.BoardDTO;
import AIproject.AIproject.dto.CommentDTO;
import AIproject.AIproject.entity.BoardEntity;
import AIproject.AIproject.entity.CommentEntity;
import AIproject.AIproject.repository.BoardRepository;
import AIproject.AIproject.repository.CommentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public BoardDTO save(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity(boardDTO);
        return new BoardDTO(boardRepository.save(boardEntity));
    }

    public List<BoardDTO> findByCategory(String category){
        List<BoardEntity> byCategory = boardRepository.findByCategory(category);
        List<BoardDTO> result = new ArrayList<>();
        for(BoardEntity b : byCategory){
            BoardDTO boardDTO = new BoardDTO(b);
            result.add(boardDTO);
        }
        return result;
    }

    public BoardDTO getBoardDetail(Long boardId) {
        BoardEntity byId = boardRepository.findById(boardId);
        BoardDTO boardDTO = new BoardDTO(byId);
        return boardDTO;
    }

    @Transactional
    public void saveComment(String value) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommentDTO commentDTO = objectMapper.readValue(value, CommentDTO.class);
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment_content(commentDTO.getComment_content());
        commentEntity.setBoard_Id(commentDTO.getBoard_Id());
        commentRepository.saveComment(commentEntity);

    }

    public List<CommentEntity> getCommentList(String value) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommentDTO commentDTO = objectMapper.readValue(value, CommentDTO.class);
        return commentRepository.findByBoardId(commentDTO.getBoard_Id());
    }
    public List<CommentEntity> getCommentList1(String board_Id) throws JsonProcessingException {

        return commentRepository.findByBoardId(board_Id);
    }
}
