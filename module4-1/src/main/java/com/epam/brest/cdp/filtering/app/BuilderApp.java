package com.epam.brest.cdp.filtering.app;

import com.epam.brest.cdp.filtering.model.Filter;
import com.epam.brest.cdp.filtering.model.MyCustomFilterImmutable;
import com.epam.brest.cdp.filtering.model.MyCustomFilterMutable;

import java.util.List;

/**
 * Created by alexander_borohov on 5.3.17.
 */
public class BuilderApp {
    public static void main(String[] args) {
        String name1 = "Filter Mutable";
        String name2 = "Filter Immutable";
        String mapping1 = "Mapping1";
        String mapping2 = "Mapping2";
        String mapping3 = "Mapping3";
        String mapping4 = "Mapping4";
        String mapping5 = "Mapping5";
//      Using withMappings() method
        Filter immutable = new MyCustomFilterImmutable.FilterBuilder()
                .withName(name1)
                .withMappings(mapping1, mapping2, mapping3, mapping4, mapping5).build();
//      Using several withMapping() methods
        Filter mutable = new MyCustomFilterMutable.FilterBuilder()
                .withName(name2)
                .withMapping(mapping1)
                .withMapping(mapping2)
                .withMapping(mapping3)
                .withMapping(mapping4)
                .withMapping(mapping5)
                .build();

        System.out.println("Here is immutable filter:");
        System.out.println(immutable);
        System.out.println("Here is mutable filter:");
        System.out.println(mutable);

        System.out.println("Trying to modify (Only Lists as Strings are immutable):");
//      Nothing will change as this filter is immutable
        List<String> mappingsImmutable = immutable.getMappings();
        for (int i = 0; i < mappingsImmutable.size(); i++) {
            mappingsImmutable.set(i, mapping3);
        }
//      Mappings is changed while getMapping() method returns entire mutable List
        List<String> mappingsMutable = mutable.getMappings();
        for (int i = 0; i < mappingsMutable.size(); i++) {
            mappingsMutable.set(i, mapping3);
        }

        System.out.println("Here is immutable filter:");
        System.out.println(immutable);
        System.out.println("Here is mutable filter:");
        System.out.println(mutable);
//      Just trying to use a builder with only 1 of 2 set fields:
        System.out.println("Just trying to use a builder with only 1 of 2 set fields:");
        Filter filter = new MyCustomFilterImmutable.FilterBuilder().withName("A filter").build();
        System.out.println(filter);
//      Just trying to build an empty Filter:
        System.out.println("Just trying to build an empty Filter:");
        Filter emptyFilter = new MyCustomFilterMutable.FilterBuilder().build();
        System.out.println(emptyFilter);
    }
}
