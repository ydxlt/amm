<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>考核数据打分</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
<script type="text/javascript">
	if('${deptId}' != '')
	{
		document.location.hash='${deptId}';
	}
</script>
</head>
<body data-spy="scroll">
	<div class="container">
		<div class="container mainbody">
			<%@ include file="/WEB-INF/pages/common/head.jsp"%>
			<div class="row-fluid">
				<div class="span10">
					<ul class="breadcrumb">
						<li><span class="divider">|</span></li>
						<li class="active">控制面板</li>
						<li><span class="divider">|</span></li>
						<li class="active">月考核数据维护</li>
					</ul>
				</div>
				<div class="btn-group span2">
					<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-list icon-white"></i> 功能菜单 <span class="caret"></span> </a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/khpj/score/assess.html">控制面板</a></li>
						<li class="divider"></li>
						<li><a href="javascript:open('专门工作查看','${ctx}/khpj/sysData/work_list.html');">专门工作</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/khpj/auditScore/list/1.html">半年考核</a></li>
						<li><a href="${ctx}/khpj/auditScore/list/2.html">年度考核</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/khpj/reports/con_disputes_report/list.html"><i class="i"></i> 矛盾纠纷报表</a></li>
						<li><a href="${ctx}/khpj/reports/machine_account_report/list.html"><i class="i"></i> 突出矛盾纠纷台帐</a></li>
						<li><a href="${ctx}/khpj/print_report/list.html"><i class="i"></i> 政绩档案报表</a></li>
						<li class="divider"></li>
						<li><a href="javascript:openChangePw()">密码修改</a></li>
					</ul>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span8">
					<div class="row-fluid">
						<div class="span6">
							&nbsp;&nbsp;&nbsp;&nbsp;${type}类管理员
						</div>
						<div class="span6">
							&nbsp;&nbsp;&nbsp;&nbsp;<span class="text-info lead">当前考核月份 ${queryMonth}</span>
						</div>
					</div>
				</div>
				<div class="span4">
					<form action="${ctx}/khpj/score/assess.html" method="post">
						<div class="input-append">
							<select class="span8" name="month">
								<c:forEach var="year" items="${years}">
									<option value="${year}" <c:if test="${year == queryMonth}">selected="selected"</c:if>>${year}</option>
								</c:forEach>
							</select>
							<button type="submit" class="btn btn-primary">月份数据查询</button>
						</div>
					</form>
				</div>
			</div>
			<!--Body content-->
			
			<c:if test="${not empty tips}">
				<div class="alert alert-success">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>系统提示</h4>
					${tips}!
				</div>
			</c:if>
			
			<c:set var="quotaLength1" value="${fn:length(deptQuota1)}"></c:set>
			<c:set var="quotaLength2" value="${fn:length(deptQuota2)}"></c:set>
			<div class="row-fluid">
				<div class="span10">
					<table class="table table-bordered table-condensed">
						<thead>
							<tr>
								<th style="width:40px;" width="40">序号</th>
								<th style="width:40px;" width="40">单位</th>
								<th colspan="2">考核指标</th>
								<th style="width:40px;" width="40">分值</th>
								<th style="width:100px;" width="100">得分</th>
								<th style="width:160px;" width="160">审核状态</th>
								<th style="width:140px;" width="140">数据包</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="dept" items="${departments}" varStatus="statue">
							<form action="${ctx}/khpj/score/save.html" method="post">
								<tr>
									<td rowspan="${quotaLength1 + quotaLength2}">${statue.index+1}</td>
									<td rowspan="${quotaLength1 + quotaLength2}">
										<a name="${dept.departmentId}" href="javascript:tips('${dept.departmentId}')">${dept.departmentName}</a>
										<!-- 
										<br/><br/>
										[<a href="javascript:open('报表查看','${ctx}/khpj/sysData/view_dept_rep.html?departmentId=${dept.departmentId}&month=${queryMonth}')">报表</a>]
										 -->
									</td>
									<td rowspan="${quotaLength1}" width="40">固定指标</td>
									<td>${deptQuota1[0].quota.quotaName}</td>
									<td>${deptQuota1[0].quota.quotaScore}</td>
									<td>
										<c:set var="val" value="${dept.departmentId}-${deptQuota1[0].quota.quotaId}"></c:set>
										<input type="hidden" name="quotaIds" value="${deptQuota1[0].quota.quotaId}"/>
										<c:if test="${pass!=scores[val].auditStat}">
											<input type="text" name="${deptQuota1[0].quota.quotaId}score" class="input-block-level" style="width:80px;" placeholder="输入分值" value="${scores[val].scoreValue}"/>
										</c:if>
										<c:if test="${pass==scores[val].auditStat}">
											${scores[val].scoreValue}
										</c:if>
									</td>
									<td>
										<c:if test="${empty scores[val]}">
											<p class="text-error"><strong>未提交</strong></p>
										</c:if>
										<c:if test="${not empty scores[val]}">
											${scoreState[scores[val].auditStat]}
											<span style="color: red; font-weight: bold;"> ${scores[val].refuseInfo}</span>
										</c:if>
									</td>
									<td rowspan="${quotaLength1 + quotaLength2}">
										<div class="accordion" id="accordion${statue.index+1}">
							            	<div class="accordion-group">
							                	<div class="accordion-heading">
							                    	<a class="accordion-toggle btn btn-info" href="#collapseOne${statue.index+1}" data-toggle="collapse" data-parent="#accordion${statue.index+1}">
							                    		${fn:substring(yearMonth[0],0,4)}年度
							                    	</a>
							                  	</div>
							                <div class="accordion-body in collapse" id="collapseOne${statue.index+1}" style="height: auto;">
							                	<div class="accordion-inner">
							                		<c:forEach var="i" items="${yearMonth}" begin="0" end="11">
														<a class="btn" href="javascript:open('${i}月份考核数据查看','${ctx}/khpj/sysData/assess_list.html?month=${i}&dept=${dept.departmentId}');">${i}月份</a><br/>
													</c:forEach>
							                    </div>
							                </div>
							                </div>
							                <div class="accordion-group">
							                	<div class="accordion-heading">
							                    	<a class="accordion-toggle btn btn-info" href="#collapseTwo${statue.index+1}" data-toggle="collapse" data-parent="#accordion${statue.index+1}">
							                    		${fn:substring(yearMonth[13],0,4)}年度
							                    	</a>
							                	</div>
							                	<div class="accordion-body collapse" id="collapseTwo${statue.index+1}" style="height: 0px;">
							                    	<div class="accordion-inner">
							                    		<c:forEach var="i" items="${yearMonth}" begin="12">
															<a class="btn" href="javascript:open('${i}月份考核数据查看','${ctx}/khpj/sysData/assess_list.html?month=${i}&dept=${dept.departmentId}');">${i}月份</a><br/>
														</c:forEach>
							                    	</div>
							                  	</div>
							                </div>
							        	</div>
									</td>
								</tr>
								<c:forEach var="quota1" items="${deptQuota1}" begin="1">
									<tr>
										<td>${quota1.quota.quotaName}</td>
										<td>${quota1.quota.quotaScore}</td>
										<td>
											<c:set var="val" value="${dept.departmentId}-${quota1.quota.quotaId}"></c:set>
											<input type="hidden" name="quotaIds" value="${quota1.quota.quotaId}"/>
											<c:if test="${pass!=scores[val].auditStat}">
												<input type="text" name="${quota1.quota.quotaId}score" class="input-block-level" style="width:80px;" placeholder="输入分值" value="${scores[val].scoreValue}"/>
											</c:if>
											<c:if test="${pass==scores[val].auditStat}">
												${scores[val].scoreValue}
											</c:if>
										</td>
										<td>
											<c:if test="${empty scores[val]}">
												<p class="text-error"><strong>未提交</strong></p>
											</c:if>
											<c:if test="${not empty scores[val]}">
												${scoreState[scores[val].auditStat]}
												<span style="color: red; font-weight: bold;">${scores[val].refuseInfo}</span>
											</c:if>
										</td>
									</tr>
								</c:forEach>
								<c:if test="${not empty deptQuota2}">
									<tr>
										<td rowspan="${quotaLength2}">动态指标</td>
										<td>${deptQuota2[0].quota.quotaName}</td>
										<td>${deptQuota2[0].quota.quotaScore}</td>
										<td>
											<c:set var="val" value="${dept.departmentId}-${deptQuota2[0].quota.quotaId}"></c:set>
											<input type="hidden" name="quotaIds" value="${deptQuota2[0].quota.quotaId}"/>
											<c:if test="${pass!=scores[val].auditStat}">
												<input type="text" name="${deptQuota2[0].quota.quotaId}score" class="input-block-level" style="width:80px;" placeholder="输入分值" value="${scores[val].scoreValue}"/>
											</c:if>
											<c:if test="${pass==scores[val].auditStat}">
												${scores[val].scoreValue}
											</c:if>
										</td>
										<td>
											<c:if test="${empty scores[val]}">
												<p class="text-error"><strong>未提交</strong></p>
											</c:if>
											<c:if test="${not empty scores[val]}">
												${scoreState[scores[val].auditStat]}
												<span style="color: red; font-weight: bold;">${scores[val].refuseInfo}</span>
											</c:if>
										</td>
									</tr>
									<c:forEach var="quota2" items="${deptQuota2}" begin="1">
										<tr>
											<td>${quota2.quota.quotaName}</td>
											<td>${quota2.quota.quotaScore}</td>
											<td>
												<c:set var="val" value="${dept.departmentId}-${quota2.quota.quotaId}"></c:set>
												<input type="hidden" name="quotaIds" value="${quota2.quota.quotaId}"/>
												<c:if test="${pass!=scores[val].auditStat}">
													<input type="text" name="${quota2.quota.quotaId}score" class="input-block-level" style="width:80px;" placeholder="输入分值" value="${scores[val].scoreValue}"/>
												</c:if>
												<c:if test="${pass==scores[val].auditStat}">
													${scores[val].scoreValue}
												</c:if>
											</td>
											<td>
												<c:if test="${empty scores[val]}">
													<p class="text-error"><strong>未提交</strong></p>
												</c:if>
												<c:if test="${not empty scores[val]}">
													${scoreState[scores[val].auditStat]}
													<span style="color: red; font-weight: bold;">${scores[val].refuseInfo}</span>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<tr>
									<td colspan="8">
										<p class="text-right">
											<input type="hidden" name="departmentId" value="${dept.departmentId}"/>
											<input type="hidden" name="month" value="${queryMonth}"/>
											<button class="btn btn-primary" type="submit">[ ${dept.departmentName} ] 数据提交审核</button>&nbsp;&nbsp;&nbsp;&nbsp;
										</p>
									</td>
								</tr>
							</form>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="span2">
					<table class="table table-bordered text-center" data-spy="affix" data-offset-top="350" style="width: 120px; top: 10px;">
						<thead>
							<tr>
								<th>固定指标 <br/> 数据包</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><c:forEach var="dept" items="${fixedQuotaDept}">
										<a class="btn btn-primary" href="javascript:open('${dept.departmentName}固定指标查看','${ctx}/khpj/sysData/view.html?type=${fixedQuota}&month=${queryMonth}&deptId=${dept.departmentId}');">${dept.departmentName}</a>
										<br/>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<td>
									<a class="btn btn-primary" href="javascript:open('固定指标汇总','${ctx}/khpj/sysData/viewFixedQuota.html');">固定指标数据包汇总</a>
								</td>
							</tr>
							<tr>
								<td>
									快速定位单位:<br/>
									<select class="span12" id="depts" onchange="window.location.href='#'+this.value">
										<c:forEach var="dept" items="${departments}" varStatus="statue">
											<option value="${dept.departmentId}">${dept.departmentName}</option>
										</c:forEach>
									</select>
									<br/>
									<a class="btn" href="javascript:scroll(0,0);">返回顶部</a>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<!-- /body content -->
		</div>
		<!-- /container-fluid -->
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/layer/layer.min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/pages/score/assess.js"></script>
	<input type="hidden" id="ctx" value="${ctx}"/>
</body>
</html>