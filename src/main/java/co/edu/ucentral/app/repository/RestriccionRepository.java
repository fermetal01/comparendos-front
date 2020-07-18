package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Restriccion;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Restriccion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RestriccionRepository extends MongoRepository<Restriccion, String> {
}
