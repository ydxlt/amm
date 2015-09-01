<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/khpj/deptQuota/list.html">
	<input type="hidden" name="pageNum" value="${pageData.pagination.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageData.pagination.pageSize}" />
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${pageContext.request.contextPath}/khpj/deptQuota/new.html" target="dialog" rel="addDialog" title="单位指标新增" mask="true" width="540" height="190"><span>添加</span></a></li>
			<!-- 
			<li class="line">line</li>
			<li><a class="delete" href="${pageContext.request.contextPath}/khpj/deptQuota/delete.html" target="selectedTodo" rel="items" title="确定要删除吗?"><span>批量删除</span></a></li>
			 -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="80" rel="deptQuotaBox">
		<thead>
			<tr>
				<th width="40" align="center"><input type="checkbox" class="checkboxCtrl" group="items" /></th>
				<th width="40" align="center">序号</th>
				<th width="60">单位类型</th>
				<th width="200">指标名称</th>
				<th width="60">指标类型</th>
				<th width="60">指标分值</th>
				<th width="60">状态</th>
				<th>描述</th>
				<th width="100" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${pageData.result}" varStatus="statue">
			<tr>
				<td align="center"><input type="checkbox" name="items" value="${entity.deptQuotaId}" /></td>
				<td align="center">${statue.index+(pageData.pagination.pageSize*(pageData.pagination.pageNo-1))+1}</td>
				<td>${entity.deptType}</td>
				<td>${entity.quota.quotaName}</td>
				<td>${qtypes[entity.quota.quotaType]}</td>
				<td>${entity.quota.quotaScore}</td>
				<td>
					<c:if test="${entity.inused eq 0}"><div style="color:red;">停用</div></c:if>
					<c:if test="${entity.inused eq 1}"><div style="color:green;">启用</div></c:if>
				</td>
				<td>${entity.quota.quotaDesc}</td>
				<td>
					<c:if test="${entity.inused eq 0}">
						<a class="delete" href="${pageContext.request.contextPath}/khpj/deptQuota/enable/${entity.deptQuotaId}.html" target="ajaxTodo" title="确定要启用吗?"><span style="color:green">启用</span></a>
					</c:if>
					<c:if test="${entity.inused eq 1}">
						<a class="delete" href="${pageContext.request.contextPath}/khpj/deptQuota/disable/${entity.deptQuotaId}.html" target="ajaxTodo" title="确定要停用吗?"><span style="color:red">停用</span></a>
					</c:if>
					&nbsp;
					<a class="btnEdit" href="${pageContext.request.contextPath}/khpj/deptQuota/edit/${entity.deptQuotaId}.html" target="dialog" rel="modifyDialog" title="单位指标更新" mask="true" width="540" height="190">修改</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20" ${pageData.pagination.pageSize==20?"selected=selected":"" }>20</option>
				<option value="50" ${pageData.pagination.pageSize==50?"selected=selected":"" }>50</option>
				<option value="100" ${pageData.pagination.pageSize==100?"selected=selected":"" }>100</option>
				<option value="200" ${pageData.pagination.pageSize==200?"selected=selected":"" }>200</option>
			</select>
			<span>条，共${pageData.pagination.totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${pageData.pagination.totalCount}" numPerPage="${pageData.pagination.pageSize}" pageNumShown="${pageData.pagination.totalCount}" currentPage="${pageData.pagination.pageNo}"></div>
	</div>
</div>
