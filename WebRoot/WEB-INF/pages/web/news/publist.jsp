<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form id="pagerForm" method="post" action="${pageContext.request.contextPath}/web/news/publist.html">
	<input type="hidden" name="pageNum" value="${pageData.pagination.pageNo}" />
	<input type="hidden" name="numPerPage" value="${pageData.pagination.pageSize}" />
</form>

<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="${pageContext.request.contextPath}/web/news/publist.html" method="post">
	<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">&nbsp;&nbsp;检 索&nbsp;&nbsp;</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${pageContext.request.contextPath}/web/news/publish.html" target="selectedTodo" rel="items" title="确定要批量发布吗?"><span>批量发布</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="120" rel="newsBox">
		<thead>
			<tr>
				<th width="40" align="center"><input type="checkbox" class="checkboxCtrl" group="items" /></th>
				<th width="40" align="center">序号</th>
				<th width="80" align="center">栏目</th>
				<th>标题</th>
				<th width="80">图片新闻</th>
				<th width="120">创建日期</th>
				<th width="40">状态</th>
				<th width="50" align="center">修改</th>
				<th width="50" align="center">查看</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="entity" items="${pageData.result}" varStatus="statue">
			<tr>
				<td align="center"><input type="checkbox" name="items" value="${entity.id}" /></td>
				<td align="center">${statue.index+(pageData.pagination.pageSize*(pageData.pagination.pageNo-1))+1}</td>
				<td>${entity.newscolumns.columnName}</td>
				<td>${entity.title}</td>
				<td><c:if test="${entity.isPicNews == 0}">否</c:if><c:if test="${entity.isPicNews == 1}">是</c:if></td>
				<td>${entity.cdate}</td>
				<td>${newsState[entity.status]}</td>
				<td>&nbsp;
					<a class="btnEdit" href="${pageContext.request.contextPath}/web/news/edit/${entity.id}.html" target="navTab" rel="modifyDialog" title="更新" mask="true">修改</a>
				</td>
				<td>&nbsp;
					<a class="btnView" href="${pageContext.request.contextPath}/web/news/view/${entity.id}.html" target="_blank" rel="modifyDialog" title="查看" mask="true">查看</a>
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
