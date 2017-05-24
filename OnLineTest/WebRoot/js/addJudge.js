
$(function () {
	
	
	

    $('#addJudge').click(function () {
        if (!validAddJudge()) {
            return;
        }
		
		$.ajax({
            type: 'POST',
            url: 'teacher/questionManageAction_addJudge.action',
            cache: false,
            data: {
				subjectId:$.trim($("#subjectId").val()),
                question: $.trim($("#judge_question").val()),
				answer: $.trim($("#judge_answer").val()),
            },
            success: function (data) {
                if (data == 1) {
                    showInfo("添加成功");
                } else if (data == 0) {
                    showInfo("添加失败，请重试");
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
	
	

});


function validAddJudge() {
    var flag = true;

    var judge_question = $.trim($("#judge_question").val());
    if (judge_question == "") {
        $('#judge_question').parent().addClass("has-error");
        $('#judge_question').next().text("请输入判断题问题");
        $("#judge_question").next().show();
        flag = false;
    }else {
        $('#judge_question').parent().removeClass("has-error");
        $('#judge_question').next().text("");
        $("#judge_question").next().hide();
    }

 	 var answer = $.trim($("#judge_answer").val());
    if (answer == -1) {
        $('#judge_answer').parent().addClass("has-error");
        $('#judge_answer').next().text("请选择判断的答案");
        $("#judge_answer").next().show();
        flag = false;
    }else {
        $('#judge_answer').parent().removeClass("has-error");
        $('#judge_answer').next().text("");
        $("#judge_answer").next().hide();
    }
	
    return flag;
}






function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


