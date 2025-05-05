package dev.downloadablefox.tabbies.webserver.services.procedure;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.entities.Procedure;
import dev.downloadablefox.tabbies.webserver.repositories.ProcedureRepository;

@Service
public class ProcedureServiceImpl implements ProcedureService{
    @Autowired
    private final ProcedureRepository procedureRepository;

    public ProcedureServiceImpl(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    @Override
        public Collection<Procedure> getProceduresByPetId(Long petId) {
            System.out.println(petId);
            return procedureRepository.findByPetId(petId);
        }

    @Override
    public Collection<Procedure> getAllProcedures() {
        return procedureRepository.findAll();
    }

    @Override
    public Procedure getProcedureById(Long id) {
        return procedureRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Procedure not found with id: " + id));
    }

    @Override
    public void createProcedure(Procedure procedure) {
        procedureRepository.save(procedure);
    }

    @Override
    public void updateProcedure(Long id, Procedure procedure) {
        if (!procedureRepository.existsById(id)) {
            throw new IllegalArgumentException("Procedure not found with id: " + id);
        }

        procedure.setId(id);
        procedureRepository.save(procedure);
    }

    @Override
    public void deleteProcedure(Long id) {
        if (!procedureRepository.existsById(id)) {
            throw new IllegalArgumentException("Procedure not found with id: " + id);
        }

        procedureRepository.deleteById(id);
    }
}
