package dev.downloadablefox.tabbies.webserver.controllers;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import dev.downloadablefox.tabbies.webserver.dtos.CatCareTipDTO;
import dev.downloadablefox.tabbies.webserver.dtos.TreatedCatsStatsDTO;
import dev.downloadablefox.tabbies.webserver.services.procedure.ProcedureService;

@RestController
@RequestMapping("/home")
public class HomeController {

    private static final List<String> TIPS = List.of(
        "Cepilla a tu gato con regularidad para evitar bolas de pelo.",
        "Proporciónale agua fresca todos los días.",
        "Limpia su caja de arena al menos una vez al día.",
        "Juega con tu gato al menos 15 minutos diarios.",
        "Lleva a tu gato al veterinario una vez al año para revisión.",
        "Evita darle comida para humanos, especialmente chocolate o cebolla."
    );

    @Autowired
    private ProcedureService procedureService;

    @GetMapping("/cat-tip")
    public CatCareTipDTO getRandomTip() {
        Random random = new Random();
        String tip = TIPS.get(random.nextInt(TIPS.size()));
        return new CatCareTipDTO(tip);
    }

    @GetMapping("/treated-cats-count")
    public TreatedCatsStatsDTO getTreatedCatsCount() {
        long count = procedureService.getAllProcedures().stream()
            .map(p -> p.getPet().getId())
            .distinct()
            .count();
        return new TreatedCatsStatsDTO(count);
    }
}
