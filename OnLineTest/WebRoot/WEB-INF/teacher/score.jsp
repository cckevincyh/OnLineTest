<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"   prefix="s"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN" class="ax-vertical-centered">
<head>
<meta charset="UTF-8">
<title>在线考试系统后台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-admin-theme.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-admin-theme.css">
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/jQuery/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-dropdown.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/js/teacherUpdatePwd.js"></script>
</head>



<body class="bootstrap-admin-with-small-navbar">
    <nav class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="collapse navbar-collapse main-navbar-collapse">
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/teacher/teacher.jsp"><strong>欢迎使用在线考试系统</strong></a>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-hover="dropdown"> <i class="glyphicon glyphicon-user"></i> 欢迎您， <s:property value="#session.teacher.teacherName"/> <i class="caret"></i></a>
                            
                                 <ul class="dropdown-menu">
                                    <li><a href="#updatepwd" data-toggle="modal">修改密码</a></li>
                                     <li role="presentation" class="divider"></li>
                                    <li><a href="${pageContext.request.contextPath}/teacherLoginAction_logout.action">退出</a></li>
                                </ul>
                                
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </nav>

    <div class="container">
        <!-- left, vertical navbar & content -->
        <div class="row">
            <!-- left, vertical navbar -->
            <div class="col-md-2 bootstrap-admin-col-left">
                <ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
                    <li>
                        <a href="${pageContext.request.contextPath}/teacher/subjectManageAction_findSubjectByPage.action"><i class="glyphicon glyphicon-chevron-right"></i> 试卷管理</a>
                    </li>
                    <li class="active">
                        <a href="${pageContext.request.contextPath}/teacher/scoreManageAction_findScoreByPage.action"><i class="glyphicon glyphicon-chevron-right"></i> 成绩查询</a>
                    </li>
                   
                </ul>
            </div>

           <!-- content -->
            <div class="col-md-10">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default bootstrap-admin-no-table-panel">
                            <div class="panel-heading">
                                <div class="text-muted bootstrap-admin-box-title">学生卷面信息</div>
                            </div>
                            <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                                <form class="form-horizontal" action="${pageContext.request.contextPath}/student/subjectManageAction_querySubject.action" method="post">
                                    <div class="col-lg-12 form-group">
                                        <label class="col-lg-6 control-label" for="query_ano"><h4><strong><s:property value="#request.resultScore.score.subject.subjectName"/></strong><h4></label>
                                    </div>
                                    <div class="col-lg-12 form-group">
                                        <label class="col-lg-6 control-label" for="query_ano"><i>(卷面总分:<s:property value="#request.resultScore.score.subject.allScore"/>)</i></label>
                                    </div>
                                      <div class="col-lg-3 form-group">
                                        <label class="col-lg-6 control-label" for="query_bno1">课程:</label>
                                          <label class="col-lg-6 control-label" for="query_bno1"><i><s:property value="#request.resultScore.score.subject.course.courseName"/></i></label>
                                    </div>
                                    
                                    <div class="col-lg-3 form-group">
                                        <label class="col-lg-6 control-label" for="query_bno1">|  考试时间:</label>
                                          <label class="col-lg-6 control-label" for="query_bno1"><i><s:property value="#request.resultScore.score.subject.subjectTime"/>分钟</i></label>
                                    </div>
                                    
                                    <div class="col-lg-3 form-group">
                                        <label class="col-lg-6 control-label" for="query_bno1">|  考生姓名:</label>
                                        <input type="hidden" id="test_studentId" value="<s:property value="#request.resultScore.score.student.studentId"/>">
                                          <label class="col-lg-6 control-label" for="query_bno1"><i><s:property value="#request.resultScore.score.student.studentName"/></i> </label>
                                    </div>
                                    
                                    <div class="col-lg-3  form-group">
                                        <label class="col-lg-6 control-label" for="query_bno1">|  总得分:</label>
                                          <label class="col-lg-6 control-label" for="query_bno1" id="allSocre"><i><s:property value="#request.resultScore.score.allScore"/>分</i></label>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                            
                              <c:set var="index" value="1"/><!--统计题目 -->
                            <!---在此插入信息-->
                            <s:if test="#request.resultScore!=null">
                            	<!--选择题 -->
                            <s:iterator value="#request.resultScore.choiceAnswers" var="choiceAnswer">    
                              <div class="col-md-12">
                        				<div class="panel panel-default">
                        			    	<div class="panel-heading">
                         			      	 <div class="text-muted bootstrap-admin-box-title">
                         			      	  <s:if test="#choiceAnswer.answer.answer==#choiceAnswer.answer.goodAnswer">
                         			      		 ${index }.<s:property value="#choiceAnswer.choice.question"/>?  (选择题  <s:property value="#request.resultScore.score.subject.choiceScore"/>分)| 回答正确 | 正确答案:<s:property value="#choiceAnswer.answer.goodAnswer"/>|得分:<s:property value="#choiceAnswer.answer.score"/>
                         			      	 </s:if>
                         			      	 <s:else>
                         			      	 	<font color="#FF0000"> ${index }.<s:property value="#choiceAnswer.choice.question"/>?  (选择题  <s:property value="#request.resultScore.score.subject.choiceScore"/>分)  | 回答错误 | 正确答案:<s:property value="#choiceAnswer.answer.goodAnswer"/>|得分:<s:property value="#choiceAnswer.answer.score"/></font>
                         			      	 </s:else>
                         			      	 </div>
                         			      	   <c:set var="index" value="${index+1 }"/><!--统计题目 -->
                         				  	</div>
                            			<div class="bootstrap-admin-panel-content">
		                                	<ul>
		                                	 <div class="radio">
												  <label>
												   <s:if test="#choiceAnswer.answer.answer=='A'.toString()">
												    <input type="radio" name='choice_<s:property value="#choiceAnswer.choice.choiceId"/>' id='optionsA_<s:property value="#choiceAnswer.choice.choiceId"/>'  checked="checked" disabled="disabled">A. <s:property value="#choiceAnswer.choice.optionA"/>
												 	 </s:if>
												 	 <s:else>
												 	    <input type="radio" name='choice_<s:property value="#choiceAnswer.choice.choiceId"/>' id='optionsA_<s:property value="#choiceAnswer.choice.choiceId"/>'  disabled="disabled">A. <s:property value="#choiceAnswer.choice.optionA"/>
												 	 </s:else>
												  </label>
												</div>
												<div class="radio">
												  <label>
												  <s:if test="#choiceAnswer.answer.answer=='B'.toString()">
												    <input type="radio" name='choice_<s:property value="#choiceAnswer.choice.choiceId"/>' id='optionsB_<s:property value="#choiceAnswer.choice.choiceId"/>' checked="checked" disabled="disabled">B. <s:property value="#choiceAnswer.choice.optionB"/>
												  	</s:if>
												  	 <s:else>
												 	   <input type="radio" name='choice_<s:property value="#choiceAnswer.choice.choiceId"/>' id='optionsB_<s:property value="#choiceAnswer.choice.choiceId"/>'  disabled="disabled">B. <s:property value="#choiceAnswer.choice.optionB"/>
												  	</s:else>
												  </label>
												</div>
												<div class="radio">
												  <label>
												    <s:if test="#choiceAnswer.answer.answer=='C'.toString()">
												  	  <input type="radio" name='choice_<s:property value="#choiceAnswer.choice.choiceId"/>' id='optionsC_<s:property value="#choiceAnswer.choice.choiceId"/>'  checked="checked" disabled="disabled">C. <s:property value="#choiceAnswer.choice.optionC"/>
												  	</s:if>
												  	 <s:else>
												  	     <input type="radio" name='choice_<s:property value="#choiceAnswer.choice.choiceId"/>' id='optionsC_<s:property value="#choiceAnswer.choice.choiceId"/>'  disabled="disabled">C. <s:property value="#choiceAnswer.choice.optionC"/>
												  	 </s:else>
												  </label>
												</div>
												<div class="radio">
												  <label>
												   <s:if test="#choiceAnswer.answer.answer=='D'.toString()">
												    <input type="radio" name='choice_<s:property value="#choiceAnswer.choice.choiceId"/>' id='optionsD_<s:property value="#choiceAnswer.choice.choiceId"/>' checked="checked" disabled="disabled">D. <s:property value="#choiceAnswer.choice.optionD"/>
												 </s:if>
												  	 <s:else>
												  	  <input type="radio" name='choice_<s:property value="#choiceAnswer.choice.choiceId"/>' id='optionsD_<s:property value="#choiceAnswer.choice.choiceId"/>'  disabled="disabled">D. <s:property value="#choiceAnswer.choice.optionD"/>
												  	  </s:else>
												  </label>
												</div>
		                               		 </ul>
                           			 	</div>
                       		 		</div>
                 			   </div>
                            </s:iterator>
                            	<!-- 判断题 -->
                              <s:iterator value="#request.resultScore.judgeAnswers" var="judgeAnswer">    
                              <div class="col-md-12">
                        				<div class="panel panel-default">
                        			    	<div class="panel-heading">
                         			      	 <div class="text-muted bootstrap-admin-box-title">
                         			      	  <s:if test="#judgeAnswer.answer.answer==#judgeAnswer.answer.goodAnswer">
                         			      		 ${index }.<s:property value="#judgeAnswer.judge.question"/>?  (判断题  <s:property value="#request.resultScore.score.subject.judgeScore"/>分)| 回答正确 | 正确答案:<s:property value="#judgeAnswer.answer.goodAnswer"/>|得分:<s:property value="#judgeAnswer.answer.score"/>
                         			      	 </s:if>
                         			      	 <s:else>
                         			      	 	<font color="#FF0000"> ${index }.<s:property value="#judgeAnswer.judge.question"/>?  (判断题  <s:property value="#request.resultScore.score.subject.judgeScore"/>分)| 回答错误| 正确答案:<s:property value="#judgeAnswer.answer.goodAnswer"/>|得分:<s:property value="#judgeAnswer.answer.score"/></font>
                         			      	 </s:else>
                         			      	 <c:set var="index" value="${index+1}"/><!--统计题目 -->
                         				  	</div>
                         				  	</div>
                            			<div class="bootstrap-admin-panel-content">
		                                	<ul>
		                                	 <div class="radio">
												  <label>
												    <s:if test="#judgeAnswer.answer.answer=='Y'.toString()">
												    <input type="radio" name='judge_<s:property value="#judgeAnswer.judge.judgeId"/>' id='optionsY_<s:property value="#judgeAnswer.judge.judgeId"/>' disabled="disabled" checked="checked">对
												  </s:if>
												  	 <s:else>
												  	  <input type="radio" name='judge_<s:property value="#judgeAnswer.judge.judgeId"/>' id='optionsY_<s:property value="#judgeAnswer.judge.judgeId"/>' disabled="disabled">对
												  	  </s:else>
												  </label>
												</div>
												<div class="radio">
												  <label>
												      <s:if test="#judgeAnswer.answer.answer=='N'.toString()">
												   		 <input type="radio" name='judge_<s:property value="#judgeAnswer.judge.judgeId"/>' id='optionsN_<s:property value="#judgeAnswer.judge.judgeId"/>' checked="checked" disabled="disabled"'>错
												 	</s:if>
												  	 <s:else>
												  	  <input type="radio" name='judge_<s:property value="#judgeAnswer.judge.judgeId"/>' id='optionsN_<s:property value="#judgeAnswer.judge.judgeId"/>' disabled="disabled"'>错
												  	 </s:else>
												  </label>
												</div>
		                               		 </ul>
                           			 	</div>
                       		 		</div>
                 			   </div>
                            </s:iterator>
                            </s:if>
                     
                        
                </div>
            </div>
        </div>
    </div>
    
    
    
 
 
 
    
    <!------------------------------修改密码模糊框-------------------------------->  
                 
                   <form class="form-horizontal">   <!--保证样式水平不混乱-->                  
                                     <!-- 模态框（Modal） -->
				<div class="modal fade" id="updatepwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">
									修改密码
								</h4>
							</div>
							
							<div class="modal-body">
							 
								<!--正文-->
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">原密码</label>
								<div class="col-sm-7">
									<input type="password" class="form-control" id="oldPwd"  placeholder="请输入原密码">
										<label class="control-label" for="oldPwd" style="display:none;"></label>		
								</div>
							</div>	
							
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">新密码</label>
								<div class="col-sm-7">
									<input type="password" class="form-control" id="newPwd"  placeholder="请输入新密码">
										<label class="control-label" for="newPwd" style="display:none;"></label>			
								</div>
							</div>	
							
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">确认密码</label>
								<div class="col-sm-7">
									<input type="password" class="form-control" id="confirmPwd"  placeholder="请输入确认密码">
										<label class="control-label" for="confirmPwd" style="display:none;"></label>			
								</div>
							</div>	
								<!--正文-->
								
								
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
								<button type="button" class="btn btn-primary" id="update_Pwd">
									修改
								</button>
							</div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>

				</form>	
                                   <!-------------------------------------------------------------->
                                   
                                   
                                   
                                   
                                   
                                   
                                   
				    <div class="modal fade" id="modal_info" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
				    <div class="modal-dialog" role="document">
				        <div class="modal-content">
				            <div class="modal-header">
				                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				                <h4 class="modal-title" id="infoModalLabel">提示</h4>
				            </div>
				            <div class="modal-body">
				                <div class="row">
				                    <div class="col-lg-12" id="div_info"></div>
				                </div>
				            </div>
				            <div class="modal-footer">
				                <button type="button" class="btn btn-default" id="btn_info_close" data-dismiss="modal">关闭</button>
				            </div>
				        </div>
				    </div>
				</div>
				    
    
 
 

 
</body>
</html>
