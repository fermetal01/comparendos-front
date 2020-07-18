package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.EstadoComparendo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the EstadoComparendo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EstadoComparendoRepository extends MongoRepository<EstadoComparendo, String> {
}
