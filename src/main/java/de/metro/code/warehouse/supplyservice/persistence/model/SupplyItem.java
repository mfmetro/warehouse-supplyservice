package de.metro.code.warehouse.supplyservice.persistence.model;

import java.util.Objects;

import com.google.common.base.MoreObjects;

public class SupplyItem {

    private Article article;
    private int quantity;

    public SupplyItem(final SupplyItem supplyItem) {
        this.article = new Article(supplyItem.article);
        this.quantity = supplyItem.quantity;
    }

    private SupplyItem(final Builder builder) {
        this.article = builder.article;
        this.quantity = builder.quantity;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(final Article article) {
        this.article = article;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, quantity);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SupplyItem)) {
            return false;
        }

        final SupplyItem other = (SupplyItem) obj;
        return Objects.equals(article, other.article) &&
            Objects.equals(quantity, other.quantity);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("article", article)
            .add("quantity", quantity)
            .toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Article article;
        private int quantity;

        public Builder withArticle(final Article article) {
            this.article = article;
            return this;
        }

        public Builder withQuantity(final int quantity) {
            this.quantity = quantity;
            return this;
        }

        public SupplyItem build() {
            return new SupplyItem(this);
        }
    }
}
