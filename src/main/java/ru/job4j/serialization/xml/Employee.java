package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {

    @XmlAttribute
    private boolean isFullTime;

    @XmlAttribute
    private double salary;

    @XmlAttribute
    private String firstName;
    private Department department;

    @XmlElementWrapper(name = "projectIds")
    @XmlElement(name = "projectId")
    private int[] projectIds;

    public Employee() {

    }

    public Employee(boolean isFullTime, double salary, String firstName, Department department, int[] projectIds) {
        this.isFullTime = isFullTime;
        this.salary = salary;
        this.firstName = firstName;
        this.department = department;
        this.projectIds = projectIds;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public void setFullTime(boolean fullTime) {
        isFullTime = fullTime;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int[] getProjectIds() {
        return projectIds;
    }

    public void setProjectIds(int[] projectIds) {
        this.projectIds = projectIds;
    }

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
