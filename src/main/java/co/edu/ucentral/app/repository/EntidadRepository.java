package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Entidad;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Entidad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EntidadRepository extends MongoRepository<Entidad, String> {
}
