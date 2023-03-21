import pt.ulusofona.cm.kotlin.challenge.models.Carro
import pt.ulusofona.cm.kotlin.challenge.models.Motor

fun main() {
    val carro = Carro("Mercedes", Motor(150,3000))
    carro.moverPara(50,100)
    println(carro)
}