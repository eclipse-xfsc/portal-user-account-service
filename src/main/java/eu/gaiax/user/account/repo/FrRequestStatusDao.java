package eu.gaiax.user.account.repo;

import eu.gaiax.user.account.repo.entities.FrRequestStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FrRequestStatusDao extends JpaRepository<FrRequestStatusEntity, Long> {
    Optional<FrRequestStatusEntity> findByName(String name);
}
