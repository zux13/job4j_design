package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.util.Objects;

public class Meat extends Food {

    private MeatType meatType;

    public Meat(String name, LocalDate expiryDate, LocalDate createDate, double price, int discount, MeatType meatType) {
        super(name, expiryDate, createDate, price, discount);
        this.meatType = meatType;
    }

    public Meat() {
        super();
    }

    public MeatType getMeatType() {
        return meatType;
    }

    public void setMeatType(MeatType meatType) {
        this.meatType = meatType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), meatType);
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
        Meat meat = (Meat) o;
        return meatType == meat.meatType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Meat{");
        sb.append("name='").append(getName()).append('\'');
        sb.append(", expiryDate=").append(getExpiryDate());
        sb.append(", createDate=").append(getCreateDate());
        sb.append(", price=").append(getPrice());
        sb.append(", discount=").append(getDiscount());
        sb.append(", meatType=").append(meatType);
        sb.append('}');
        return sb.toString();
    }
}
