<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/web/newsColumns/list">
	<input type="hidden" name="pageNum" value="${pageData.pagination.pageNo}" /> <input type="hidden" name="numPerPage" value="${pageData.pagination.pageSize}" />
</form>

<div class="pageContent">
	<div layoutH="0" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
		<div style="padding: 10px 0 0 10px;">网站结构</div>
		<ul id="struts" class="ztree"></ul>
	</div>

	<div id="jbsxBox" class="unitBox" style="margin-left:243px;" layoutH="0">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="${pageContext.request.contextPath}/web/newsColumns/new" target="dialog" rel="addDialog" title=" 网站栏目新增" mask="true" width="540" height="250"><span>添加</span></a>
				</li>
				<!-- <li class="line">line</li>
			<li><a class="delete" href="${pageContext.request.contextPath}/web/newsColumns/delete" target="selectedTodo" rel="items" title="确定要删除吗?"><span>批量删除</span></a></li>
		 -->
		 		<li class="line">line</li>
				<li><a class="add" href="${pageContext.request.contextPath}/web/newsColumns/refresh" target="ajaxTodo" title="确定要刷新菜单吗?"><span>刷新菜单</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="80" rel="departmentBox">
			<thead>
				<tr>
					<th width="40" align="center"><input type="checkbox" class="checkboxCtrl" group="items" />
					</th>
					<th width="40" align="center">序号</th>
					<th>栏目名称</th>
					<th width="80">栏目编号</th>
					<th width="80">栏目类型</th>
					<th width="50" align="center">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entity" items="${pageData.result}" varStatus="statue">
					<tr>
						<td align="center"><input type="checkbox" name="items" value="${entity.id}" />
						</td>
						<td align="center">${statue.index+(pageData.pagination.pageSize*(pageData.pagination.pageNo-1))+1}</td>
						<td>${entity.columnName}</td>
						<td>${entity.columnCode}</td>
						<td>${entity.columnType}</td>
						<td>&nbsp; 
							<c:if test="${entity.enable == '1'}">
								<a class="btnEdit" href="${pageContext.request.contextPath}/web/newsColumns/edit/${entity.id}" target="dialog" rel="modifyDialog" title="网站栏目更新" mask="true" width="540" height="250">修改</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="20" ${pageData.pagination.pageSize==20? "selected=selected":"" }>20</option>
					<option value="50" ${pageData.pagination.pageSize==50? "selected=selected":"" }>50</option>
					<option value="100" ${pageData.pagination.pageSize==100? "selected=selected":"" }>100</option>
					<option value="200" ${pageData.pagination.pageSize==200? "selected=selected":"" }>200</option>
				</select> <span>条，共${pageData.pagination.totalCount}条</span>
			</div>

			<div class="pagination" targetType="navTab" totalCount="${pageData.pagination.totalCount}" numPerPage="${pageData.pagination.pageSize}" pageNumShown="${pageData.pagination.totalCount}" currentPage="${pageData.pagination.pageNo}"></div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resource/pages/web/newsColumns/list.js"></script>