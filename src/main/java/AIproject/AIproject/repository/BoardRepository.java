package AIproject.AIproject.repository;

import AIproject.AIproject.dto.BoardDTO;
import AIproject.AIproject.entity.BoardEntity;

import java.util.List;

public interface BoardRepository {

    BoardEntity save(BoardEntity boardEntity);
    List<BoardEntity> findByCategory(String category);

    BoardEntity findById(Long boardId);
}
