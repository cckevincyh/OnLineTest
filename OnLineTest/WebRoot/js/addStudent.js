
$(function () {
	
	
	

    $('#addStudent').click(function () {
        if (!validAddStudent()) {
            return;
        }
		
		$.ajax({
            type: 'POST',
            url: 'admin/studentManageAction_addStudent.action',
            cache: false,
            data: {
				studentId: $.trim($("#addStudentId").val()),
                studentName: $.trim($("#addStudentName").val()),
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


function validAddStudent() {
    var flag = true;

    var addStudentId = $.trim($("#addStudentId").val());
    if (addStudentId == "") {
        $('#addStudentId').parent().addClass("has-error");
        $('#addStudentId').next().text("请输入学生学号");
        $("#addStudentId").next().show();
        flag = false;
    }else {
        $('#addStudentId').parent().removeClass("has-error");
        $('#addStudentId').next().text("");
        $("#addStudentId").next().hide();
    }
	
	
	var addStudentName = $.trim($("#addStudentName").val());
    if (addStudentName == "") {
        $('#addStudentName').parent().addClass("has-error");
        $('#addStudentName').next().text("请输入学生姓名");
        $("#addStudentName").next().show();
        flag = false;
    }else {
        $('#addStudentName').parent().removeClass("has-error");
        $('#addStudentName').next().text("");
        $("#addStudentName").next().hide();
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


