package com.epam.brest.jmp.jmm;

import java.util.Objects;

/**
 * Just simple object to be created during program execution
 * Created by alexander_borohov on 16.6.17.
 */
public class DummyObject {
    private String propertyA;
    private Integer propertyB;

    public DummyObject(String propertyA, Integer propertyB) {
        this.propertyA = propertyA;
        this.propertyB = propertyB;
    }

    public String getPropertyA() {
        return propertyA;
    }

    public void setPropertyA(String propertyA) {
        this.propertyA = propertyA;
    }

    public Integer getPropertyB() {
        return propertyB;
    }

    public void setPropertyB(Integer propertyB) {
        this.propertyB = propertyB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DummyObject that = (DummyObject) o;
        return Objects.equals(getPropertyA(), that.getPropertyA()) &&
                Objects.equals(getPropertyB(), that.getPropertyB());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPropertyA(), getPropertyB());
    }

    @Override
    public String toString() {
        return "DummyObject{" +
                "propertyA='" + propertyA + '\'' +
                ", propertyB=" + propertyB +
                '}';
    }
}
