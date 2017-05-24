
$(function () {
	

    $('#updateChoice').click(function () {

    	
    	if (!validUpdateChoice()) {
            return;
        }
    	
    	$.ajax({
            type: 'POST',
            url: 'teacher/questionManageAction_updateChoice.action',
            cache: false,
            data: {
            	choiceId:$.trim($("#updateChoiceId").val()),
                question: $.trim($("#updateChoice_question").val()),
				optionA: $.trim($("#updateOptionA").val()),
				optionB: $.trim($("#updateOptionB").val()),
				optionC: $.trim($("#updateOptionC").val()),
				optionD: $.trim($("#updateOptionD").val()),
				answer: $.trim($("#updateChoice_answer").val()),
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






function updateChoice(id){
		

	$.ajax({
		            type: 'POST',
		            url: 'teacher/questionManageAction_getChoice.action',
		            cache: false,
					dataType: "json",
		            data: {
		                choiceId: id,
		            },
		            success: function (data) {
		            	$("#updateChoiceId").val(data.choiceId);
						$("#updateChoice_question").val(data.question);
						$("#updateOptionA").val(data.optionA);
						$("#updateOptionB").val(data.optionB);
						$("#updateOptionC").val(data.optionC);
						$("#updateOptionD").val(data.optionD);
						$("#updateChoice_answer").val(data.answer);
		            }
		        });
		        
		
	
}


function validUpdateChoice() {
    var flag = true;

    var choice_question = $.trim($("#updateChoice_question").val());
    if (choice_question == "") {
        $('#updateChoice_question').parent().addClass("has-error");
        $('#updateChoice_question').next().text("请输入选择题问题");
        $("#updateChoice_question").next().show();
        flag = false;
    }else {
        $('#updateChoice_question').parent().removeClass("has-error");
        $('#updateChoice_question').next().text("");
        $("#updateChoice_question").next().hide();
    }

 	 var optionA = $.trim($("#updateOptionA").val());
    if (optionA == "") {
        $('#updateOptionA').parent().addClass("has-error");
        $('#updateOptionA').next().text("请输入选项A的答案");
        $("#updateOptionA").next().show();
        flag = false;
    }else {
        $('#updateOptionA').parent().removeClass("has-error");
        $('#updateOptionA').next().text("");
        $("#updateOptionA").next().hide();
    }
	
	 var optionB = $.trim($("#updateOptionB").val());
    if (optionB == "") {
        $('#updateOptionB').parent().addClass("has-error");
        $('#updateOptionB').next().text("请输入选项B的答案");
        $("#updateOptionB").next().show();
        flag = false;
    }else {
        $('#updateOptionB').parent().removeClass("has-error");
        $('#updateOptionB').next().text("");
        $("#updateOptionB").next().hide();
    }
	
	 var optionC = $.trim($("#updateOptionC").val());
    if (optionC == "") {
        $('#updateOptionC').parent().addClass("has-error");
        $('#updateOptionC').next().text("请输入选项C的答案");
        $("#updateOptionC").next().show();
        flag = false;
    }else {
        $('#updateOptionC').parent().removeClass("has-error");
        $('#updateOptionC').next().text("");
        $("#updateOptionC").next().hide();
    }
	
	
	 var optionD = $.trim($("#updateOptionD").val());
    if (optionD == "") {
        $('#updateOptionD').parent().addClass("has-error");
        $('#updateOptionD').next().text("请输入选项D的答案");
        $("#updateOptionD").next().show();
        flag = false;
    }else {
        $('#updateOptionD').parent().removeClass("has-error");
        $('#updateOptionD').next().text("");
        $("#updateOptionD").next().hide();
    }
	
	
	var answer = $.trim($("#updateChoice_answer").val());
    if (answer == -1) {
        $('#updateChoice_answer').parent().addClass("has-error");
        $('#updateChoice_answer').next().text("请选择正确答案");
        $("#updateChoice_answer").next().show();
        flag = false;
    }else {
        $('#updateChoice_answer').parent().removeClass("has-error");
        $('#updateChoice_answer').next().text("");
        $("#updateChoice_answer").next().hide();
    }
    return flag;
}




function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


