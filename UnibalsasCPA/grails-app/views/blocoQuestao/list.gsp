
<%@ page import="br.edu.unibalsas.siscpa.BlocoQuestao" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap">
		<g:set var="entityName" value="${message(code: 'blocoQuestao.label', default: 'BlocoQuestao')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
	<nav class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
					
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					
					<a class="brand" href="${createLink(uri: '/')}">CPA</a>

					<div class="nav-collapse">
						<ul class="nav">							
							<li<%= request.forwardURI == "${createLink(uri: '/')}" ? ' class="active"' : '' %>><a href="${createLink(uri: '/')}">Inicio</a></li>
							<li class="controller"><g:link controller="Cpa">Cadastrar CPA</g:link></li>
							
							
							
							
						<!-- 	<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
								<li<%= c.logicalPropertyName == controllerName ? ' class="active"' : '' %>><g:link controller="${c.logicalPropertyName}">${c.naturalName}</g:link></li>
							</g:each>  -->
						</ul>
					</div>
				</div>
			</div>
		</nav>
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
						
							<g:sortableColumn property="tipo" title="${message(code: 'blocoQuestao.tipo.label', default: 'Tipo')}" />
						
							<th></th>
						</tr>
					</thead>
					<tbody>
					<g:each in="${blocoQuestaoInstanceList}" var="blocoQuestaoInstance">
						<tr>
						
							<td>${fieldValue(bean: blocoQuestaoInstance, field: "tipo")}</td>
						
							<td class="link">
								<g:link action="show" id="${blocoQuestaoInstance.id}" class="btn btn-small">Show &raquo;</g:link>
							</td>
						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<bootstrap:paginate total="${blocoQuestaoInstanceTotal}" />
				</div>
			</div>

		</div>
	</body>
</html>
