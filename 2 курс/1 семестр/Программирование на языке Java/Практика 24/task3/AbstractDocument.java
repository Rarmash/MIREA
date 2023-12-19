package task3;

abstract class AbstractDocument implements IDocument{
    private String title;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public void save() {

    }

    @Override
    public void print() {

    }

    @Override
    public String getInfo() {
        return null;
    }
}
