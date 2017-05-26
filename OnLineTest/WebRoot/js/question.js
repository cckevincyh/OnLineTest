
function question(id){
	var question_action = $.trim($("#question_action").val()) +"?subjectId="+ id;
	
	 window.location.href = question_action;
}
