<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<html lang="zh-cn">
<head>
<title>突出矛盾纠纷台帐列表</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container mainbody">
	<%@ include file="/WEB-INF/pages/common/head.jsp"%>
		<div class="row">
			<div class="span10">
				<ul class="breadcrumb">
					<li><span class="divider">|</span></li>
					<li class="active">控制面板</li>
					<li><span class="divider">|</span></li>
					<li class="active">突出矛盾纠纷台帐列表</li>
				</ul>
			</div>
			<div class="btn-group span2">
				<p class="text-right">
					<sec:authorize ifAllGranted="LABEL_SYS_ASSESS">
						<!-- 考核管理员 -->
						<a class="btn btn-primary" href="${ctx}/khpj/score/assess.html"><i class="icon-backward icon-white"></i> 返回 </a>
					</sec:authorize>
					<sec:authorize ifAnyGranted="LABEL_SYS_DEPUTY_AUDIT">
						<!-- 副书记 -->
						<a class="btn btn-primary" href="${ctx}/khpj/score/audit.html"><i class="icon-backward icon-white"></i> 返回 </a>
					</sec:authorize>
					<sec:authorize ifAllGranted="LABEL_SYS_AUDIT">
						<!-- 书记 -->
						<a class="btn btn-primary" href="${ctx}/khpj/score/audit.html"><i class="icon-backward icon-white"></i> 返回 </a>
					</sec:authorize>
					&nbsp;
					<a class="btn btn-primary" href="${ctx}/khpj/print_report/machine_account.html?month=${queryMonth}"><i class="icon-print icon-white"></i> 打印 </a>&nbsp;&nbsp;
				</p>
			</div>
		</div>
		<div class="row-fluid">
			<form action="${ctx}/khpj/reports/machine_account_report/list.html" method="post">
				<div class="row-fluid">
					<div class="span5">当前为：${queryMonth} 月份数据</div>
					<div class="span7">
						<div class="input-append">
							<select class="span6" name="deptId">
								<option value="">所有</option>
								<c:forEach var="dept" items="${depts}">
									<option value="${dept.departmentId}" <c:if test="${dept.departmentId == deptId}">selected="selected"</c:if>>(${dept.departmentType})${dept.departmentName}</option>
								</c:forEach>
							</select>
							<select class="span6" name="month">
								<c:forEach var="year" items="${years}">
									<option value="${year}" <c:if test="${year == queryMonth}">selected="selected"</c:if>>${year}</option>
								</c:forEach>
							</select>
							<button type="submit" class="btn btn-primary">历史数据查询</button>
						</div>
					</div>
				</div>
			</form>
			<c:if test="${empty datas}">
				暂无数据
			</c:if>
			<c:if test="${not empty datas}">
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<tr>
							<th class="span1">序号</th>
							<th class="span1">单位</th>
							<th class="span3">矛盾纠纷简要情况</th>
							<th class="span1">责任单位<br/>责任人</th>
							<th class="span1">化解时限</th>
							<th class="span3">化解情况</th>
							<th class="span1">交办督办情况</th>
							<th class="span1">销号情况</th>
							<c:if test="${not empty delete}">
								<th class="span1">操作</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="d" items="${datas.result}" varStatus="statue">
							<tr>
								<td>${statue.index+(datas.pagination.pageSize*(datas.pagination.pageNo-1))+1}</td>
								<td>${d.department.departmentName}</td>
								<td>${d.cdDescription}</td>
								<td>${d.dutyPerson}</td>
								<td>${d.resoleTime}</td>
								<td>${d.resoleDescription}</td>
								<td>${d.supervision}</td>
								<td>${d.xiaoHao}</td>
								<c:if test="${not empty delete}">
									<td>
										<a href="${ctx}/khpj/reports/machine_account_report/delete/${d.machineAccountId}.html" onclick="return confirm('是否删除？');">删除</a>
									</td>
								</c:if>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<ul class="pager">
						<li>显示 ${datas.pagination.pageSize} 条每页 共 ${datas.pagination.totalCount} 条记录</li>
						<c:if test="${datas.pagination.totalCount <= datas.pagination.pageSize}">
							<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
							<li>当前 ${datas.pagination.pageNo} 页</li>
							<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
						</c:if>
						<c:if test="${datas.pagination.totalCount > datas.pagination.pageSize}">
							<li <c:if test="${datas.pagination.pageNo == 1}">class="disabled"</c:if>><a href="${ctx}/khpj/reports/machine_account_report/list.html?pageNum=${datas.pagination.pageNo - 1}&month=${queryMonth}&deptId=${deptId}">上一页</a></li>
							<li>当前 ${datas.pagination.pageNo} 页</li>
							<li <c:if test="${datas.pagination.pageNo == totlePage}">class="disabled"</c:if>><a href="${ctx}/khpj/reports/machine_account_report/list.html?pageNum=${datas.pagination.pageNo + 1}&month=${queryMonth}&deptId=${deptId}">下一页</a></li>
						</c:if>
					</ul>
				</c:if>
			</div>
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
</body>
</html>