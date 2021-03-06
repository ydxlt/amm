<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>我的政绩档案鉴定表维护</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container mainbody">
		<%@ include file="/WEB-INF/pages/common/head.jsp"%>
		<div class="row-fluid">
			<div class="span10">
				<ul class="breadcrumb">
					<li><span class="divider">|</span></li>
					<li class="active">控制面板</li>
					<li><span class="divider">|</span> 政绩档案鉴定表</li>
				</ul>
			</div>
			<div class="btn-group span2">
				<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-list icon-white"></i> 功能菜单 <span class="caret"></span> </a>
				<ul class="dropdown-menu">
					<li><a href="${ctx}/khpj/sysData/list.html">控制面板</a>
					</li>
					<li class="divider"></li>
					<li><a href="${ctx}/khpj/sysData/new.html">新增本月数据</a>
					</li>
					<li class="divider"></li>
					<li><a href="${ctx}/khpj/reports/register_report/my.html"><i class="i"></i> 政绩档案登记表</a>
					</li>
					<li><a href="${ctx}/khpj/reports/appraisal_report/my.html"><i class="i"></i> 政绩档案鉴定表</a>
					</li>
					<li><a href="${ctx}/khpj/reports/bonus_penalty_report/my.html"><i class="i"></i> 政绩档案奖罚表</a>
					</li>
				</ul>
			</div>
		</div>

		<c:if test="${not empty tips}">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4>系统提示</h4>
				${tips}!
			</div>
		</c:if>

		<ul class="nav nav-tabs">
			<li <c:if test="${empty data}">class="active"</c:if>><a href="#A" data-toggle="tab">政绩档案鉴定列表</a></li>
			<li <c:if test="${not empty data}">class="active"</c:if>><a href="#B" data-toggle="tab">鉴定表维护</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane <c:if test="${empty data}">active</c:if>" id="A">
				<div class="row-fluid" style="height:400px;">
					<c:if test="${empty datas}">
						暂无数据
					</c:if>
					<c:if test="${not empty datas}">
						<ul>
							<c:forEach var="d" items="${datas.result}">
								<li><a href="${ctx}/khpj/reports/appraisal_report/view/${d.appraisalReportId}.html" target="_blank">${d.createDate} ${d.duty} ${d.name} 政绩档案鉴定表</a></li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</div>
			<div class="tab-pane <c:if test="${not empty data}">active</c:if>" id="B">
				<div class="row-fluid">
					<form action="${ctx}/khpj/reports/appraisal_report/save.html" method="post" onsubmit="return confirm('是否提交数据？')">
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th colspan="6">
										<h3 class="text-center">综治、维稳责任人履行职责情况鉴定表</h3>
										<div>
											<p class="text-right">
												<select class="span2" name="createDate">
													<c:forEach var="year" items="${years}">
														<option value="${year}" <c:if test="${currentYear==year}">selected="selected"</c:if>>${year}</option>
													</c:forEach>
												</select>
											</p>
										</div>
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
											<input type="text" class="input-small" name="name" value="${data.name}"/>
										</p>
									</td>
									<td width="17%">
										<p class="text-center">单位</p>
									</td>
									<td width="17%">
										<p>
											${dept}
										</p>
									</td>
									<td width="17%">
										<p class="text-center">职务</p>
									</td>
									<td width="17%">
										<p>
											<input type="text" class="input-small" name="duty" value="${data.duty}"/>
										</p>
									</td>
								</tr>
								<tr>
									<td>
										<p class="text-center">自我鉴定</p>
									</td>
									<td colspan="5">
										<p>
											<textarea rows="15" name="selfAssessment" class="input-block-level">${data.selfAssessment}</textarea>
										</p>
									</td>
								</tr>
								<tr>
									<td>
										<p class="text-center">综治委意见</p>
									</td>
									<td colspan="5">
										<p>
											<textarea rows="5" name="opinion" class="input-block-level">${data.opinion}</textarea>
										</p>
									</td>
								</tr>
							</tbody>
						</table>
						<div class="row">
							<p class="text-right">
								<button class="btn btn-primary" type="submit">提 交</button>
								<input type="hidden" name="appraisalReportId" value="${data.appraisalReportId}"/>
								&nbsp;&nbsp;
							</p>
						</div>
					</form>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
</body>
</html>