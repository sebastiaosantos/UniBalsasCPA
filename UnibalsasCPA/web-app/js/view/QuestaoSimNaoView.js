var QuestaoSimNaoView = Backbone.View.extend({
	el : $('#questao-simnao-form'), 
	template: _.template($("#questao-simnao-template").html()),
	initialize : function() {
		this.render();
	},
	render : function() {
		this.$el.html(this.template());
	}
});
