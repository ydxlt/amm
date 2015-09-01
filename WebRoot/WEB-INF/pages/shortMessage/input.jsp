<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="pageContent">
	<form method="post" action="${ctx}/shortMessage/save.php"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent">
			<p class="nowrap">
				<label style="text-align: right">
					电话号码：
				</label>
				<input type="hidden" name="id" value="${sms.id}" />
				<textarea id="phonoNumbers" rows="2" cols="99" name="phonoNumbers"
					class="text" readonly="readonly">${sms.phonoNumbers}</textarea>
			</p>
			<p class="nowrap">
				<label style="text-align: right">
					短信内容：
				</label>
				<textarea id="content" rows="2" cols="99" name="content"
					class="text" readonly="readonly">${sms.content}</textarea>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">
								保存
							</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">
								取消
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>