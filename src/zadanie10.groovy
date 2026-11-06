package zadanie10

// Zadanie 10: lodówka i przepisy. Argumenty zastępują całą domyślną lodówkę.
// Przykład: jajka ser chleb
def lodowka = ['jajka', 'mleko', 'maka', 'ser']
if (args.length > 0) {
    lodowka = args.toList()
}

def przepisy = [
        nalesniki: ['jajka', 'mleko', 'maka'],
        omlet: ['jajka', 'ser'],
        tosty: ['chleb', 'ser'],
        salatka: ['pomidor', 'ogorek', 'ser']
]

przepisy.each { danie, skladniki ->
    def brakuje = skladniki - lodowka
    if (brakuje.isEmpty()) {
        println "${danie}: możesz przygotować!"
    } else if (brakuje.size() == 1) {
        println "${danie}: dokup tylko ${brakuje[0]}"
    } else {
        println "${danie}: brakuje ${brakuje.join(', ')}"
    }
}

