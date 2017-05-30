/**
 * 这个文件下的代码还是不要乱改好,我这个文件上花了一天的时间，遇到了各种BUG
 */

$(window).bind('beforeunload',function(){	
			var arr =  $("input[type=radio]:checked");
			var dataStr = "";
			for(var i = 0; i<arr.length;i++){
				if (i == 0) {
					dataStr = arr[i].value;
				}else{
					dataStr = arr[i].value + "-" + dataStr;
				}
			}
			//传入考生的id,试卷id,试题答案字符串
			$.ajax({
	            type: 'POST',
	            url: '../onLineTest/onLineTestAction_onLineTest.action',
	            cache: false,
				sync:false, 
	            data: {
	                answer: dataStr,
	                subjectId: $.trim($("#test_subjectId").val()),
					studentId:$.trim($("#test_studentId").val()),
	            },
	            success: function (data) {
					editCookie($.trim($("#test_studentId").val()) + "-" + $.trim($("#test_subjectId").val()), 0, 0);

	            },
	            error: function (jqXHR, textStatus, errorThrown) {
					editCookie($.trim($("#test_studentId").val()) + "-" + $.trim($("#test_subjectId").val()), 0, 0);
						alert("你已交卷");
	            }
     	   });
		return ;
	}
);


$(function() {
	
			var _minute = parseInt($.trim($("#test_time").val()));//考试时间(分钟)
			var _expiresHours = _minute * 60 * 1000;//过期时间
			if(!hasSetCookie()){//如果没有这个cookie就新增一个
				addCookie($.trim($("#test_studentId").val()) + "-" + $.trim($("#test_subjectId").val()), _expiresHours, _expiresHours);	//cookieName：考生的id+考试科目,cookieValue:考试时间，expiresHours：cookie过期时间
			} 
			//开始倒计时
			settime($("#remainTime")); //传入倒计时的控件
		});
		function hasSetCookie(){
			//获取cookie字符串 
			var strCookie = document.cookie;
			//将多cookie切割为多个名/值对 
			var arrCookie = strCookie.split("; ");
			  //遍历cookie数组，处理每个cookie对 
			for (var i = 0; i < arrCookie.length; i++) {
				var arr = arrCookie[i].split("=");
				//找到名称为考试id+考试科目的cookie，则返回true,否则返回false
				if (arr[0] == $.trim($("#test_studentId").val()) + "-" + $.trim($("#test_subjectId").val())) {
					return true;
				}
			};
			return false;
		}
		//开始倒计时
		function settime(remainTime) {
			var _countdown = parseInt(getCookieValue($.trim($("#test_studentId").val()) + "-" + $.trim($("#test_subjectId").val()))) / 1000;	//得到倒数秒
			if (_countdown <= 0) {	//倒数结束
				alert("考试已结束！");
				endExam();	//结束考试
//				window.location.href = "student.jsp";
			} else {
				var _second = _countdown % 60;
				var _minute = parseInt(_countdown / 60) % 60;
				var _hour = parseInt(parseInt(_countdown / 60) / 60);

				if (_hour < 10)
					_hour = "0" + _hour.toString();
				if (_second < 10)
					_second = "0" + _second.toString();
				if (_minute < 10)
					_minute = "0" + _minute.toString();

				remainTime.html("<i>"+_hour + ":" + _minute + ":" + _second+"</i>");	//倒计时控件显示
				_countdown--;
				editCookie($.trim($("#test_studentId").val()) + "-" + $.trim($("#test_subjectId").val()), _countdown * 1000, _countdown * 1000);
			}
			//每1000毫秒执行一次
			setTimeout(function() {
				settime(remainTime);
			}, 1000);
		};

		//添加cookie
		function addCookie(name, value, expiresHours) {
			var cookieString = name + "=" + escape(value); //escape() 函数可对字符串进行编码，这样就可以在所有的计算机上读取该字符串。
			//判断是否设置过期时间,0代表关闭浏览器时失效
			if (expiresHours > 0) {
				var date = new Date();
				date.setTime(date.getTime() + expiresHours * 1000);
				cookieString = cookieString + ";expires=" + date.toUTCString();
			}
			document.cookie = cookieString;
		}

		//修改cookie的值
		function editCookie(name, value, expiresHours) {
			var cookieString = name + "=" + escape(value);
			if (expiresHours > 0) {
				var date = new Date();
				date.setTime(date.getTime() + expiresHours * 1000); //单位是毫秒
				cookieString = cookieString + ";expires=" + date.toGMTString();
			}
			document.cookie = cookieString;
		}

		//根据名字获取cookie的值
		function getCookieValue(name) {
			var strCookie = document.cookie;
			var arrCookie = strCookie.split("; ");
			for (var i = 0; i < arrCookie.length; i++) {
				var arr = arrCookie[i].split("=");
				if (arr[0] == name) {
					return unescape(arr[1]);
					break;
				} else {
					continue;
				};
			};
		}
		
		
		function endExam(){
			var arr =  $("input[type=radio]:checked");
			var dataStr = "";
			for(var i = 0; i<arr.length;i++){
				if (i == 0) {
					dataStr = arr[i].value;
				}else{
					dataStr = arr[i].value + "-" + dataStr;
				}
			}
			//传入考生的id,试卷id,试题答案字符串
			$.ajax({
	            type: 'POST',
	            url: '../onLineTest/onLineTestAction_onLineTest.action',
	            cache: false,
				sync:false, 
	            data: {
	                answer: dataStr,
	                subjectId: $.trim($("#test_subjectId").val()),
					studentId:$.trim($("#test_studentId").val()),
	            },
	            success: function (data) {
					$(window).unbind('beforeunload');
                    window.location.href = "student.jsp";
	            },
	            error: function (jqXHR, textStatus, errorThrown) {
					$(window).unbind('beforeunload');
					 window.location.href = "student.jsp";
	            }
     	   });
		}
		
		
		function assignment(){
			alert("已交卷");
			endExam();
		}
			
