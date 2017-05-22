window.onload = new function(){
	
	$.ajax({
        type: 'GET',
        url: 'teacher/getAllCourseAction.action',
        dataType: "json",
        cache: false,
        success: function (data) {
			for(var index in data) {
				var op = document.createElement("option");
				op.value = data[index].courseId;
				var textNode = document.createTextNode(data[index].courseName);
				op.appendChild(textNode);
				
				document.getElementById("courseId").appendChild(op);
			}

        },
    });
	
	
	
	
};