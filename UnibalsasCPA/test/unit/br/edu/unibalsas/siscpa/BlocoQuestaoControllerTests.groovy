package br.edu.unibalsas.siscpa



import org.junit.*
import grails.test.mixin.*

@TestFor(BlocoQuestaoController)
@Mock(BlocoQuestao)
class BlocoQuestaoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/blocoQuestao/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.blocoQuestaoInstanceList.size() == 0
        assert model.blocoQuestaoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.blocoQuestaoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.blocoQuestaoInstance != null
        assert view == '/blocoQuestao/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/blocoQuestao/show/1'
        assert controller.flash.message != null
        assert BlocoQuestao.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/blocoQuestao/list'

        populateValidParams(params)
        def blocoQuestao = new BlocoQuestao(params)

        assert blocoQuestao.save() != null

        params.id = blocoQuestao.id

        def model = controller.show()

        assert model.blocoQuestaoInstance == blocoQuestao
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/blocoQuestao/list'

        populateValidParams(params)
        def blocoQuestao = new BlocoQuestao(params)

        assert blocoQuestao.save() != null

        params.id = blocoQuestao.id

        def model = controller.edit()

        assert model.blocoQuestaoInstance == blocoQuestao
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/blocoQuestao/list'

        response.reset()

        populateValidParams(params)
        def blocoQuestao = new BlocoQuestao(params)

        assert blocoQuestao.save() != null

        // test invalid parameters in update
        params.id = blocoQuestao.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/blocoQuestao/edit"
        assert model.blocoQuestaoInstance != null

        blocoQuestao.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/blocoQuestao/show/$blocoQuestao.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        blocoQuestao.clearErrors()

        populateValidParams(params)
        params.id = blocoQuestao.id
        params.version = -1
        controller.update()

        assert view == "/blocoQuestao/edit"
        assert model.blocoQuestaoInstance != null
        assert model.blocoQuestaoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/blocoQuestao/list'

        response.reset()

        populateValidParams(params)
        def blocoQuestao = new BlocoQuestao(params)

        assert blocoQuestao.save() != null
        assert BlocoQuestao.count() == 1

        params.id = blocoQuestao.id

        controller.delete()

        assert BlocoQuestao.count() == 0
        assert BlocoQuestao.get(blocoQuestao.id) == null
        assert response.redirectedUrl == '/blocoQuestao/list'
    }
}
