package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 5);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isEqualToIgnoringCase("CUBE")
                .contains("ub")
                .isEqualTo("Cube");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .containsIgnoringCase("TET")
                .endsWith("on")
                .isEqualTo("Tetrahedron");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(3, 10);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty()
                .containsIgnoringCase("N o")
                .contains("object")
                .startsWith("Unknown")
                .isEqualTo("Unknown object");
    }

    @Test
    void checkNumberOfVerticesForUnknownObject() {
        Box box = new Box(14, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isLessThan(3)
                .isNegative()
                .isGreaterThan(-5)
                .isEqualTo(-1);
    }

    @Test
    void checkNumberOfVerticesForTetrahedron() {
        Box box = new Box(4, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isGreaterThan(-1)
                .isLessThan(10)
                .isEven()
                .isNotZero()
                .isEqualTo(4);
    }

    @Test
    void checkIfSphereExists() {
        Box box = new Box(0, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue();
    }

    @Test
    void checkIfSomeObjectExists() {
        Box box = new Box(14, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isFalse();
    }

    @Test
    void checkAreaOfSphereChain() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isNotZero()
                .isGreaterThan(1000)
                .isEqualTo(1256.64d, withPrecision(0.01d))
                .isCloseTo(1256.63d, withPrecision(0.008d))
                .isLessThan(1257d);
    }

    @Test
    void checkAreaOfTetrahedronChain() {
        Box box = new Box(4, 5);
        double area = box.getArea();
        assertThat(area).isNotZero()
                .isGreaterThan(1)
                .isCloseTo(43.1011d, withPrecision(0.3))
                .isLessThan(50d)
                .isEqualTo(43.3011d, withPrecision(0.0002d))
                .isCloseTo(43.3d, Percentage.withPercentage(1.0d));
    }
}