package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Categoria;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Categoria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CategoriaRepository extends MongoRepository<Categoria, String> {
}
