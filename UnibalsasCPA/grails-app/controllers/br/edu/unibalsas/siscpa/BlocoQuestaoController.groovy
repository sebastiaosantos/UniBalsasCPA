package br.edu.unibalsas.siscpa

import org.springframework.dao.DataIntegrityViolationException

class BlocoQuestaoController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [blocoQuestaoInstanceList: BlocoQuestao.list(params), blocoQuestaoInstanceTotal: BlocoQuestao.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[blocoQuestaoInstance: new BlocoQuestao(params)]
			break
		case 'POST':
	        def blocoQuestaoInstance = new BlocoQuestao(params)
	        if (!blocoQuestaoInstance.save(flush: true)) {
	            render view: 'create', model: [blocoQuestaoInstance: blocoQuestaoInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'blocoQuestao.label', default: 'BlocoQuestao'), blocoQuestaoInstance.id])
	        redirect action: 'show', id: blocoQuestaoInstance.id
			break
		}
    }

    def show() {
        def blocoQuestaoInstance = BlocoQuestao.get(params.id)
        if (!blocoQuestaoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'blocoQuestao.label', default: 'BlocoQuestao'), params.id])
            redirect action: 'list'
            return
        }

        [blocoQuestaoInstance: blocoQuestaoInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def blocoQuestaoInstance = BlocoQuestao.get(params.id)
	        if (!blocoQuestaoInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blocoQuestao.label', default: 'BlocoQuestao'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [blocoQuestaoInstance: blocoQuestaoInstance]
			break
		case 'POST':
	        def blocoQuestaoInstance = BlocoQuestao.get(params.id)
	        if (!blocoQuestaoInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'blocoQuestao.label', default: 'BlocoQuestao'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (blocoQuestaoInstance.version > version) {
	                blocoQuestaoInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'blocoQuestao.label', default: 'BlocoQuestao')] as Object[],
	                          "Another user has updated this BlocoQuestao while you were editing")
	                render view: 'edit', model: [blocoQuestaoInstance: blocoQuestaoInstance]
	                return
	            }
	        }

	        blocoQuestaoInstance.properties = params

	        if (!blocoQuestaoInstance.save(flush: true)) {
	            render view: 'edit', model: [blocoQuestaoInstance: blocoQuestaoInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'blocoQuestao.label', default: 'BlocoQuestao'), blocoQuestaoInstance.id])
	        redirect action: 'show', id: blocoQuestaoInstance.id
			break
		}
    }

    def delete() {
        def blocoQuestaoInstance = BlocoQuestao.get(params.id)
        if (!blocoQuestaoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'blocoQuestao.label', default: 'BlocoQuestao'), params.id])
            redirect action: 'list'
            return
        }

        try {
            blocoQuestaoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'blocoQuestao.label', default: 'BlocoQuestao'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'blocoQuestao.label', default: 'BlocoQuestao'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
