package br.edu.unibalsas.siscpa

class BlocoQuestao {

    static hasMany = [Questoes: Questao ]
	
	
	String tipo
	
	
    static constraints = {
		tipo(nullable:false)
		
    }
}
