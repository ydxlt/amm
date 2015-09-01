<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>我的政绩档案奖惩表查看</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container mainbody">
		<div class="row">
			<p class="text-right"><a class="btn btn-primary" href="${ctx}/khpj/print_report/bp_report.html?id=${data.bPReportId}"><i class="icon-print icon-white"></i> 打印 </a>&nbsp;&nbsp;</p>
		</div>
		<table class="table table-bordered table-condensed">
			<thead>
				<tr>
					<th colspan="6">
						<h3 class="text-center">综治、维稳责任人
						<c:if test="${data.type==0}">奖励</c:if>
						<c:if test="${data.type==1}">处罚</c:if>
						情况登记表</h3> 
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<p class="text-center">姓名</p>
					</td>
					<td width="17%">
						<p>
							${data.name}
						</p>
					</td>
					<td width="17%">
						<p class="text-center">单位</p>
					</td>
					<td width="17%">
						<p>${data.department.departmentName}</p>
					</td>
					<td width="17%">
						<p class="text-center">职务</p>
					</td>
					<td width="17%">
						<p>
							${data.duty}
						</p>
					</td>
				</tr>
				<tr>
					<td>
						<p class="text-center">
							<c:if test="${data.type==0}">主要事迹</c:if>
							<c:if test="${data.type==1}">主要问题</c:if>
						</p>
					</td>
					<td colspan="5">
						<p>
							${data.mainAffair}
						</p>
					</td>
				</tr>
				<tr>
					<td>
						<p class="text-center">
							<c:if test="${data.type==0}">何种奖励</c:if>
							<c:if test="${data.type==1}">何种处罚</c:if>
						</p>
					</td>
					<td colspan="5">
						<p>
							${data.bonusOrPenalty}
						</p>
					</td>
				</tr>
			</tbody>
		</table>

	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
</body>
</html>