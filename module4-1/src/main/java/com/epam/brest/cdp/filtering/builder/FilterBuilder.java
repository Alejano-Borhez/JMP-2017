package com.epam.brest.cdp.filtering.builder;

import com.epam.brest.cdp.filtering.model.Filter;

/**
 * Simple Builder interface for creating instances of {@link Filter} entities
 *
 * Created by alexander_borohov on 5.3.17.
 */
public interface FilterBuilder {
    FilterBuilder copyFromFilter(Filter filter);
    FilterBuilder withName(String name);
    FilterBuilder withMapping(String mapping);
    FilterBuilder withMappings(String... mappings);
    Filter build();
}
