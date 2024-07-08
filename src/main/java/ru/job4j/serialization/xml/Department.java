package ru.job4j.serialization.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "department")
@XmlAccessorType(XmlAccessType.FIELD)
public class Department {

    @XmlAttribute
    private String departmentName;

    @XmlAttribute
    private String location;

    public Department() {

    }

    public Department(String departmentName, String location) {
        this.departmentName = departmentName;
        this.location = location;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{"
                + "departmentName='" + departmentName + '\''
                + ", location='" + location + '\''
                + '}';
    }
}
