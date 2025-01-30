package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.util.Objects;

public class Vegetable extends Food {

    private VegetableType vegetableType;

    public Vegetable(String name, LocalDate expiryDate, LocalDate createDate, double price, int discount,
                     VegetableType vegetableType) {
        super(name, expiryDate, createDate, price, discount);
        this.vegetableType = vegetableType;
    }

    public Vegetable() {
        super();
    }

    public VegetableType getVegetableType() {
        return vegetableType;
    }

    public void setVegetableType(VegetableType vegetableType) {
        this.vegetableType = vegetableType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Vegetable vegetable = (Vegetable) o;
        return vegetableType == vegetable.vegetableType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vegetableType);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vegetables{");
        sb.append("name='").append(getName()).append('\'');
        sb.append(", expiryDate=").append(getExpiryDate());
        sb.append(", createDate=").append(getCreateDate());
        sb.append(", price=").append(getPrice());
        sb.append(", discount=").append(getDiscount());
        sb.append(", vegetableType=").append(vegetableType);
        sb.append('}');
        return sb.toString();
    }
}
