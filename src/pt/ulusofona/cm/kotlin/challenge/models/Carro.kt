package pt.ulusofona.cm.kotlin.challenge.models

import pt.ulusofona.cm.kotlin.challenge.exceptions.AlterarPosicaoException
import java.util.*

class Carro(override var identificador:String, var motor:Motor) : Veiculo(identificador){
    override var dataDeAquisicao = Date()

    override fun requerCarta(): Boolean {
        return true
    }

    override fun moverPara(x: Int, y: Int) {

        if (posicao.x == x && posicao.y == y) {
            throw AlterarPosicaoException("Não pode mover-se para a mesma posição")
        }

        motor.ligar()
        posicao.alterarPosicaoPara(x, y)
        motor.desligar()

    }

}