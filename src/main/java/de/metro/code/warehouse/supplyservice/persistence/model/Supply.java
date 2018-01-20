package de.metro.code.warehouse.supplyservice.persistence.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.google.common.base.MoreObjects;

public class Supply {

    private String id;
    private LocalDateTime arrivalTime;
    private List<SupplyItem> supplyItems;

    private Supply(final Builder builder) {
        this.id = builder.id;
        this.arrivalTime = builder.arrivalTime;
        this.supplyItems = builder.supplyItems;
    }

    public Supply(final Supply supply) {
        this.id = supply.id;
        this.arrivalTime = supply.arrivalTime;
        this.supplyItems = supply.supplyItems.stream().map(SupplyItem::new).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(final LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<SupplyItem> getSupplyItems() {
        return supplyItems;
    }

    public void setSupplyItems(final List<SupplyItem> supplyItems) {
        this.supplyItems = supplyItems;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, arrivalTime, supplyItems);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Supply)) {
            return false;
        }

        final Supply other = (Supply) obj;
        return Objects.equals(id, other.id) &&
            Objects.equals(arrivalTime, other.arrivalTime) &&
            Objects.equals(supplyItems, other.supplyItems);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("id", id)
            .add("arrivalTime", arrivalTime)
            .add("supplyItems", supplyItems)
            .toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private LocalDateTime arrivalTime;
        private List<SupplyItem> supplyItems;

        public Builder withId(final String id) {
            this.id = id;
            return this;
        }

        public Builder withArrivalTime(final LocalDateTime arrivalTime) {
            this.arrivalTime = arrivalTime;
            return this;
        }

        public Builder withSupplyItems(final List<SupplyItem> supplyItems) {
            this.supplyItems = supplyItems.stream().map(SupplyItem::new).collect(Collectors.toList());
            return this;
        }

        public Supply build() {
            return new Supply(this);
        }
    }

}
