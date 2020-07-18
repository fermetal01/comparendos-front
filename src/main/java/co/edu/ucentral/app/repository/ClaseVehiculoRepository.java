package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.ClaseVehiculo;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the ClaseVehiculo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaseVehiculoRepository extends MongoRepository<ClaseVehiculo, String> {
}
