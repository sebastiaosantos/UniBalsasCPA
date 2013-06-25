package br.edu.unibalsas.siscpa

import org.springframework.dao.DataIntegrityViolationException

class CpaController {

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [cpaInstanceList: Cpa.list(params), cpaInstanceTotal: Cpa.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[cpaInstance: new Cpa(params)]
			break
		case 'POST':
	        def cpaInstance = new Cpa(params)
	        if (!cpaInstance.save(flush: true)) {
	            render view: 'create', model: [cpaInstance: cpaInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'cpa.label', default: 'Cpa'), cpaInstance.id])
	        redirect action: 'show', id: cpaInstance.id
			break
		}
    }

    def show() {
        def cpaInstance = Cpa.get(params.id)
        if (!cpaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cpa.label', default: 'Cpa'), params.id])
            redirect action: 'list'
            return
        }

        [cpaInstance: cpaInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def cpaInstance = Cpa.get(params.id)
	        if (!cpaInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cpa.label', default: 'Cpa'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [cpaInstance: cpaInstance]
			break
		case 'POST':
	        def cpaInstance = Cpa.get(params.id)
	        if (!cpaInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cpa.label', default: 'Cpa'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (cpaInstance.version > version) {
	                cpaInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'cpa.label', default: 'Cpa')] as Object[],
	                          "Another user has updated this Cpa while you were editing")
	                render view: 'edit', model: [cpaInstance: cpaInstance]
	                return
	            }
	        }

	        cpaInstance.properties = params

	        if (!cpaInstance.save(flush: true)) {
	            render view: 'edit', model: [cpaInstance: cpaInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'cpa.label', default: 'Cpa'), cpaInstance.id])
	        redirect action: 'show', id: cpaInstance.id
			break
		}
    }

    def delete() {
        def cpaInstance = Cpa.get(params.id)
        if (!cpaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'cpa.label', default: 'Cpa'), params.id])
            redirect action: 'list'
            return
        }

        try {
            cpaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'cpa.label', default: 'Cpa'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cpa.label', default: 'Cpa'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}
