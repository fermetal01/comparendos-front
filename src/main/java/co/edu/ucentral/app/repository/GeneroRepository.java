package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Genero;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Genero entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GeneroRepository extends MongoRepository<Genero, String> {
}
