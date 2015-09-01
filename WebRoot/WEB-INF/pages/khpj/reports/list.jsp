<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<%@ taglib prefix='sec' uri='http://www.springframework.org/security/tags'%>
<html lang="zh-cn">
<head>
<title>我的政绩档案奖惩表维护</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container mainbody">
		<%@ include file="/WEB-INF/pages/common/head.jsp"%>
		<div class="row-fluid">
			<div class="span10">
				<ul class="breadcrumb">
					<li><span class="divider">|</span>
					</li>
					<li class="active">控制面板</li>
					<li><span class="divider">|</span> 政绩档案报表</li>
				</ul>
			</div>
			<div class="btn-group span2">
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
			</div>
		</div>
		<ul class="nav nav-tabs">
			<li <c:if test="${type == 'r'}">class="active"</c:if>><a href="#A" data-toggle="tab">政绩档案登记表</a>
			</li>
			<li <c:if test="${type == 'ar'}">class="active"</c:if>><a href="#B" data-toggle="tab">政绩档案鉴定表</a>
			</li>
			<li <c:if test="${type == 'bp'}">class="active"</c:if>><a href="#C" data-toggle="tab">政绩档案奖罚表</a>
			</li>
		</ul>
		<div class="tab-content" style="padding-left:50px;min-height:400px; overflow: auto">
			<div class="tab-pane <c:if test="${type == 'r'}">active</c:if>" id="A">
				<div class="row-fluid">
					<c:if test="${empty r}">
						暂无数据
					</c:if>
					<c:if test="${not empty r}">
						<ul>
							<c:forEach var="d" items="${r.result}">
								<li style="padding:5px;"><a href="javascript:open('${ctx}/khpj/reports/register_report/view/${d.registerReportId}.html','政绩档案登记表');" class="defaultLinkBlack">${d.department.departmentName} ${d.duty} ${d.name} 政绩档案登记表</a></li>
							</c:forEach>
						</ul>
						<ul class="pager">
							<li>显示 ${r.pagination.pageSize} 条每页 共 ${r.pagination.totalCount} 条记录</li>
							<c:if test="${r.pagination.totalCount <= r.pagination.pageSize}">
								<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
								<li>当前 ${r.pagination.pageNo} 页</li>
								<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
							</c:if>
							<c:if test="${r.pagination.totalCount > r.pagination.pageSize}">
								<li <c:if test="${r.pagination.pageNo == 1}">class="disabled"</c:if>><a href="${ctx}/khpj/print_report/list.html?type=r&pageNum=${r.pagination.pageNo - 1}#A">上一页</a></li>
								<li>当前 ${r.pagination.pageNo} 页</li>
								<li <c:if test="${r.pagination.pageNo == totlePage}">class="disabled"</c:if>><a href="${ctx}/khpj/print_report/list.html?type=r&pageNum=${r.pagination.pageNo + 1}#A">下一页</a></li>
							</c:if>
						</ul>
					</c:if>
				</div>
			</div>
			<div class="tab-pane <c:if test="${type == 'ar'}">active</c:if>" id="B">
				<div class="row-fluid">
					<c:if test="${empty ar}">
						暂无数据
					</c:if>
					<c:if test="${not empty ar}">
						<ul>
							<c:forEach var="d" items="${ar.result}">
								<li style="padding:5px;"><a href="javascript:open('${ctx}/khpj/reports/appraisal_report/view/${d.appraisalReportId}.html','政绩档案鉴定表');" class="defaultLinkBlack">${d.department.departmentName} ${d.duty} ${d.name} 政绩档案鉴定表 </a></li>
							</c:forEach>
						</ul>
						<ul class="pager">
							<li>显示 ${ar.pagination.pageSize} 条每页 共 ${ar.pagination.totalCount} 条记录</li>
							<c:if test="${ar.pagination.totalCount <= ar.pagination.pageSize}">
								<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
								<li>当前 ${ar.pagination.pageNo} 页</li>
								<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
							</c:if>
							<c:if test="${ar.pagination.totalCount > ar.pagination.pageSize}">
								<li <c:if test="${ar.pagination.pageNo == 1}">class="disabled"</c:if>><a href="${ctx}/khpj/print_report/list.html?type=ar&pageNum=${ar.pagination.pageNo - 1}#B">上一页</a></li>
								<li>当前 ${ar.pagination.pageNo} 页</li>
								<li <c:if test="${ar.pagination.pageNo == totlePage}">class="disabled"</c:if>><a href="${ctx}/khpj/print_report/list.html?type=ar&pageNum=${ar.pagination.pageNo + 1}#B">下一页</a></li>
							</c:if>
						</ul>
					</c:if>
				</div>
			</div>
			<div class="tab-pane <c:if test="${type == 'bp'}">active</c:if>" id="C">
				<div class="row-fluid">
					<c:if test="${empty bp}">
						暂无数据
					</c:if>
					<c:if test="${not empty bp}">
						<ul>
							<c:forEach var="d" items="${bp.result}">
								<li style="padding:5px;"><a href="javascript:open('${ctx}/khpj/reports/bonus_penalty_report/view/${d.bPReportId}.html','政绩档案<c:if test="${d.type == 0}">奖励</c:if><c:if test="${d.type == 1}">惩罚</c:if>表');" class="defaultLinkBlack">${d.department.departmentName} ${d.duty} ${d.name} 政绩档案<c:if test="${d.type == 0}">奖励</c:if><c:if test="${d.type == 1}">惩罚</c:if>表</a></li>
							</c:forEach>
						</ul>
						<ul class="pager">
							<li>显示 ${bp.pagination.pageSize} 条每页 共 ${bp.pagination.totalCount} 条记录</li>
							<c:if test="${bp.pagination.totalCount <= bp.pagination.pageSize}">
								<li class="disabled"><a href="javascript:void(0)">上一页</a></li>
								<li>当前 ${bp.pagination.pageNo} 页</li>
								<li class="disabled"><a href="javascript:void(0)">下一页</a></li>
							</c:if>
							<c:if test="${bp.pagination.totalCount > bp.pagination.pageSize}">
								<li <c:if test="${bp.pagination.pageNo == 1}">class="disabled"</c:if>><a href="${ctx}/khpj/print_report/list.html?type=bp&pageNum=${bp.pagination.pageNo - 1}#C">上一页</a></li>
								<li>当前 ${bp.pagination.pageNo} 页</li>
								<li <c:if test="${bp.pagination.pageNo == totlePage}">class="disabled"</c:if>><a href="${ctx}/khpj/print_report/list.html?type=bp&pageNum=${bp.pagination.pageNo + 1}#C">下一页</a></li>
							</c:if>
						</ul>
					</c:if>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/layer/layer.min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/pages/reports/list.js"></script>
	<input id="ctx" type="hidden" value="${ctx}" />
</body>
</html>