modules = {
    application {
        resource url:'js/application.js'
    }
	
	handlebars{
		resource url:'js/handlebars.js'
	}
	
	questao{
		dependsOn 'underscore, backbone, jquery'
		resource url: 'js/model/QuestaoModel.js', attrs: [type: 'js'], bundle:'bundle_questao'
		resource url: 'js/view/QuestaoView.js', attrs: [type: 'js'], bundle:'bundle_questao'
		resource url: 'template/QuestaoTemplate.html', attrs: [type: 'js'], bundle:'bundle_questao'
		//resource url: 'js/templates/questao/questao.js', attrs: [type: 'js'], bundle:'bundle_questao'
		
	}
}