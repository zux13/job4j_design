package ru.job4j.ood.lsp.violation;

public class HistoryConstraint {
}

class Document {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class ReadOnlyDocument extends Document {
    @Override
    public void setContent(String content) {
        /* Контракт историй вычислений: "Если базовый класс допускает определенные изменения состояния,
        * то производный класс также должен позволять такие же изменения, а не запрещать их."
        * В данном случае происходит нарушение LSP */
        throw new UnsupportedOperationException("Read-only document");
    }
}
