package de.metro.code.warehouse.supplyservice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.metro.code.warehouse.supplyservice.persistence.SupplyRepository;
import de.metro.code.warehouse.supplyservice.persistence.model.Supply;

@RestController
public class SuppliesController {

    @Autowired
    private SupplyRepository supplyRepository;

    @RequestMapping(path = "/supply/{id}", method = RequestMethod.GET)
    public @ResponseBody Supply getSupply(final @PathVariable("id") String id) {
        return supplyRepository.getById(id).orElseThrow(() -> new ResourceNotFoundException());
    }

    @RequestMapping(path = "/supplyFeed", method = RequestMethod.GET)
    public @ResponseBody List<String> supplyFeed(
        final @RequestParam("date") String dateString,
        final @RequestParam(name = "time", defaultValue = "00:00:00") String timeString) {

        final LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
        final LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ISO_TIME);

        final LocalDateTime pointInTime = LocalDateTime.of(date, time);

        return supplyRepository.findRecent(pointInTime).stream()
            .map(Supply::getId)
            .collect(Collectors.toList());
    }
}
