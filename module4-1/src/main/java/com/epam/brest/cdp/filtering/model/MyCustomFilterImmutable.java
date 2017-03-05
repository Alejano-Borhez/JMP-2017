package com.epam.brest.cdp.filtering.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple {@link Filter} implementation with immutable fields
 * Created by alexander_borohov on 5.3.17.
 */
public class MyCustomFilterImmutable implements Filter {
    private String name;
    private List<String> mappings;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<String> getMappings() {
        return mappings.stream().collect(Collectors.toList());
    }

    private MyCustomFilterImmutable(FilterBuilder builder) {
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
            return new MyCustomFilterImmutable(this);
        }
    }

    @Override
    public String toString() {
        return "MyCustomFilterImmutable{" +
                "name='" + name + '\'' +
                ", mappings=" + mappings +
                '}';
    }
}
