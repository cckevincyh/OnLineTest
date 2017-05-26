
function test(id){
	var test_action = $.trim($("#test_action").val()) +"?subjectId="+ id;
	 window.location.href = test_action;
}
