class Main {
    void main(String[] args) {
        String wynik = "";

        for (int i = 0; i < args.length; i++) {
            wynik += args[i] + ", ";
        }

        System.out.println(wynik);


        double liczba = 6.6;
        String inna = " inny tekst ";
        String zmienna = " wartosc tekstsowa ";
        String tekst = " wartosc " + inna + zmienna + 6.6;

        System.out.println(tekst);
        //Przykład konwersji, która nie zaokrągla
        double srednia = 34.67;
        int wyniki = (int) srednia;
        System.out.println(wyniki);

        if (wyniki == 33) {
            System.out.println("weszliśmy do warunku");
        } else {
            System.out.println("nie weszliśmy do warunku");
        }

    }
}
