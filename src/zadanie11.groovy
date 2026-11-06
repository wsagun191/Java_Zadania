package zadanie11

// Zadanie 11: promocje jako domknięcia. Kolejność na liście zmienia końcową cenę.
def rabatProcentowy = { cena ->
    cena * 0.8
}

def kupon = { cena ->
    def poKuponie = cena - 10
    poKuponie < 0 ? 0 : poKuponie
}

def rabatZaProg = { cena ->
    cena >= 100 ? cena - 15 : cena
}

def obliczCene(cena, List promocje) {
    def wynik = cena
    for (promocja in promocje) {
        wynik = promocja(wynik)
    }
    return wynik
}

def cena = 100
println "Sam rabat: ${rabatProcentowy(cena)} zł"
println "Sam kupon: ${kupon(cena)} zł"
println "Najpierw rabat: ${obliczCene(cena, [rabatProcentowy, kupon])} zł"
println "Najpierw kupon: ${obliczCene(cena, [kupon, rabatProcentowy])} zł"

def cenaProgowa = 110
println "Najpierw próg: ${obliczCene(cenaProgowa, [rabatZaProg, rabatProcentowy])} zł"
println "Najpierw procent: ${obliczCene(cenaProgowa, [rabatProcentowy, rabatZaProg])} zł"

println "Kupon od 5 zł: ${obliczCene(5, [kupon])} zł"
println "Kupon od 10 zł: ${obliczCene(10, [kupon])} zł"
println "Bez promocji: ${obliczCene(100, [])} zł"

