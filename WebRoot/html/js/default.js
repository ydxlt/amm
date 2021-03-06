function getDepartmentIndex()
{	
	var type = $("#type").val();
	$.getJSON($("#ctx").val()+"/dept/"+type+".html", function(data){
		if(data != '')
		{
			$("#username").empty();
			var option;
			for(i=0;i<data.length;i++)
			{
				option = "<option value=\""+data[i].departmentName +"\">"+data[i].departmentName+"</option>";
				$("#username").append(option);
			}
		}
	});
}

(function(a){a.fn.nbspSlider=function(b){var c={widths:"800px",heights:"300px",autoplay:0,delays:4e3,prevId:"prevBtn",nextId:"nextBtn",effect:"horizontal",speeds:800,altOpa:.5,altBgColor:"#ccc",altHeight:"40px",altShow:0,altFontColor:"#fff",starEndNoEff:0,preNexBtnShow:1,numBtnSty:"num",numBtnShow:1},b=a.extend(c,b);return b.widths=parseInt(b.widths)+"px",b.heights=parseInt(b.heights)+"px",this.each(function(){function i(d,e,f){var g;e?g=e:g=n(d,f),a("ul",c).trigger("fn_"+b.effect,[g])}function k(){j.attr("class",b.numBtnSty)}function m(d){k(),a("#btnList li",c).eq(d).attr("class",b.numBtnSty+"_act")}function p(b){var d=a(c).find("ul li img").eq(b).attr("alt");a(c).find("#altbox").text(d)}var c=a(this),d=a("li",c).length,e=d-1,f=0;b.effect=="horizontal"?a(c).find("ul").css("width",d*parseInt(b.widths)+"px"):a("ul",c).css("width",b.widths),a(c).css({width:b.widths,height:parseInt(b.heights)+20+"px",position:"relative",overflow:"hidden"}),a("ul",c).css({position:"absolute",left:"0"}),a("ul li,img",c).css({width:b.widths,height:b.heights,border:"none","float":"left"}),a(c).append('<span class="sliderBtn" id='+b.prevId+'></span><span class="sliderBtn" id='+b.nextId+'></span><div id="altbox">'+a(c).find("ul li img").eq(0).attr("alt")+'</div><div id="btnList"></div>'),a("#altbox",c).css({width:parseInt(b.widths)-20+"px",height:b.altHeight,"line-height":b.altHeight,opacity:b.altOpa,background:b.altBgColor,padding:"0 10px 0 10px",position:"absolute",bottom:"0",color:b.altFontColor,"text-align":"left",left:"0"});var g=d;for(var h=1;h<=g;g--)a(c).find("#btnList").append("<li>"+(b.numBtnSty=="num"?g:"<i style='float:left;text-indent:-9999px;'>"+g+"</i>")+"</li>");a("#btnList",c).css({"width":parseInt(b.widths)-20+"px","left":"0"}),b.numBtnSty=="num"?a("#btnList li",c).addClass("btnSty"):a("#btnList li",c).addClass(b.numBtnSty),a(".sliderBtn",c).css("opacity",.2).hover(function(){a(this).stop(!0,!1).animate({opacity:"0.5"},300)},function(){a(this).stop(!0,!1).animate({opacity:"0.2"},300)}),b.preNexBtnShow==0?a(".sliderBtn",c).hide():"",b.altShow==0?a(c).find("#altbox").hide():"",b.numBtnShow==0?a("#btnList",c).hide():"",a(".sliderBtn",c).click(function(){var b=a(this).attr("id").substring(0,4);i(b)});var j=a(c).find("#btnList li");(function(c){c.hover(function(){a(this).hasClass(b.numBtnSty+"_act")?"":a(this).attr("class",b.numBtnSty+"_hover")},function(){a(this).hasClass(b.numBtnSty+"_act")?"":a(this).attr("class",b.numBtnSty)})})(j),k(),a("#btnList li:last-child",c).attr("class",b.numBtnSty+"_act");var l;j.click(function(){k(),f=f>e?0:parseInt(a(this).text())-1;var c=function(){return b.effect=="horizontal"?f*parseInt(b.widths):f*parseInt(b.heights)}();c==0?c:c="-"+c,i("next",parseInt(c)+"px",1),a(this).attr("class",b.numBtnSty+"_act")});var n=function(a,c){a=="next"?f=f>=e?0:f+1:0,a=="prev"?f=f<=0?e:f-1:0,c==1?f=0:m("-"+(f+1)),p(f);var d=f*parseInt(b.widths)*-1,g=f*parseInt(b.heights)*-1;return b.effect=="horizontal"?d:g};a("ul",c).bind("fn_"+b.effect,function(c,d){b.effect=="horizontal"?b.noEffect==1?a(this).css("margin-left",d):f!=0&&f!=e||b.starEndNoEff!=1?a(this).animate({marginLeft:d},b.speeds):a(this).css("margin-left",d):b.effect=="none"||b.starEndNoEff==1&&(f==0||f==e)?a(this).css("margin-top",d):b.effect=="fade"?a(this).hide(1).animate({marginTop:d},30).fadeIn("slow"):a(this).animate({marginTop:d},b.speeds),p(f)});if(b.autoplay==1){var o;a(c).hover(function(){clearInterval(o)},function(){o=setInterval(function(){i("next")},b.delays)}).trigger("mouseleave")}})}})(jQuery)

$(document).ready(function(){
	$("#slideshow").nbspSlider({
		widths:         "280px",        // 幻灯片宽度
		heights:        "190px",		// 幻灯片高度
		effect:	         "fade",
		autoplay:       1,
		delays:         4000,// 是否自动播放幻灯片(1为是0为
		effect:	         "vertical",
		speeds:          300,
		preNexBtnShow:   0,
		prevId: 		'prevBtn',      // 上一张幻灯片按钮ID
		nextId: 		'nextBtn',		// 下一张幻灯片按钮ID
		altShow:         1,
		altOpa:          1,            // ALT区块透明度
		altBgColor:      '#FFF',  		// ALT区块背景颜色
		altFontColor:    '#000',
		altHeight:       '20px'  		// ALT区块高度

	});
});