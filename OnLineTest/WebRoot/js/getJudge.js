function getJudge(id){

	$.ajax({
		            type: 'POST',
		            url: 'teacher/questionManageAction_getJudge.action',
		            cache: false,
					dataType: "json",
		            data: {
		                judgeId: id,
		            },
		            success: function (data) {
		            	$("#findJudge_course").val(data.subject.course.courseName);
		              	 $("#findJudge_subject").val(data.subject.subjectName);
						$("#findJudge_question").val(data.question);
						$("#findJudge_answer").val(data.answer);
						$("#findJudge_score").val(data.subject.judgeScore);
		            }
		        });
		        
}