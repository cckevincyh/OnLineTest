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
<script src="${pageContext.request.contextPath}/js/addChoice.js"></script>
<script src="${pageContext.request.contextPath}/js/addJudge.js"></script>
<script src="${pageContext.request.contextPath}/js/getChoice.js"></script>
<script src="${pageContext.request.contextPath}/js/getJudge.js"></script>
<script src="${pageContext.request.contextPath}/js/updateChoice.js"></script>
<script src="${pageContext.request.contextPath}/js/updateJudge.js"></script>
<script src="${pageContext.request.contextPath}/js/deleteChoice.js"></script>
<script src="${pageContext.request.contextPath}/js/deleteJudge.js"></script>
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
                     <li class="active">
                        <a href="${pageContext.request.contextPath}/teacher/subjectManageAction_findSubjectByPage.action"><i class="glyphicon glyphicon-chevron-right"></i> 试卷管理</a>
                    </li>
                    <li>
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
                                <div class="text-muted bootstrap-admin-box-title"> 试卷：<s:property value="#request.subject.subjectName"/></div>
                            </div>
                            <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                                    <div class="col-lg-2 form-group">
                                        <button type="button" class="btn btn-primary"  data-toggle="modal" data-target="#choiceModal">添加选择题</button>          
                                    </div>
                                     <div class="col-lg-2 form-group">
                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#judgeModal">添加判断题</button>          
                                    </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>问题</th>
                                <th>类型</th>
                                <th>分数</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            
                            
                            <!---在此插入信息-->
                            <s:if test="#request.subject.choiceNum!=0 || #request.subject.judgeNum!=0">
                            <s:iterator value="#request.subject.choices" var="choices">    
                             <tbody>
	                         	   <td><s:property value="#choices.question"/></td>
	                         	   <td>单选题</td>
	                         	   <td><s:property value="#request.subject.choiceScore"/>分</td>
	                                <td>
	                                <button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#findChoiceModal" onclick="getChoice(<s:property value="#choices.choiceId"/>)" >查看</button>
	                                	<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#updateChoiceModal" onclick="updateChoice(<s:property value="#choices.choiceId"/>)">修改</button>
	                                	<button type="button" class="btn btn-danger btn-xs" onclick="deleteChoice(<s:property value="#choices.choiceId"/>)">删除</button>
	                               	</td>                                              
                          	  </tbody>                  	
                            </s:iterator>
                             <s:iterator value="#request.subject.judges" var="judges">    
                             <tbody>
	                         	   <td><s:property value="#judges.question"/></td>
	                         	   <td>判断题</td>
	                         	   <td><s:property value="#request.subject.judgeScore"/>分</td>
	                                <td>
	                                <button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#findJudgeModal" onclick="getJudge(<s:property value="#judges.judgeId"/>)" >查看</button>
	                                	<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#updateJudgeModal" onclick="updateJudge(<s:property value="#judges.judgeId"/>)">修改</button>
	                                	<button type="button" class="btn btn-danger btn-xs" onclick="deleteJudge(<s:property value="#judges.judgeId"/>)">删除</button>
	                               	</td>                                              
                          	  </tbody>                  	
                            </s:iterator>
                            </s:if>
                            <s:else>
                            	<tbody>
	                         	   	<td>暂无数据</td>
	                                <td>暂无数据</td>
	                                <td>暂无数据</td>
	                                <td>暂无数据</td>
                          	  </tbody>
                            </s:else>
                            
                        </table>
                        
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    
    
    
    
    
    
    
    
    
   									  <!--------------------------------------添加的模糊框------------------------>  
                                 <form class="form-horizontal">   <!--保证样式水平不混乱-->   
                                        <!-- 模态框（Modal） -->
									<div class="modal fade" id="choiceModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="myModalLabel">
														添加新的选择题
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------表单-------------------->
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">问题</label>
												<div class="col-sm-7">
												<input type="hidden" class="form-control" id="subjectId" value="<s:property value="#request.subject.subjectId"/>">
													<input type="text" class="form-control" id="choice_question"  placeholder="请输入选择题问题">
												<label class="control-label" for="choice_question" style="display:none;"></label>
												</div>
										</div>
										
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">选项A</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="optionA"  placeholder="请输入选项A答案">
												<label class="control-label" for="optionA" style="display:none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">选项B</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="optionB"  placeholder="请输入选项B答案">
												<label class="control-label" for="optionB" style="display:none;"></label>
												</div>
										</div>
										
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">选项C</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="optionC"  placeholder="请输入选项C答案">
												<label class="control-label" for="optionC" style="display:none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">选项D</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="optionD"  placeholder="请输入选项D答案">
												<label class="control-label" for="optionC" style="display:none;"></label>
												</div>
										</div>
										
										
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">正确答案</label>
											<div class="col-sm-7">
												 <select class="form-control" id="choice_answer">
                                           				 <option value="-1">请选择</option>     
                                           				  <option value="A">A</option>  
                                           				   <option value="B">B</option>  
                                           				    <option value="C">C</option>  
                                           				     <option value="D">D</option>                                      
                                      			  </select>
												<label class="control-label" for="choice_answer" style="display: none;"></label>	
											</div>
										</div>
											
										<!---------------------表单-------------------->
									</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭
													</button>
													<button type="button" class="btn btn-primary" id="addChoice">
														添加
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>

                                 </form>	
 								<!--------------------------------------添加的模糊框------------------------>  
 
 
 
 
 
 								 <!--------------------------------------添加的模糊框------------------------>  
                                 <form class="form-horizontal">   <!--保证样式水平不混乱-->   
                                        <!-- 模态框（Modal） -->
									<div class="modal fade" id="judgeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="myModalLabel">
														添加新的判断题
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------表单-------------------->
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">问题</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="judge_question"  placeholder="请输入选择题问题">
												<label class="control-label" for="judge_question" style="display:none;"></label>
												</div>
										</div>
								
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">正确答案</label>
											<div class="col-sm-7">
												 <select class="form-control" id="judge_answer">
                                           				 <option value="-1">请选择</option>     
                                           				  <option value="Y">对</option>  
                                           				   <option value="N">错</option>  
                                      			  </select>
												<label class="control-label" for="judge_answer" style="display: none;"></label>	
											</div>
										</div>
											
										<!---------------------表单-------------------->
									</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭
													</button>
													<button type="button" class="btn btn-primary" id="addJudge">
														添加
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>

                                 </form>	
 								<!--------------------------------------添加的模糊框------------------------>  
 
 
     
                                     <!-- 修改模态框（Modal） -->
                                     <!-------------------------------------------------------------->  
                                
                                        <!-- 修改模态框（Modal） -->
                               <form class="form-horizontal">   <!--保证样式水平不混乱-->   
									<div class="modal fade" id="updateChoiceModal" tabindex="-1" role="dialog" aria-labelledby="updateChoiceModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="updateChoiceModalLabel">
														修改选择题
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------表单-------------------->
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">问题</label>
												<div class="col-sm-7">
												<input type="hidden" class="form-control" id="updateChoiceId">
													<input type="text" class="form-control" id="updateChoice_question"  placeholder="请输入选择题问题">
												<label class="control-label" for="updateChoice_question" style="display:none;"></label>
												</div>
										</div>
										
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">选项A</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateOptionA"  placeholder="请输入选项A答案">
												<label class="control-label" for="updateOptionA" style="display:none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">选项B</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateOptionB"  placeholder="请输入选项B答案">
												<label class="control-label" for="updateOptionB" style="display:none;"></label>
												</div>
										</div>
										
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">选项C</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateOptionC"  placeholder="请输入选项C答案">
												<label class="control-label" for="updateOptionC" style="display:none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">选项D</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateOptionD"  placeholder="请输入选项D答案">
												<label class="control-label" for="updateOptionD" style="display:none;"></label>
												</div>
										</div>
										
										
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">正确答案</label>
											<div class="col-sm-7">
												 <select class="form-control" id="updateChoice_answer">
                                           				 <option value="-1">请选择</option>     
                                           				  <option value="A">A</option>  
                                           				   <option value="B">B</option>  
                                           				    <option value="C">C</option>  
                                           				     <option value="D">D</option>                                      
                                      			  </select>
												<label class="control-label" for="updateChoice_answer" style="display: none;"></label>	
											</div>
										</div>
															
										</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭
													</button>
													<button type="button" class="btn btn-primary" id="updateChoice">
														修改
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>
	
                                 </form>
                                   <!-------------------------------------------------------------->
 
    
    
    
    
    
    				 <!-- 修改模态框（Modal） -->
                                     <!-------------------------------------------------------------->  
                                
                                        <!-- 修改模态框（Modal） -->
                               <form class="form-horizontal">   <!--保证样式水平不混乱-->   
									<div class="modal fade" id="updateJudgeModal" tabindex="-1" role="dialog" aria-labelledby="updateJudgeModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="updateJudgeModalLabel">
														修改判断题
													</h4>
												</div>
												<div class="modal-body">
												
									<!---------------------表单-------------------->
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">问题</label>
												<div class="col-sm-7">
												<input type="hidden" class="form-control" id="updateJudgeId" >
													<input type="text" class="form-control" id="updateJudge_question"  placeholder="请输入选择题问题">
												<label class="control-label" for="updateJudge_question" style="display:none;"></label>
												</div>
										</div>
								
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">正确答案</label>
											<div class="col-sm-7">
												 <select class="form-control" id="updateJudge_answer">
                                           				 <option value="-1">请选择</option>     
                                           				  <option value="Y">对</option>  
                                           				   <option value="N">错</option>  
                                      			  </select>
												<label class="control-label" for="updateJudge_answer" style="display: none;"></label>	
											</div>
										</div>
															
										</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭
													</button>
													<button type="button" class="btn btn-primary" id="updateJudge">
														修改
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>
	
                                 </form>
                                   <!-------------------------------------------------------------->
    
    
    
 
 
 
 
    
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
                                   
                                   
                                   
                                   
                                   
   						 <!--------------------------------------查看的模糊框------------------------>  
                                 <form class="form-horizontal">   <!--保证样式水平不混乱-->   
                                        <!-- 模态框（Modal） -->
									<div class="modal fade" id="findChoiceModal" tabindex="-1" role="dialog" aria-labelledby="findChoiceModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="findChoiceModalLabel">
														查看选择题信息
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------表单-------------------->
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">所属课程</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="findChoice_course" readonly="readonly">
												<label class="control-label" for="findChoice_course" style="display:none;"></label>
												</div>
										</div>
										
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">所属试卷</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="findChoice_subject" readonly="readonly">
												<label class="control-label" for="findChoice_subject" style="display:none;"></label>
												</div>
										</div>
										
										
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">问题</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="findChoice_question" readonly="readonly">
												<label class="control-label" for="findChoice_question" style="display:none;"></label>
												</div>
										</div>
										
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">选项A</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="findOptionA" readonly="readonly">
												<label class="control-label" for="findOptionA" style="display:none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">选项B</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="findOptionB" readonly="readonly">
												<label class="control-label" for="findOptionB" style="display:none;"></label>
												</div>
										</div>
										
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">选项C</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="findOptionC" readonly="readonly">
												<label class="control-label" for="findOptionC" style="display:none;"></label>
												</div>
										</div>
										
										<div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">选项D</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="findOptionD" readonly="readonly">
												<label class="control-label" for="findOptionD" style="display:none;"></label>
												</div>
										</div>
										
										
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">正确答案</label>
											<div class="col-sm-7">
													<input type="text" class="form-control" id="findChoice_answer" readonly="readonly">
													<label class="control-label" for="findChoice_answer" style="display:none;"></label>
											</div>
										</div>
										
										
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">分值</label>
											<div class="col-sm-7">
													<input type="text" class="form-control" id="findChoice_score" readonly="readonly">
													<label class="control-label" for="findChoice_score" style="display:none;"></label>
											</div>
										</div>
									</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>

                                 </form>	
 								<!--------------------------------------查看的模糊框------------------------>  
 								
 								
 								
 								
 									 <!--------------------------------------查看的模糊框------------------------>  
                                 <form class="form-horizontal">   <!--保证样式水平不混乱-->   
                                        <!-- 模态框（Modal） -->
									<div class="modal fade" id="findJudgeModal" tabindex="-1" role="dialog" aria-labelledby="findJudgeModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="findJudgeModalLabel">
														查看判断题信息
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------表单-------------------->
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">所属课程</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="findJudge_course" readonly="readonly">
												<label class="control-label" for="findJudge_course" style="display:none;"></label>
												</div>
										</div>
										
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">所属试卷</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="findJudge_subject" readonly="readonly">
												<label class="control-label" for="findJudge_subject" style="display:none;"></label>
												</div>
										</div>
										
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">问题</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="findJudge_question" readonly="readonly">
												<label class="control-label" for="findJudge_question" style="display:none;"></label>
												</div>
										</div>
										
										
										
										
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">正确答案</label>
											<div class="col-sm-7">
													<input type="text" class="form-control" id="findJudge_answer" readonly="readonly">
													<label class="control-label" for="findJudge_answer" style="display:none;"></label>
											</div>
										</div>
										
										
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">分值</label>
											<div class="col-sm-7">
													<input type="text" class="form-control" id="findJudge_score" readonly="readonly">
													<label class="control-label" for="findJudge_score" style="display:none;"></label>
											</div>
										</div>
									</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default" data-dismiss="modal">关闭
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>

                                 </form>	
 								<!--------------------------------------查看的模糊框------------------------>  
                                   
                                   
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
