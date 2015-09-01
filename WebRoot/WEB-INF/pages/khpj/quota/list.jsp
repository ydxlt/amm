<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="pagerForm" method="post" action="${ctx}/khpj/quota/list/${type}.html">
	<input type="hidden" name="pageNum" value="${pageData.pagination.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageData.pagination.pageSize}" />
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/khpj/quota/new/${type}.html" target="dialog" rel="addDialog" title="指标信息新增" mask="true" width="540" height="300"><span>添加</span></a></li>
			<!-- <li class="line">line</li>
			<li><a class="delete" href="${ctx}/khpj/quota/delete.html" target="selectedTodo" rel="items" title="确定要删除吗?"><span>批量删除</span></a></li>
			 -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="80" rel="quotaBox">
		<thead>
			<tr>
				<th width="40" align="center"><input type="checkbox" class="checkboxCtrl" group="items" /></th>
				<th width="40" align="center">序号</th>
				<th width="100">指标类型</th>
				<th width="200">指标名称</th>
				<th width="40">指标分值</th>
				<th>描述</th>
				<th width="50" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${pageData.result}" varStatus="statue">
			<tr>
				<td align="center"><input type="checkbox" name="items" value="${entity.quotaId}" /></td>
				<td align="center">${statue.index+(pageData.pagination.pageSize*(pageData.pagination.pageNo-1))+1}</td>
				<td>${qtypes[entity.quotaType]}</td>
				<td>${entity.quotaName}</td>
				<td>${entity.quotaScore}</td>
				<td>${entity.quotaDesc}</td>
				<td>&nbsp;
					<a class="btnEdit" href="${ctx}/khpj/quota/edit/${entity.quotaId}.html" target="dialog" rel="modifyDialog" title="指标信息更新" mask="true" width="540" height="300">修改</a>
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
