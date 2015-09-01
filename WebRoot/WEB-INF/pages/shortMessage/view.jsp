<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="pageContent" layoutH="40" >
	<table class="table" width="100%" layoutH="120" rel="newsBox">
	<form class="pageForm required-validate" >
		<div class="pageFormContent">
		<p class="nowrap">
			<label style="text-align: right">
				电话号码：
			</label>				
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
					<div class="button">
						<div class="buttonContent" align="right">
							<button type="button" class="close">
								关闭
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
	</table>
</div>
