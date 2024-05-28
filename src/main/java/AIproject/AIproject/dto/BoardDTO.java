package AIproject.AIproject.dto;

import AIproject.AIproject.entity.BoardEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private Long board_Id;
    private String category;
    private String content;

    public BoardDTO(){

    }
    public BoardDTO(BoardEntity boardEntity){
        this.board_Id = boardEntity.getBoard_Id();
        this.category = boardEntity.getCategory();
        this.content = boardEntity.getContent();
    }
}
