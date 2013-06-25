package br.edu.unibalsas.siscpa



import org.junit.*
import grails.test.mixin.*

@TestFor(CpaController)
@Mock(Cpa)
class CpaControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cpa/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.cpaInstanceList.size() == 0
        assert model.cpaInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.cpaInstance != null
    }

    void testSave() {
        controller.save()

        assert model.cpaInstance != null
        assert view == '/cpa/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cpa/show/1'
        assert controller.flash.message != null
        assert Cpa.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cpa/list'

        populateValidParams(params)
        def cpa = new Cpa(params)

        assert cpa.save() != null

        params.id = cpa.id

        def model = controller.show()

        assert model.cpaInstance == cpa
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cpa/list'

        populateValidParams(params)
        def cpa = new Cpa(params)

        assert cpa.save() != null

        params.id = cpa.id

        def model = controller.edit()

        assert model.cpaInstance == cpa
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cpa/list'

        response.reset()

        populateValidParams(params)
        def cpa = new Cpa(params)

        assert cpa.save() != null

        // test invalid parameters in update
        params.id = cpa.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cpa/edit"
        assert model.cpaInstance != null

        cpa.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cpa/show/$cpa.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cpa.clearErrors()

        populateValidParams(params)
        params.id = cpa.id
        params.version = -1
        controller.update()

        assert view == "/cpa/edit"
        assert model.cpaInstance != null
        assert model.cpaInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cpa/list'

        response.reset()

        populateValidParams(params)
        def cpa = new Cpa(params)

        assert cpa.save() != null
        assert Cpa.count() == 1

        params.id = cpa.id

        controller.delete()

        assert Cpa.count() == 0
        assert Cpa.get(cpa.id) == null
        assert response.redirectedUrl == '/cpa/list'
    }
}
