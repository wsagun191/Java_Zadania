def silnia(n) {
    def wynik = 1

    for (int i = 1; i <= n; i++) {
        wynik *= i
    }

    return wynik
}

args.each { liczba ->
    try {
        int n = liczba.toInteger()

        if (n < 0) {
            println "Liczba nie może być ujemna: $n"
        } else {
            println "Silnia z $n wynosi ${silnia(n)}"
        }

    } catch (Exception e) {
        println "Błąd: $liczba nie jest liczbą całkowitą!"
    }
}
