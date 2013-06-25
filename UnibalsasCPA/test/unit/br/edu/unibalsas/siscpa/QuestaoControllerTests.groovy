package br.edu.unibalsas.siscpa



import org.junit.*
import grails.test.mixin.*

@TestFor(QuestaoController)
@Mock(Questao)
class QuestaoControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/questao/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.questaoInstanceList.size() == 0
        assert model.questaoInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.questaoInstance != null
    }

    void testSave() {
        controller.save()

        assert model.questaoInstance != null
        assert view == '/questao/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/questao/show/1'
        assert controller.flash.message != null
        assert Questao.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/questao/list'

        populateValidParams(params)
        def questao = new Questao(params)

        assert questao.save() != null

        params.id = questao.id

        def model = controller.show()

        assert model.questaoInstance == questao
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/questao/list'

        populateValidParams(params)
        def questao = new Questao(params)

        assert questao.save() != null

        params.id = questao.id

        def model = controller.edit()

        assert model.questaoInstance == questao
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/questao/list'

        response.reset()

        populateValidParams(params)
        def questao = new Questao(params)

        assert questao.save() != null

        // test invalid parameters in update
        params.id = questao.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/questao/edit"
        assert model.questaoInstance != null

        questao.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/questao/show/$questao.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        questao.clearErrors()

        populateValidParams(params)
        params.id = questao.id
        params.version = -1
        controller.update()

        assert view == "/questao/edit"
        assert model.questaoInstance != null
        assert model.questaoInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/questao/list'

        response.reset()

        populateValidParams(params)
        def questao = new Questao(params)

        assert questao.save() != null
        assert Questao.count() == 1

        params.id = questao.id

        controller.delete()

        assert Questao.count() == 0
        assert Questao.get(questao.id) == null
        assert response.redirectedUrl == '/questao/list'
    }
}
