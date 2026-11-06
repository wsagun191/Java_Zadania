package zadanie09

// Zadanie 9: suma n początkowych wyrazów szeregu harmonicznego, 1 + 1/2 + ... + 1/n.
double sumaSzereguHarmonicznego(int n) {
    if (n < 1) {
        throw new IllegalArgumentException('Szereg harmoniczny liczymy dla dodatniej liczby całkowitej.')
    }

    double suma = 0.0d
    for (int i = 1; i <= n; i++) {
        suma += 1.0d / i
    }
    return suma
}

if (args.length == 0) {
    System.err.println 'Podaj przynajmniej jedną dodatnią liczbę całkowitą, np. 1 2 4.'
    System.exit(1)
}

def wystapilBlad = false
args.each { arg ->
    try {
        int liczba = arg.toInteger()
        double suma = sumaSzereguHarmonicznego(liczba)
        println "Suma ${liczba} początkowych wyrazów szeregu harmonicznego to: ${suma}"
    } catch (NumberFormatException ignored) {
        System.err.println "Argument '${arg}' nie jest prawidłową liczbą całkowitą typu int."
        wystapilBlad = true
    } catch (IllegalArgumentException problem) {
        System.err.println "Argument '${arg}': ${problem.message}"
        wystapilBlad = true
    }
}

if (wystapilBlad) {
    System.exit(1)
}

