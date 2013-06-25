var QuestaoView = Backbone.View.extend({
	el : $('#questao-form'),
	//template : _.template($("#questaoTemplate").html()),
	template: _.template($("#questao-aberta-template").html()),
	initialize : function() {
		this.render();
	},
	render : function() {
		this.$el.html(this.template());
	}
});