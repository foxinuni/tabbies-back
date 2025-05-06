package dev.downloadablefox.tabbies.webserver.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.downloadablefox.tabbies.webserver.entities.Procedure;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long>{

    Collection<Procedure> findByPetId(Long petId);
}