function getSubject(id){

	$.ajax({
		            type: 'POST',
		            url: 'student/subjectManageAction_getSubject.action',
		            cache: false,
					dataType: "json",
		            data: {
		                subjectId: id,
		            },
		            success: function (data) {
		            	$("#findSubjectName").val(data.subjectName);
		              	 $("#findCourseName").val(data.course.courseName);
						$("#findSubjectTime").val(data.subjectTime);
						$("#findChoiceScore").val(data.choiceScore);
						$("#findJudgeScore").val(data.judgeScore);
						$("#findChoiceNum").val(data.choiceNum);
						$("#findJudgeNum").val(data.judgeNum);
						$("#findAllScore").val(data.allScore);
		            }
		        });
		        
}