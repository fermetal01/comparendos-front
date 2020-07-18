package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.TipoSanguineo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the TipoSanguineo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TipoSanguineoRepository extends MongoRepository<TipoSanguineo, String> {
}
