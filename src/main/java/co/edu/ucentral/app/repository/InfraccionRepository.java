package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Infraccion;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Infraccion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InfraccionRepository extends MongoRepository<Infraccion, String> {
}
