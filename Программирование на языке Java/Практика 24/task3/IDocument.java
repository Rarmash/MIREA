package task3;

interface IDocument {

    String getTitle();
    void setTitle(String title);

    void open();
    void close();
    void save();
    void print();
    String getInfo();
}
