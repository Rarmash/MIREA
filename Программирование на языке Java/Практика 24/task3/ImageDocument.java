package task3;

class ImageDocument extends AbstractDocument{
    String pathToImage;
    int height;
    int weight;

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    boolean flag;
    public void open() {
        System.out.println(" image document is open");
        flag = true;
    }
    @Override
    public void close() {
        if (!flag) {
            System.out.println("image document was not opened");
        } else {
            System.out.println("image document closed");
            flag = false;
        }

    }
    public void save() {
        System.out.println("image document saved");

    }

    @Override
    public void print() {
        System.out.println("!just a picture!");

    }

    @Override
    public String getInfo() {
       return "pathToImage = " + pathToImage  + ", height = " + height + ", weight = " + weight;
    }


}
