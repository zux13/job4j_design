package ru.job4j.ood.isp.violation;

public interface GraphicsEditor {
    void drawLine();
    void drawRectangle();
    void drawCircle();
    void applyFilter();
    void addText();
}

class BasicGraphicsEditor implements GraphicsEditor {
    @Override
    public void drawLine() {
        /* Drawing line */
    }

    @Override
    public void drawRectangle() {
        /* Drawing rectangle */
    }

    @Override
    public void drawCircle() {
        /* Drawing circle */
    }

    @Override
    public void applyFilter() {
        throw new UnsupportedOperationException("Этот редактор не поддерживает фильтры");
    }

    @Override
    public void addText() {
        throw new UnsupportedOperationException("Этот редактор не поддерживает добавление текста");
    }
}