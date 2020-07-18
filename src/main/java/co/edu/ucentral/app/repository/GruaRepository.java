package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Grua;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Grua entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GruaRepository extends MongoRepository<Grua, String> {
}
