package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Persona;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Persona entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PersonaRepository extends MongoRepository<Persona, String> {
}
