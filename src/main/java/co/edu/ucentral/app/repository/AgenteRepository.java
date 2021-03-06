package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Agente;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Agente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgenteRepository extends MongoRepository<Agente, String> {
}
