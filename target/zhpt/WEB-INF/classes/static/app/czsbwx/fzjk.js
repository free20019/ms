var fzjk=(function ($) {
	var fzjkmap;
	//所有车辆数
	var clydqvehilist;
	//车辆经纬度数组
	var vhicsz=new Array();
	$(function () {
//		fzjkcx('');
		/*生成表格数据*/
		var clTabel = null;
		clTabel = new CheckBox('#treetable');
		findtree();
		$('.scrollbar-macosx').scrollbar();
		$('.searchBar input[type=text]').on({
			focus: function () {
				$(this).parent().attr('state', 'focus');
			},
			blur: function () {
				$(this).parent().removeAttr('state')
			}
		});
		var position = new AMap.LngLat(120.16378, 30.25840);//创建中心点坐标
		fzjkmap = new AMap.Map('fzjkMap', {
			center : position,
			level : 15,
			resizeEnable : true
		});
		fzjkmap.plugin( [ "AMap.ToolBar", "AMap.OverView", "AMap.Scale" ],
				function() {
					//加载工具条
				tool = new AMap.ToolBar( {
					direction : false,//隐藏方向导航
					ruler : false,//隐藏视野级别控制尺
					autoPosition : false
				//禁止自动定位
						});
				fzjkmap.addControl(tool);
				//加载鹰眼
				view = new AMap.OverView();
				fzjkmap.addControl(view);
				//加载比例尺
				scale = new AMap.Scale();
				fzjkmap.addControl(scale);
				fzjkmap.plugin( [ "AMap.MapType" ], function() {
					var type = new AMap.MapType( {
						defaultType : 0
					});//初始状态使用2D地图
					fzjkmap.addControl(type);
					});
			});
		//添加车辆组
		function zTreeOnDblClick(event, treeId, treeNode){
			if(event!=null){
            	if(!treeNode.isParent){
            		alert("子节点无法操作");
            		return false;
            	}
			}
			$('#qd-modal').modal('show');
			$('#tree-save').html('添加');
			$("#treetbody").html('');
			$('#tree_name').val('');
			for(var i=0;i<clydqvehilist.length;i++){
				$("#treetbody").append("<tr><td style='width:340px'>"+clydqvehilist[i].VEHI_NO+"</td><td><input type='checkbox'  class='checkbox' name='treebox' value='"+clydqvehilist[i].VEHI_NO+"'/></td></tr>");
			}
			//提交
        	$('#tree-save').off('click').on('click', function () {
        		if($('#tree_name').val()==''){
					layer.msg('名称必须填写！');
					return false;
				}
        		var veh_array=new Array();  
        		$('input[name="treebox"]:checked').each(function(){  
        			veh_array.push($(this).val());//向数组中添加元素  
        		}); 
        		var vehstr=veh_array.join(';');//将数组元素连接起来以构建一个字符串  
        		if(vehstr==''){
					layer.msg('车辆必须添加！');
					return false;
				}
    			jqxhr=$.ajax({
    				type: "POST",
    	 	        url:"../../sbwx/addtree",
    	 	        data:{
    	 	        	"tree_name" : $('#tree_name').val(),
    	 	        	"vehstr" : vehstr
    	 	        },
    	 	        dataType: 'json',
    	 			timeout : 3600000,
					success:function(data){
						if(data.msg=='success'){
							$("#qd-modal").modal('hide');
							layer.msg('添加成功');
							findtree();
						}else if(data.msg=='数据异常'){
							layer.msg('添加失败');
						}else if(data.msg=='参数校验异常异常:%s'){
							layer.msg('添加车辆过多或分组名称重复');
						}
					},
					error: function(){
					}         
				});
        	})
		}
		function findtree(){
  			jqxhr=$.ajax({
				type: "POST",
	 	        url:"../../sbwx/findtree",
	 	        data:{},
	 	        dataType: 'json',
	 			timeout : 3600000,
				success:function(data){
					if(""==data){
						layer.msg('没有分组');
					}else{
						var setting = {
	                            edit: {
	                                enable: true,
	                                showRemoveBtn: false,
	                                showRenameBtn: false
	                            },
	                            check: {
	                                enable: false
	                            },
	                            data: {
	                                simpleData: {
	                                    enable: true
	                                }
	                            },
	                            callback:{  
	                            	 onRightClick: OnRightClick,
	                            }, 
	                        };
	                        var treeObj,
	                            zTreeNodes = data;
	                        treeObj = $.fn.zTree.init($("#vehi_list2"), setting, zTreeNodes);
					}
				},
				error: function(){
				}         
			});
		}

		function OnRightClick(event, treeId, treeNode){
			if(event!=null){
				 var zTree = $.fn.zTree.getZTreeObj("vehi_list2");
				if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
	                zTree.cancelSelectedNode();
	            } else if (treeNode && !treeNode.noR) {
	            	if(!treeNode.isParent){
	            		alert("子节点无法操作");
	            		return false;
	            	}
	                zTree.selectNode(treeNode);
	                showRMenu("node", event.offsetX, event.offsetY);
	            }
			}
		} 
		function showRMenu(type, x, y) {
            $("#rMenu ul").show();
            $("#rMenu").css({"top":y+"px", "left":(x+70)+"px", "visibility":"visible"}); //设置右键菜单的位置、可见
            $("body").bind("mousedown", onBodyMouseDown);
        }
        //隐藏右鍵
        function hideRMenu() {
        	var zTree = $.fn.zTree.getZTreeObj("vehi_list2");
            if (zTree) $("#rMenu").css({ "visibility": "hidden" });
            $("body").unbind("mousedown", onBodyMouseDown);
        }
        function onBodyMouseDown(event) {
        	if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
                $("#rMenu").css({ "visibility": "hidden" });    
            }
        }
        //删除树
        $('#removeTree').off('click').on('click',function(){
        	removeTree();
		})
        function removeTree(){
        	var treeObj = $.fn.zTree.getZTreeObj("vehi_list2");
        	var nodes = treeObj.getSelectedNodes();
        	if(nodes && nodes.length > 0){
				layer.confirm('你确定要删除选中的数据', {
					btn: ['删除', '取消'],
					offset: '100px'
				}, function (layerIndex) {
	        		jqxhr=$.ajax({
		     		type: "POST",
		 	        url:"../../sbwx/removeTree",
		 	        data:{
		 	        	"id" : nodes[0].id
		 	        },
		 	        dataType: 'json',
		 			timeout : 3600000,
		 			success:function(data){
						if(data.msg=='success'){
							$("#qd-modal").modal('hide');
							layer.msg('删除成功');
							hideRMenu();
							findtree();
						}else if(data.msg=='数据异常'){
							layer.msg('删除失败');
						}
		 			}
	        		})
				}, function (layerIndex) {
					layer.close(layerIndex);
				});
        	}else{
        		alert("先选中元素");
        	}
        }
        //修改树
        $('#editTree').on('click',function(){
        	editTree();
		})
        function editTree(){
        	var treeObj = $.fn.zTree.getZTreeObj("vehi_list2");
        	var nodes = treeObj.getSelectedNodes();
        	if(nodes && nodes.length > 0){
        		$('#qd-modal').modal('show');
        		$('#tree-save').html('修改');
        		//回显
        		$('#tree_name').val(nodes[0].name);
        		var obj=nodes[0].children;
        		$("#treetbody").html('');
        		var tree=new Array();  
        		for(var i=0;i<obj.length;i++){        			
        			$("#treetbody").append("<tr><td style='width:340px'>"+obj[i].name+"</td><td><input type='checkbox'  class='checkbox' name='treebox' value='"+obj[i].name+"' checked/></td></tr>");
        			tree.push(obj[i].name);
        		}
        		if(clydqvehilist!=null){			
        			for(var j=0;j<clydqvehilist.length;j++){
        				if(tree.indexOf(clydqvehilist[j].VEHI_NO)== -1)
        					$("#treetbody").append("<tr><td style='width:340px'>"+clydqvehilist[j].VEHI_NO+"</td><td><input type='checkbox' class='checkbox' name='treebox' value='"+clydqvehilist[j].VEHI_NO+"'/></td></tr>");
        			}
        		}
    			//提交
            	$('#tree-save').off('click').on('click', function () {
	        		//修改内容
	        		if($('#tree_name').val()==''){
						layer.msg('名称必须填写！');
						return false;
					}
	        		var veh_array=new Array();  
	        		$('input[name="treebox"]:checked').each(function(){  
	        			veh_array.push($(this).val());//向数组中添加元素  
	        		}); 
	        		var vehstr=veh_array.join(';');//将数组元素连接起来以构建一个字符串  
	        		if(vehstr==''){
						layer.msg('车辆必须添加！');
						return false;
					}
	        		jqxhr=$.ajax({
		     		type: "POST",
		 	        url:"../../sbwx/editTree",
		 	        data:{
		 	        	"id" : nodes[0].id,
		 	        	"tree_name" : $('#tree_name').val(),
    	 	        	"vehstr" : vehstr
		 	        },
		 	        dataType: 'json',
		 			timeout : 3600000,
		 			success:function(data){
						if(data.msg=='success'){
							$("#qd-modal").modal('hide');
							layer.msg('修改成功');
							hideRMenu();
							findtree();
						}else if(data.msg=='数据异常'){
							layer.msg('修改失败');
						}else if(data.msg=='参数校验异常异常:%s'){
							layer.msg('添加车辆过多或分组名称重复');
						}
		 			}
	        		})
            	})
        	}else{
        		alert("先选中元素");
        	}
        }
        //树查询车辆信息
        $('#Treefind').off('click').on('click',function(){
        	Treefind();
		})
        function Treefind(){
        	var treeObj = $.fn.zTree.getZTreeObj("vehi_list2");
        	var nodes = treeObj.getSelectedNodes();
        	if(nodes && nodes.length > 0){
        		var obj=nodes[0].children;
        		var veh_array=new Array();  
        		for(var i=0;i<obj.length;i++){        			
        			veh_array.push("'"+obj[i].name+"'");//向数组中添加元素  
        		}
        		var vehstr=veh_array.join(',');//将数组元素连接起来以构建一个字符串  
        		fzjkcx(vehstr);
        	}else{
        		alert("先选中元素");
        	}
        }
		//查询车辆
		$('#fzjkcx').on('click',function(){
			fzjkcx('');
		})
		//初始化tree
		function initTree(obj){
			var dominZTree=[];
			dominZTree.push({"id":"1","name":"车辆","pId":null});
			for(var i=0;i<obj.length;i++){
				var str='{"id":"100000'+i+'","name":"'+obj[i].VEHI_NO+'","pId":"1"}';
				var jsonstr=JSON.parse(str);
				dominZTree.push(jsonstr);
			}
			var setting = {
		        check:{
//		        	chkboxType : {"Y": "ps", "N": "ps"},
//		            chkStyle: "checkbox",//复选框类型
		            enable: false //每个节点上是否显示 CheckBox
		        },
		        edit:{
		        	enable: true,
                    showRemoveBtn: false,
                    showRenameBtn: false
		        },
		        data: {
		            simpleData: {//简单数据模式
		                enable:true,
		                idKey: "id",
		                pIdKey: "pId",
		                rootPId: null
		            }
		        },
			        callback: {
			        	onDblClick: zTreeOnDblClick
			        }
		    };
			zTreeObj = $.fn.zTree.init($("#vehi_list"), setting, dominZTree);
		}
		function fzjkcx(obj){
			markers=[];
			jqxhr=$.ajax({
	     		type: "POST",
	 	        url:"../../sbwx/findfzjk",
	 	        data:{
	 	        	"cphm" : $('#cphm_value').val(),
	 	        	"status" : $('#status').val(),
	 	        	'vehstr':obj
	 	        },
	 	        dataType: 'json',
	 			timeout : 3600000,
	 			success:function(data){
	 				clydqvehilist = data;	
	 				initTree(clydqvehilist);
	 				var count1=0;
	 				var count2=0;
	 				var count3=0;
	 				var count4=0;
	 				var count5=0;
	 				var count6=0;
	 				var count7=0;
	 				var count8=0;
	 				var count9=0;
	 				var amount=0;
	 				for(var z=0;z<data.length;z++){
	 					var count10=0;
	 					var obj=data[z];
	 					//主机故障
	 					if(obj.LOW_VOLTAGE!='0'||obj.NO_POWER!='0'||obj.NO_GPS!='0'||obj.NO_UPLOAD!='0'){
	 						count1 ++;
	 						count10++;
	 					}
	 					//定位故障
	 					if(obj.MOD_FAULT!='0'||obj.ANT_FAULT!='0'||obj.INEXACT!='0'){
	 						count2 ++;count10++;
	 					}
	 					//通信故障
	 					if(obj.COMM_FAULT!='0'){
	 						count3 ++;count10++;
	 					}
	 					//定位回传故障
	 					if(obj.GPS_OVER_BACK!='0'||obj.GPS_NO_BACK!='0'){
	 						count4 ++;count10++;
	 					}
	 					//摄像头故障
	 					if(obj.CAM_OCCLUSION!='0'||obj.CAM_NOSIGN!='0'){
	 						count5 ++;count10++;
	 					}
	 					//视频主机/存储故障
	 					if(obj.HD_FAULT!='0'||obj.SD_FAULT!='0'||obj.VD_FAULT!='0'||obj.VD_EX_FAULT!='0'){
	 						count6 ++;count10++;
	 					}
	 					//计价器断开故障
	 					if(obj.METER_DISCONN!='0'){
	 						count7 ++;count10++;
	 					}
	 					//导航屏断开故障
	 					if(obj.NAV_DISCONN!='0'){
	 						count8 ++;count10++;
	 					}
	 					//空车灯故障
	 					if(obj.ST_NO_CHG!='0'||obj.ST_OVER_CHG!='0'){
	 						count9 ++;count10++;
	 					}
	 					if(count10>1){
	 						amount++;
	 					}
	 				}
	 				$('#total').html(data.length);
	 				$('#num1').html(count1);
	 				$('#num2').html(count2);
	 				$('#num3').html(count3);
	 				$('#num4').html(count4);
	 				$('#num5').html(count5);
	 				$('#num6').html(count6);
	 				$('#num7').html(count7);
	 				$('#num8').html(count8);
	 				$('#num9').html(count9);
	 				$('#num').html(amount);
	 				for(var j=0;j<data.length;j++){ 	
	 					addmks(data[j]);
	 					if(j==data.length-1){
	 						addCluster(1);
	 					}
	 				}
	 			}
			});	
		}
		//将查询出的所有车辆放入地图
		
		var markers= [];
		function addmks(obj){
			if(obj.hasOwnProperty('PX')&&obj.hasOwnProperty('PY')){		
				var count=0;
				var markerContent = document.createElement("div");
				markerContent.className = "markerContentStyle";
				//点标记中的图标
				var markerImg= document.createElement("img");
				markerImg.className="markerlnglat";
				//主机故障
				if(obj.LOW_VOLTAGE!='0'||obj.NO_POWER!='0'||obj.NO_GPS!='0'||obj.NO_UPLOAD!='0'){
					count ++;
					markerImg.src="../../resources/images/401.png";
				}
				//定位故障
				if(obj.MOD_FAULT!='0'||obj.ANT_FAULT!='0'||obj.INEXACT!='0'){
					count ++;
					markerImg.src="../../resources/images/402.png";
				}
				//通信故障
				if(obj.COMM_FAULT!='0'){
					count ++;
					markerImg.src="../../resources/images/403.png";
				}
				//定位回传故障
				if(obj.GPS_OVER_BACK!='0'||obj.GPS_NO_BACK!='0'){
					count ++;
					markerImg.src="../../resources/images/404.png";
				}
				//摄像头故障
				if(obj.CAM_OCCLUSION!='0'||obj.CAM_NOSIGN!='0'){
					count ++;
					markerImg.src="../../resources/images/405.png";
				}
				//视频主机/存储故障
				if(obj.HD_FAULT!='0'||obj.SD_FAULT!='0'||obj.VD_FAULT!='0'||obj.VD_EX_FAULT!='0'){
					count ++;
					markerImg.src="../../resources/images/406.png";
				}
				//计价器断开故障
				if(obj.METER_DISCONN!='0'){
					count ++;
					markerImg.src="../../resources/images/407.png";
				}
				//导航屏断开故障
				if(obj.NAV_DISCONN!='0'){
					count ++;
					markerImg.src="../../resources/images/408.png";
				}
				//空车灯故障
				if(obj.ST_NO_CHG!='0'||obj.ST_OVER_CHG!='0'){
					count ++;
					markerImg.src="../../resources/images/409.png";
				}
				if(count>1){
					markerImg.src="../../resources/images/410.png";
				}
				markerContent.appendChild(markerImg);
				var marker1 = new AMap.Marker({
					//   map:mapObjhistory,
					position:new AMap.LngLat(obj.PX,obj.PY),     
					offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
					draggable:false,  //是否可拖动
					content:markerContent   //自定义点标记覆盖物内容
				});
				marker1.setMap(fzjkmap);  //在地图上添加点			
				markers.push(marker1);
				AMap.event.addListener(marker1,"click",function(e){  
					vhicmarker(obj.VEHI_NO);
				});
			}
		}
		//点击车辆图标,在地图上显示该车辆的当前信息
		function vhicmarker(obj){
			jqxhr=$.ajax({
				 type: "POST",
			        url:"../../sbwx/vhicmarker",
			        data:{
						vehi_no : obj
			        },
			       dataType: 'json',
					timeout : 180000,
				success:function(json){
			        	var cust=json;
			        	for(var i=0;i<cust.length;i++){
			        		addMarker(cust[0]);
			        	}
				},
				error:function(){
					
				}		
			});
		}
		//将单个车辆的位置信息显示在地图上
		var marker="";
		function addMarker(vehicle){
			//每次加载将上次地图添加的点删除
			if(marker!=""){
				marker.setMap(null);
			}
			var markerContent = document.createElement("div");
		    markerContent.className = "txtstyle";
		    //点标记中的图标
		    var markerImg= document.createElement("img");
			markerImg.className="markerlnglat";
			var count=0;
			var gz='';
			//主机故障
			if(vehicle.LOW_VOLTAGE!='0'||vehicle.NO_POWER!='0'||vehicle.NO_GPS!='0'||vehicle.NO_UPLOAD!='0'){
				count ++;
				gz +='主机故障(';
				if(vehicle.LOW_VOLTAGE!='0'){
					gz +='终端主电源欠压,';
				}
				if(vehicle.NO_POWER!='0'){
					gz +='主电源掉电,';
				}
				if(vehicle.NO_GPS!='0'){
					gz +='无定位数据,';
				}
				if(vehicle.NO_UPLOAD!='0'){
					gz +='无数据上传,';
				}
				gz +='),';
				markerImg.src="../../resources/images/c.png";
			}
			//定位故障
			if(vehicle.MOD_FAULT!='0'||vehicle.ANT_FAULT!='0'||vehicle.INEXACT!='0'){
				count ++;
				gz +='定位故障(';
				if(vehicle.MOD_FAULT!='0'){
					gz +='定位模块故障,';
				}
				if(vehicle.ANT_FAULT!='0'){
					gz +='天线短路,';
				}
				if(vehicle.INEXACT!='0'){
					gz +='非精确定位,';
				}
				gz +='),';
				markerImg.src="../../resources/images/c.png";
			}
			//通信故障
			if(vehicle.COMM_FAULT!='0'){
				count ++;
				gz +='通信故障(';
				gz +='通讯故障';
				gz +='),';
				markerImg.src="../../resources/images/c.png";
			}
			//定位回传故障
			if(vehicle.GPS_OVER_BACK!='0'||vehicle.GPS_NO_BACK!='0'){
				count ++;
				gz +='定位回传故障(';
				if(vehicle.GPS_OVER_BACK!='0'){
					gz +='定位回传过密,';
				}
				if(vehicle.GPS_NO_BACK!='0'){
					gz +='回传数据丢失,';
				}
				gz +='),';
				markerImg.src="../../resources/images/c.png";
			}
			//摄像头故障
			if(vehicle.CAM_OCCLUSION!='0'||vehicle.CAM_NOSIGN!='0'){
				count ++;
				gz +='摄像头故障(';
				if(vehicle.CAM_OCCLUSION!='0'){
					gz +='摄像头遮挡,';
				}
				if(vehicle.CAM_NOSIGN!='0'){
					gz +='摄像头信号丢失,';
				}
				gz +='),';
				markerImg.src="../../resources/images/c.png";
			}
			//视频主机/存储故障
			if(vehicle.HD_FAULT!='0'||vehicle.SD_FAULT!='0'||vehicle.VD_FAULT!='0'||vehicle.VD_EX_FAULT!='0'){
				count ++;
				gz +='视频主机/存储故障(';
				if(vehicle.HD_FAULT!='0'){
					gz +='硬盘故障,';
				}
				if(vehicle.SD_FAULT!='0'){
					gz +='SD卡故障,';
				}
				if(vehicle.VD_FAULT!='0'){
					gz +='视频主机故障,';
				}
				if(vehicle.VD_EX_FAULT!='0'){
					gz +='视频扩展故障,';
				}
				gz +='),';
				
				markerImg.src="../../resources/images/c.png";
			}
			//计价器断开故障
			if(vehicle.METER_DISCONN!='0'){
				count ++;
				gz +='计价器断开故障(';
				gz +='计价器连接断开';
				gz +='),';
				markerImg.src="../../resources/images/c.png";
			}
			//导航屏断开故障
			if(vehicle.NAV_DISCONN!='0'){
				count ++;
				gz +='导航屏断开故障(';
				gz +='导航屏断开';
				gz +='),';
				markerImg.src="../../resources/images/c.png";
			}
			//空车灯故障
			if(vehicle.ST_NO_CHG!='0'||vehicle.ST_OVER_CHG!='0'){
				count ++;
				gz +='空车灯故障(';
				if(vehicle.ST_NO_CHG!='0'){
					gz +='空重车不变化,';
				}
				if(vehicle.ST_OVER_CHG!='0'){
					gz +='空重车切换频繁,';
				}
				gz +='),';
				markerImg.src="../../resources/images/c.png";
			}
			if(count>1){
				markerImg.src="../../resources/images/h.png";
			}
			markerContent.appendChild(markerImg);
			marker = new AMap.Marker({
			    map:fzjkmap,
			    position:new AMap.LngLat(vehicle.PX,vehicle.PY),     
			    offset:new AMap.Pixel(-0,-0), //相对于基点的偏移位置
			    draggable:false,  //是否可拖动
			    content:markerContent   //自定义点标记覆盖物内容
			});
			fzjkmap.setCenter(new AMap.LngLat(vehicle.PX,vehicle.PY));
			var txt = "<table><tr><td><b style='color:#3399FF'>"+vehicle.VEHI_NO+"</b></td>" +
			"<td></td></tr><tr><td><b>[所属业户]</b>："+vehicle.COMP_NAME+"</td></tr>" +
			"<tr><td><b>[车辆商标]</b>："+vehicle.VT_NAME+"</td></tr>" +
			"<tr><td><b>[车辆颜色]</b>："+vehicle.VC_NAME+"</td></tr>" +
			"<tr><td><b>[SIM卡]</b>："+vehicle.SIM_NUM+"</td></tr>" +
			"<tr><td><b>[车辆所属人]</b>："+(vehicle.OWN_NAME==null?"":vehicle.OWN_NAME)+"</td></tr>" +
			"<tr><td><b>[联系电话]</b>："+(vehicle.OWN_TEL==null?"":vehicle.OWN_TEL)+"</td></tr>" +
			"<tr><td><b>[经度]</b>："+vehicle.PX+"</td></tr><tr><td><b>[纬度]</b>："+vehicle.PY+"</td></tr>" +
			"<tr><td><b>[故障类型]</b>："+gz+"</td></tr>" +
			"<tr><td><b>[故障最后上传时间]</b>："+(vehicle.DBTIME==null?"":(new Date(vehicle.DBTIME)).Format("yyyy-MM-dd"))+"</td></tr>" +
//			"<tr><td><b>[维修人员]</b>："+(vehicle.WXRY==null?"":vehicle.WXRY)+"</td></tr>" +
//			"<tr><td><b>[维修地点]</b>："+(vehicle.RA_ADDR==null?"":vehicle.RA_ADDR)+"</td></tr>" +
//			"<tr><td><b>[维修类型]</b>："+(vehicle.RT_TYPE==null?"":vehicle.RT_TYPE)+"</td></tr>" +
			"<tr><td><b>[经纬度上传时间]</b>："+(vehicle.STIME==null?"":(new Date(vehicle.STIME)).Format("yyyy-MM-dd hh:mm:ss"))+"</td></tr>" +
//			"<tr><td><b>[维修完成时间]</b>："+(vehicle.RR_TIME_END==null?"":(new Date(vehicle.RR_TIME_END)).Format("yyyy-MM-dd hh:mm:ss"))+"</td></tr>" +
			"";
			var info = [];
			info.push(txt);
			               
			var inforWindowone = new AMap.InfoWindow({                 
			  offset:new AMap.Pixel(5,10),                 
			  content:info.join("</table>")                 
			});           
			  inforWindowone.open(fzjkmap,marker.getPosition());                 
			  AMap.event.addListener(marker,"click",function(e){                 
				  inforWindowone.open(fzjkmap,marker.getPosition());                 
				});
			  
		}
		//点聚合
		var cluster;
		function addCluster(tag){
			if(cluster) {
				cluster.setMap(null);
			}
			fzjkmap.clearMap();
			if(tag==1) {
				var sts=[{url:"../../resources/images/12.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
						{url:"../../resources/images/11.png", size:new AMap.Size(32,32),offset:new AMap.Pixel(-16,-30)},
						{url:"../../resources/images/13.jpg", size:new AMap.Size(48,48),offset:new AMap.Pixel(-24,-45),textColor:'#CC0066'}];
				fzjkmap.plugin(["AMap.MarkerClusterer"],function(){
					cluster = new AMap.MarkerClusterer(fzjkmap,markers,{minClusterSize:10,styles:sts});
				});
			}else {
				fzjkmap.plugin(["AMap.MarkerClusterer"],function(){
					cluster = new AMap.MarkerClusterer(fzjkmap,markers);
				});
			}
		}
		//比较查询出的时间和当前时间
		function comparetime(time){
			var today;
			if(time==null){
				today="";
			}else{				
				today=(new Date(time)).Format("yyyy-MM-dd");
			}
			var date = new Date();
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			if (month < 10) {
			    month = "0" + month;
			}
			if (day < 10) {
			    day = "0" + day;
			}
			var nowDate = year + "-" + month + "-" + day;
			return (nowDate>today?1:0);
		}
		//绑定-跟踪 跳转
		function jump(){
			//跟踪
			$('.gz').on('click',function(){
				maker_vehi($($(this).prev()).html());
			});
		}
		
		//跟踪-定位
		function maker_vehi(c){
			for(var i=0;i<clydqvehilist.length;i++){
				if(clydqvehilist[i] != undefined && clydqvehilist[i].VEHI_NO == c){
					var obj = clydqvehilist[i];
					addMarker(obj);
				}
			}
		}
	})
})(jQuery)
function findjgtz(tel){
	var _parent = parent.$(window.parent.document);
	if (_parent.find('.ip-tabBarItem[data-name="sjyddcx"]').length > 0) {
		_parent.find('.ip-tabBarItem[data-name="sjyddcx"]').trigger('click');
		_parent.find('#sjyddcx').get(0).contentWindow.location.reload(true);
	}
	_parent.find('.ip-menuItem [data-skip="sjyddcx"]').trigger("click");
	_parent.find('#defaultTel').val(tel);
}
