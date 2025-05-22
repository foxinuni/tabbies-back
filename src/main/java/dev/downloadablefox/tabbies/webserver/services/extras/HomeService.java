package dev.downloadablefox.tabbies.webserver.services.extras;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import dev.downloadablefox.tabbies.webserver.dtos.CatCareTipDTO;

@Service
public class HomeService {

    private static final List<String> CAT_TIPS = Arrays.asList(
        "Cepilla el pelaje de tu gato con frecuencia para evitar bolas de pelo.",
        "Limpia su caja de arena a diario para mantener la higiene.",
        "Asegúrate de que tenga agua fresca siempre disponible.",
        "Llévalo al veterinario al menos una vez al año.",
        "Juega con tu gato todos los días para mantenerlo activo y feliz."
    );

    public CatCareTipDTO getRandomCatCareTip() {
        Random rand = new Random();
        String tip = CAT_TIPS.get(rand.nextInt(CAT_TIPS.size()));
        return new CatCareTipDTO(tip);
    }
}
