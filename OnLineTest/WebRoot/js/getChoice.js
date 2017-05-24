function getChoice(id){

	$.ajax({
		            type: 'POST',
		            url: 'teacher/questionManageAction_getChoice.action',
		            cache: false,
					dataType: "json",
		            data: {
		                choiceId: id,
		            },
		            success: function (data) {
		            	$("#findChoice_course").val(data.subject.course.courseName);
		              	 $("#findChoice_subject").val(data.subject.subjectName);
						$("#findChoice_question").val(data.question);
						$("#findOptionA").val(data.optionA);
						$("#findOptionB").val(data.optionB);
						$("#findOptionC").val(data.optionC);
						$("#findOptionD").val(data.optionD);
						$("#findChoice_answer").val(data.answer);
						$("#findChoice_score").val(data.subject.choiceScore);
		            }
		        });
		        
}