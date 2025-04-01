package dev.downloadablefox.tabbies.webserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.downloadablefox.tabbies.webserver.entities.Procedure;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Long>{
    
}