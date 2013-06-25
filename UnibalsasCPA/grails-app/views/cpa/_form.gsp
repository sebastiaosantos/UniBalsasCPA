<%@ page import="br.edu.unibalsas.siscpa.Cpa" %>



<div class="fieldcontain ${hasErrors(bean: cpaInstance, field: 'descricao', 'error')} ">
	<label for="descricao">
		<g:message code="cpa.descricao.label" default="Descricao" />
		
	</label>
	<g:textField name="descricao" value="${cpaInstance?.descricao}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cpaInstance, field: 'semestre', 'error')} required">
	<label for="semestre">
		<g:message code="cpa.semestre.label" default="Semestre" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="semestre" from="${1..2}" class="range" required="" value="${fieldValue(bean: cpaInstance, field: 'semestre')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: cpaInstance, field: 'dataDeCriacao', 'error')} required">
	<label for="dataDeCriacao">
		<g:message code="cpa.dataDeCriacao.label" default="Data De Criacao" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dataDeCriacao" precision="day"  value="${cpaInstance?.dataDeCriacao}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: cpaInstance, field: 'ativa', 'error')} ">
	<label for="ativa">
		<g:message code="cpa.ativa.label" default="Ativa" />
		
	</label>
	<g:checkBox name="ativa" value="${cpaInstance?.ativa}" />
</div>

<div class="fieldcontain ${hasErrors(bean: cpaInstance, field: 'blocoQuestoes', 'error')} ">
	<label for="blocoQuestoes">
		<g:message code="cpa.blocoQuestoes.label" default="Bloco Questoes" />
		
	</label>
	<g:select name="blocoQuestoes" from="${br.edu.unibalsas.siscpa.BlocoQuestao.list()}" multiple="multiple" optionKey="id" size="5" value="${cpaInstance?.blocoQuestoes*.id}" class="many-to-many"/>
</div>

