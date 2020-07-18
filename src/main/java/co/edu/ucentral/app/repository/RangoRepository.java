package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Rango;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Rango entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RangoRepository extends MongoRepository<Rango, String> {
}
