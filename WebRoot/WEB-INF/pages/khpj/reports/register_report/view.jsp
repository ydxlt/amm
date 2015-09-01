<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>我的政绩档案登记表查看</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container mainbody">
		<div class="row-fluid">
			<div class="row">
				<p class="text-right"><a class="btn btn-primary" href="${ctx}/khpj/print_report/register_report.html?id=${data.registerReportId}"><i class="icon-print icon-white"></i> 打印 </a>&nbsp;&nbsp;</p>
			</div>
			<table class="table table-bordered table-condensed">
				<thead>
					<tr>
						<th colspan="6"><h3 class="text-center">综治、维稳责任人履行职责情况登记表</h3>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<p class="text-center">姓名</p></td>
						<td width="17%">
							<p>
								${data.name}
							</p></td>
						<td width="17%">
							<p class="text-center">单位</p>
						</td>
						<td width="17%">
							<p>${data.department.departmentName}</p>
						</td>
						<td width="17%">
							<p class="text-center">职务</p></td>
						<td width="17%">
							<p>
								${data.duty}
							</p></td>
					</tr>
					<tr>
						<td>
							<p class="text-center">工作情况</p>
						</td>
						<td colspan="5">
							<p>
								${data.workingSituation}
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p class="text-center">责任人审签</p>
						</td>
						<td colspan="5">
							<p>
								${data.auditResult}
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p class="text-center">备注</p>
						</td>
						<td colspan="5">
							<p>
								${data.remarks}
							</p>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
</body>
</html>