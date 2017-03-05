package com.epam.brest.cdp.filtering.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Simple {@link Filter} implementation with mutable fields
 * Created by alexander_borohov on 5.3.17.
 */
public class MyCustomFilterMutable implements Filter {
    private String name;
    private List<String> mappings;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<String> getMappings() {
        return this.mappings;
    }

    private MyCustomFilterMutable(FilterBuilder builder) {
        this.name = builder.name;
        this.mappings = builder.mappings;
    }

    public static class FilterBuilder implements com.epam.brest.cdp.filtering.builder.FilterBuilder {
        private String name;
        private List<String> mappings;
        public FilterBuilder() {
            this.mappings = new ArrayList<>();
            this.name = "";
        }

        @Override
        public FilterBuilder copyFromFilter(Filter filter) {
            this.mappings = filter.getMappings();
            this.name = filter.getName();
            return this;
        }

        @Override
        public com.epam.brest.cdp.filtering.builder.FilterBuilder withName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public com.epam.brest.cdp.filtering.builder.FilterBuilder withMapping(String mapping) {
            this.mappings.add(mapping);
            return this;
        }

        @Override
        public com.epam.brest.cdp.filtering.builder.FilterBuilder withMappings(String... mappings) {
            this.mappings = Arrays.asList(mappings);
            return this;
        }

        @Override
        public Filter build() {
            return new MyCustomFilterMutable(this);
        }
    }

    @Override
    public String toString() {
        return "MyCustomFilterMutable{" +
                "name='" + name + '\'' +
                ", mappings=" + mappings +
                '}';
    }
}
