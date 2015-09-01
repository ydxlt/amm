<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
	<head>
		<title>考核数据查看</title>
		<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
	</head>
	<body style="padding: 10px">
		<div class="container-fluid">
			<c:if test="${not empty years}">
				<select onchange="javascript:self.location.href='${ctx}/khpj/sysData/view.html?deptId=${deptId}&type=${type}&month='+this.value">
					<c:forEach var="year" items="${years}">
						<option value="${year}" <c:if test="${year == month}">selected="selected"</c:if>>${year}</option>
					</c:forEach>
				</select><br/>
			</c:if>
			<c:if test="${empty sys_data}">
				暂无数据
			</c:if>
			<c:if test="${not empty sys_data}">
				<!--Body content-->
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th>
								${sysDataType[sys_data.contentType]}
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								<p>${sys_data.title}</p>
							</td>
						</tr>
						<tr>
							<td>
								<textarea style="width:100%;height:400px;visibility:hidden;" readonly="readonly">
									${sys_data.content}
								</textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- /body content -->
				</c:if>
			</div>
		<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
		<script type="text/javascript" src="${ctx}/resource/kindeditor/kindeditor-min.js"></script>
		<script type="text/javascript" src="${ctx}/resource/kindeditor/zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/resource/pages/sysData/view.js"></script>
	</body>
</html>