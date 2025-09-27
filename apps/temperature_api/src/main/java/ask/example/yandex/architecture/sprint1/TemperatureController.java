package ask.example.yandex.architecture.sprint1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class TemperatureController {

    private static final Logger log = LoggerFactory.getLogger(TemperatureController.class);
    private final EmulationService emulationService;
    private final Random random = new Random();

    public TemperatureController(EmulationService emulationService) {
        this.emulationService = emulationService;
    }

    /**
     * Обрабатывает GET-запросы к эндпоинту /temperature.
     * Возвращает случайные температурные данные для указанного местоположения.
     *
     * @param location строковый параметр запроса, указывающий географическое положение
     * @return объект TemperatureResponse со сгенерированными данными
     */
    @GetMapping("/temperature")
    public TemperatureResponse getTemperature(@RequestParam String location) {
        log.info("Получен запрос температуры в location={}", location);
        return emulationService.generateRandomForLocation(location);
    }


    /**
     * Обрабатывает GET-запросы к эндпоинту /temperature/id.
     * Возвращает случайные температурные данные для указанного местоположения.
     *
     * @param id параметр запроса, указывающий идентификатор устройства
     * @return объект TemperatureResponse со сгенерированными данными
     */
    @GetMapping("/temperature/{id}")
    public TemperatureResponse getTemperatureById(@PathVariable String id) {
        log.info("Получен запрос температуры для id={}", id);
        return emulationService.generateRandomForId(id);
    }

}
