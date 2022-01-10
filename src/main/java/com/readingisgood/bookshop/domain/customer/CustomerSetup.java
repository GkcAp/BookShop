package com.readingisgood.bookshop.domain.customer;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
public class CustomerSetup {
    String id;
    String name;
    String surname;
    HashSet<String> orders;
}
