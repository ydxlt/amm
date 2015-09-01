<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>单位考核数据维护</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container">
		<div class="container mainbody">
			<%@ include file="/WEB-INF/pages/common/head.jsp"%>
			<div class="row-fluid">
				<div class="span10">
					<ul class="breadcrumb">
						<li><span class="divider">|</span></li>
						<li class="active">控制面板</li>
						<li><span class="divider">|</span></li>
					</ul>
				</div>
				<div class="btn-group span2">
					<a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-list icon-white"></i> 功能菜单 <span class="caret"></span> </a>
					<ul class="dropdown-menu">
						<li><a href="${ctx}/khpj/sysData/list.html">控制面板</a></li>
						<li class="divider"></li>
						<li><a href="javascript:openDepartment()">单位概况维护</a></li>
						<li><a href="javascript:openScore('${user.userDep.departmentId}')">单位得分情况</a></li>
						<li class="divider"></li>
						<li><a href="${ctx}/khpj/sysData/new.html">新增本月数据</a></li>
						<li class="divider"></li>
						<li class="disabled muted">
							<c:if test="${!fn:endsWith(submitDay[0], reportMonth)}">
								<a href="javascript:void(0);"><i class="i"></i> 政绩档案表<br/>[每年${reportMonth}月开放]</a>
							</c:if>
							<c:if test="${fn:endsWith(submitDay[0], reportMonth)}">
								<a href="${ctx}/khpj/reports/register_report/my.html"><i class="i"></i> 政绩档案表</a>
							</c:if>
						</li>
						<li class="divider"></li>
						<!-- <li><a href="${ctx}/khpj/reports/con_disputes_report/my.html"><i class="i"></i> 矛盾纠纷报表</a></li> -->
						<li><a href="javascript:openChangePw()">密码修改</a></li>
					</ul>
				</div>
			</div>
			<!--Body content-->
			<div class="row">
				<div class="span3">
					<p>&nbsp;&nbsp;&nbsp;&nbsp;类别：${user.userType}类</p>
				</div>
				<div class="span3">
					<p>单位名称：${user.userName}</p>
				</div>
				<div class="span6">
					<p class="text-right">
						当前排名第<span class="badge badge-important">${totalScore}</span>位 &nbsp;&nbsp; <span id="star"></span>&nbsp;&nbsp;
					</p>
				</div>
			</div>
			<div class="alert">
				<h4>
					今天是
					<script type="text/javascript" src="${ctx}/resource/utils/calendar.js"></script>
					温馨提醒：距离${submitDay[0]}月份数据提交剩下${submitDay[1]}天
				</h4>
				<div class="row-fluid">
					<div class="span9">当前为${queryMonth}月份数据</div>
					<div class="span3">
						<form action="${ctx}/khpj/sysData/list.html" method="post">
							<div class="input-append">
								<select class="span6" name="month">
									<c:forEach var="year" items="${years}">
										<option value="${year}" <c:if test="${year == queryMonth}">selected="selected"</c:if>>${year}</option>
									</c:forEach>
								</select>
								<button type="submit" class="btn btn-primary">历史数据查询</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span3">
						<table class="table table-hover table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th>${queryMonth}月固定指标数据</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>&nbsp;
										<c:if test="${fixedquota == '1'}">
										<a href="javascript:open('${ctx}/khpj/sysData/view.html?month=${queryMonth}&deptId=${user.userDep.departmentId}&type=${fixquotaType}','${queryMonth}固定指标考核数据查看');">${queryMonth}月固定指标数据</a>
										<c:if test="${submitDay[0] == queryMonth}">
										[<a href="${ctx}/khpj/sysData/edit.html?month=${submitDay[0]}&deptId=${user.userDep.departmentId}&type=${fixquotaType}">维护</a>]
										</c:if>
										</c:if>
									</td>
								</tr>
							</tbody>
						</table>
				</div>
				<div class="span6">
						<table class="table table-hover table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th colspan="3">${queryMonth}月专门小组工作数据</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>&nbsp;
										<c:if test="${speciallyWork == '1'}">
										<a href="javascript:open('${ctx}/khpj/sysData/view.html?month=${queryMonth}&deptId=${user.userDep.departmentId}&type=${plan}','${queryMonth}专门小组工作部署考核数据查看');">工作部署</a>
										<c:if test="${submitDay[0] == queryMonth}">
										[<a href="${ctx}/khpj/sysData/edit.html?month=${submitDay[0]}&deptId=${user.userDep.departmentId}&type=${plan}">维护</a>]
										</c:if>
										</c:if>
									</td>
									<td>&nbsp;
										<c:if test="${speciallyWork == '1'}">
										<a href="javascript:open('${ctx}/khpj/sysData/view.html?month=${queryMonth}&deptId=${user.userDep.departmentId}&type=${develop}','${queryMonth}专门小组工作开展考核数据查看');">工作开展</a>
										<c:if test="${submitDay[0] == queryMonth}">
										[<a href="${ctx}/khpj/sysData/edit.html?month=${submitDay[0]}&deptId=${user.userDep.departmentId}&type=${develop}">维护</a>]
										</c:if>
										</c:if>
									</td>
									<td>&nbsp;
										<c:if test="${speciallyWork == '1'}">
										<a href="javascript:open('${ctx}/khpj/sysData/view.html?month=${queryMonth}&deptId=${user.userDep.departmentId}&type=${effect}','${queryMonth}专门小组工作效果考核数据查看');">工作效果</a>
										<c:if test="${submitDay[0] == queryMonth}">
										[<a href="${ctx}/khpj/sysData/edit.html?month=${submitDay[0]}&deptId=${user.userDep.departmentId}&type=${effect}">维护</a>]
										</c:if>
										</c:if>
									</td>
								</tr>
							</tbody>
						</table>
				</div>
				<div class="span3">
					<table class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>${queryMonth}月矛盾纠纷报表</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									[<a href="${ctx}/khpj/reports/con_disputes_report/view.html?reportDate=${queryMonth}&deptId=${user.userDep.departmentId}">查看报表</a>]
									<c:if test="${submitDay[0] == queryMonth}">
									[<a href="${ctx}/khpj/reports/con_disputes_report/new.html">维护</a>]
									</c:if>
									[<a href="${ctx}/khpj/reports/machine_account_report/my.html">台帐</a>]
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span4">
					<table class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>部门文件</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="entity" items="${pageData}">
								<c:if test="${entity.contentType == departmentType}">
									<tr>
										<td><img src="${ctx}/html/css/img/arr.gif" border="0" />&nbsp;<a href="javascript:open('${ctx}/khpj/sysData/view/${entity.sysDataId}.html','${queryMonth}部门文件考核数据查看');"><c:if test="${empty entity.title}">无标题</c:if>${entity.title}</a>
											<c:if test="${submitDay[0] == queryMonth}">
											[<a href="${ctx}/khpj/sysData/edit/${entity.sysDataId}.html">维护</a>]
											[<a href="${ctx}/khpj/sysData/delete/${entity.sysDataId}.html" onclick="return confirm('是否删除该条数据？');">删除</a>]
											</c:if>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="span4">
					<table class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>活动图片</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="entity" items="${pageData}">
								<c:if test="${entity.contentType == acitveType}">
									<tr>
										<td><img src="${ctx}/html/css/img/arr.gif" border="0" />&nbsp;<a href="javascript:open('${ctx}/khpj/sysData/view/${entity.sysDataId}.html','${queryMonth}活动图片考核数据查看');"><c:if test="${empty entity.title}">无标题</c:if>${entity.title}</a>
											<c:if test="${submitDay[0] == queryMonth}">
											[<a href="${ctx}/khpj/sysData/edit/${entity.sysDataId}.html">维护</a>]
											[<a href="${ctx}/khpj/sysData/delete/${entity.sysDataId}.html" onclick="return confirm('是否删除该条数据？');">删除</a>]
											</c:if>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="span4">
					<table class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>综合材料</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="entity" items="${pageData}">
								<c:if test="${entity.contentType == materialType}">
									<tr>
										<td><img src="${ctx}/html/css/img/arr.gif" border="0" />&nbsp;<a href="javascript:open('${ctx}/khpj/sysData/view/${entity.sysDataId}.html','${queryMonth}综合材料考核数据查看');"><c:if test="${empty entity.title}">无标题</c:if>${entity.title}</a>
											<c:if test="${submitDay[0] == queryMonth}">
											[<a href="${ctx}/khpj/sysData/edit/${entity.sysDataId}.html">维护</a>]
											[<a href="${ctx}/khpj/sysData/delete/${entity.sysDataId}.html" onclick="return confirm('是否删除该条数据？');">删除</a>]
											</c:if>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<!-- <div class="row-fluid">
				<p class="text-right">
					<a href="#myModal" role="button" class="btn btn-primary" data-toggle="modal">提交本月数据</a>&nbsp;&nbsp;
				</p>
			</div> -->
			<!-- /body content -->
		</div>
		<!-- /container-fluid -->
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/raty/jquery.raty.min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/layer/layer.min.js"></script>
	<script type="text/javascript">
		$(function(){
			var total = ${totalScoreRank}*5;
			$('#star').raty({path:'${ctx}/resource/raty/img', half: true ,noRatedMsg: "排名指数：较后",	hints: ['排名指数：中下', '排名指数：中', '排名指数：中上', '排名指数：靠前', '排名指数：优秀'], readOnly: true, score: total});
		});
		
		<c:if test="${empty pageData && submitDay[0] != queryMonth}">
		$(function(){
			layer.alert("${queryMonth}月未提交相关数据",0,"系统提示");
		});
		</c:if>
	</script>
	<script type="text/javascript" src="${ctx}/resource/pages/sysData/list.js"></script>
	<input id="ctx" type="hidden" value="${ctx}" />
</body>
</html>