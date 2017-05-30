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

<script src="${pageContext.request.contextPath}/js/studentUpdatePwd.js"></script>
<script src="${pageContext.request.contextPath}/js/test.js"></script>
<script src="${pageContext.request.contextPath}/js/onLineTest.js"></script>
</head>



<body class="bootstrap-admin-with-small-navbar">
    <nav class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="collapse navbar-collapse main-navbar-collapse">
                        <a class="navbar-brand" href="${pageContext.request.contextPath}/student/student.jsp"><strong>欢迎使用在线考试系统</strong></a>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" role="button" class="dropdown-toggle" data-hover="dropdown"> <i class="glyphicon glyphicon-user"></i> 欢迎您， <s:property value="#session.student.studentName"/> <i class="caret"></i></a>
                            
                                 <ul class="dropdown-menu">
                                    <li><a href="#updatepwd" data-toggle="modal">修改密码</a></li>
                                     <li role="presentation" class="divider"></li>
                                    <li><a href="${pageContext.request.contextPath}/studentLoginAction_logout.action">退出</a></li>
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
                     <li class="active">
                        <a href="${pageContext.request.contextPath}/student/subjectManageAction_findSubjectByPage.action"><i class="glyphicon glyphicon-chevron-right"></i> 在线考试</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/student/scoreManageAction_findMyScoreByPage.action"><i class="glyphicon glyphicon-chevron-right"></i> 个人成绩查询</a>
                    </li>
                   
                </ul>
            </div>

           <!-- content -->
            <div class="col-md-10">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default bootstrap-admin-no-table-panel">
                            <div class="panel-heading">
                                <div class="text-muted bootstrap-admin-box-title">试卷信息</div>
                            </div>
                            <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                                <form class="form-horizontal" action="${pageContext.request.contextPath}/student/subjectManageAction_querySubject.action" method="post">
                                    <div class="col-lg-12 form-group">
                                        <label class="col-lg-6 control-label" for="query_ano"><h4><strong><s:property value="#request.subject.subjectName"/></strong><h4></label>
                                    </div>
                                    <div class="col-lg-12 form-group">
                                        <label class="col-lg-6 control-label" for="query_ano"><i>(卷面总分:<s:property value="#request.resultScore.score.subject.allScore"/>)</i></label>
                                    </div>
                                      <div class="col-lg-3 form-group">
                                        <label class="col-lg-6 control-label" for="query_bno1">课程:</label>
                                         <input type="hidden" id="test_subjectId" value="<s:property value="#request.subject.subjectId"/>">
                                          <label class="col-lg-6 control-label" for="query_bno1"><i><s:property value="#request.subject.course.courseName"/></i></label>
                                    </div>
                                    
                                    <div class="col-lg-3 form-group">
                                        <label class="col-lg-6 control-label" for="query_bno1">|  考试时间:</label>
                                        	<input type="hidden" id="test_time" value="<s:property value="#request.subject.subjectTime"/>">
                                          <label class="col-lg-6 control-label" for="query_bno1"><i><s:property value="#request.subject.subjectTime"/>分钟</i></label>
                                    </div>
                                    
                                    <div class="col-lg-3 form-group">
                                        <label class="col-lg-6 control-label" for="query_bno1">|  考生姓名:</label>
                                        <input type="hidden" id="test_studentId" value="<s:property value="#session.student.studentId"/>">
                                          <label class="col-lg-6 control-label" for="query_bno1"><i><s:property value="#session.student.studentName"/></i> </label>
                                    </div>
                                    
                                    <div class="col-lg-3  form-group">
                                        <label class="col-lg-6 control-label" for="query_bno1">|  剩余时间:</label>
                                          <label class="col-lg-6 control-label" for="query_bno1" id="remainTime"></label>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                            
                              <c:set var="index" value="1"/><!--统计题目 -->
                            <!---在此插入信息-->
                            <s:if test="#request.subject!=null">
                            	<!--选择题 -->
                            <s:iterator value="#request.subject.choices" var="choice">    
                              <div class="col-md-12">
                        				<div class="panel panel-default">
                        			    	<div class="panel-heading">
                         			      	 <div class="text-muted bootstrap-admin-box-title">${index }.<s:property value="#choice.question"/>?(选择题)</div>
                         			      	   <c:set var="index" value="${index+1 }"/><!--统计题目 -->
                         				  	</div>
                            			<div class="bootstrap-admin-panel-content">
		                                	<ul>
		                                	 <div class="radio">
												  <label>
												    <input type="radio" name='choice_<s:property value="#choice.choiceId"/>' id='optionsA_<s:property value="#choice.choiceId"/>' value='<s:property value="#choice.choiceId"/>_1_A'>A. <s:property value="#choice.optionA"/>
												  </label>
												</div>
												<div class="radio">
												  <label>
												    <input type="radio" name='choice_<s:property value="#choice.choiceId"/>' id='optionsB_<s:property value="#choice.choiceId"/>' value='<s:property value="#choice.choiceId"/>_1_B'>B. <s:property value="#choice.optionB"/>
												  </label>
												</div>
												<div class="radio">
												  <label>
												    <input type="radio" name='choice_<s:property value="#choice.choiceId"/>' id='optionsC_<s:property value="#choice.choiceId"/>' value='<s:property value="#choice.choiceId"/>_1_C'>C. <s:property value="#choice.optionC"/>
												  </label>
												</div>
												<div class="radio">
												  <label>
												    <input type="radio" name='choice_<s:property value="#choice.choiceId"/>' id='optionsD_<s:property value="#choice.choiceId"/>' value='<s:property value="#choice.choiceId"/>_1_D'>D. <s:property value="#choice.optionD"/>
												  </label>
												</div>
		                               		 </ul>
                           			 	</div>
                       		 		</div>
                 			   </div>
                            </s:iterator>
                            	<!-- 判断题 -->
                              <s:iterator value="#request.subject.judges" var="judge">    
                              <div class="col-md-12">
                        				<div class="panel panel-default">
                        			    	<div class="panel-heading">
                         			      	 <div class="text-muted bootstrap-admin-box-title">${index }.<s:property value="#judge.question"/>?(判断题)</div>
                         			      	 <c:set var="index" value="${index+1}"/><!--统计题目 -->
                         				  	</div>
                            			<div class="bootstrap-admin-panel-content">
		                                	<ul>
		                                	 <div class="radio">
												  <label>
												    <input type="radio" name='judge_<s:property value="#judge.judgeId"/>' id='optionsY_<s:property value="#judge.judgeId"/>' value='<s:property value="#judge.judgeId"/>_2_Y'>对
												  </label>
												</div>
												<div class="radio">
												  <label>
												    <input type="radio" name='judge_<s:property value="#judge.judgeId"/>' id='optionsN_<s:property value="#judge.judgeId"/>' value='<s:property value="#judge.judgeId"/>_2_N'>错
												  </label>
												</div>
		                               		 </ul>
                           			 	</div>
                       		 		</div>
                 			   </div>
                 			   
                            </s:iterator>
                            </s:if>
                		     <div class="col-md-12" align="center">
                                        <button type="button" class="btn btn-primary" onclick="assignment()">提交试卷</button>          
                 			    </div>
                        
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
