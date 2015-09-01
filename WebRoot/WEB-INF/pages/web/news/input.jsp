<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<h2 class="contentTitle">网站内容新增</h2>
<div class="pageContent" layoutH="40">
	<form method="post" action="${ctx}/web/news/save.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent">
			<p class="nowrap">
				<label style="text-align: right">新闻标题：</label>
				<input name="title" class="required" type="text" size="80" value="${news.title}" alt="请输入新闻标题"/>
				<input name="id" type="hidden" value="${news.id}"/>
			</p>
			<p class="nowrap">
				<label style="text-align: right">所属栏目：</label>
				<select class="combox required" ref="childer_column" refUrl="${ctx}/web/newsColumns/getChilderColumns/{value}">
					<option value="0">请选择一级栏目</option>
					<c:forEach var="column" items="${columns}">
						<option value="${column.id}" <c:if test="${news.newscolumns.newscolumns.id == column.id}">selected="selected"</c:if>>${column.columnName}</option>
					</c:forEach>
				</select>
				<select class="combox required" id="childer_column" name="newsColumnsId">
					<c:if test="${not empty news.newscolumns}">
						<option value="${news.newscolumns.id}">${news.newscolumns.columnName}</option>
					</c:if>
					<c:if test="${empty news}">
						<option value="">请选择二级目录</option>
					</c:if>
				</select>
			</p>
			<p>
				<label style="text-align: right">作者：</label>
				<input name="author" type="text" size="20" value="${news.newsdetails.author}" alt="请输入作者"/>
			</p>
			<p>
				<label style="text-align: right">编辑：</label>
				<input name="editor" type="text" size="20" value="${news.newsdetails.editor}" alt="请输入编辑"/>
			</p>
			<p class="nowrap">
				<label style="text-align: right">关键字：</label>
				<input name="keyWords" type="text" size="80" value="${news.keyWords}" alt="请输入关键字"/>
			</p>
			<p>
				<label style="text-align: right">发布时间：</label>
				<input name="cdate" type="text" size="20" value="${news.cdate}" class="date" dateFmt="yyyy-MM-dd HH:mm" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p class="nowrap">
				<label style="text-align: right">摘要:</label>
				<textarea rows="3" cols="80" name="abstract_">${news.newsdetails.abstract_}</textarea>
			</p>
			<p class="nowrap">
				<label style="text-align: right">图片新闻:</label>
				<span id="uploadList">
					<c:if test="${news.isPicNews == 1}">
						<input type='radio' name='picture' value='${news.picture}'/>
					</c:if>
				</span>
			</p>
			<p class="nowrap">
				<label style="text-align: right">正文:</label>
				<textarea id="content" rows="14" cols="70" name="content" class="editor">${news.newsdetails.content}</textarea>
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
	<script type="text/javascript">
		//自定义插件
			var editor;
			var allPlugin;
			$(pageInit);
			function pageInit()
			{
				allPlugin={
					paipan:{c:'paiban',t:'一键排版',e:function(){
			            var text = editor.getSource();
			            text = text.replaceAll("<p style=\"","<p style=\"text-indent:2em;");
			            editor.setSource(text);
			        }}
				}
			}
		$(document).ready(
		function() {
			// 初始化xhEditor编辑器插件
			editor = $('#content').xheditor({
				plugins:allPlugin,
				tools : 'Bold,Italic,Underline,|,FontSize,Align,List,Outdent,Indent,|,Table,Img,Link,|,paipan,Preview,Print,Fullscreen,Source',
				upMultiple : true,
				upImgUrl : "${ctx}/web/news/xhEditorUpload.html",
				upImgExt : "jpg,jpeg,gif,bmp,png",
				upLinkUrl : "${ctx}/web/news/xhEditorUpload.html",
				upLinkExt : "zip,rar,txt,doc,docx,xls,xlsx",
				onUpload : insertUpload,
				html5Upload : false
			});
			// xbhEditor编辑器图片上传回调函数
			function insertUpload(msg) {
				var _msg = msg.toString();
				var _picture_name = _msg.substring(_msg.lastIndexOf("/") + 1);
				var _picture_path = Substring(_msg);
				var _str = "<input type='radio' name='picture' value='"
						+ _picture_path
						+ "'/>"
						+ _picture_name + "<br/>";
				$("#content").append(_msg);
				$("#uploadList").append(_str);
			}
			// 处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.
			function Substring(s) {
				return s.substring(s.substring(0, s.lastIndexOf("/"))
						.lastIndexOf("/"), s.length);
			}
		});
	</script>
</div>