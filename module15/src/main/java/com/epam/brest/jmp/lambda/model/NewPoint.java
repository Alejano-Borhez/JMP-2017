package com.epam.brest.jmp.lambda.model;

import java.util.Objects;

/**
 * Simple class for lambdas
 * Created by alexander_borohov on 2.6.17.
 */
public class NewPoint {
    private Double a;
    private Double b;

    public NewPoint(Double a, Double b) {
        this.a = a;
        this.b = b;
    }

    public Double getA() {
        return a;
    }

    public Double getB() {
        return b;
    }

    public void setA(final Double a) {
        this.a = a;
    }

    public void setB(final Double b) {
        this.b = b;
    }

    public NewPoint(Point point) {
        this.a = point.getX();
        this.b = point.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewPoint point = (NewPoint) o;
        return Objects.equals(getA(), point.getA()) &&
                Objects.equals(getB(), point.getB());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getA(), getB());
    }

    @Override
    public String toString() {
        return "NewPoint{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
