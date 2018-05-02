package candy.crush.domain.items;

public class Candy {


    public Candy(int amountOfSugar) {
        this.amountOfSugar = amountOfSugar;
    }

    public int getAmountOfSugar() {
        return amountOfSugar;
    }

    private final int amountOfSugar;
}

