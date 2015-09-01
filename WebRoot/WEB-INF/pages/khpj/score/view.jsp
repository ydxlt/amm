<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<html lang="zh-cn">
<head>
<title>考核数据打分</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container">
		<div class="container mainbody">
		<br/>
			<div class="row-fluid">
				<div class="span8">当前得分月份：${queryMonth}</div>
				<div class="span4">
					<form action="${ctx}/khpj/score/view/${dept.departmentId}.html" method="post" id="queryForm">
						<div class="input-append">
							<select name="month">
								<c:forEach var="year" items="${years}">
									<option value="${year}" <c:if test="${year == queryMonth}">selected="selected"</c:if>>${year}</option>
								</c:forEach>
							</select>
							<button type="submit" class="btn btn-primary">查询</button>
						</div>
					</form>
				</div>
			</div>
			<c:set var="quotaLength1" value="${fn:length(deptQuota1)}"></c:set>
			<c:set var="quotaLength2" value="${fn:length(deptQuota2)}"></c:set>
			<div class="row-fluid">
				<table class="table table-bordered table-condensed">
					<thead>
						<tr>
							<!-- <th style="width:40px;" width="40">序号</th> -->
							<th style="width:40px;" width="40">单位</th>
							<th colspan="2">考核指标</th>
							<th style="width:40px;" width="40">分值</th>
							<th style="width:120px;" width="120">得分</th>
							<!-- <th style="width:120px;" width="120">审核状态</th> -->
							<!-- <th style="width:120px;" width="120">数据包</th> -->
						</tr>
					</thead>
					<tbody>
						<tr>
							<!-- <td rowspan="${quotaLength1 + quotaLength2}">${statue.index+1}</td> -->
							<td rowspan="${quotaLength1 + quotaLength2}"><a href="javascript:tips('${dept.departmentId}')">${dept.departmentName}</a></td>
							<td rowspan="${quotaLength1}" width="40">固定指标</td>
							<td>${deptQuota1[0].quota.quotaName}</td>
							<td>${deptQuota1[0].quota.quotaScore}</td>
							<td>
								<c:set var="val" value="${dept.departmentId}-${deptQuota1[0].quota.quotaId}"></c:set> 
									${scores[val].scoreValue}
							</td>
							<!-- 
							<td id="${scores[val].scoreId}statue"><c:if test="${not empty scores[val]}">
											${scoreState[scores[val].auditStat]}
										</c:if> <c:if test="${empty scores[val]}">
									<p class="text-error">
										<strong>未提交</strong>
									</p>
								</c:if>
							</td>
							<td rowspan="${quotaLength1 + quotaLength2}">
								<%-- <c:forEach var="i" items="${yearMonth}">
									<a class="btn" href="javascript:open('${i}月份考核数据查看','${ctx}/khpj/sysData/assess_list.html?month=${i}&dept=${dept.departmentId}');">${i}月份</a>
									<br />
								</c:forEach> --%>
							</td>
							-->
						</tr>
						<c:forEach var="quota1" items="${deptQuota1}" begin="1">
							<tr>
								<td>${quota1.quota.quotaName}</td>
								<td>${quota1.quota.quotaScore}</td>
								<td>
									<c:set var="val" value="${dept.departmentId}-${quota1.quota.quotaId}"></c:set> 
									${scores[val].scoreValue}
								</td>
								<!-- 
								<td id="${scores[val].scoreId}statue"><c:if test="${not empty scores[val]}">
												${scoreState[scores[val].auditStat]}
											</c:if> <c:if test="${empty scores[val]}">
										<p class="text-error">
											<strong>未提交</strong>
										</p>
									</c:if>
								</td>
								-->
							</tr>
						</c:forEach>
						<c:if test="${not empty deptQuota2}">
							<tr>
								<td rowspan="${quotaLength2}">动态指标</td>
								<td>${deptQuota2[0].quota.quotaName}</td>
								<td>${deptQuota2[0].quota.quotaScore}</td>
								<td>
									<c:set var="val" value="${dept.departmentId}-${deptQuota2[0].quota.quotaId}"></c:set> 
									${scores[val].scoreValue}
								</td>
								<!-- 
								<td id="${scores[val].scoreId}statue"><c:if test="${not empty scores[val]}">
												${scoreState[scores[val].auditStat]}
											</c:if> <c:if test="${empty scores[val]}">
										<p class="text-error">
											<strong>未提交</strong>
										</p>
									</c:if>
								</td>
								-->
							</tr>
							<c:forEach var="quota2" items="${deptQuota2}" begin="1">
								<tr>
									<td>${quota2.quota.quotaName}</td>
									<td>${quota2.quota.quotaScore}</td>
									<td>
										<c:set var="val" value="${dept.departmentId}-${quota2.quota.quotaId}"></c:set> 
									${scores[val].scoreValue}
									</td>
									<!-- 
									<td id="${scores[val].scoreId}statue"><c:if test="${not empty scores[val]}">
												${scoreState[scores[val].auditStat]}
											</c:if> <c:if test="${empty scores[val]}">
											<p class="text-error">
												<strong>未提交</strong>
											</p>
										</c:if>
									</td>
									-->
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
			<!-- /body content -->
		</div>
		<!-- /container-fluid -->
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/layer/layer.min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/pages/score/view.js"></script>
	<input type="hidden" id="ctx" value="${ctx}" />
</body>
</html>