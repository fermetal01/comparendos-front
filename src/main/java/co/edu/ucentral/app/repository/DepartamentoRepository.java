package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.Departamento;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the Departamento entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepartamentoRepository extends MongoRepository<Departamento, String> {
}
