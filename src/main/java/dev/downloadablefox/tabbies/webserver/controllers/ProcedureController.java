package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.downloadablefox.tabbies.webserver.dtos.ProcedureUpsert;
import dev.downloadablefox.tabbies.webserver.dtos.ProcedureView;
import dev.downloadablefox.tabbies.webserver.entities.Procedure;
import dev.downloadablefox.tabbies.webserver.services.common.ModelMapper;
import dev.downloadablefox.tabbies.webserver.services.procedure.ProcedureService;

@Controller
@RequestMapping("/procedures")
public class ProcedureController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProcedureService procedureService;

    @GetMapping("/")
    @ResponseBody
    public Collection<ProcedureView> listProcedures() {
        return procedureService.getAllProcedures()
            .stream()
            .map(modelMapper::toProcedureDTO)
            .toList();
    }

    @PostMapping("/")
    @ResponseBody
    public ProcedureView createProcedure(@RequestBody ProcedureUpsert dto) {
        Procedure procedure = modelMapper.toProcedureEntity(dto);
        procedureService.createProcedure(procedure);
        return modelMapper.toProcedureDTO(procedure);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ProcedureView getProcedureById(@PathVariable Long id) {
        Procedure procedure = procedureService.getProcedureById(id);
        return modelMapper.toProcedureDTO(procedure);
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    public ProcedureView updateProcedure(@PathVariable Long id, @RequestBody ProcedureUpsert dto) {
        Procedure procedure = modelMapper.toProcedureEntity(dto);
        procedureService.updateProcedure(id, procedure);
        return modelMapper.toProcedureDTO(procedure);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteProcedure(@PathVariable Long id) {
        procedureService.deleteProcedure(id);
        return ResponseEntity.noContent().build();
    }
}
