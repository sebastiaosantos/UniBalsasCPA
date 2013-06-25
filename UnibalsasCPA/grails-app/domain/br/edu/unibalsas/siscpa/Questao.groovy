package br.edu.unibalsas.siscpa

class Questao {
	
	int ordem
    String descricao

    static constraints = {
		descricao(maxSize:3000)
    }
}
