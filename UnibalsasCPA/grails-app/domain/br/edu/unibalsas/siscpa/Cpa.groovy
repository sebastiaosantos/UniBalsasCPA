package br.edu.unibalsas.siscpa

class Cpa implements Serializable {
	
	static hasMany = [blocoQuestoes: BlocoQuestao]

	int semestre
	Date dataDeCriacao
	String descricao
	boolean ativa
	
	
	
    static constraints = {
		descricao(nullable:false)
		semestre(range:1..2,nullable:false)
		dataDeCriacao(nullable:false)
    }
}
