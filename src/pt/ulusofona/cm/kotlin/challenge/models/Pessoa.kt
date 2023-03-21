package pt.ulusofona.cm.kotlin.challenge.models
import pt.ulusofona.cm.kotlin.challenge.exceptions.AlterarPosicaoException
import pt.ulusofona.cm.kotlin.challenge.exceptions.MenorDeIdadeException
import pt.ulusofona.cm.kotlin.challenge.exceptions.PessoaSemCartaException
import pt.ulusofona.cm.kotlin.challenge.exceptions.VeiculoNaoEncontradoException
import pt.ulusofona.cm.kotlin.challenge.interfaces.Movimentavel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*


class Pessoa(var nome: String, var dataDeNascimento: Date) : Movimentavel {
    var veiculos : List<Veiculo> = listOf()
    lateinit var carta : Carta
    var posicao = Posicao(0, 0)

    fun comprarVeiculo(veiculo: Veiculo) {
        this.veiculos += veiculo
        this.veiculos.last().dataDeAquisicao = Date()
    }

    fun pesquisarVeiculo(identificador: String): Veiculo {

        for (veiculo in veiculos) {

            if (veiculo.identificador == identificador) {
                return veiculo
            }
        }

        throw VeiculoNaoEncontradoException("Não exite o veiculo com o identificador: $identificador")
    }

    fun venderVeiculo(identificador: String, compradorDoCarro: Pessoa) {
        val veiculo = pesquisarVeiculo(identificador)
        compradorDoCarro.comprarVeiculo(veiculo)
        compradorDoCarro.veiculos.last().dataDeAquisicao = Date()
        this.veiculos -= veiculo

    }

    fun moverVeiculoPara(identificador: String, x: Int, y: Int) {
        val veiculo = pesquisarVeiculo(identificador)

        if(!temCarta() && veiculo.requerCarta()) {
            throw PessoaSemCartaException("$nome não tem carta")
        }

        veiculo.moverPara(x, y)
    }

    fun temCarta(): Boolean {

        if (this::carta.isInitialized && carta.javaClass.simpleName == Carta().javaClass.simpleName) {
            return true
        }

        return false
    }

    fun tirarCarta() {

        val hoje = Date() //Date retorna a data em milisegundos

        val dataNascimento = this.dataDeNascimento

        val diff = hoje.time - dataNascimento.time

        val idade: Int = ((diff / 365*24*60*60*1000)).toInt()

        if (idade < 18) {
            throw MenorDeIdadeException("$nome não tem idade para tirar a carta")
        }

        this.carta = Carta()

    }

    override fun moverPara(x: Int, y: Int) {

        if (posicao.x == x && posicao.y == y) {
            throw AlterarPosicaoException("Não pode mover-se para a mesma posição")
        }

        posicao.alterarPosicaoPara(x, y)
    }

    override fun toString(): String {

        val formatoDaData = SimpleDateFormat("dd-MM-yyyy")
        val dataFinal = formatoDaData.format(dataDeNascimento)

        return "Pessoa | " + nome + " | " + dataFinal + " | Posicao | x:" + posicao.x + " | y:" + posicao.y
    }

}