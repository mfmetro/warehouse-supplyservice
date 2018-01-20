package de.metro.code.warehouse.supplyservice.persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import de.metro.code.warehouse.supplyservice.persistence.model.Supply;

public interface SupplyRepository {

    Supply save(Supply supply);

    List<Supply> findRecent(LocalDateTime earliestDateTime);

    Optional<Supply> getById(String id);
}
