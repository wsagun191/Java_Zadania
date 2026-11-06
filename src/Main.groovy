def plik = new File("dane.txt")
def scanner = new Scanner(System.in)

println "Wybierz operację:"
println "1 - Odczytaj plik"
println "2 - Dodaj tekst do pliku"
println "3 - Odczytaj i dodaj tekst"

print "Twój wybór: "
def wybor = scanner.nextLine()

if (plik.exists()) {

    if (wybor == "1" || wybor == "3") {
        println "Zawartość pliku:"
        println plik.text
    }

    if (wybor == "2" || wybor == "3") {
        print "Wpisz tekst: "
        def tekst = scanner.nextLine()

        plik.append(tekst + "\n")

        println "Tekst został dodany!"
    }

} else {
    println "Plik nie istnieje!"
}
