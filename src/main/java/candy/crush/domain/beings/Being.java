package candy.crush.domain.beings;

public class Being {

    private final String name;
    private int size;

    public Being(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return name;
    }
}
