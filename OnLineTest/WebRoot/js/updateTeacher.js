$(function () {
	

     $('#updateTeacher').click(function () {
        if (!validUpdateTeacher()) {
            return;
        }
		
		$.ajax({
            type: 'POST',
            url: 'admin/teacherManageAction_updateTeacher.action',
            cache: false,
            data: {
				teacherId: $.trim($("#updateTeacherId").val()),
                teacherName: $.trim($("#updateTeacherName").val()),
				password: $.trim($("#updatePwd").val()),
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





function updateTeacher(id){
	
	
	$.ajax({
            type: 'POST',
            url: 'admin/teacherManageAction_getTeacher.action',
            cache: false,
			dataType: "json",
            data: {
                teacherId: id,
            },
            success: function (data) {
              	 $("#updateTeacherId").val(data.teacherId);
				$("#updateTeacherName").val(data.teacherName);

            }
        });

}


function validUpdateTeacher() {
     var flag = true;

    var updateTeacherId = $.trim($("#updateTeacherId").val());
    if (updateTeacherId == "") {
        $('#updateTeacherId').parent().addClass("has-error");
        $('#updateTeacherId').next().text("请输入学生学号");
        $("#updateTeacherId").next().show();
        flag = false;
    }else {
        $('#updateTeacherId').parent().removeClass("has-error");
        $('#updateTeacherId').next().text("");
        $("#updateTeacherId").next().hide();
    }
	
	
	var updateTeacherName = $.trim($("#updateTeacherName").val());
    if (updateTeacherName == "") {
        $('#updateTeacherName').parent().addClass("has-error");
        $('#updateTeacherName').next().text("请输入学生姓名");
        $("#updateTeacherName").next().show();
        flag = false;
    }else {
        $('#updateTeacherName').parent().removeClass("has-error");
        $('#updateTeacherName').next().text("");
        $("#updateTeacherName").next().hide();
    }
	
	
	 var password = $.trim($("#updatePwd").val());
    if (password == "") {
        $('#updatePwd').parent().addClass("has-error");
        $('#updatePwd').next().text("请输入密码");
        $("#updatePwd").next().show();
        flag = false;
    } else if (password.length<3 || password.length > 15) {
        $("#updatePwd").parent().addClass("has-error");
        $("#updatePwd").next().text("密码长度必须在3~15之间");
        $("#updatePwd").next().show();
        flag = false;
    } else {
        $('#updatePwd').parent().removeClass("has-error");
        $('#updatePwd').next().text("");
        $("#updatePwd").next().hide();
    }

  
    return flag;
}



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


