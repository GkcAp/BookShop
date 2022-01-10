package com.readingisgood.bookshop.domain.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Document
public class Book {

    String id;
    String name;
    Double price;
    int stock;
    int currentUsageStock;

    public Book(BookSetup setUp) {

        this.id = setUp.getId();
        this.name = setUp.getName();
        this.price = setUp.getPrice();
        this.stock = setUp.getStockLimit();
        this.currentUsageStock = setUp.getCurrentUsageStock();
    }

    @JsonIgnore
    public void increaseCurrentUsageStock() {
        this.currentUsageStock++;
    }

    public boolean stockLimitReached() {
        return this.stock > 0 && this.currentUsageStock >= this.stock;
    }

}
