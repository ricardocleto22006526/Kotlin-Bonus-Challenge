package pt.ulusofona.cm.kotlin.challenge.models
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoDesligadoException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoLigadoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Ligavel

class Motor(var cavalos: Int, var cilindrada: Int) : Ligavel {
    var ligado: Boolean = false


    override fun ligar() {

        if (ligado){
            throw VeiculoLigadoException("O carro já está ligado!")
        }

        ligado = true
    }

    override fun desligar() {

        if (!ligado){
            throw VeiculoDesligadoException("O carro já está desligado!")
        }

        ligado = false
    }


    override fun estaLigado():Boolean {
        return ligado
    }


    override fun toString(): String {
        return "Motor | $cavalos | $cilindrada"
    }


}