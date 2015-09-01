<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent" layoutH="0">
	<form method="post" action="${ctx}/web/messages/update.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p class="nowrap">
				<label style="text-align: right">标题：</label>
				${message.title}
				<input name="messageId" type="hidden" value="${message.messageId}"/>
			</p>
			<p class="nowrap">
				<label style="text-align: right">内容：</label>
				<textarea rows="7" cols="60" style="border: 0">${message.content}</textarea>
			</p>
			<p>
				<label style="text-align: right">留言者/时间：</label>
				${message.messName}/${message.ltime}
			</p>
			<p class="nowrap">
				<label style="text-align: right">回复:</label>
				<textarea rows="6" cols="60" name="recontent"></textarea>
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