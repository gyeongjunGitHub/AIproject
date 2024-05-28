package AIproject.AIproject.repository;

import AIproject.AIproject.entity.BoardEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JpaBoardRepository implements BoardRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public BoardEntity save(BoardEntity boardEntity) {
        em.persist(boardEntity);
        return boardEntity;
    }

    @Override
    public List<BoardEntity> findByCategory(String category) {
        return em.createQuery("select b from BoardEntity as b where b.category=:category", BoardEntity.class)
                .setParameter("category", category)
                .getResultList();
    }
}
