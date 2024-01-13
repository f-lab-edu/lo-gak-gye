package me.minkh.app.service.info.converter;

import lombok.extern.slf4j.Slf4j;
import me.minkh.app.dto.info.EquipmentResponseDto;
import me.minkh.app.dto.lostark.EquipmentDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class EquipmentDtoConverter {

    private final String[] words = { "악몽", "구원", "지배", "사멸", "환각" };

    public EquipmentResponseDto convert(EquipmentDto[] dtoList) {
        if (dtoList == null) {
            return new EquipmentResponseDto(null);
        }

        List<EquipmentDto> list = Arrays.stream(dtoList)
                .filter(e ->
                        e.getType().equals("투구") ||
                        e.getType().equals("상의") ||
                        e.getType().equals("하의") ||
                        e.getType().equals("장갑") ||
                        e.getType().equals("어깨"))
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
