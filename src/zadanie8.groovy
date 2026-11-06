package zadanie08

// Zadanie 8: processData przetwarza dane oddzielone przecinkami.
// Źródło: tekst 10,2,30,4 | plik dane/liczby.txt | konsola
// Przykład argumentów: tekst 10,2,30,4 sortuj

String processData(String dane, String operacja) {
    if (dane == null || dane.trim().isEmpty()) {
        throw new IllegalArgumentException('Podaj dane oddzielone przecinkami.')
    }
    if (operacja != 'sortuj') {
        throw new IllegalArgumentException("Nieznana operacja: '${operacja}'. Dostępna operacja: sortuj.")
    }

    def elementy = dane.split(',').collect { it.trim() }.findAll { !it.isEmpty() }
    if (elementy.isEmpty()) {
        throw new IllegalArgumentException('Po podziale nie zostały żadne elementy.')
    }

    boolean sameLiczby = elementy.every { it ==~ /-?\d+(\.\d+)?/ }
    def posortowane = sameLiczby
            ? elementy.sort { a, b -> a.toBigDecimal() <=> b.toBigDecimal() }
            : elementy.sort()
    return posortowane.join(', ')
}

String odczytajDaneZPliku(String sciezka) {
    def plik = new File(sciezka)
    if (!plik.isFile()) {
        throw new IOException("Plik nie istnieje: ${plik.absolutePath}")
    }

    def tresc = plik.getText('UTF-8').replace('\r\n', '\n').replace('\r', '\n')
    return tresc.split('\n').collect { it.trim() }.findAll { !it.isEmpty() }.join(',')
}

void wypiszWynik(String dane, String operacja) {
    println "Dane wejściowe: ${dane}"
    println "Wynik (${operacja}): ${processData(dane, operacja)}"
}

String czytajLinie(BufferedReader czytnik, String komunikat) {
    println komunikat
    def linia = czytnik.readLine()
    if (linia == null) {
        throw new IllegalArgumentException('Nie podano danych.')
    }
    return linia.trim()
}

def zrodlo = args.length > 0 ? args[0] : 'konsola'
def operacja = 'sortuj'
String dane = null

if (!(zrodlo in ['tekst', 'plik', 'konsola'])) {
    System.err.println 'Użycie: tekst "10,2,30" [sortuj] | plik ścieżka [sortuj] | konsola'
    System.exit(1)
}

try {
    if (zrodlo == 'konsola') {
        if (args.length > 1) {
            System.err.println 'Tryb konsola nie przyjmuje dodatkowych argumentów.'
            System.exit(1)
        }

        def czytnik = new BufferedReader(new InputStreamReader(System.in, 'UTF-8'))
        def wybor = czytajLinie(czytnik, 'Podaj źródło danych: tekst albo plik.')
        if (wybor == 'tekst') {
            dane = czytajLinie(czytnik, 'Wpisz dane oddzielone przecinkami:')
        } else if (wybor == 'plik') {
            def sciezka = czytajLinie(czytnik, 'Podaj ścieżkę do pliku:')
            dane = odczytajDaneZPliku(sciezka)
        } else {
            throw new IllegalArgumentException("Nieznane źródło: '${wybor}'. Wpisz tekst albo plik.")
        }

        def podanaOperacja = czytajLinie(czytnik, 'Podaj operację (sortuj):')
        if (!podanaOperacja.isEmpty()) {
            operacja = podanaOperacja
        }
    } else if (args.length < 2 || args.length > 3) {
        System.err.println 'Użycie: tekst "10,2,30" [sortuj] | plik ścieżka [sortuj] | konsola'
        System.exit(1)
    } else {
        if (args.length == 3) {
            operacja = args[2]
        }
        dane = zrodlo == 'plik' ? odczytajDaneZPliku(args[1]) : args[1]
    }

    wypiszWynik(dane, operacja)
} catch (IllegalArgumentException | IOException problem) {
    System.err.println "Błąd przetwarzania danych: ${problem.message}"
    System.exit(1)
}

