
$(function () {
	
	
	

    $('#addCourse').click(function () {
        if (!validAddCourse()) {
            return;
        }
		
		$.ajax({
            type: 'POST',
            url: 'admin/courseManageAction_addCourse.action',
            cache: false,
            data: {
                courseName: $.trim($("#addCourseName").val()),
            },
            success: function (data) {
                if (data == 1) {
                    showInfo("添加成功");

                } else if (data == 0) {
                    showInfo("添加失败，请重试");
                } else if (data == -1) {
                    showInfo("该课程已存在");
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


function validAddCourse() {
    var flag = true;

    var courseName = $.trim($("#addCourseName").val());
    if (courseName == "") {
        $('#addCourseName').parent().addClass("has-error");
        $('#addCourseName').next().text("请输入课程名");
        $("#addCourseName").next().show();
        flag = false;
    }else {
        $('#addCourseName').parent().removeClass("has-error");
        $('#addCourseName').next().text("");
        $("#addCourseName").next().hide();
    }

  
    return flag;
}






function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


