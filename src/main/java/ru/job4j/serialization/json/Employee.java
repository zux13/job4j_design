package ru.job4j.serialization.json;

import java.util.Arrays;

public record Employee(boolean isFullTime, double salary, String firstName, Department department, int[] projectIds) {
    @Override
    public String toString() {
        return "Employee{"
                + "isFullTime=" + isFullTime
                + ", salary=" + salary
                + ", firstName='" + firstName + '\''
                + ", department=" + department
                + ", projectIds=" + Arrays.toString(projectIds)
                + '}';
    }
}
