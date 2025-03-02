package task3;

class DocumentFactory implements ICreateDocument {
    @Override
    public IDocument CreateNew() {
        // Здесь должна быть реализация создания нового документа
        return new ImageDocument(); // Например, создаем объект ImageDocument
    }

    @Override
    public IDocument CreateOpen() {
        // Здесь должна быть реализация открытия существующего документа
        return new TxtDocument(); // Например, создаем объект TxtDocument
    }
}