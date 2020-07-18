package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Licencia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Licencia entity.
 */
@Repository
public interface LicenciaRepository extends MongoRepository<Licencia, String> {

    @Query("{}")
    Page<Licencia> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Licencia> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Licencia> findOneWithEagerRelationships(String id);
}
