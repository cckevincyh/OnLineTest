
$(function () {
	

    $('#updateSubject').click(function () {

    	
    	if (!validUpdateSubject()) {
            return;
        }
    	
    	$.ajax({
            type: 'POST',
            url: 'teacher/subjectManageAction_updateSubject.action',
            cache: false,
            data: {
            	subjectId:$.trim($("#updateSubjectId").val()),
                subjectName: $.trim($("#updateSubjectName").val()),
			  	courseId: $.trim($("#updateCourse").val()),
				subjectTime: $.trim($("#updateSubjectTime").val()),
				choiceScore: $.trim($("#updateChoiceScore").val()),
				judgeScore: $.trim($("#updateJudgeScore").val()),
            },
            success: function (data) {
                if (data == 1) {
                    showInfo("修改成功");

                } else if (data == 0) {
                    showInfo("修改失败，请重试");
                }else {
                    showInfo("修改失败，请重试");
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("修改失败，请重试");
            }
        });
			
		
	});
	
	
	
	
	
	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
	
	

});






function updateSubject(id){
		$("#updateCourse option[value!=-1]").remove();//移除先前的选项
		
		
		$.ajax({
	        type: 'GET',
	        url: 'teacher/getAllCourseAction.action',
	        dataType: "json",
	        cache: false,
	        success: function (data) {
				for(var index in data) {
					var op = document.createElement("option");
					op.value = data[index].courseId;
					var textNode = document.createTextNode(data[index].courseName);
					op.appendChild(textNode);
					document.getElementById("updateCourse").appendChild(op);
				}
				$.ajax({
		            type: 'POST',
		            url: 'teacher/subjectManageAction_getSubject.action',
		            cache: false,
					dataType: "json",
		            data: {
		                subjectId: id,
		            },
		            success: function (data) {
		            	$("#updateSubjectId").val(data.subjectId);
		              	 $("#updateSubjectName").val(data.subjectName);
						$("#updateCourse").val(data.course.courseId);
						$("#updateSubjectTime").val(data.subjectTime);
						$("#updateChoiceScore").val(data.choiceScore);
						$("#updateJudgeScore").val(data.judgeScore);
		            }
		        });
	
	        },
		});
		
	
}



function validUpdateSubject() {
    var flag = true;


    var updateSubjectName = $.trim($("#updateSubjectName").val());
    if (updateSubjectName == "") {
        $('#updateSubjectName').parent().addClass("has-error");
        $('#updateSubjectName').next().text("请输入试卷名称");
        $("#updateSubjectName").next().show();
        flag = false;
    }else {
        $('#updateSubjectName').parent().removeClass("has-error");
        $('#updateSubjectName').next().text("");
        $("#updateSubjectName").next().hide();
    }
	
	
	var updateCourse = $.trim($("#updateCourse").val());
	if(updateCourse == -1){
		 $('#updateCourse').parent().addClass("has-error");
        $('#updateCourse').next().text("请选择所属课程");
        $("#updateCourse").next().show();
        flag = false;
	}else {
        $('#updateCourse').parent().removeClass("has-error");
        $('#updateCourse').next().text("");
        $("#updateCourse").next().hide();
    }
	
	
	var updateSubjectTime = $.trim($("#updateSubjectTime").val());
	if(updateSubjectTime == ""){
		 $('#updateSubjectTime').parent().addClass("has-error");
        $('#updateSubjectTime').next().text("请输入考试时间");
        $("#updateSubjectTime").next().show();
        flag = false;
	}else if(updateSubjectTime<=0 || updateSubjectTime!=parseInt(updateSubjectTime)){
		 $('#updateSubjectTime').parent().addClass("has-error");
        $('#updateSubjectTime').next().text("时间必须为正整数");
        $("#updateSubjectTime").next().show();
        flag = false;
	}else {
        $('#updateSubjectTime').parent().removeClass("has-error");
        $('#updateSubjectTime').next().text("");
        $("#updateSubjectTime").next().hide();
    } 
	
	
	var updateChoiceScore = $.trim($("#updateChoiceScore").val());
	if(updateChoiceScore == ""){
		 $('#updateChoiceScore').parent().addClass("has-error");
        $('#updateChoiceScore').next().text("请输入选择题分值");
        $("#updateChoiceScore").next().show();
        flag = false;
	}else if(updateChoiceScore<=0 || updateChoiceScore!=parseInt(updateChoiceScore)){
		 $('#updateChoiceScore').parent().addClass("has-error");
        $('#updateChoiceScore').next().text("选择题分值必须为正整数");
        $("#updateChoiceScore").next().show();
        flag = false;
	}else {
        $('#updateChoiceScore').parent().removeClass("has-error");
        $('#updateChoiceScore').next().text("");
        $("#updateChoiceScore").next().hide();
    } 
	
	
	var updateJudgeScore = $.trim($("#updateJudgeScore").val());
	if(updateJudgeScore == ""){
		 $('#updateJudgeScore').parent().addClass("has-error");
        $('#updateJudgeScore').next().text("请输入判断题分值");
        $("#updateJudgeScore").next().show();
        flag = false;
	}else if(updateJudgeScore<=0 || updateJudgeScore!=parseInt(updateJudgeScore)){
		 $('#updateJudgeScore').parent().addClass("has-error");
        $('#updateJudgeScore').next().text("判断题分值必须为正整数");
        $("#updateJudgeScore").next().show();
        flag = false;
	}else {
        $('#updateJudgeScore').parent().removeClass("has-error");
        $('#updateJudgeScore').next().text("");
        $("#updateJudgeScore").next().hide();
    } 
	
	
	
    return flag;
}



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


