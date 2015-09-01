<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
	<head>
		<title>部门报表汇总</title>
		<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
	</head>
	<body>
		<div class="container mainbody">
			<!-- 矛盾纠纷报表查看 -->
			<div class="row">
				<h5>&nbsp;&nbsp;&nbsp;&nbsp;矛盾纠纷报表</h5>
				<c:if test="${empty cdreports}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;暂无数据
				</c:if>
				<c:forEach var="data" items="${cdreports}" varStatus="statue">
					<div class="span4">
						<table class="table table-bordered table-condensed">
							<tbody>
								<tr>
									<td width="50" height="50">
										<p>
											类别
										</p>
									</td>
									<td colspan="3">
										<p>
											${types[data.conDisClass]}
										</p>
									</td>
								</tr>
								<tr>
									<td>
										<p>
											层级
										</p>
									</td>
									<td>
										<p>
											县区
										</p>
									</td>
									<td>
										<p>
											乡镇
										</p>
									</td>
									<td>
										<p>
											村居
										</p>
									</td>
								</tr>
								<tr>
									<td>
										<p>
											排查数
										</p>
									</td>
									<td>
										<p>
											${data.countyCount}
										</p>
									</td>
									<td>
										<p>
											${data.townCount}
										</p>
									</td>
									<td>
										<p>
											${data.villageCount}
										</p>
									</td>
								</tr>
								<tr>
									<td>
										<p>
											调处数
										</p>
									</td>
									<td>
										<p>
											${data.couDisCount}
										</p>
									</td>
									<td>
										<p>
											${data.townDisCount}
										</p>
									</td>
									<td>
										<p>
											${data.vilDisCount}
										</p>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:forEach>
			</div>
			<hr/>
			<!-- 突出矛盾纠纷台账查看 -->
			<div class="row-fluid">
				<h5>突出矛盾纠纷台账</h5>
				<c:if test="${empty machineAccount}">
					暂无数据
				</c:if>
				<c:if test="${not empty machineAccount}">
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="span1">
									序号
								</th>
								<th class="span2">
									单位
								</th>
								<th>
									矛盾纠纷简要情况
								</th>
								<th>
									责任单位
									<br />
									责任人
								</th>
								<th>
									化解时限
								</th>
								<th>
									化解情况
								</th>
								<th>
									交办督办情况
								</th>
								<th>
									销号情况
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="d" items="${machineAccount}" varStatus="statue">
								<tr>
									<td>
										${statue.index + 1}
									</td>
									<td>
										${d.department.departmentName}
									</td>
									<td>
										${d.cdDescription}
									</td>
									<td>
										${d.dutyPerson}
									</td>
									<td>
										${d.resoleTime}
									</td>
									<td>
										${d.resoleDescription}
									</td>
									<td>
										${d.supervision}
									</td>
									<td>
										${d.xiaoHao}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
			<hr/>
			<!-- 政绩档案鉴定表查看 -->
			<div class="row-fluid">
				<h5>政绩档案鉴定表</h5>
				<c:if test="${empty appraisalReport}">
						暂无数据
					</c:if>
				<c:if test="${not empty appraisalReport}">
					<ul>
						<c:forEach var="d" items="${appraisalReport}">
							<li>
								<a href="${ctx}/khpj/reports/appraisal_report/view/${d.appraisalReportId}.html"
									target="_blank">${d.createDate} ${d.duty} ${d.name} 政绩档案鉴定表</a>
							</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<hr/>
			<!-- 政绩档案登记表查看 -->
			<div class="row-fluid">
				<h5>政绩档案登记表</h5>
				<c:if test="${empty registerReport}">
						暂无数据
					</c:if>
				<c:if test="${not empty registerReport}">
					<ul>
						<c:forEach var="d" items="${registerReport}">
							<li>
								<a href="${ctx}/khpj/reports/register_report/view/${d.registerReportId}.html"
									target="_blank">${d.reportYear} ${d.name} ${d.duty} 政绩档案登记表</a>
							</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<hr/>
			<!-- 政绩档案奖罚表查看 -->
			<div class="row-fluid">
				<h5>政绩档案奖罚表</h5>
				<c:if test="${empty bPReport}">
						暂无数据
					</c:if>
				<c:if test="${not empty bPReport}">
					<ul>
						<c:forEach var="d" items="${bPReport}">
							<li>
								<a href="${ctx}/khpj/reports/bonus_penalty_report/view/${d.bPReportId}.html"
									target="_blank">${d.createDate} ${d.duty} ${d.name} 政绩档案 <c:if
										test="${d.type == 0}">奖励</c:if>
									<c:if test="${d.type == 1}">惩罚</c:if>表</a>
							</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
			<hr/>
		</div>
		<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	</body>
</html>