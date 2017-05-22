
$(function () {
	
	
	

    $('#addTeacher').click(function () {
        if (!validAddTeacher()) {
            return;
        }
		
		$.ajax({
            type: 'POST',
            url: 'admin/teacherManageAction_addTeacher.action',
            cache: false,
            data: {
				teacherId: $.trim($("#addTeacherId").val()),
                teacherName: $.trim($("#addTeacherName").val()),
				password: $.trim($("#addPwd").val()),
            },
            success: function (data) {
                if (data == 1) {
                    showInfo("添加成功");

                } else if (data == 0) {
                    showInfo("添加失败，请重试");
                } else if (data == -1) {
                    showInfo("该学号已存在");
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


function validAddTeacher() {
    var flag = true;

    var addTeacherId = $.trim($("#addTeacherId").val());
    if (addTeacherId == "") {
        $('#addTeacherId').parent().addClass("has-error");
        $('#addTeacherId').next().text("请输入教师职工号");
        $("#addTeacherId").next().show();
        flag = false;
    }else {
        $('#addTeacherId').parent().removeClass("has-error");
        $('#addTeacherId').next().text("");
        $("#addTeacherId").next().hide();
    }
	
	
	var addTeacherName = $.trim($("#addTeacherName").val());
    if (addTeacherName == "") {
        $('#addTeacherName').parent().addClass("has-error");
        $('#addTeacherName').next().text("请输入教师姓名");
        $("#addTeacherName").next().show();
        flag = false;
    }else {
        $('#addTeacherName').parent().removeClass("has-error");
        $('#addTeacherName').next().text("");
        $("#addTeacherName").next().hide();
    }
	
	
	 var password = $.trim($("#addPwd").val());
    if (password == "") {
        $('#addPwd').parent().addClass("has-error");
        $('#addPwd').next().text("请输入密码");
        $("#addPwd").next().show();
        flag = false;
    } else if (password.length<3 || password.length > 15) {
        $("#addPwd").parent().addClass("has-error");
        $("#addPwd").next().text("密码长度必须在3~15之间");
        $("#addPwd").next().show();
        flag = false;
    } else {
        $('#addPwd').parent().removeClass("has-error");
        $('#addPwd').next().text("");
        $("#addPwd").next().hide();
    }

  
    return flag;
}






function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


