package pt.ulusofona.cm.kotlin.challenge.models

class Posicao(var x: Int, var y: Int) {

    fun alterarPosicaoPara(x: Int,y: Int){
        this.x=x
        this.y=y
    }

    override fun toString(): String {

        return "Posicao | x:$x | y:$y"
    }

}