package task2;

class VictorianChair implements IChair {
    public int getAge() {
        return age;
    }

    private final int age;
    public VictorianChair(int age) {
        this.age = age;
    }
}
