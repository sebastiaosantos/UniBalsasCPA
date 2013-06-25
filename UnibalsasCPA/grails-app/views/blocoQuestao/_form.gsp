<%@ page import="br.edu.unibalsas.siscpa.BlocoQuestao" %>



<div class="fieldcontain ${hasErrors(bean: blocoQuestaoInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="blocoQuestao.tipo.label" default="Tipo" />
		
	</label>
	<g:textField name="tipo" value="${blocoQuestaoInstance?.tipo}"/>
</div>

