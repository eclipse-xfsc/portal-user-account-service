package eu.gaiax.user.account.repo;

import eu.gaiax.user.account.repo.entities.FrRequestTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FrRequestTypeDao extends JpaRepository<FrRequestTypeEntity, Long> {
    Optional<FrRequestTypeEntity> findByName(String name);
}
