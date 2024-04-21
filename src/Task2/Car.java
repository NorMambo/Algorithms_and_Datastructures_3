package Task2;

public class Car {
    private String LPN; //EX. KSF762 or KSF76A
    private String color;
    private String make;
    private String year;

    private String[] colors = {"white", "red", "blue", "green", "black", "grey"};
    private String[] brands = {"VW", "Ferrari", "Fiat", "Volvo", "Audi", "Skoda", "Renault", "Suzuki"};

    public Car(String LPN, String color, String make, int year) {
        this.LPN = LPN; this.color = color; this.make = make; this.year = String.valueOf(year);
    }

    public Car() {

        String LPN = "";
        char rand;
        for (int i = 0; i < 3; i++) { //65 - 90 CAP letters (plate)
            rand = (char) Math.floor(Math.random() * ((90 - 65) + 1) + 65);
            LPN += String.valueOf(rand);
        }
        for (int i = 0; i < 3; i++) { // 48 - 57 NUMBERS (plate)
            rand = (char) Math.floor(Math.random() * ((57 - 48) + 1) + 48);
            LPN += String.valueOf(rand);
        }
        this.LPN = LPN;

        int idx;
        idx = (int) Math.floor(Math.random() * 6); // index for color
        this.color = colors[idx];

        idx = (int) Math.floor(Math.random() * 8); // index for make
        this.make = brands[idx];

        int year = (int) Math.floor(Math.random() * ((2023 - 1980) + 1) + 1980);
        this.year = String.valueOf(year);

    }

    // 1st HASH
//    public int hash(int tableSize) {
//
//        int hashVal = 0;
//        String concString = LPN.concat(color).concat(make).concat(year);
//
//        for (int i = 0; i < concString.length(); i++)
//            hashVal = 37 * hashVal + concString.charAt(i);
//
//        hashVal%=tableSize;
//
//        if (hashVal < 0)
//            hashVal+=tableSize;
//
//        return hashVal;
//    }

    // 2nd HASH
    public int hash(int tableSize) {

        int hashVal = 0;
        String concString = LPN.concat(color).concat(make).concat(year);

        for (int i = 0; i < concString.length(); i++)
            hashVal = 1 * hashVal + concString.charAt(i);

        hashVal%=tableSize;

        if (hashVal < 0)
            hashVal+=tableSize;

        return hashVal;
    }

}
