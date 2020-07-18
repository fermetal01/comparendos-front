package co.edu.ucentral.app.repository;

import co.edu.ucentral.app.domain.OrganismoTransito;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the OrganismoTransito entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganismoTransitoRepository extends MongoRepository<OrganismoTransito, String> {
}
