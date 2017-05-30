
function score(id,sid){
	var score_action = $.trim($("#score_action").val()) +"?subjectId="+ id + "&studentId="+sid;
	
	 window.location.href = score_action;
}
