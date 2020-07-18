package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Ciudad;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Ciudad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CiudadRepository extends MongoRepository<Ciudad, String> {
}
