//传输数据
	
	// 百度地图API功能;点击生成和拖动标注
	var iscreatr=false;
	function initialize() {
	
		var map = new BMap.Map("allmap",{minZoom:12,maxZoom:20});	// 创建Map实例
		
		//map.centerAndZoom(new BMap.Point(116.4035,39.915),15);  //初始化时，即可设置中心点和地图缩放级别。
		map.centerAndZoom("深圳",8);		// 初始化地图,设置中心点坐标和地图级别。  
		map.enableScrollWheelZoom(true);// 鼠标滑动轮子可以滚动
		
		var menu = new BMap.ContextMenu(); // 给地图添加右键菜单
		
		// 点击标注后显示信息窗口
		var popWindow = ["<div class='infoBoxContent'><div class=''></div>",
		     "<div class='list'><ul><h2>东震集团</h2>"
		     ,"<li><div class='left'>编号：</div><div><input name='numId' id='numId' type='text' size='15'/></div></li>"
		     ,"<li><div class='left'>位置：</div><div><input name='sites' id='sites' type='text' size='15' readonly/></div></li>"
		     ,"<li><div class='left'><button id='nodeInfo'>点击这里</button></div></li>"
		     ,"</ul></div>"
		     ,"</div>"];
		

		var infoBox = new BMapLib.InfoBox(map,popWindow.join(""),{
			boxStyle:{
				background:"url('resource/baiduMap/img/tipbox.gif') no-repeat center top"
				,width: "270px"
				,height: "210px"
			}
			,closeIconMargin: "1px 1px 0 0"
			,enableAutoPan: true
			,align: INFOBOX_AT_TOP
		});
		
		var txtMenuItem = [
			{
				text:'添加标注',
				callback:function(e){
					var marker = new BMap.Marker(e), px = map.pointToPixel(e);
					map.addOverlay(marker);// 将标注添加到地图中
					infoBox.open(marker);
					//-------------帮定事件1--------------------
					$('#nodeInfo').click(function(){
				        $.ajax({  
				            url:'index/PointInfo/savePointInfo.php',  
				            type:'POST',
				            data:{
								numId : $("#numId").val(),
								longitude : e.lng,
								latitude : e.lat
							},  
				            dataType:'json',
				            success:function (data) {
				            	alert(data.message); 
				            },
				            error: function( jqXHR,  textStatus,  errorThrown ){alert('error: '+jqXHR);}
				        });  
					});
					marker.enableDragging();// 可拖拽
					marker.addEventListener("click",overlay_style);// 添加对应的事件
					marker.addEventListener("dragend",infoBox_show);// 添加拖拽事件,显示信息
				}
				
			}
		];
		
		for(var i=0; i < txtMenuItem.length; i++){
			menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,100));
		}
		
		map.addContextMenu(menu);
		
		//获取marker的属性
		function overlay_style(e){
			var p = e.target;
			if(p instanceof BMap.Marker){
				var point = new BMap.Point(p.getPosition().lng ,p.getPosition().lat);// 拖拽位置的坐标
				var marker = new BMap.Marker(point);
				infoBox.open(marker);
			}else{
				alert("无法获知该覆盖物类型");
			}	
		}
		
		function infoBox_show(e){
			var point = new BMap.Point(e.point.lng ,e.point.lat);// 拖拽位置的坐标
			var marker = new BMap.Marker(point);
			infoBox.open(marker);
		}

		
		// 删除功能
		/* var removeMarker = function(e,ee,marker){
				map.removeOverlay(marker);
		} */
		
		// 创建右键菜单
		/* var markerMenu=new BMap.ContextMenu();
		
		markerMenu.addItem(new BMap.MenuItem('删除',removeMarker.bind(marker)));
		
		map.addOverlay(marker);
		marker.addContextMenu(markerMenu); */
		
		// 添加点击事件
		/* map.addEventListener("click", function(e){
			
			if(iscreatr==true)
				return;

			//-------遮盖物(标注)-------
			//var point = new BMap.Point(e.point.lng ,e.point.lat);// 点击位置的坐标
			//var marker = new BMap.Marker(point);// 创建标注
			//map.addOverlay(marker);// 将标注添加到地图中
			//marker.addEventListener("click",overlay_style);// 添加对应的事件
			
			//marker.enableDragging();// 可拖拽
			
			//infoBox.open(marker);
			
			//获取marker的属性
			/* function overlay_style(e){
				var p = e.target;
				if(p instanceof BMap.Marker){
					infoBox.open(marker);
					alert("该覆盖物是点，点的坐标是：" + p.getPosition().lng + "," + p.getPosition().lat);
				}else{
					alert("无法获知该覆盖物类型");
				}	
			} */
			
			// 添加拖拽事件
			/* marker.addEventListener("dragend", function(e){
				var point = new BMap.Point(e.point.lng ,e.point.lat);// 拖拽位置的坐标
				var marker = new BMap.Marker(point);
				infoBox.open(marker);
			}); */
			
		/* }); */
		
		// 定义并添加控件
		var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
		var top_left_navigation = new BMap.NavigationControl();// 左上角，添加默认缩放平移控件
		map.addControl(top_left_control);
		map.addControl(top_left_navigation);
		
		// 加载已标注的报警器结点
		/*var str = '[{"uname":"王强","day":"2010/06/17"},{"uname":"王海云","day":"2010/06/11"}]';
		var jsonList=eval("("+str+")");
			for(var i=0;i<1;i++){

				//for(var key in jsonList[i]){
					var point = new BMap.Point(113.958028,22.636338);// 点击位置的坐标
					var marker = new BMap.Marker(point);// 创建标注
					map.addOverlay(marker);// 将标注添加到地图中
				//} 
			}  // 加载已标注点结束
*/	}   // end initiallize mothed
	
	function loadScript() {
		var script = document.createElement("script");
		script.src = "http://api.map.baidu.com/api?v=1.2&callback=initialize";
		document.body.appendChild(script);
		
	}
