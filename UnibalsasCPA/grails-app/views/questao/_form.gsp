<%@ page import="br.edu.unibalsas.siscpa.Questao" %>



<div class="fieldcontain ${hasErrors(bean: questaoInstance, field: 'descricao', 'error')} ">
	<label for="descricao">
		<g:message code="questao.descricao.label" default="Descricao" />
		
	</label>
	<g:textArea name="descricao" cols="40" rows="5" maxlength="3000" value="${questaoInstance?.descricao}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: questaoInstance, field: 'ordem', 'error')} required">
	<label for="ordem">
		<g:message code="questao.ordem.label" default="Ordem" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="ordem" type="number" value="${questaoInstance.ordem}" required=""/>
</div>

