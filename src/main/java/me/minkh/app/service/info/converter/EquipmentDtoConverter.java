package me.minkh.app.service.info.converter;

import lombok.extern.slf4j.Slf4j;
import me.minkh.app.dto.info.EquipmentResponseDto;
import me.minkh.app.dto.lostark.EquipmentDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static me.minkh.app.service.LostArkConstants.*;

@Slf4j
@Service
public class EquipmentDtoConverter {

    private final String[] words = { NIGHTMARE, SALVATION, DOMINION, ENTROPY, HALLUCINATION };

    public EquipmentResponseDto convert(EquipmentDto[] dtoList) {

        if (dtoList == null) {
            return new EquipmentResponseDto(null);
        }

        List<EquipmentDto> list = Arrays.stream(dtoList)
                .filter(e ->
                        e.getType().equals(HELM) ||
                        e.getType().equals(CHESTPIECE) ||
                        e.getType().equals(PANTS) ||
                        e.getType().equals(GLOVES) ||
                        e.getType().equals(PAULDRONS))
                .toList();

        Set<String> set = ConcurrentHashMap.newKeySet();
        for (EquipmentDto dto : list) {
            String name = dto.getName();
            for (String word: words) {
                if (name.contains(word)) {
                    set.add(word);
                }
            }
        }

        if (set.size() != 1) {
            return new EquipmentResponseDto(null);
        }

        String name = set.stream().findFirst().orElse(null);
        return new EquipmentResponseDto(name);
    }
}
