package pl.put.poznan.sorting.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.sorting.logic.SortingMadness;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sort")
public class SortingMadnessController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(SortingMadnessController.class);

    @GetMapping("/objects")
    public Object sortObjects(
            @RequestParam(value = "algo", defaultValue = "default") String[] algo,
            @RequestParam(value = "attr", required = false) String attr,
            @RequestParam(value = "reverse", defaultValue = "false") Boolean reverse,
            @RequestParam(value = "autoAlgo", defaultValue = "false") Boolean autoAlgorithm,
            @RequestBody String data
    ) {
        logger.info("Wywołano GET /sort z parametrami: algo={}, attr={}", Arrays.toString(algo), attr);

        logger.debug("Otrzymano dane do posortowania: {}", data);

        List<Map<String, Object>> dynamicDataList = new ArrayList<>();

        try {
            // Parsowanie dynamiczne z JSON
            logger.info("Rozpocęzcie przetwarzania JSON...");
            List<Map<String, Object>> parsedData = objectMapper.readValue(
                    data, objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));

            dynamicDataList = parsedData;
            logger.info("Zakończono przetwarzanie JSON.\nLiczba obiektów do przetworzenia: {}", parsedData.size());

        } catch (JsonProcessingException e) {
            logger.error("Bład podczas przetwarzania pliku JSON: {}", e.getMessage());
            throw new RuntimeException("Failed to parse JSON");
        }

        // Funkcja sortująca
        SortingMadness<Map<String, Object>> sorter = new SortingMadness<>(
                dynamicDataList.toArray(new Map[0]), algo, attr, reverse, autoAlgorithm);

        return sorter.getResult();
    }

    @GetMapping("/primitives")
    public Object sortArray(
            @RequestParam(value = "algo", defaultValue = "default") String[] algo,
            @RequestParam(value = "reverse", defaultValue = "false") Boolean reverse,
            @RequestParam(value = "autoAlgo", defaultValue = "false") Boolean autoAlgorithm,
            @RequestBody String data
    ) {
        logger.info("Wywołano GET /sort z parametrami: algo={}", Arrays.toString(algo));

        logger.debug("Otrzymano dane do posortowania: {}", data);

        List<Object> parsedDataList;

        try {
            logger.info("Rozpocęzcie przetwarzania JSON...");
            parsedDataList = objectMapper.readValue(
                    data, objectMapper.getTypeFactory().constructCollectionType(List.class, Object.class));
            logger.info("Zakończono przetwarzanie JSON.\nLiczba obiektów do przetworzenia: {}", parsedDataList.size());
        } catch (JsonProcessingException e) {
            logger.error("Bład podczas przetwarzania pliku JSON: {}", e.getMessage());
            throw new RuntimeException("Failed to parse JSON");
        }

        SortingMadness<Object> sorter = new SortingMadness<>(
                parsedDataList.toArray(new Object[0]), algo,null, reverse,autoAlgorithm);

        logger.debug("Zakończono sortowanie.");
        return sorter.getResult();
    }
}
