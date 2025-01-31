package ru.job4j.ood.isp.violation;

public interface Printer {
    void print(String document);
    void scan(String document);
    void fax(String document);
    void staple();
}

class AllInOnePrinter implements Printer {
    @Override
    public void print(String document) {
        /* printing... */
    }

    @Override
    public void scan(String document) {
        /* scanning... */
    }

    @Override
    public void fax(String document) {
        /* fax impl... */
    }

    @Override
    public void staple() {
        /* staple... */
    }
}

class SimplePrinter implements Printer {
    @Override
    public void print(String document) {
        /* printing... */
    }

    @Override
    public void scan(String document) {
        throw new UnsupportedOperationException("Этот принтер не поддерживает сканирование");
    }

    @Override
    public void fax(String document) {
        throw new UnsupportedOperationException("Этот принтер не поддерживает факс");
    }

    @Override
    public void staple() {
        throw new UnsupportedOperationException("Этот принтер не поддерживает скрепление");
    }
}
