$(function () {
	
	
    $('#login_submit').click(function () {
        if (!validLogin()) {
            return;
        }
		
		$.ajax({
            type: 'POST',
            url: 'studentLoginAction_login.action',
            cache: false,
            data: {
                studentId: $.trim($("#studentId").val()),
                password: $.trim($("#password").val()),
            },
            success: function (data) {
                if (data == 1) {
                    window.location.href = "student/student.jsp";

                } else if (data == 0) {
                    showInfo("登录失败，请重试");
                } else if (data == -1) {
                    showInfo("学号不存在");
                }else if (data == -2) {
                    showInfo("密码错误");
                }  else {
                    showInfo("登录失败，请重试");
                }

            },
            error: function (jqXHR, textStatus, errorThrown) {
                showInfo("登录失败，请重试");
            }
        });
			
		
	});
	
		
		
		var alert = $('.alert');
	    var formWidth = $('.bootstrap-admin-login-form').innerWidth();
	    var alertPadding = parseInt($('.alert').css('padding'));
	    if (isNaN(alertPadding)) {
	        alertPadding = parseInt($(alert).css('padding-left'));
	    }
	    $('.alert').width(formWidth - 2 * alertPadding);

});

function validLogin() {
    var flag = true;

    var studentId = $.trim($("#studentId").val());
    if (studentId == "") {
        $('#studentId').parent().addClass("has-error");
        $('#studentId').next().text("请输入学号");
        $("#studentId").next().show();
        flag = false;
    } else if (studentId.length<2 || studentId.length > 15) {
        $("#studentId").parent().addClass("has-error");
        $("#studentId").next().text("学号长度必须在2~15之间");
        $("#studentId").next().show();
        flag = false;
    } else {
        $('#studentId').parent().removeClass("has-error");
        $('#studentId').next().text("");
        $("#studentId").next().hide();
    }

    var password = $.trim($("#password").val());
    if (password == "") {
        $('#password').parent().addClass("has-error");
        $('#password').next().text("请输入密码");
        $("#password").next().show();
        flag = false;
    } else if (password.length<3 || password.length > 15) {
        $("#password").parent().addClass("has-error");
        $("#password").next().text("密码长度必须在3~15之间");
        $("#password").next().show();
        flag = false;
    } else {
        $('#password').parent().removeClass("has-error");
        $('#password').next().text("");
        $("#password").next().hide();
    }
    return flag;
}

function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}