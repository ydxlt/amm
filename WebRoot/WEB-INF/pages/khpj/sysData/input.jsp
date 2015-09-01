<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>考核数据维护</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body>
	<div class="container mainbody">
		<%@ include file="/WEB-INF/pages/common/head.jsp"%>

		<div class="row-fluid">
			<div class="span10">
				<ul class="breadcrumb">
					<li><span class="divider">|</span>
					</li>
					<li><a href="${ctx}/khpj/sysData/list.html">控制面板</a> <span class="divider">|</span>
					</li>
					<li class="active">新增考核数据</li>
				</ul>
			</div>
			<div class="btn-group span2">
				<a class="btn btn-primary" href="${ctx}/khpj/sysData/list.html"><i class="icon-backward icon-white"></i> 返回 </a>
			</div>
		</div>

		<c:if test="${not empty tips}">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4>系统提示</h4>
				${tips}!
			</div>
		</c:if>

		<div class="row-fluid">
			<ul class="nav nav-tabs">
				<c:if test="${department.transFixedQuota == '1'}">
					<li><a href="#FIX" data-toggle="tab">固定指标数据</a>
					</li>
				</c:if>
				<li class="active"><a href="#A" data-toggle="tab">单位文件</a>
				</li>
				<li><a href="#B" data-toggle="tab">活动图片</a>
				</li>
				<li><a href="#C" data-toggle="tab">综合材料</a>
				</li>
				<li><a href="#D" data-toggle="tab">矛盾纠纷报表及台帐</a>
				</li>
				<c:if test="${department.speciallyWork == '1'}">
					<li><a href="#SPECIALLYWORK" data-toggle="tab">专门工作</a>
					</li>
				</c:if>
			</ul>
			<div class="tab-content">
				<c:if test="${department.transFixedQuota == '1'}">
					<div class="tab-pane" id="FIX">
						<form action="${ctx}/khpj/sysData/save.html" method="post" onsubmit="return confirm('是否提交数据？')">
							<table class="table table-bordered table-condensed">
								<thead>
									<tr>
										<th>固定指标数据</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty showHistory}">
										<tr>
											<td>考核月份 <select name="scoreMonth">
													<c:forEach var="h" items="${showHistory}">
														<option value="${h}" <c:if test="${showHistoryMonth==h}">selected="selected"</c:if>>${h}</option>
													</c:forEach>
											</select>
											</td>
										</tr>
									</c:if>
									<tr>
										<td><textarea name="content" style="width:100%;height:400px;visibility:hidden;" class="editor">
												<c:if test="${not empty fix_quota.content}">
													${fix_quota.content}
												</c:if>
												<c:if test="${empty fix_quota.content}">
													${fix_quota_template}
												</c:if>
											</textarea></td>
									</tr>
									<tr>
										<td>
											<p class="text-center">
												<input type="hidden" name="contentType" value="${fixquotaType}" /> <input type="hidden" name="sysDataId" value="${fix_quota.sysDataId}" />
												<button class="btn btn-primary" type="submit">提 交</button>
											</p></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
				</c:if>
				<div class="tab-pane active" id="A">
					<form action="${ctx}/khpj/sysData/save.html" method="post" onsubmit="return confirm('是否提交数据？')">
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th>新增单位文件</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="text" name="title" class="span11" data-rule="required;" placeholder="输入单位文件标题" /></td>
								</tr>
								<c:if test="${not empty showHistory}">
									<tr>
										<td>考核月份 <select name="scoreMonth">
												<c:forEach var="h" items="${showHistory}">
													<option value="${h}" <c:if test="${showHistoryMonth==h}">selected="selected"</c:if>>${h}</option>
												</c:forEach>
										</select>
										</td>
									</tr>
								</c:if>
								<tr>
									<td><textarea name="content" style="width:100%;height:400px;visibility:hidden;" class="editor"></textarea></td>
								</tr>
								<tr>
									<td>
										<p class="text-center">
											<input type="hidden" name="contentType" value="${departmentType}" />
											<button class="btn btn-primary" type="submit">提 交</button>
											<a href="${ctx}/khpj/sysData/list.html" role="button" class="btn btn-primary" data-toggle="modal">返回</a>
										</p></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div class="tab-pane" id="B">
					<form action="${ctx}/khpj/sysData/save.html" method="post" onsubmit="return confirm('是否提交数据？')">
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th>新增活动图片</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="text" name="title" class="span11" data-rule="required;" placeholder="输入活动图片标题" /></td>
								</tr>
								<c:if test="${not empty showHistory}">
									<tr>
										<td>考核月份 <select name="scoreMonth">
												<c:forEach var="h" items="${showHistory}">
													<option value="${h}" <c:if test="${showHistoryMonth==h}">selected="selected"</c:if>>${h}</option>
												</c:forEach>
										</select>
										</td>
									</tr>
								</c:if>
								<tr>
									<td><textarea name="content" style="width:100%;height:400px;visibility:hidden;" class="editor"></textarea></td>
								</tr>
								<tr>
									<td>
										<p class="text-center">
											<input type="hidden" name="contentType" value="${acitveType}" />
											<button class="btn btn-primary" type="submit">提 交</button>
											<a href="${ctx}/khpj/sysData/list.html" role="button" class="btn btn-primary" data-toggle="modal">返回</a>
										</p></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div class="tab-pane" id="C">
					<form action="${ctx}/khpj/sysData/save.html" method="post" onsubmit="return confirm('是否提交数据？')">
						<table class="table table-bordered table-condensed">
							<thead>
								<tr>
									<th>新增综合材料</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="text" name="title" class="span11" data-rule="required;" placeholder="输入单位文件标题" /></td>
								</tr>
								<c:if test="${not empty showHistory}">
									<tr>
										<td>考核月份 <select name="scoreMonth">
												<c:forEach var="h" items="${showHistory}">
													<option value="${h}" <c:if test="${showHistoryMonth==h}">selected="selected"</c:if>>${h}</option>
												</c:forEach>
										</select>
										</td>
									</tr>
								</c:if>
								<tr>
									<td><textarea name="content" style="width:100%;height:400px;visibility:hidden;" class="editor"></textarea></td>
								</tr>
								<tr>
									<td>
										<p class="text-center">
											<input type="hidden" name="contentType" value="${materialType}" />
											<button class="btn btn-primary" type="submit">提 交</button>
											<a href="${ctx}/khpj/sysData/list.html" role="button" class="btn btn-primary" data-toggle="modal">返回</a>
										</p></td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div class="tab-pane" id="D">
					<div class="tab-pane">
						<ul class="nav nav-tabs">
							<li><a href="#REPORT_1" class="active" data-toggle="tab">矛盾纠纷报表</a>
							</li>
							<li><a href="#REPORT_2" data-toggle="tab">突出矛盾纠纷台帐</a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="REPORT_1">
								<form action="${ctx}/khpj/reports/con_disputes_report/save.html?from=sysdata" method="post" onsubmit="return confirm('是否提交数据？')">
									<c:set var="countyCount" value="0"></c:set>
									<c:set var="townCount" value="0"></c:set>
									<c:set var="villageCount" value="0"></c:set>
									<c:set var="couDisCount" value="0"></c:set>
									<c:set var="townDisCount" value="0"></c:set>
									<c:set var="vilDisCount" value="0"></c:set>
									<c:set var="typesize" value="${fn:length(types)}"></c:set>
									<div class="row-fluid">
										<div class="row-fluid">
										<c:forEach var="type" items="${types}" varStatus="statue">
											<div class="span4">
												<table class="table table-bordered table-condensed">
													<tbody>
														<tr>
															<td width="50" height="50">
																<p>类别</p></td>
															<td colspan="3">
																<p>${type.value}</p> <input type="hidden" name="${type.key}_id" value="${data[type.key].cDReportId}" /></td>
														</tr>
														<tr>
															<td>
																<p>层级</p>
															</td>
															<td>
																<p>县区</p>
															</td>
															<td>
																<p>乡镇</p>
															</td>
															<td>
																<p>村居</p>
															</td>
														</tr>
														<tr>
															<td>
																<p>排查数</p>
															</td>
															<td>
																<p>
																	<input type="text" class="input-mini" data-rule="integer;" name="${type.key}_countyCount" value="${data[type.key].countyCount}" />
																</p>
															</td>
															<td>
																<p>
																	<input type="text" class="input-mini" data-rule="integer;" name="${type.key}_townCount" value="${data[type.key].townCount}" />
																</p>
															</td>
															<td>
																<p>
																	<input type="text" class="input-mini" data-rule="integer;" name="${type.key}_villageCount" value="${data[type.key].villageCount}" />
																</p>
															</td>
														</tr>
														<tr>
															<td>
																<p>调处数</p>
															</td>
															<td>
																<p>
																	<input type="text" class="input-mini" data-rule="integer;" name="${type.key}_couDisCount" value="${data[type.key].couDisCount}" />
																</p>
															</td>
															<td>
																<p>
																	<input type="text" class="input-mini" data-rule="integer;" name="${type.key}_townDisCount" value="${data[type.key].townDisCount}" />
																</p>
															</td>
															<td>
																<p>
																	<input type="text" class="input-mini" data-rule="integer;" name="${type.key}_villageDisCount" value="${data[type.key].vilDisCount}" />
																</p>
															</td>
														</tr>
													</tbody>
												</table>
											</div>
											<c:if test="${(statue.index + 1)%3==0 && statue.index+1 < typesize}">
												</div><div class="row-fluid">
											</c:if>
										</c:forEach>
										</div>
									</div>

									<div class="row">
										<p class="text-right">
											<button class="btn btn-primary" type="submit">提 交</button>
											&nbsp;&nbsp;
										</p>
									</div>
								</form>
							</div>
							<div class="tab-pane" id="REPORT_2">
								<form action="${ctx}/khpj/reports/machine_account_report/save.html" method="post" onsubmit="return confirm('是否提交数据？')">
									<table class="table table-bordered table-condensed">
										<thead>
											<tr>
												<th>新增突出矛盾纠纷台帐</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>责任单位/责任人：<input type="text" name="dutyPerson" class="span3" data-rule="required;" placeholder="输入责任单位/责任人" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 化解时限：<input type="text" name="resoleTime" class="span3"
													data-rule="required;" placeholder="输入化解时限" /></td>
											</tr>
											<tr>
												<td>矛盾纠纷简要情况<br /> <textarea name="cdDescription" class="span11" rows="7"></textarea></td>
											</tr>
											<tr>
												<td>化解情况<br /> <textarea name="resoleDescription" class="span11" rows="7"></textarea></td>
											</tr>
											<tr>
												<td>交办督办情况：<input type="text" name="supervision" class="span3" data-rule="required;" placeholder="输入交办督办情况" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 消号情况：<input type="text" name="xiaoHao" class="span3" data-rule="required;"
													placeholder="输入消号情况" /></td>
											</tr>
											<tr>
												<td>
													<p class="text-center">
														<button class="btn btn-primary" type="submit">提 交</button>
														<a href="${ctx}/khpj/sysData/list.html" role="button" class="btn btn-primary" data-toggle="modal">返回</a>
													</p></td>
											</tr>
										</tbody>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
				<c:if test="${department.speciallyWork == '1'}">
					<div class="tab-pane" id="SPECIALLYWORK">
						<ul class="nav nav-tabs">
							<li><a href="#PLAN" class="active" data-toggle="tab">工作部署</a>
							</li>
							<li><a href="#DEVELOP" data-toggle="tab">工作开展</a>
							</li>
							<li><a href="#EFFECT" data-toggle="tab">工作效果</a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="PLAN">
								<form action="${ctx}/khpj/sysData/save.html" method="post" onsubmit="return confirm('是否提交数据？')">
									<table class="table table-bordered table-condensed">
										<thead>
											<tr>
												<th>工作部署</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty showHistory}">
												<tr>
													<td>考核月份 <select name="scoreMonth">
															<c:forEach var="h" items="${showHistory}">
																<option value="${h}" <c:if test="${showHistoryMonth==h}">selected="selected"</c:if>>${h}</option>
															</c:forEach>
													</select>
													</td>
												</tr>
											</c:if>
											<tr>
												<td><textarea name="content" style="width:100%;height:400px;visibility:hidden;" class="editor">${work_plan.content}</textarea></td>
											</tr>
											<tr>
												<td>
													<p class="text-center">
														<input type="hidden" name="contentType" value="${plan}" /> <input type="hidden" name="sysDataId" value="${work_plan.sysDataId}" />
														<button class="btn btn-primary" type="submit">提 交</button>
													</p></td>
											</tr>
										</tbody>
									</table>
								</form>
							</div>
							<div class="tab-pane" id="DEVELOP">
								<form action="${ctx}/khpj/sysData/save.html" method="post" onsubmit="return confirm('是否提交数据？')">
									<table class="table table-bordered table-condensed">
										<thead>
											<tr>
												<th>工作开展</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty showHistory}">
												<tr>
													<td>考核月份 <select name="scoreMonth">
															<c:forEach var="h" items="${showHistory}">
																<option value="${h}" <c:if test="${showHistoryMonth==h}">selected="selected"</c:if>>${h}</option>
															</c:forEach>
													</select>
													</td>
												</tr>
											</c:if>
											<tr>
												<td><textarea name="content" style="width:100%;height:400px;visibility:hidden;" class="editor">${work_develop.content}</textarea></td>
											</tr>
											<tr>
												<td>
													<p class="text-center">
														<input type="hidden" name="contentType" value="${develop}" /> <input type="hidden" name="sysDataId" value="${work_develop.sysDataId}" />
														<button class="btn btn-primary" type="submit">提 交</button>
													</p></td>
											</tr>
										</tbody>
									</table>
								</form>
							</div>
							<div class="tab-pane" id="EFFECT">
								<form action="${ctx}/khpj/sysData/save.html" method="post" onsubmit="return confirm('是否提交数据？')">
									<table class="table table-bordered table-condensed">
										<thead>
											<tr>
												<th>工作效果</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty showHistory}">
												<tr>
													<td>考核月份 <select name="scoreMonth">
															<c:forEach var="h" items="${showHistory}">
																<option value="${h}" <c:if test="${showHistoryMonth==h}">selected="selected"</c:if>>${h}</option>
															</c:forEach>
													</select>
													</td>
												</tr>
											</c:if>
											<tr>
												<td><textarea name="content" style="width:100%;height:400px;visibility:hidden;" class="editor">${work_effect.content}</textarea></td>
											</tr>
											<tr>
												<td>
													<p class="text-center">
														<input type="hidden" name="contentType" value="${effect}" /> <input type="hidden" name="sysDataId" value="${work_effect.sysDataId}" />
														<button class="btn btn-primary" type="submit">提 交</button>
													</p></td>
											</tr>
										</tbody>
									</table>
								</form>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>
		<%@ include file="/WEB-INF/pages/common/foot.jsp"%>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/kindeditor/zh_CN.js"></script>
	<script type="text/javascript">
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('.editor', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageRemote : false,
			uploadJson : '${ctx}/khpj/sysData/upload.html', //服务端上传图片处理URI
			items : [
				'undo', 'redo', '|', 'preview', 'print', 'cut', 'copy', 'paste',
				'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
				'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
				'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
				'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 
				'table', 'hr', 'pagebreak', 'anchor'
			]
		});
	});
	</script>
</body>
</html>