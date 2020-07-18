package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Patio;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Patio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PatioRepository extends MongoRepository<Patio, String> {
}
