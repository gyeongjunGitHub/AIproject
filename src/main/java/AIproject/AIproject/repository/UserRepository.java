package AIproject.AIproject.repository;

import AIproject.AIproject.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    @PersistenceContext
    EntityManager em;

    public Optional<UserEntity> findById(String Id){
        UserEntity userEntity = em.find(UserEntity.class, Id);
        return Optional.ofNullable(userEntity);
    }
}
