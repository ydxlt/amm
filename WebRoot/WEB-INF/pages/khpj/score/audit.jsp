<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<html lang="zh-cn">
<head>
<title>考核数据审核</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body data-spy="scroll">
	<div class="container">
		<div class="container mainbody">
			<%@ include file="/WEB-INF/pages/common/head.jsp"%>
			<div class="row-fluid">
				<div class="span10">
					<ul class="breadcrumb">
						<li>控制面板 <span class="divider">|</span>
						</li>
						<li>月考核数据审核</li>
					</ul>
				</div>
				<div class="btn-group span2">
					<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-list icon-white"></i> 功能菜单 <span class="caret"></span> </a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/base/department/rank.html">单位排名</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/khpj/score/audit.html">月考核数据</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/khpj/auditScore/audit/1.html">半年考核</a></li>
						<li><a href="${ctx}/khpj/auditScore/audit/2.html">年度考核</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/khpj/reports/con_disputes_report/list.html"><i class="i"></i> 矛盾纠纷报表</a></li>
						<li class="divider"></li>
						<li><a href="javascript:open('专门小组工作数据','${ctx}/khpj/sysData/work_list.html');">专门小组工作</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/khpj/print_report/list.html"><i class="i"></i> 政绩档案报表</a></li>
						<li class="divider"></li>
						<li><a href="javascript:openChangePw()">密码修改</a></li>
						<sec:authorize ifAnyGranted="LABEL_SYS_DEPUTY_AUDIT">
							<li class="divider"></li>
							<li><a href="${ctx}/index.html" target="_blank"><i class="i"></i> 新闻内容审核</a></li>
						</sec:authorize>
					</ul>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span4">
					&nbsp;&nbsp;&nbsp;&nbsp;<span class="text-info lead">当前考核月份 ${queryMonth}</span>
				</div>
				<div class="span4">
					<sec:authorize ifAnyGranted="LABEL_SYS_DEPUTY_AUDIT">
						<p class="text-right">
							<a class="btn btn-success" href="javascript:auditScoreBatch('${ctx}/khpj/score/pass_batch.html?deptType=${type}&month=${queryMonth}','是否批量审核通过所有数据？')"><i class="icon-check icon-white"></i> 批量通过 </a>
							&nbsp;&nbsp;
							<button type="submit" class="btn btn-warning" onclick="javascript:publish('${ctx}/khpj/score/publish.html?month=${queryMonth}','发布前请确认是否完全审核！<br/>是否发布${queryMonth}月份分值？');"> <i class="icon-white icon-share"></i>排名发布</button>
						</p>
					</sec:authorize>
				</div>
				<div class="span4">
					<form action="${ctx}/khpj/score/audit.html" method="post" id="queryForm">
						<div class="input-append">
							<select name="type" class="span6" onchange="javascript:$('#queryForm').submit();">
								<option value="A" <c:if test="${type == 'A'}">selected="selected"</c:if>>A类单位</option>
								<option value="B" <c:if test="${type == 'B'}">selected="selected"</c:if>>B类单位</option>
								<option value="C" <c:if test="${type == 'C'}">selected="selected"</c:if>>C类单位</option>
							</select> <select class="span6" name="month">
								<c:forEach var="year" items="${years}">
									<option value="${year}" <c:if test="${year == queryMonth}">selected="selected"</c:if>>${year}</option>
								</c:forEach>
							</select>
							<button type="submit" class="btn btn-primary">查询</button>
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
								<th style="width:120px;" width="120">得分</th>
								<th style="width:120px;" width="120">审核状态</th>
								<th style="width:140px;" width="140">数据包</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="dept" items="${departments}" varStatus="statue">
								<tr>
									<td rowspan="${quotaLength1 + quotaLength2}">${statue.index+1}</td>
									<td rowspan="${quotaLength1 + quotaLength2}"><a name="${dept.departmentId}" href="javascript:tips('${dept.departmentId}')">${dept.departmentName}</a>
									</td>
									<td rowspan="${quotaLength1}" width="40">固定指标</td>
									<td>${deptQuota1[0].quota.quotaName}</td>
									<td>${deptQuota1[0].quota.quotaScore}</td>
									<td>
										<c:set var="val" value="${dept.departmentId}-${deptQuota1[0].quota.quotaId}"></c:set> 
										<c:if test="${not empty scores[val]}">
											${scores[val].scoreValue}
											<c:if test="${unaudited == scores[val].auditStat}">
												<sec:authorize ifAnyGranted="LABEL_SYS_DEPUTY_AUDIT">
												<span id="${scores[val].scoreId}panel" class="text-right">
													<button class="btn btn-mini btn-success" onclick="auditPass('${scores[val].scoreId}')" type="button">通过</button>
													<button class="btn btn-mini btn-warning" onclick="refuse('${scores[val].scoreId}')" type="button">拒绝</button>
												</span>
												</sec:authorize>
											</c:if>
										</c:if>
									</td>
									<td id="${scores[val].scoreId}statue"><c:if test="${not empty scores[val]}">
											${scoreState[scores[val].auditStat]}
										</c:if> <c:if test="${empty scores[val]}">
											<p class="text-error"><strong>未提交</strong></p>
										</c:if></td>
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
										<td><c:set var="val" value="${dept.departmentId}-${quota1.quota.quotaId}"></c:set> <c:if test="${not empty scores[val]}">
												${scores[val].scoreValue}
												<c:if test="${unaudited ==  scores[val].auditStat}">
													<sec:authorize ifAnyGranted="LABEL_SYS_DEPUTY_AUDIT">
													<span id="${scores[val].scoreId}panel" class="text-right">
														<button class="btn btn-mini btn-success" onclick="auditPass('${scores[val].scoreId}')" type="button">通过</button>
														<button class="btn btn-mini btn-warning" onclick="refuse('${scores[val].scoreId}')" type="button">拒绝</button>
													</span>
													</sec:authorize>
												</c:if>
											</c:if></td>
										<td id="${scores[val].scoreId}statue"><c:if test="${not empty scores[val]}">
												${scoreState[scores[val].auditStat]}
											</c:if> <c:if test="${empty scores[val]}">
												<p class="text-error"><strong>未提交</strong></p>
											</c:if></td>
									</tr>
								</c:forEach>
								<c:if test="${not empty deptQuota2}">
									<tr>
										<td rowspan="${quotaLength2}">动态指标</td>
										<td>${deptQuota2[0].quota.quotaName}</td>
										<td>${deptQuota2[0].quota.quotaScore}</td>
										<td>
											<c:set var="val" value="${dept.departmentId}-${deptQuota2[0].quota.quotaId}"></c:set>
											<input type="hidden" name="quotaIds" value="${deptQuota2[0].quota.quotaId}" /> <c:if test="${not empty scores[val]}">
												${scores[val].scoreValue}
												<c:if test="${unaudited ==  scores[val].auditStat}">
													<sec:authorize ifAnyGranted="LABEL_SYS_DEPUTY_AUDIT">
													<span id="${scores[val].scoreId}panel" class="text-right">
														<button class="btn btn-mini btn-success" onclick="auditPass('${scores[val].scoreId}')" type="button">通过</button>
														<button class="btn btn-mini btn-warning" onclick="refuse('${scores[val].scoreId}')" type="button">拒绝</button>
													</span>
													</sec:authorize>
												</c:if>
											</c:if></td>
										<td id="${scores[val].scoreId}statue">
											<c:if test="${not empty scores[val]}">
												${scoreState[scores[val].auditStat]}
											</c:if> <c:if test="${empty scores[val]}">
												<p class="text-error"><strong>未提交</strong></p>
											</c:if></td>
									</tr>
									<c:forEach var="quota2" items="${deptQuota2}" begin="1">
										<tr>
											<td>${quota2.quota.quotaName}</td>
											<td>${quota2.quota.quotaScore}</td>
											<td>
												<c:set var="val" value="${dept.departmentId}-${quota2.quota.quotaId}"></c:set>
												<input type="hidden" name="quotaIds" value="${quota2.quota.quotaId}" /> <c:if test="${not empty scores[val]}">
													${scores[val].scoreValue}
													<c:if test="${unaudited ==  scores[val].auditStat}">
														<sec:authorize ifAnyGranted="LABEL_SYS_DEPUTY_AUDIT">
														<span id="${scores[val].scoreId}panel" class="text-right">
															<button class="btn btn-mini btn-success" onclick="auditPass('${scores[val].scoreId}')" type="button">通过</button>
															<button class="btn btn-mini btn-warning" onclick="refuse('${scores[val].scoreId}')" type="button">拒绝</button>
														</span>
														</sec:authorize>
													</c:if>
												</c:if></td>
											<td id="${scores[val].scoreId}statue"><c:if test="${not empty scores[val]}">
												${scoreState[scores[val].auditStat]}
											</c:if> <c:if test="${empty scores[val]}">
												<p class="text-error"><strong>未提交</strong></p>
											</c:if></td>
										</tr>
									</c:forEach>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="span2">
					<table class="table table-bordered text-center" data-spy="affix" data-offset-top="350" style="width: 120px; top: 10px;">
						<thead>
							<tr>
								<th>固定指标 <br /> 数据包</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><c:forEach var="dept" items="${fixedQuotaDept}">
										<a class="btn btn-primary" href="javascript:open('${dept.departmentName}固定指标查看','${ctx}/khpj/sysData/view.html?type=${fixedQuota}&month=${submitDay[0]}&deptId=${dept.departmentId}');">${dept.departmentName}</a><br/>
									</c:forEach></td>
							</tr>
							<tr>
								<td>
									<a class="btn btn-primary" href="javascript:open('固定指标汇总数据','${ctx}/khpj/sysData/viewFixedQuota.html');">固定指标数据包汇总</a>
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
			<div id="refuse" style="padding:10px;display: none">
				<form action="javascript:void(0);" method="post">
					<fieldset>
						<legend>新增拒绝原因</legend>
						<!-- <table class="table table-striped">
							<thead>
								<tr>
									<th>审核意见记录</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>审核意见111</td>
								</tr>
							</tbody>
						</table> -->
						<input type="hidden" id="sid" name="sid"/>
						<textarea id="refuseInfo" name="refuseInfo" rows="5" class="input-block-level"></textarea>
						<span class="help-block"></span>
						<div class="controls">
							<p class="text-right"><button type="submit" class="btn" onclick="javascript:auditRefuse();">提交</button></p>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
		<!-- /container-fluid -->
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/layer/layer.min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/pages/score/audit.js"></script>
	<input type="hidden" id="ctx" value="${ctx}" />
</body>
</html>