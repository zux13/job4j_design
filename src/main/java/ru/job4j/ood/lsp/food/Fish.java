package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.util.Objects;

public class Fish extends  Food {

    private FishType type;

    public Fish(String name, LocalDate expiryDate, LocalDate createDate, double price, int discount, FishType type) {
        super(name, expiryDate, createDate, price, discount);
        this.type = type;
    }

    public Fish() {
        super();
    }

    public FishType getType() {
        return type;
    }

    public void setType(FishType type) {
        this.type = type;
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
        Fish fish = (Fish) o;
        return type == fish.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Fish{");
        sb.append("name='").append(getName()).append('\'');
        sb.append(", expiryDate=").append(getExpiryDate());
        sb.append(", createDate=").append(getCreateDate());
        sb.append(", price=").append(getPrice());
        sb.append(", discount=").append(getDiscount());
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
