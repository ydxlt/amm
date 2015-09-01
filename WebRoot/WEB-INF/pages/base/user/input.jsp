<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
	<form method="post" action="${ctx}/base/user/save" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p>
				<label style="text-align: right">用户名称：</label>
				<input name="userName" class="required" type="text" size="30" value="${user.userName}" alt="请输入用户名称"/>
				<input name="userId" type="hidden" value="${user.userId}"/>
				<input name="password" type="hidden" value="${user.password}"/>
			</p>
			<p>
				<label style="text-align: right">电话号码：</label>
				<input name="phoneNumber" class="required" type="text" size="30" value="${user.phoneNumber}" alt="请输入电话号码"/>
			</p>
			<p>
				<label style="text-align: right">用户单位：</label>
				<select name="demartmentId">
					<c:forEach var="dept" items="${departments}">
						<option value="${dept.departmentId}" <c:if test="${dept.departmentId == user.userDep.departmentId}">selected="selected"</c:if>>(${dept.departmentType})${dept.departmentName}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label style="text-align: right">用户角色：${userRole.roleId}</label>
				<select name="roleId">
					<c:forEach var="role" items="${roles}">
						<option value="${role.roleId}" <c:if test="${role.roleId == userRole.roleId}">selected="selected"</c:if>>${role.roleDesc}</option>
					</c:forEach>
				</select>
			</p>
			<p class="nowrap">
				<label style="text-align: right">描述:</label>
				<textarea rows="3" cols="40" name="userDesc">${user.userDesc}</textarea>
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