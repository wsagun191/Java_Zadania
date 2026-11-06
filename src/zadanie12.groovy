package zadanie12

// Zadanie 12: raport błędów ERROR z pliku app.log.
// Katalog roboczy ma zawierać app.log. Wynik trafia też do raport.txt.
def plikLogow = new File('logi.txt')
if (!plikLogow.isFile()) {
    println 'Brak pliku logi.txt.'
    System.exit(1)
}

def logi = plikLogow.getText('UTF-8')
def wiersze = []

logi.eachLine { linia ->
    def czesci = linia.trim().split(/\s+/, 3)
    if (czesci.length == 3) {
        wiersze << czesci
    }
}

def bledy = wiersze.findAll { it[1] == 'ERROR' }.collect { it[2] }
def grupy = bledy.groupBy { it }
def raport = grupy.collect { komunikat, wystapienia ->
    "${wystapienia.size()} x ${komunikat}"
}.join('\n')

raport = raport ?: 'Brak błędów ERROR.'
println raport
new File('raport.txt').setText(raport, 'UTF-8')

