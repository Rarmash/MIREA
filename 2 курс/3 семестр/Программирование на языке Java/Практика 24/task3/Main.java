package task3;

class Main {
    public static void main(String[] args) {
        ICreateDocument documentFactory = new DocumentFactory();
        IDocument newDocument = documentFactory.CreateNew();
        IDocument openDocument = documentFactory.CreateOpen();
        newDocument.close();
        System.out.println(newDocument.getInfo());
        openDocument.save();
        System.out.println(openDocument.getInfo());
    }
}
