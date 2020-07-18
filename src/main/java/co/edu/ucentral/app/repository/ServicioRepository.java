package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Servicio;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Servicio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServicioRepository extends MongoRepository<Servicio, String> {
}
