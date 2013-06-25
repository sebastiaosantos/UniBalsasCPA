var QuestaoView = Backbone.View.extend({
	el : $('#questao-multiplaEscolha-form'),
	//template : _.template($("#questaoTemplate").html()),
	template: _.template($("#questao-multiplaEscolha-template").html()),
	initialize : function() {
		this.render();
	},
	render : function() {

		$(function(){
		    
		    //Cria uma fun��o para Criar os campos
		    function createDivFields(){
		        /*
		         Criamos a variavel, e atribuimos os campos que ser�o criados;
		         Utilizamos o colchetes nos nomes do campos para informar que os dados 
		         em forma de array;
		         Adiciona uma div, para que nela seja criado novos campos extras;
		         E um link para para chamar o evento de adicionar;
		        */
		        var html  = '<div class="items">';
		            html += '<label>Op��o: <input type="radio"  name="groupMultiolaEscolha" id="radiogroup"> <input type="text" name="op�ao[]" /></label>';
		            html += '<a href="#" class="addTel">Adicionar Op��o</a>';
		            html += '<div class="item"></div>';
		            html += '<div>';
		            return html;
		    }
		    
		    //Cria a fun��o para adicionar os campos extras de telefone
		    function createFieldTel(num){
		        /*
		         Repare que � informado que ter� um parametro;
		         Ser� por ele iremos identificar de quem pertence esses campos;
		        */
		        var novoCampo  = '<label> Op��o :';
		            novoCampo += '<input type="text" name="telExtra['+num+'][]" />';
		            novoCampo += '</label><br />';
		            return novoCampo;
		    }
		    
		    //cria uma fun��o para conta os campos criados
		    function getTotalItems(){
		        //Contamos o total de campos, e diminuimos 1
		        //Porque o array � iniciado seu indice com 0
		        return $(".items").length - 1;
		    }
		    
		    //Adiciona os nome e telefone
		    $("#add").click(function(){
		        //Adicionado no final do elemento ( #boxFields) os campos
		        $("#boxFields").append(createDivFields());
		        return false;
		    });
		    
		    //Adiciona os campos extras
		    $(".addTel").live('click', function(){
		        /*
		            Utilizamos Live para atribui o evento click ao link addTel
		            Isso porque como criamos dinamicamente esse elemento
		            ele ainda n�o est� no DOM, quando o jQuery vai executar
		        */
		        
		        //Chamamos o contador
		        var total = getTotalItems();
		        
		        //Voltamos um elemento (parent);
		        //e depois buscamos .item, informando que precisa ser o primeiro encontrado
		        //Adiciona no final do elemento (.item) os novos campos
		        
		        $(this).parent().children('.item:first').append(createFieldTel(total));
		        
		        return false;
		    });

		});

		
		this.$el.html(this.template());
	}
});
