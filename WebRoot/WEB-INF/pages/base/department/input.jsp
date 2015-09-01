<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
	<form method="post" action="${ctx}/base/department/save.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label style="text-align: right">部门名称：</label>
				<input name="departmentName" class="required" type="text" size="30" value="${department.departmentName}" alt="请输入部门名称"/>
				<input name="departmentId" type="hidden" value="${department.departmentId}"/>
			</p>
			<p>
				<label style="text-align: right">部门类型：</label>
				<select name="departmentType" class="required">
					<option value="A" <c:if test="${department.departmentType == 'A'}">selected="selected"</c:if>>A</option>
					<option value="B" <c:if test="${department.departmentType == 'B'}">selected="selected"</c:if>>B</option>
					<option value="C" <c:if test="${department.departmentType == 'C'}">selected="selected"</c:if>>C</option>
				</select>
			</p>
			<p class="nowrap">
				<label style="text-align: right">描述:</label>
				<textarea rows="3" cols="40" name="departmentDesc">${department.departmentDesc}</textarea>
			</p>
			
			<p class="nowrap">
				<label style="text-align: right">固定指标模版:</label>
				<textarea id="content" rows="20" cols="80" name="content" class="editor">${department.transFixedQuotaTemplate}</textarea>
			</p>
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