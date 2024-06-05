package AIproject.AIproject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "comment")
@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private String comment_id;

    @Column(name = "comment_content")
    private String comment_content;

    @Column (name = "board_Id")
    private String board_Id;
}
