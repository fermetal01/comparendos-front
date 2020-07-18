package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Combustible;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Combustible entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CombustibleRepository extends MongoRepository<Combustible, String> {
}
