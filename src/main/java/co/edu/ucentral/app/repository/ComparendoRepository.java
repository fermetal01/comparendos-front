package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Comparendo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Comparendo entity.
 */
@Repository
public interface ComparendoRepository extends MongoRepository<Comparendo, String> {

    @Query("{}")
    Page<Comparendo> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Comparendo> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Comparendo> findOneWithEagerRelationships(String id);
}
