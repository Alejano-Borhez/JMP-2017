package com.epam.brest.cdp.filtering.model;

import java.util.List;

/**
 * Represents a simple Filter entity. Has only getters methods as we try to
 * create new instances through Builder pattern
 * ALL IMPLEMENTATIONS SHOULD USE PRIVATE CONSTRUCTORS
 * Created by alexander_borohov on 5.3.17.
 */
public interface Filter {
    String getName();
    List<String> getMappings();
}
