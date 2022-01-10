package com.readingisgood.bookshop.domain.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSetup {
    String id;
    String name;
    Double price;
    int stockLimit;
    int currentUsageStock;
}
