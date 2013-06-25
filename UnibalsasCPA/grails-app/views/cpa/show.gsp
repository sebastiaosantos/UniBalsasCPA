
<%@ page import="br.edu.unibalsas.siscpa.Cpa" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'cpa.label', default: 'Cpa')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li>
							<g:link class="list" action="list">
								<i class="icon-list"></i>
								<g:message code="default.list.label" args="[entityName]" />
							</g:link>
						</li>
						<li>
							<g:link class="create" action="create">
								<i class="icon-plus"></i>
								<g:message code="default.create.label" args="[entityName]" />
							</g:link>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="span9">

				<div class="page-header">
					<h1><g:message code="default.show.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>

				<dl>
				
					<g:if test="${cpaInstance?.descricao}">
						<dt><g:message code="cpa.descricao.label" default="Descricao" /></dt>
						
							<dd><g:fieldValue bean="${cpaInstance}" field="descricao"/></dd>
						
					</g:if>
				
					<g:if test="${cpaInstance?.semestre}">
						<dt><g:message code="cpa.semestre.label" default="Semestre" /></dt>
						
							<dd><g:fieldValue bean="${cpaInstance}" field="semestre"/></dd>
						
					</g:if>
				
					<g:if test="${cpaInstance?.dataDeCriacao}">
						<dt><g:message code="cpa.dataDeCriacao.label" default="Data De Criacao" /></dt>
						
							<dd><g:formatDate date="${cpaInstance?.dataDeCriacao}" /></dd>
						
					</g:if>
				
					<g:if test="${cpaInstance?.ativa}">
						<dt><g:message code="cpa.ativa.label" default="Ativa" /></dt>
						
							<dd><g:formatBoolean boolean="${cpaInstance?.ativa}" /></dd>
						
					</g:if>
				
					<g:if test="${cpaInstance?.blocoQuestoes}">
						<dt><g:message code="cpa.blocoQuestoes.label" default="Bloco Questoes" /></dt>
						
							<g:each in="${cpaInstance.blocoQuestoes}" var="b">
							<dd><g:link controller="blocoQuestao" action="show" id="${b.id}">${b?.encodeAsHTML()}</g:link></dd>
							</g:each>
						
					</g:if>
				
				</dl>

				<g:form>
					<g:hiddenField name="id" value="${cpaInstance?.id}" />
					<div class="form-actions">
						<g:link class="btn" action="edit" id="${cpaInstance?.id}">
							<i class="icon-pencil"></i>
							<g:message code="default.button.edit.label" default="Edit" />
						</g:link>
						<button class="btn btn-danger" type="submit" name="_action_delete">
							<i class="icon-trash icon-white"></i>
							<g:message code="default.button.delete.label" default="Delete" />
						</button>
					</div>
				</g:form>

			</div>

		</div>
	</body>
</html>
