<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%><!-- 标签：c标签，spring标签等 -->
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>java email</title>

<!-- Bootstrap -->
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<h1>Email</h1>
	<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
	<script src="${ctx}/jquery/jquery.min.js"></script>
	<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
	<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${ctx}/pages/js/java_email.js"></script>

	<b>本页url-->http://localhost:9011/pages/email/java_email.jsp</b>

	<h6>邮件列表</h6>
	<form id="mailList" name="mailList">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>序号</th>
					<th>姓名</th>
					<th>email</th>
					<th>用户权限</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="index" value="0" />
				<c:forEach items="${emails}" var="email">
					<tr>
						<td>${index+1}</td>
						<td><input type="text" name="mailList[${index}].userName"
							value="${email.userName}" /></td>
						<td><input type="text" name="mailList[${index}].email"
							value="${email.email}" /></td>
						<td><input type="text" name="mailList[${index}].role"
							value="${email.role}" /></td>
					</tr>
					<c:set var="index" value="${index+1}" />
				</c:forEach>
			</tbody>
		</table>
	</form>

	<div class="btn-toolbar" role="toolbar" aria-label="...">
		<div class="btn-group" role="group" aria-label="表格操作按钮组">
			<button type="button" class="btn btn-default" data-toggle="modal"
				data-target="#myAdd">增加</button>
			<button type="button" class="btn btn-default" onclick="del();">删除</button>
			<button type="button" class="btn btn-default" onclick="modify();">修改</button>
		</div>
		<div class="btn-group" role="group" aria-label="发送邮件">
			<button type="button" class="btn btn-default" id="mailTo">发送邮件</button>
		</div>
	</div>

	<!-- 增加按钮弹出的模态窗口   myAdd -->
	<div class="modal fade" id="myAdd" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">新增一个email</h4>
				</div>
				<div class="modal-body">
					<form id="addForm" name="addForm">
						<div class="form-group">
							<label for="exampleInputName">Name</label> <input type="text"
								class="form-control" id="exampleInputName" name="userName" />
						</div>
						<div class="form-group">
							<label for="exampleInputEmail">Email address</label> <input
								type="email" class="form-control" id="exampleInputEmail"
								placeholder="Email" name="email" />
						</div>
						<div class="form-group">
							<label for="exampleInputRole">Email role</label> <input
								type="text" class="form-control" id="exampleInputRole"
								name="role" />
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="addButton">Save
						changes</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>