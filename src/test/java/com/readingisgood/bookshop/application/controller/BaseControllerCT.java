package com.readingisgood.bookshop.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.readingisgood.bookshop.application.common.CustomHttpHeaders.*;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class BaseControllerCT {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected HttpHeaders httpHeaders;

    @BeforeEach
    public void before() {
        httpHeaders = new HttpHeaders();
        httpHeaders.add(STOREFRONT_ID, "1");
        httpHeaders.add(APPLICATION_ID, "1");
        httpHeaders.add(CORRELATION_ID, "23423434");
        httpHeaders.add(AGENT_NAME, "bookShop-api");
        httpHeaders.add("Content-Type", "application/json");
    }
}
