var QuestaoModel = Backbone.Model.extend({
	defaults : {
		descricao : '',
		textAjuda : '',
		tipoDePergunta : '1'
	},
	validate : function(attrs) {
		if (attrs.descricao == '')
			return 'A descrição é obrigatória';
		if (attrs.tipoDePergunta == '')
			return 'O tipo é obrigatório'
	}
});