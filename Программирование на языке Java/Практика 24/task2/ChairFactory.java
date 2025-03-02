package task2;

class ChairFactory implements IAbstractChairFactory {
    @Override
    public VictorianChair createVictorianChair() {
        return new VictorianChair(130);
    }

    @Override
    public MagicChair createMagicChair() {
        return new MagicChair();

    }

    @Override
    public FunctionalChair createFunctionalChair() {
        return new FunctionalChair();

    }
}
