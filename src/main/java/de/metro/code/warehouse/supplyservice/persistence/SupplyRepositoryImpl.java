package de.metro.code.warehouse.supplyservice.persistence;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import de.metro.code.warehouse.supplyservice.persistence.model.Supply;

@Component
public class SupplyRepositoryImpl implements SupplyRepository {

    private Map<String, Supply> supplies = new HashMap<>();

    @Override
    public Supply save(final Supply supply) {
        final Supply newSupply = new Supply(supply);
        supplies.put(supply.getId(), newSupply);
        return newSupply;
    }

    @Override
    public List<Supply> findRecent(final LocalDateTime earliestDateTime) {
        return supplies.values().stream()
            .filter(supply -> !earliestDateTime.isAfter(supply.getArrivalTime()))
            .sorted(this::compareOrdersByCreationTime)
            .map(Supply::new)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Supply> getById(final String id) {
        final Supply supply = supplies.get(id);

        if (supply == null) {
            return Optional.empty();
        } else {
            return Optional.of(supply);
        }
    }

    private int compareOrdersByCreationTime(final Supply firstSupply, final Supply secondSupply) {
        return firstSupply.getArrivalTime().compareTo(secondSupply.getArrivalTime());
    }

}
