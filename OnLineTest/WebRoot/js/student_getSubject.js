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
						$("#findSubjectTime").val(data.subjectTime  + " 分钟");
						$("#findChoiceScore").val(data.choiceScore  + " 分");
						$("#findJudgeScore").val(data.judgeScore + " 分");
						$("#findChoiceNum").val(data.choiceNum + " 个");
						$("#findJudgeNum").val(data.judgeNum + " 个");
						$("#findAllScore").val(data.allScore + " 分");
		            }
		        });
		        
}