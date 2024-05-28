package AIproject.AIproject.entity;

import AIproject.AIproject.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.stereotype.Controller;

@Entity
@Getter
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_Id")
    private Long board_Id;

    @Column(name = "category")
    private String category;

    @Column(name = "content")
    private String content;

    public BoardEntity(){

    }
    public BoardEntity(BoardDTO boardDTO){
        this.category = boardDTO.getCategory();
        this.content = boardDTO.getContent();
    }

}
