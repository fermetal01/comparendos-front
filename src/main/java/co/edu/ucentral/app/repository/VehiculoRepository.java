package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Vehiculo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the Vehiculo entity.
 */
@Repository
public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {

    @Query("{}")
    Page<Vehiculo> findAllWithEagerRelationships(Pageable pageable);

    @Query("{}")
    List<Vehiculo> findAllWithEagerRelationships();

    @Query("{'id': ?0}")
    Optional<Vehiculo> findOneWithEagerRelationships(String id);
}
