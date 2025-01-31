package ru.job4j.ood.isp.violation;

public interface AnimalCare {
    void feed();
    void clean();
    void walk();
    void groom();
}

class DogCare implements AnimalCare {
    @Override
    public void feed() {
        /* feeding... */
    }

    @Override
    public void clean() {
        /* cleaning... */
    }

    @Override
    public void walk() {
        /* walking... */
    }

    @Override
    public void groom() {
        /* grooming... */
    }
}

class FishCare implements AnimalCare {
    @Override
    public void feed() {
        /* feeding... */
    }

    @Override
    public void clean() {
        /* cleaning... */
    }

    @Override
    public void walk() {
        throw new UnsupportedOperationException("Рыбы не ходят");
    }

    @Override
    public void groom() {
        throw new UnsupportedOperationException("Рыбы не нуждаются в стрижке");
    }
}
