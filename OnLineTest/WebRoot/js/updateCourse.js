$(function () {
	

     $('#updateCourse').click(function () {
        if (!validUpdateCourse()) {
            return;
        }
		
		$.ajax({
            type: 'POST',
            url: 'admin/courseManageAction_updateCourse.action',
            cache: false,
            data: {
				courseId:$.trim($("#updateId").val()),
                courseName: $.trim($("#updateCourseName").val()),
				 
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





function updateCourse(id){
	
	
	$.ajax({
            type: 'POST',
            url: 'admin/courseManageAction_getCourse.action',
            cache: false,
			dataType: "json",
            data: {
                courseId: id,
            },
            success: function (data) {
              	 $("#updateId").val(data.courseId);
				$("#updateCourseName").val(data.courseName);

            }
        });

}


function validUpdateCourse() {
     var flag = true;

    var courseName = $.trim($("#updateCourseName").val());
    if (courseName == "") {
        $('#updateCourseName').parent().addClass("has-error");
        $('#updateCourseName').next().text("请输入课程名");
        $("#updateCourseName").next().show();
        flag = false;
    }else {
        $('#updateCourseName').parent().removeClass("has-error");
        $('#updateCourseName').next().text("");
        $("#updateCourseName").next().hide();
    }

  
    return flag;
}



function showInfo(msg) {
    $("#div_info").text(msg);
    $("#modal_info").modal('show');
}


