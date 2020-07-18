package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.TipoLicencia;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the TipoLicencia entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoLicenciaRepository extends MongoRepository<TipoLicencia, String> {
}
