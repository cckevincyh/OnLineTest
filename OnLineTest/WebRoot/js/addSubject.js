
$(function () {
	

  $('#addSubject').click(function () {


 	if (!validAddSubject()) {
        return;
    }
	
	
		$.ajax({
            type: 'POST',
            url: 'teacher/subjectManageAction_addSubject.action',
            cache: false,
            data: {
                subjectName: $.trim($("#addSubjectName").val()),
			  	courseId: $.trim($("#addCourse").val()),
				subjectTime: $.trim($("#addSubjectTime").val()),
				choiceScore: $.trim($("#addChoiceScore").val()),
				judgeScore: $.trim($("#addJudgeScore").val()),
            },
            success: function (data) {
                if (data == 1) {
                    showInfo("添加成功");

                } else if (data == 0) {
                    showInfo("添加失败，请重试");
                } else if (data == -1) {
                    showInfo("该试卷已存在");
                }else {
                    showInfo("添加失败，请重试");
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("添加失败，请重试");
            }
        });
	

	});


		
   
	
		$('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
       		 location.reload();  	//刷新当前页面
    	});
		
		
		 $('#btn_add').click(function () {
		 	$("#addCourse option[value!=-1]").remove();//移除先前的选项
		 	
			
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
						document.getElementById("addCourse").appendChild(op);
					}
		
		        },
  			  });
			
	});
});


function validAddSubject() {
    var flag = true;


    var addSubjectName = $.trim($("#addSubjectName").val());
    if (addSubjectName == "") {
        $('#addSubjectName').parent().addClass("has-error");
        $('#addSubjectName').next().text("请输入试卷名称");
        $("#addSubjectName").next().show();
        flag = false;
    }else {
        $('#addSubjectName').parent().removeClass("has-error");
        $('#addSubjectName').next().text("");
        $("#addSubjectName").next().hide();
    }
	
	
	var addCourse = $.trim($("#addCourse").val());
	if(addCourse == -1){
		 $('#addCourse').parent().addClass("has-error");
        $('#addCourse').next().text("请选择所属课程");
        $("#addCourse").next().show();
        flag = false;
	}else {
        $('#addCourse').parent().removeClass("has-error");
        $('#addCourse').next().text("");
        $("#addCourse").next().hide();
    }
	
	
	var addSubjectTime = $.trim($("#addSubjectTime").val());
	if(addSubjectTime == ""){
		 $('#addSubjectTime').parent().addClass("has-error");
        $('#addSubjectTime').next().text("请输入考试时间");
        $("#addSubjectTime").next().show();
        flag = false;
	}else if(addSubjectTime<=0 || addSubjectTime!=parseInt(addSubjectTime)){
		 $('#addSubjectTime').parent().addClass("has-error");
        $('#addSubjectTime').next().text("时间必须为正整数");
        $("#addSubjectTime").next().show();
        flag = false;
	}else {
        $('#addSubjectTime').parent().removeClass("has-error");
        $('#addSubjectTime').next().text("");
        $("#addSubjectTime").next().hide();
    } 
	
	
	var addChoiceScore = $.trim($("#addChoiceScore").val());
	if(addChoiceScore == ""){
		 $('#addChoiceScore').parent().addClass("has-error");
        $('#addChoiceScore').next().text("请输入选择题分值");
        $("#addChoiceScore").next().show();
        flag = false;
	}else if(addChoiceScore<=0 || addChoiceScore!=parseInt(addChoiceScore)){
		 $('#addChoiceScore').parent().addClass("has-error");
        $('#addChoiceScore').next().text("选择题分值必须为正整数");
        $("#addChoiceScore").next().show();
        flag = false;
	}else {
        $('#addChoiceScore').parent().removeClass("has-error");
        $('#addChoiceScore').next().text("");
        $("#addChoiceScore").next().hide();
    } 
	
	
	var addJudgeScore = $.trim($("#addJudgeScore").val());
	if(addJudgeScore == ""){
		 $('#addJudgeScore').parent().addClass("has-error");
        $('#addJudgeScore').next().text("请输入判断题分值");
        $("#addJudgeScore").next().show();
        flag = false;
	}else if(addJudgeScore<=0 || addJudgeScore!=parseInt(addJudgeScore)){
		 $('#addJudgeScore').parent().addClass("has-error");
        $('#addJudgeScore').next().text("判断题分值必须为正整数");
        $("#addJudgeScore").next().show();
        flag = false;
	}else {
        $('#addJudgeScore').parent().removeClass("has-error");
        $('#addJudgeScore').next().text("");
        $("#addJudgeScore").next().hide();
    } 
	
	
	
    return flag;
}




function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


