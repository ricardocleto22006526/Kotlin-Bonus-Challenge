package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.AlterarPosicaoException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoDesligadoException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoLigadoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Ligavel
import java.util.*

class Carro(override var identificador:String, var motor:Motor) : Veiculo(identificador), Ligavel{
    override var dataDeAquisicao = Date()

    override fun requerCarta(): Boolean {
        return true
    }

    override fun moverPara(x: Int, y: Int) {

        if (posicao.x == x && posicao.y == y) {
            throw AlterarPosicaoException("Não pode mover-se para a mesma posição")
        }

        if (motor.estaLigado()){
            posicao.alterarPosicaoPara(x, y)
            motor.desligar()
        }
        
    }

    override fun ligar() {

        if (estaLigado()){
            throw VeiculoLigadoException("O veiculo já está ligado")
        }

        motor.ligar()
    }

    override fun desligar() {

        if (!estaLigado()){
            throw VeiculoDesligadoException("O veiculo já está desligado")
        }

        motor.desligar()
    }

    override fun estaLigado(): Boolean {
        return motor.estaLigado()
    }

}