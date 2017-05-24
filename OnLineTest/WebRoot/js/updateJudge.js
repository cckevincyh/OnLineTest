
$(function () {
	

    $('#updateJudge').click(function () {

    	
    	if (!validUpdateJudge()) {
            return;
        }
    	
    	$.ajax({
            type: 'POST',
            url: 'teacher/questionManageAction_updateJudge.action',
            cache: false,
            data: {
            	judgeId:$.trim($("#updateJudgeId").val()),
                question: $.trim($("#updateJudge_question").val()),
				answer: $.trim($("#updateJudge_answer").val()),
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






function updateJudge(id){
		

	$.ajax({
		            type: 'POST',
		            url: 'teacher/questionManageAction_getJudge.action',
		            cache: false,
					dataType: "json",
		            data: {
		                judgeId: id,
		            },
		            success: function (data) {
						$("#updateJudgeId").val(data.judgeId);
		            	$("#updateJudge_question").val(data.question);
						$("#updateJudge_answer").val(data.answer);
		            }
		        });
		        
		
	
}


function validUpdateJudge() {
    var flag = true;

    var judge_question = $.trim($("#updateJudge_question").val());
    if (judge_question == "") {
        $('#updateJudge_question').parent().addClass("has-error");
        $('#updateJudge_question').next().text("请输入判断题问题");
        $("#updateJudge_question").next().show();
        flag = false;
    }else {
        $('#updateJudge_question').parent().removeClass("has-error");
        $('#updateJudge_question').next().text("");
        $("#updateJudge_question").next().hide();
    }

 	 var answer = $.trim($("#updateJudge_answer").val());
    if (answer == -1) {
        $('#updateJudge_answer').parent().addClass("has-error");
        $('#updateJudge_answer').next().text("请选择判断的答案");
        $("#updateJudge_answer").next().show();
        flag = false;
    }else {
        $('#updateJudge_answer').parent().removeClass("has-error");
        $('#updateJudge_answer').next().text("");
        $("#updateJudge_answer").next().hide();
    }
	
    return flag;
}





function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


