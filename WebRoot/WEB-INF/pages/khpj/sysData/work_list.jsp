<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>考核数据查看</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body style="padding-top:10px;">
	<div class="container-fluid">
		<c:if test="${not empty years}">
			<select onchange="javascript:self.location.href='${ctx}/khpj/sysData/work_list.html?month='+this.value">
				<c:forEach var="year" items="${years}">
					<option value="${year}" <c:if test="${year == month}">selected="selected"</c:if>>${year}</option>
				</c:forEach>
			</select><br/>
		</c:if>
		<ul class="nav nav-tabs">
			<li class="active"><a href="#A" data-toggle="tab">工作部署</a>
			</li>
			<li><a href="#B" data-toggle="tab">工作开展</a>
			</li>
			<li><a href="#C" data-toggle="tab">工作效果</a>
			</li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="A">
				<c:if test="${empty sysDataPlan}">暂无数据</c:if>
				<c:forEach var="sys_data" items="${sysDataPlan}">
					${sys_data.department.departmentName}
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>${sysDataType[sys_data.contentType]}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<textarea style="width:100%;height:400px;visibility:hidden;">
										<p>（${sys_data.department.departmentName}） ${sys_data.title}</p>
										${sys_data.content}
									</textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</c:forEach>
			</div>
			<div class="tab-pane" id="B">
				<c:if test="${empty sysDataDevelop}">暂无数据</c:if>
				<c:forEach var="sys_data" items="${sysDataDevelop}">
					${sys_data.department.departmentName}
					<table class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>${sysDataType[sys_data.contentType]}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<textarea style="width:100%;height:400px;visibility:hidden;">
										<p>（${sys_data.department.departmentName}） ${sys_data.title}</p>
										${sys_data.content}
									</textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</c:forEach>
			</div>
			<div class="tab-pane" id="C">
				<c:if test="${empty sysDataEffect}">暂无数据</c:if>
				<c:forEach var="sys_data" items="${sysDataEffect}">
					${sys_data.department.departmentName}
					<table class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>${sysDataType[sys_data.contentType]}</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<textarea style="width:100%;height:400px;visibility:hidden;">
										<p>（${sys_data.department.departmentName}） ${sys_data.title}</p>
										${sys_data.content}
									</textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</c:forEach>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/kindeditor/zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/resource/pages/sysData/work_list.js"></script>
</body>
</html>