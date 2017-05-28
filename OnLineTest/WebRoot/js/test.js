function test(id){
	//先去请求这个学生是否在考试或者该试卷已经做过了
	$.ajax({
            type: 'POST',
            url: 'student/studentManageAction_getState.action',
            cache: false,
            data: {
                studentId: $.trim($("#test_studentId").val()),
				subjectId:id,
            },
            success: function (data) {
                if (data == 1) {
					var test_action = $.trim($("#test_action").val()) +"?subjectId="+ id;
					 window.location.href = test_action;
                } else if (data == 0) {
					showInfo("失败，请重试");
                } else if (data == -1) {
					showInfo("正在考试,请继续考试");
                }else if (data == -2){
					showInfo("该试卷已经做过了");
                }else{
					showInfo("失败，请重试");
				}

            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("失败，请重试");
            }
        });
}


function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}

