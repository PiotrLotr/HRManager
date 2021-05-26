
public enum Position {

    DESIGNER(4000, 6000),
    MANAGER(7000,10000),
    APPRENTICE(2000,4000),
    CHIEF(8000,12000),
    SALESMAN(5000,8000);

    private final double lowerEdge, upperEdge;

    Position(int lowerEdge, int upperEdge) {
        this.lowerEdge= lowerEdge;
        this.upperEdge = upperEdge;
    }

    public double getLowerEdge() {
        return lowerEdge;
    }

    public double getUpperEdge() {
        return upperEdge;
    }
}


