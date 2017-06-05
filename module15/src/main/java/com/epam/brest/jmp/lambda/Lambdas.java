package com.epam.brest.jmp.lambda;

import com.epam.brest.jmp.lambda.model.NewPoint;
import com.epam.brest.jmp.lambda.model.Point;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Working with lambdas
 * Created by alexander_borohov on 2.6.17.
 */
public class Lambdas {
    private static Predicate<NewPoint> coordinatesLessThanThree = newPoint -> (newPoint.getA() < 0.5 && newPoint.getB() < 0.5);
    private static ToDoubleFunction<NewPoint> summingCoordinates = point -> point.getA() + point.getB();
    private static BinaryOperator<NewPoint> multiplyCoordinates =
            (p1, p2) -> {
                p1.setA(p1.getA() * p2.getA());
                p1.setB(p1.getB() * p2.getB());
                return p1;
            };

    public static void main(String[] args) {
        List<Point> workPoints = generatedPoints();
        System.out.println("Got list of Points:");

        List<NewPoint> newPoints = workPoints
                .stream()
                .peek(System.out::println)
                .map(point -> new NewPoint(point.getX(), point.getY()))
                .peek(System.out::println)
                .filter(coordinatesLessThanThree)
                .distinct()
                .peek(System.out::println)
                .collect(toList());

        Double sum = newPoints
                .stream()
                .mapToDouble(summingCoordinates)
                .sum();

        System.out.println("Got sum of coordinates of NewPoints: " + sum);

        NewPoint multiply = newPoints
                .stream()
                .reduce(new NewPoint(1.0, 1.0), multiplyCoordinates);

        System.out.println("Got sum of multiplied coordinates of NewPoints: " + multiply.getA() * multiply.getB());

    }

    private static List<Point> generatedPoints() {
        return Stream.generate(() -> new Point(Math.random(), Math.random())).limit(10).collect(toList());
    }
}
