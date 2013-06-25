<%@ page import="br.edu.unibalsas.siscpa.Questao"%>
<!doctype html>
<html>
<head>
<meta name="layout" content="bootstrap">
<r:require modules="questao" />
<g:set var="entityName"
	value="${message(code: 'questao.label', default: 'Questao')}" />
<title><g:message code="default.create.label"
		args="[entityName]" /></title>
<script>
	/*jQuery(document).ready(function() {
	        var HelloView = Backbone.View.extend({
	            el: $('#questao-form'),
	            initialize: function() {
	                this.render();
	            },
	            render: function() {
	                $(this.el).append("<h1>Hello World</h1>");
	            }
	        });
	        var helloView = new HelloView();
	    });*/

	window.onclick = function() {
		/*var QuestaoView = Backbone.View.extend({
		    el: $('#questao-form'),
		    template: _.template("<h2>Opa</h2><p>Im</p>"),
		    initialize: function() {
		        this.render();
		    },
		    render: function() {
		        this.$el.html(this.template());
		    }
		});*/

		var questaoView = new QuestaoView();
		var questaoSimNao = new QuestaoSimNaoView();
	};
</script>
</head>
<body>
	<div class="row-fluid">


		<div class="span3">
			<div class="well">
				<ul class="nav nav-list">

					<li class="nav-header">
						${entityName}
					</li>
					<li><g:link class="list" action="list">
							<i class="icon-list"></i>
							<g:message code="default.list.label" args="[entityName]" />
						</g:link></li>
					<li class="active"><g:link class="create" action="create">
							<i class="icon-plus icon-white"></i>
							<g:message code="default.create.label" args="[entityName]" />
						</g:link></li>
				</ul>
			</div>
		</div>


		<div class="span9">

			<div class="page-header">
				<h1>
					<g:message code="default.create.label" args="[entityName]" />
				</h1>
			</div>

			<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">
					${flash.message}
				</bootstrap:alert>
			</g:if>

			<g:hasErrors bean="${questaoInstance}">
				<bootstrap:alert class="alert-error">
					<ul>
						<g:eachError bean="${questaoInstance}" var="error">
							<li
								<g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
									error="${error}" /></li>
						</g:eachError>
					</ul>
				</bootstrap:alert>
			</g:hasErrors>

			<fieldset>
				<g:form class="form-horizontal" action="create">
					<fieldset>
						<%--<f:all bean="questaoInstance"/>
							--%>
						<g:select name="user.age" from="${['Aberta', 'Sim-Nao']}" value="${1}"
          noSelection="['':'-Selecione o tipo de questão-']"/>
						<div id="questao-form"></div>
						<div id="questao-simnao-form"></div>
						<div class="form-actions">
							<button type="submit" class="btn btn-primary">
								<i class="icon-ok icon-white"></i>
								<g:message code="default.button.create.label" default="Create" />
							</button>
						</div>
					</fieldset>
				</g:form>
			</fieldset>

		</div>


	</div>

	<!-- Templates -->

	<script type="text/template" id="questao-aberta-template">

<div class="control-group">
	<label class="control-label" for="inputDescricao">Descrição</label>
	<div class="controls">
		<input type="text" id="inputDescricao"
			placeholder="Descrição da pergunta">
	</div>
</div>
<div class="control-group">
	<label class="control-label" for="inputAjuda">Texto de ajuda</label>
	<div class="controls">
		<input type="text" id="inputAjuda" placeholder="Texto de ajuda">
	</div>
</div>

</script>

	<script type="text/template" id="questao-simnao-template">

<div class="control-group">
	<label class="control-label" for="inputAjuda">Opção</label>
	<div class="controls">
		<input type="radio" name="radioGroup" > <span class="input-xlarge uneditable-input">SIM</span>
		<input type="radio" name="radioGroup" > <span class="input-xlarge uneditable-input">NÃO</span>
	</div>
</div>
</script>

</body>
</html>
