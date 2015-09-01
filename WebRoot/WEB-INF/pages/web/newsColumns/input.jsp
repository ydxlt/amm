<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
	<form method="post" action="${ctx}/web/newsColumns/save" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label style="text-align: right">栏目名称：</label>
				<input name="columnName" class="required" type="text" size="30" value="${newsColumns.columnName}" alt="请输入栏目名称"/>
				<input name="id" type="hidden" value="${newsColumns.id}"/>
			</p>
			<c:if test="${empty newsColumns}">
			<!-- 当columns为新增的时候可以更改 -->
			<p>
				<label style="text-align: right">栏目编码：</label>
				<input name="columnCode" class="required" type="text" size="30" alt="请输入栏目编码"/>
			</p>
			<p>
				<label style="text-align: right">上级栏目：</label>
				<select name="newscolumnsId">
					<option value="">无</option>
					<c:forEach var="column" items="${columns}">
						<option value="${column.id}">${column.columnName}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label style="text-align: right">栏目类型：</label>
				<select name="columnType" class="required">
					<option value="list">列表</option>
					<option value="view">单页</option>
				</select>
			</p>
			<p>
				<label style="text-align: right">可否编辑：</label>
				<select name="enable" class="required">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</p>
			</c:if>
			
			<c:if test="${not empty newsColumns}">
			<!-- 当columns为新增的时候可以更改 -->
			<p>
				<label style="text-align: right">栏目编码：</label>
				${newsColumns.columnCode}
				<input name="columnCode" type="hidden" value="${newsColumns.columnCode}"/>
			</p>
			<p>
				<label style="text-align: right">上级栏目：</label>
				<c:forEach var="column" items="${columns}">
					<c:if test="${column.id == newsColumns.newscolumns.id}">${column.columnName}</c:if>
				</c:forEach>
			</p>
			<p>
				<label style="text-align: right">栏目类型：</label>
				${newsColumns.columnType}
				<input name="columnType" type="hidden" value="${newsColumns.columnType}"/>
			</p>
			<p>
				<label style="text-align: right">可否编辑：</label>
				${newsColumns.enable}
				<input name="enable" type="hidden" value="${newsColumns.enable}"/>
			</p>
			</c:if>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>