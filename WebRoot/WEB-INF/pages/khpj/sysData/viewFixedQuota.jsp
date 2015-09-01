<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
	<head>
		<title>考核数据查看</title>
		<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
	</head>
	<body style="padding:10px;">
		<div class="container-fluid">
			<c:if test="${not empty years}">
				选择月份
				<select onchange="javascript:self.location.href='${ctx}/khpj/sysData/viewFixedQuota.html?month='+this.value">
					<c:forEach var="year" items="${years}">
						<option value="${year}" <c:if test="${year == month}">selected="selected"</c:if>>${year}</option>
					</c:forEach>
				</select><br/>
			</c:if>
			<c:if test="${empty sys_datas}">
				暂无数据
			</c:if>
			<c:if test="${not empty sys_datas}">
				<!--Body content-->
				<c:forEach var="sys_data" items="${sys_datas}">
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>
								${sys_data.department.departmentName}
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								${sys_data.title}
							</td>
						</tr>
						<tr>
							<td>
								${sys_data.content}
							</td>
						</tr>
					</tbody>
				</table>
				</c:forEach>
				<!-- /body content -->
				</c:if>
			</div>
	</body>
</html>