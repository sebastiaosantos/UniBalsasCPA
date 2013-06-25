
<%@ page import="br.edu.unibalsas.siscpa.Cpa" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'cpa.label', default: 'Cpa')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="row-fluid">
			
			<div class="span3">
				<div class="well">
					<ul class="nav nav-list">
						<li class="nav-header">${entityName}</li>
						<li class="active">
							<g:link class="list" action="list">
								<i class="icon-list icon-white"></i>
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
					<h1><g:message code="default.list.label" args="[entityName]" /></h1>
				</div>

				<g:if test="${flash.message}">
				<bootstrap:alert class="alert-info">${flash.message}</bootstrap:alert>
				</g:if>
				
				<table class="table table-striped">
					<thead>
						<tr>
						
							<g:sortableColumn property="descricao" title="${message(code: 'cpa.descricao.label', default: 'Descricao')}" />
						
							<g:sortableColumn property="semestre" title="${message(code: 'cpa.semestre.label', default: 'Semestre')}" />
						
							<g:sortableColumn property="dataDeCriacao" title="${message(code: 'cpa.dataDeCriacao.label', default: 'Data De Criacao')}" />
						
							<g:sortableColumn property="ativa" title="${message(code: 'cpa.ativa.label', default: 'Ativa')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${cpaInstanceList}" var="cpaInstance">
						<tr>
						
							<td>${fieldValue(bean: cpaInstance, field: "descricao")}</td>
						
							<td>${fieldValue(bean: cpaInstance, field: "semestre")}</td>
						
							<td><g:formatDate date="${cpaInstance.dataDeCriacao}" /></td>
						
							<td><g:formatBoolean boolean="${cpaInstance.ativa}" /></td>
						
							<td class="link">
								<g:link action="show" id="${cpaInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${cpaInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
