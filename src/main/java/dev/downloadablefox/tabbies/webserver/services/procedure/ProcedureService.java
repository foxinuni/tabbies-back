package dev.downloadablefox.tabbies.webserver.services.procedure;

import java.util.Collection;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.Procedure;

@Service
public interface ProcedureService {
    public Collection<Procedure> getAllProcedures();
    public Procedure getProcedureById(Long id);
    public void createProcedure(Procedure procedure);
    public void updateProcedure(Long id, Procedure procedure);
    public void deleteProcedure(Long id);
}