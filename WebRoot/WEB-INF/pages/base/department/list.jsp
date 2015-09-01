<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<form id="pagerForm" method="post" action="${ctx}/base/department/list.html">
	<input type="hidden" name="pageNum" value="${pageData.pagination.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageData.pagination.pageSize}" />
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${ctx}/base/department/new.html" target="dialog" rel="addDialog" title="部门信息新增" mask="true" width="840" height="520"><span>添加</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="${ctx}/base/department/delete.html" target="selectedTodo" rel="items" title="确定要删除吗?"><span>批量删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="80" rel="departmentBox">
		<thead>
			<tr>
				<th width="40" align="center"><input type="checkbox" class="checkboxCtrl" group="items" /></th>
				<th width="40" align="center">序号</th>
				<th width="100">部门名称</th>
				<th width="40">类型</th>
				<th width="80">固定指标</th>
				<th width="80">专门小组工作</th>
				<th>描述</th>
				<th width="50" align="center">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${pageData.result}" varStatus="statue">
			<tr>
				<td align="center"><input type="checkbox" name="items" value="${entity.departmentId}" /></td>
				<td align="center">${statue.index+(pageData.pagination.pageSize*(pageData.pagination.pageNo-1))+1}</td>
				<td>${entity.departmentName}</td>
				<td>${entity.departmentType}</td>
				<td><c:if test="${empty entity.transFixedQuota || entity.transFixedQuota == '0'}">否</c:if><c:if test="${entity.transFixedQuota == '1'}"><div style="color:red">是</div></c:if></td>
				<td><c:if test="${empty entity.speciallyWork || entity.speciallyWork == '0'}">否</c:if><c:if test="${entity.speciallyWork == '1'}"><div style="color:red">是</div></c:if></td>
				<td>${entity.departmentDesc}</td>
				<td>&nbsp;
					<a class="btnEdit" href="${ctx}/base/department/edit/${entity.departmentId}.html" target="dialog" rel="modifyDialog" title="部门信息更新" mask="true" width="840" height="520">修改</a>
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
