$(function () {
	

     $('#updateStudent').click(function () {
        if (!validUpdateStudent()) {
            return;
        }
		
		$.ajax({
            type: 'POST',
            url: 'admin/studentManageAction_updateStudent.action',
            cache: false,
            data: {
				studentId: $.trim($("#updateStudentId").val()),
                studentName: $.trim($("#updateStudentName").val()),
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





function updateStudent(id){
	
	
	$.ajax({
            type: 'POST',
            url: 'admin/studentManageAction_getStudent.action',
            cache: false,
			dataType: "json",
            data: {
                studentId: id,
            },
            success: function (data) {
              	 $("#updateStudentId").val(data.studentId);
				$("#updateStudentName").val(data.studentName);

            }
        });

}


function validUpdateStudent() {
     var flag = true;

    var updateStudentId = $.trim($("#updateStudentId").val());
    if (updateStudentId == "") {
        $('#updateStudentId').parent().addClass("has-error");
        $('#updateStudentId').next().text("请输入学生学号");
        $("#updateStudentId").next().show();
        flag = false;
    }else {
        $('#updateStudentId').parent().removeClass("has-error");
        $('#updateStudentId').next().text("");
        $("#updateStudentId").next().hide();
    }
	
	
	var updateStudentName = $.trim($("#updateStudentName").val());
    if (updateStudentName == "") {
        $('#updateStudentName').parent().addClass("has-error");
        $('#updateStudentName').next().text("请输入学生姓名");
        $("#updateStudentName").next().show();
        flag = false;
    }else {
        $('#updateStudentName').parent().removeClass("has-error");
        $('#updateStudentName').next().text("");
        $("#updateStudentName").next().hide();
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


