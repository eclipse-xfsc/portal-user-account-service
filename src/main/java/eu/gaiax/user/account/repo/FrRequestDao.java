package eu.gaiax.user.account.repo;

import eu.gaiax.user.account.repo.entities.FrRequest;
import eu.gaiax.user.account.repo.entities.FrRequestTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface FrRequestDao extends JpaRepository<FrRequest, Long> {
  Optional<FrRequest> findByEmailAndRequestType(@NonNull String email, @NonNull FrRequestTypeEntity requestType);

  @Override
  @NonNull
  Optional<FrRequest> findById(@NonNull Long aLong);
}
