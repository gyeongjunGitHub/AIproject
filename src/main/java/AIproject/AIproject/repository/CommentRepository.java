package AIproject.AIproject.repository;

import AIproject.AIproject.entity.CommentEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {
    @PersistenceContext
    EntityManager em;
    public void saveComment(CommentEntity commentEntity) {
        em.persist(commentEntity);
    }

    public List<CommentEntity> findByBoardId(String board_Id) {
        return em.createQuery("select c from CommentEntity as c where c.board_Id=:board_Id",CommentEntity.class)
                .setParameter("board_Id", board_Id)
                .getResultList();
    }
}
