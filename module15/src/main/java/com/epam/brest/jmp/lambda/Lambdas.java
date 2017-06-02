package com.epam.brest.jmp.lambda;

import static java.util.stream.Collectors.toList;

import com.epam.brest.jmp.lambda.model.NewPoint;
import com.epam.brest.jmp.lambda.model.Point;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

/**
 * Working with lambdas
 * Created by alexander_borohov on 2.6.17.
 */
public class Lambdas {
    private static Predicate<NewPoint> coordinatesLessThanThree = newPoint -> (newPoint.getA() < 0.3 && newPoint.getB() < 0.3);
    private static ToDoubleFunction<NewPoint> summingCoordinates = point -> point.getA() + point.getB();
    private static ToDoubleFunction<NewPoint> multiplyingCoordinates = point -> point.getA() * point.getB();

    public static void main(String[] args) {
        List<Point> workPoints = generatedPoints();
        System.out.println("Got list of Points:");
        workPoints.stream().forEach(System.out::println);

        List<NewPoint> newPoints = workPoints.stream()
                .map(point -> new NewPoint(point.getX(), point.getY()))
                .collect(toList());

        System.out.println("Got list of NewPoints:");
        newPoints.stream().forEach(System.out::println);


        System.out.println("Got Filtered list of NewPoints:");

        Double sum = newPoints.stream()
                .filter(coordinatesLessThanThree)
                .distinct()
                .peek(System.out::println)
                .mapToDouble(summingCoordinates)
                .sum();
        System.out.println("Got sum of coordinates of NewPoints: " + sum);

        Double multiply = newPoints.stream()
                .filter(coordinatesLessThanThree)
                .distinct()
                .mapToDouble(multiplyingCoordinates)
                .sum();

        System.out.println("Got sum of multiplied coordinates of NewPoints: " + multiply);

    }

    private static List<Point> generatedPoints() {
        return Stream.generate(() -> new Point(Math.random(), Math.random()))
                .limit(10).collect(toList());
    }
}
