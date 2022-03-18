package hen


fun main() {
    val ukrHen: Hen? = HenFactory().getHen("Украина") // через фабрику
    println(ukrHen?.getDescription())

    val belHen: MoldovanHen = MoldovanHen() // обращение напрямую
    println(belHen.getDescription())

}
