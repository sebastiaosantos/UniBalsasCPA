package br.edu.unibalsas.siscpa

import org.springframework.dao.DataIntegrityViolationException

class QuestaoController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [questaoInstanceList: Questao.list(params), questaoInstanceTotal: Questao.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[questaoInstance: new Questao(params)]
			break
		case 'POST':
	        def questaoInstance = new Questao(params)
	        if (!questaoInstance.save(flush: true)) {
	            render view: 'create', model: [questaoInstance: questaoInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'questao.label', default: 'Questao'), questaoInstance.id])
	        redirect action: 'show', id: questaoInstance.id
			break
		}
    }

    def show() {
        def questaoInstance = Questao.get(params.id)
        if (!questaoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'questao.label', default: 'Questao'), params.id])
            redirect action: 'list'
            return
        }

        [questaoInstance: questaoInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def questaoInstance = Questao.get(params.id)
	        if (!questaoInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'questao.label', default: 'Questao'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [questaoInstance: questaoInstance]
			break
		case 'POST':
	        def questaoInstance = Questao.get(params.id)
	        if (!questaoInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'questao.label', default: 'Questao'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (questaoInstance.version > version) {
	                questaoInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'questao.label', default: 'Questao')] as Object[],
	                          "Another user has updated this Questao while you were editing")
	                render view: 'edit', model: [questaoInstance: questaoInstance]
	                return
	            }
	        }

	        questaoInstance.properties = params

	        if (!questaoInstance.save(flush: true)) {
	            render view: 'edit', model: [questaoInstance: questaoInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'questao.label', default: 'Questao'), questaoInstance.id])
	        redirect action: 'show', id: questaoInstance.id
			break
		}
    }

    def delete() {
        def questaoInstance = Questao.get(params.id)
        if (!questaoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'questao.label', default: 'Questao'), params.id])
            redirect action: 'list'
            return
        }

        try {
            questaoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'questao.label', default: 'Questao'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'questao.label', default: 'Questao'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
