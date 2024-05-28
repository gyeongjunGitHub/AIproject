package AIproject.AIproject.service;

import AIproject.AIproject.dto.BoardDTO;
import AIproject.AIproject.entity.BoardEntity;
import AIproject.AIproject.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;

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
}
