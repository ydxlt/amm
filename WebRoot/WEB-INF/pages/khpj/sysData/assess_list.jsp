<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<html lang="zh-cn">
<head>
<title>考核数据查看</title>
<%@ include file="/WEB-INF/pages/common/styles.jsp"%>
</head>
<body style="padding:10px;">
	<div class="container-fluid">
	<!-- 
	<select onchange="javascript:self.location.href='${ctx}/khpj/sysData/view.html?deptId=${deptId}&month=${month}&type=${type}'">
		<c:forEach var="year" items="${years}">
			<option value="${year}" <c:if test="${year == month}">selected="selected"</c:if>>${year}</option>
		</c:forEach>
	</select>
	 -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#A" data-toggle="tab">单位文件</a>
			</li>
			<li><a href="#B" data-toggle="tab">活动图片</a>
			</li>
			<li><a href="#C" data-toggle="tab">综合材料</a>
			</li>
			<li><a href="#D" data-toggle="tab">报表</a>
			</li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="A">
				<c:if test="${empty sysDataDeptFile}">暂无数据</c:if>
				<c:forEach var="sys_data" items="${sysDataDeptFile}">
					${sys_data.department.departmentName}
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>${sysDataType[sys_data.contentType]} <span style="float: right;">上报时间：${sys_data.createDate}</span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<textarea style="width:100%;height:400px;visibility:hidden;" readonly="readonly">
										<p align="center"> ${sys_data.title}</p>
										<p align="center">（${sys_data.department.departmentName}）</p>
										${sys_data.content}
									</textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</c:forEach>
			</div>
			<div class="tab-pane" id="B">
				<c:if test="${empty sysDataActive}">暂无数据</c:if>
				<c:forEach var="sys_data" items="${sysDataActive}">
					${sys_data.department.departmentName}
					<table class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>${sysDataType[sys_data.contentType]} <span style="float: right;">上报时间：${sys_data.createDate}</span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<textarea style="width:100%;height:400px;visibility:hidden;" readonly="readonly">
										<p align="center"> ${sys_data.title}</p>
										<p align="center">（${sys_data.department.departmentName}）</p>
										${sys_data.content}
									</textarea>s
								</td>
							</tr>
						</tbody>
					</table>
				</c:forEach>
			</div>
			<div class="tab-pane" id="C">
				<c:if test="${empty sysDataMaterial}">暂无数据</c:if>
				<c:forEach var="sys_data" items="${sysDataMaterial}">
					${sys_data.department.departmentName}
					<table class="table table-hover table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th>${sysDataType[sys_data.contentType]} <span style="float: right;">上报时间：${sys_data.createDate}</span></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<textarea style="width:100%;height:400px;visibility:hidden;">
										<p align="center"> ${sys_data.title}</p>
										<p align="center">（${sys_data.department.departmentName}）</p>
										${sys_data.content}
									</textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</c:forEach>
			</div>
			<div class="tab-pane" id="D">
			<!-- 矛盾纠纷报表查看 -->
			<div class="row">
				<h5>&nbsp;&nbsp;&nbsp;&nbsp;矛盾纠纷报表</h5>
				<c:if test="${empty cdreports}">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;暂无数据
				</c:if>
				<c:forEach var="data" items="${cdreports}" varStatus="statue">
					<div class="span4">
						<table class="table table-bordered table-condensed">
							<tbody>
								<tr>
									<td width="50" height="50">
										<p>
											类别
										</p>
									</td>
									<td colspan="3">
										<p>
											${types[data.conDisClass]}
										</p>
									</td>
								</tr>
								<tr>
									<td>
										<p>
											层级
										</p>
									</td>
									<td>
										<p>
											县区
										</p>
									</td>
									<td>
										<p>
											乡镇
										</p>
									</td>
									<td>
										<p>
											村居
										</p>
									</td>
								</tr>
								<tr>
									<td>
										<p>
											排查数
										</p>
									</td>
									<td>
										<p>
											${data.countyCount}
										</p>
									</td>
									<td>
										<p>
											${data.townCount}
										</p>
									</td>
									<td>
										<p>
											${data.villageCount}
										</p>
									</td>
								</tr>
								<tr>
									<td>
										<p>
											调处数
										</p>
									</td>
									<td>
										<p>
											${data.couDisCount}
										</p>
									</td>
									<td>
										<p>
											${data.townDisCount}
										</p>
									</td>
									<td>
										<p>
											${data.vilDisCount}
										</p>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:forEach>
			</div>
			<hr/>
			<!-- 突出矛盾纠纷台账查看 -->
			<div class="row-fluid">
				<h5>突出矛盾纠纷台账</h5>
				<c:if test="${empty machineAccount}">
					暂无数据
				</c:if>
				<c:if test="${not empty machineAccount}">
					<table class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="span1">
									序号
								</th>
								<th class="span2">
									单位
								</th>
								<th>
									矛盾纠纷简要情况
								</th>
								<th>
									责任单位
									<br />
									责任人
								</th>
								<th>
									化解时限
								</th>
								<th>
									化解情况
								</th>
								<th>
									交办督办情况
								</th>
								<th>
									销号情况
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="d" items="${machineAccount}" varStatus="statue">
								<tr>
									<td>
										${statue.index + 1}
									</td>
									<td>
										${d.department.departmentName}
									</td>
									<td>
										${d.cdDescription}
									</td>
									<td>
										${d.dutyPerson}
									</td>
									<td>
										${d.resoleTime}
									</td>
									<td>
										${d.resoleDescription}
									</td>
									<td>
										${d.supervision}
									</td>
									<td>
										${d.xiaoHao}
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/pages/common/scripts.jsp"%>
	<script type="text/javascript" src="${ctx}/resource/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="${ctx}/resource/kindeditor/zh_CN.js"></script>
	<script type="text/javascript" src="${ctx}/resource/pages/sysData/assess_list.js"></script>
</body>
</html>