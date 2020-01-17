(function (Vue, _, $) {
		var vm = new Vue({
			el: '#root',
			data: function () {
				return {
					ydwwyyArr: [],
					yyywdwArr: [],
					yzpwdwwyyArr: [],
					sevenwdwwyyArr: [],
					wkcwbhArr: [],
					dggzArr: [],
					arr: [],
					ydwwyyList: [],
					yyywdwList: [],
					yzpwdwwyyList: [],
					sevenwdwwyyList: [],
					wkcwbhList: [],
					dggzList: [],
					excelList: [],
					indexList: [],
					type: 1,
					visibleLength: 0,
					currentPage: 0,
					queryLicensePlate: '',
					vehicleList: [],
					wxclList: [],
					tableFields: [
					    {name: 'gId', title: '序号', width: 60, align: 'center'},
						{name: 'cphm', title: '车牌号码', width: 140, align: 'center'},
						{name: 'cplx', title: '车牌类型', width: 80, align: 'center'},
						{name: 'cz', title: '车主', width: 80, align: 'center'},
						{name: 'sim', title: 'SIM卡', width: 80, align: 'center'},
						{name: 'lxdh', title: '联系电话', width: 80, align: 'center'},
//						{name: 'ztai', title: '状态', width: 80, align: 'center'},
//						{name: 'kozc', title: '空重车', width: 80, align: 'center'},
						{name: 'gzlx', title: '故障类型', width: 80, align: 'center'},
						{name: 'gzsj', title: '故障时间', width: 80, align: 'center'}
					],
					//高德地图海量点ui
					PointSimplifier:null,
					pointSimplifierIns:null,
					groupStyleMap:{},
					id:0,
					queryServiceType: '',
					serviceTypeOption: [
						{id: 0, text: '全部'},
						{id: 1, text: '正在维修'},
						{id: 2, text: '未维修'}
					],
					/*树形组件*/
					realTimeMonitorTreeLoading: false,
					realTimeMonitorTree: [
						{
							label: '一级 1', type: 'group', children: [
								{label: '二级 1-1'}
							]
						},
						{
							label: '一级 2', type: 'group', children: [
								{label: '二级 2-1'},
								{label: '二级 2-2'}
							]
						}
					],
					realTimeMonitorTreeKeys:[],
					realTimeMonitorTreeExpanded: [],
					realTimeMonitorTreeChecked:[],
					realTimeMonitorMapLoading:false,
					realTimeMonitorTreeLoading: false,
					rightClickMenuIsDisplay: false,
					rightClickMenu: {
						display: false,
						top: 0,
						left: 0,
						data: []
					},
					rightClickActiveMenuItem: {},
					map: null,
					mapMarkers: [],
					mapMarker: '',
					geocoder:null,
					address:'',
					mapCluster: null,
					faultStatistics: {
						total: 0,
						ydwwyy: 0,
						yyywdw: 0,
						yzpwdwwyy: 0,
						sevenwdwwyy: 0,
						wkcwbh: 0,
						dggz: 0
					},
					dialog: {
						title: '添加组',
						form: {
							clzmc: '',
							clzxx: []
						}
					},
					transfer: {
						data: [],
						length: 20
					}
				}
			},
			mounted: function () {			
				$('#errorTime').text("(更新时间："+new Date(new Date()-1000 * 60 * 60 * 24).Format('yyyy年MM月dd日')+")");
				var _this = this;
				this.$nextTick(function () {
					$('.scrollbar-macosx').scrollbar();
					titlePanelScaling('#root', {width: 530});
					$('#wxcljkTable').jsGrid({
						width: '100%',
						height: 'calc(100% - 47px)',
						editing: true,
						sorting: true,
						paging: false,
						autoload: true,
						rowClick:this.handleJumpVehicleClick,
						fields: _this.tableFields
					});
					$('#wxcljkDialog').on('hide.bs.modal', function (e) {
						_this.dialog.form.clzmc = '';
						_this.dialog.form.clzxx= [];
					})
					$(window).resize(function () {
						_this.vehicleListVisibleLength();
					})
					var position = new AMap.LngLat(120.16378, 30.25840);//创建中心点坐标
					_this.map = new AMap.Map('wxcljkMap', {
						center : position,
						level : 15,
						resizeEnable : true
					});
					_this.vehicleListVisibleLength();
					_this.initMapPlugin();
					_this.getVehicleList();
					_this.findtree();
					/* --注意注意 每次获取tree数据时 要执行这行代码---------------------- */
					_this.initTrueExpanded();
				});
			},
			computed: {
				/* 查询车辆 */
//				findVehile: function () {
//					var input = this.queryLicensePlate;
//					if (input.length < 3) return false;
////					return _.filter(this.wxclList, function (item) {
////						if (!item || !input || item.VEHI_NO.indexOf(input) === -1) return false;
////						return true
////					});
//				},
				fandVehileLength: function (){
					var length = this.transfer.length;
					return _.filter(this.transfer.data, function (item, index) {
						if (index>=length) return false;
						return true
					});
				},
				/* 总页数 */
				vehicleListPage: function () {
					return Math.ceil(this.findVehile.length / this.visibleLength)
				},
				/* 可显示的页数 */
				currentDisplayPages: function () {
					return this.currentPage * this.visibleLength - 1;
				},
				/* 判断加载按钮是否启用 */
				hasDisabledLoadMoreButton: function () {
					var allPage = this.vehicleListPage;
					if (!allPage) return true;
					return (this.currentPage >= this.vehicleListPage);
				}
			},
			methods: {
				findVehile: function () {
					var _this=this;
					var input = _this.queryLicensePlate;
					if (input.length < 1) _this.getVehicleDetails(_this.indexList);
					_this.excelList=_.filter(_this.indexList, function (item) {
						if (item && input && item.VEHI_NO.indexOf(input) > -1) 
						return true
					});
					_this.getVehicleDetails(_this.excelList);
				},
				regeoCode: function (lnglat) {
					var _this=this;
					_this.address='';
			        if(!_this.geocoder){
			        	_this.geocoder = new AMap.Geocoder({
			                city: "010", //城市设为北京，默认：“全国”
			                radius: 1000 //范围，默认：500
			            });
			        }
			        _this.geocoder.getAddress(lnglat, function(status, result) {
			            if (status === 'complete'&&result.regeocode) {
			            	_this.address = result.regeocode.formattedAddress;
			            	document.getElementById('aaa').innerText=_this.address; 
			            }else{
//			            	alert(JSON.stringify(result))   	
			            }
			        });
			    },
				/* 一页可现实多少条内容 */
				vehicleListVisibleLength: function () {
					var _this = this;
					var vehicleListHeight = $(_this.$refs.vehicleInfoList).height();
					this.visibleLength = Math.floor(vehicleListHeight / 38);

				},
				getVehicleList: function () {
					var _this = this;
					_this.realTimeMonitorMapLoading=true;
					_this.jqxhr=$.ajax('../../sbwx/vehicle',{
						type: 'GET',
						dataType: 'json'
					}).then(function (res) {
						_this.vehicle(res);
					})
				},
				vehicle: function (res){
					var _this = this;
					_this.wxclList=[];
					_this.vehicleList = res;
					_this.resetFaultStatistics();
					_.each(res, function (item, index) {
						var s = {};
						var flag = 0;
	 					if(item.hasOwnProperty('PX')&&item.hasOwnProperty('PY')){	
							//有定位无营运
							if (_this.hasYdwwyy(item)) {
								_this.faultStatistics.ydwwyy++;
								flag++;
								s.groupId = 1;
								_this.ydwwyyArr.push(s);
								_this.ydwwyyList.push(item);
							}
							//有营运无定位
							if (_this.hasYyywdw(item)) {
								_this.faultStatistics.yyywdw++;
								flag++;
								s.groupId = 2;
								_this.yyywdwArr.push(s);
								_this.yyywdwList.push(item);
							}
							//有抓拍无定位无营运
							if (_this.hasYzpwdwwyy(item)) {
								_this.faultStatistics.yzpwdwwyy++;
								flag++;
								s.groupId = 3;
								_this.yzpwdwwyyArr.push(s);
								_this.yzpwdwwyyList.push(item);
							}
							//7天无定位无营运
							if (_this.hasSevenwdwwyy(item)) {
								_this.faultStatistics.sevenwdwwyy++;
								flag++;
								s.groupId = 4;
								_this.sevenwdwwyyArr.push(s);
								_this.sevenwdwwyyList.push(item);
							}
							//空重车无变化
							if (_this.hasWkcwbh(item)) {
								_this.faultStatistics.wkcwbh++;
								flag++;
								s.groupId = 5;
								_this.wkcwbhArr.push(s);
								_this.wkcwbhList.push(item);
							}
							// 多车故障
							if(flag >= 1) {
								_this.wxclList.push(item);
		 						s.lnglat = [item.PX,item.PY];
								s.name = item.VEHI_NO;	
								if(flag>1){										
									_this.faultStatistics.dggz++;
									s.groupId = 10;
									_this.dggzArr.push(s);
									_this.dggzList.push(item);
									_this.arr.push(s);
								}else{
									_this.arr.push(s);
								}
							}
//								_this.addMapCarMarker(item);
//								if (index === res.length - 1) _this.addMapCluster(1);
	 					}
					});
					_this.faultStatistics.total = _this.wxclList.length;
					_this.getVehicleDetails(_this.wxclList);
					_this.indexList=_this.wxclList;
					_this.markvehi(_this.arr);
					_this.realTimeMonitorMapLoading=false;
				},
				/* 故障类型点击事件 */
				getTotal: function(){
					this.markvehi(this.arr);
					this.indexList=this.wxclList;
					this.getVehicleDetails(this.wxclList);
				},
				getYdwwyy: function(){
					this.markvehi(this.ydwwyyArr);
					this.indexList=this.ydwwyyList;
					this.getVehicleDetails(this.ydwwyyList);
				},
				getYyywdw: function(){
					this.markvehi(this.yyywdwArr);
					this.indexList=this.yyywdwList;
					this.getVehicleDetails(this.yyywdwList);
				},
				getYzpwdwwyy: function(){
					this.markvehi(this.yzpwdwwyyArr);
					this.indexList=this.yzpwdwwyyList;
					this.getVehicleDetails(this.yzpwdwwyyList);
				},
				getSevenwdwwyy: function(){
					this.markvehi(this.sevenwdwwyyArr);
					this.indexList=this.sevenwdwwyyList;
					this.getVehicleDetails(this.sevenwdwwyyList);
				},
				getWkcwbh: function(){
					this.markvehi(this.wkcwbhArr);
					this.indexList=this.wkcwbhList;
					this.getVehicleDetails(this.wkcwbhList);
				},
				getDggz: function(){
					this.markvehi(this.dggzArr);
					this.indexList=this.dggzList;
					this.getVehicleDetails(this.dggzList);
				},
				/* 将故障车辆信息显示在左侧栏 */
				getVehicleDetails: function(list){
					var _this=this;
					if(list.length>0){
						var data = _.map(list, function (item, index) {
								var gz = '';
								//有定位无营运
								if (_this.hasYdwwyy(item)){
									gz +='有定位无营运;'
								}
								//有营运无定位
								if (_this.hasYyywdw(item)) {
									gz +='有营运无定位;'
								}
								//有抓拍无定位无营运
								if (_this.hasYzpwdwwyy(item)) {
									gz +='有抓拍无定位无营运;'
								}
								//7天无定位无营运
								if (_this.hasSevenwdwwyy(item)) {
									gz +='7天无定位无营运;'
								}
								//空重车无变化
								if (_this.hasWkcwbh(item)) {
									gz +='空重车无变化;'
								}
								item.gId = index + 1;
								item.cphm=item.VEHI_NO;
								item.cplx=item.VT_NAME;
								item.cz=item.OWN_NAME;
								item.sim=item.SIM_NUM;
								item.lxdh=item.OWN_TEL;
								item.gzlx=gz;
								item.gzsj=item.DB_TIME==null?"":(new Date(item.DB_TIME)).Format("yyyy-MM-dd hh:mm:ss");								
								return item;
							});
						_this.excelList=data;
						$("#wxcljkTable").jsGrid({
							data: data
						})
					}
				},
				//导出
				handleVehicleExcel: function(){
					var _this = this;
					layer.confirm('你确定要导出数据', {
						btn: ['确定', '取消'],
						offset: '100px'
					}, function (layerIndex) {
						var newWin = window.open('','_blank'),formStr = '';
						//设置样式为隐藏，打开新标签再跳转页面前，如果有可现实的表单选项，用户会看到表单内容数据
				        formStr = '<form style="visibility:hidden;" method="POST" action="' + basePath+"sbwx/vehicleexcel" + '">' +
				          "<input type='hidden' name='params' value='"+JSON.stringify(_this.excelList)+"' />" +
				          '</form>'; 
				        newWin.document.body.innerHTML = formStr;
					    newWin.document.forms[0].submit();
					    layer.close(layerIndex);		
					    setTimeout(function(){ newWin.close() },1000);
					}, function (layerIndex) {
						layer.close(layerIndex);
					});
				},
				/* 重置统计信息 */
				resetFaultStatistics: function() {
					this.faultStatistics.total = 0;
					this.faultStatistics.ydwwyy = 0;
					this.faultStatistics.yyywdw = 0;
					this.faultStatistics.yzpwdwwyy = 0;
					this.faultStatistics.sevenwdwwyy = 0;
					this.faultStatistics.wkcwbh = 0;
					this.faultStatistics.dggz = 0;
				},
				/* 初始化地图插件 */
				initMapPlugin: function() {
					var _this = this;
					this.map.plugin(['AMap.ToolBar', 'AMap.OverView', 'AMap.Scale', 'AMap.MapType'], function () {
						//加载工具条
						var tool = new AMap.ToolBar({
							direction: false,//隐藏方向导航
							ruler: false,//隐藏视野级别控制尺
							autoPosition: false//禁止自动定位
						});
						_this.map.addControl(tool);
						//加载鹰眼
						var view = new AMap.OverView();
						_this.map.addControl(view);
						//初始状态使用2D地图
						var type = new AMap.MapType( {
							defaultType : 0
						});
//						_this.map.addControl(type);
					})
				},
				//打印
				markvehi: function (obj){
					var _this = this;
					if(_this.pointSimplifierIns){
						_this.pointSimplifierIns.setData(null);
					}
					AMapUI.load(['ui/misc/PointSimplifier', 'lib/$'], function(PointSimplifier, $) {
				        if (!PointSimplifier.supportCanvas) {
				            alert('当前环境不支持 Canvas！');
				            return;
				        }
				        for (var i = 1; i <= 10; i++) {
				        	_this.groupStyleMap[i] = {
								    pointStyle: {
								        content: PointSimplifier.Render.Canvas.getImageContent("../../resources/images/"+(i+400)+".png"),
								        width: 16,
								        height: 16,
								        offset: ['-50%', '-50%'],
								        fillStyle: null
								    }
								};
						}
				        _this.pointSimplifierIns = new PointSimplifier({
				            zIndex: 40,
				            map: _this.map, // 所属的地图实例
				            getPosition: function(item) {
			            		return item.lnglat;
				            },
				            getHoverTitle: function(dataItem, idx) {
				                return '车牌: ' + dataItem.name;
				            },
				            // 使用GroupStyleRender
				            renderConstructor: PointSimplifier.Render.Canvas.GroupStyleRender,
				            renderOptions: {
				            	pointStyle: {
				            		width: 5,
				                    height: 5,
							        fillStyle: null
				                },
				                //鼠标hover时的title信息
				                hoverTitleStyle: {
				                	
				                },
				                getGroupId: function(item, idx) {
				                	return item.groupId;
				                },
				                groupStyleOptions: function(gid) {
				                	return _this.groupStyleMap[gid];
				                }
				            }
				        });
				    	//监听事件
				        _this.pointSimplifierIns.on('pointClick', function(e, record) {
				        	_this.vehicleMarker(record.data.name);
				        });
				        _this.pointSimplifierIns.setData(obj);
				        _this.map.setZoomAndCenter(13,[120.209561,30.245278])
				    });
								
				},
				/* 将单个车辆的位置信息显示在地图上 */
				addMapMarker: function(item,type) {
					var _this = this;
					if(!(item.hasOwnProperty('PX')&&item.hasOwnProperty('PY'))){	
						_this.$message.error('该车辆无定位！');
						return false;
					}
					//逆地理编码
					var lnglat=[];
					lnglat.push(item.PX);
					lnglat.push(item.PY);
					_this.regeoCode(lnglat);
					
					if(this.mapMarker) this.mapMarker.setMap(null);
					var markerContent = document.createElement('div');
					markerContent.className = 'txtstyle';
					//点标记中的图标
					var markerImg = document.createElement('img');
					markerImg.className = 'markerlnglat';
					var count = 0;
					var gz = '';
					//有定位无营运
					if (this.hasYdwwyy(item)){
						count++;
						gz +='有定位无营运;'
						markerImg.src='../../resources/images/401.png';
					}
					//有营运无定位
					if (this.hasYyywdw(item)) {
						count++;
						gz +='有营运无定位;'
						markerImg.src = '../../resources/images/402.png';
					}
					//有抓拍无定位无营运
					if (this.hasYzpwdwwyy(item)) {
						count++;
						gz +='有抓拍无定位无营运;'
						markerImg.src = '../../resources/images/403.png';
					}
					//7天无定位无营运
					if (this.hasSevenwdwwyy(item)) {
						count++;
						gz +='7天无定位无营运;'
						markerImg.src = '../../resources/images/404.png';
					}
					//空重车无变化
					if (this.hasWkcwbh(item)) {
						count++;
						gz +='空重车无变化;'
						markerImg.src = '../../resources/images/405.png';
					}
					if(count>1) markerImg.src='../../resources/images/410.png';
					if(type==1&&gz==""){
						markerImg.src = '../../resources/images/411.png';
					}
					markerContent.appendChild(markerImg);
					this.mapMarker = new AMap.Marker({
						map: this.map,
						position: new AMap.LngLat(item.PX, item.PY),
						offset: new AMap.Pixel(-7,-10), //相对于基点的偏移位置
						draggable: false,  //是否可拖动
						content: markerContent   //自定义点标记覆盖物内容
					});
					this.map.setCenter(new AMap.LngLat(item.PX,item.PY));
					var txt = "<table><tr><td><b style='color:#3399FF'>"+item.VEHI_NO+"</b></td>" +
						"<td></td></tr><tr><td><b>[所属业户]</b>："+item.COMP_NAME+"</td></tr>" +
						"<tr><td><b>[车辆商标]</b>："+item.VT_NAME+"</td></tr>" +
						"<tr><td><b>[车辆颜色]</b>："+item.VC_NAME+"</td></tr>" +
						"<tr><td><b>[SIM卡]</b>："+item.SIM_NUM+"</td></tr>" +
						"<tr><td><b>[车辆所属人]</b>："+(item.OWN_NAME==null?"":item.OWN_NAME)+"</td></tr>" +
						"<tr><td><b>[联系电话]</b>："+(item.OWN_TEL==null?"":item.OWN_TEL)+"</td></tr>" +
						"<tr><td><b>[经度]</b>："+item.PX+"</td></tr><tr><td><b>[纬度]</b>："+item.PY+"</td></tr>" +
						"<tr><td><b>[故障类型]</b>："+gz+"</td></tr>" +
						"<tr><td><b>[故障上传时间]</b>："+(item.DB_TIME==null?"":(new Date(item.DB_TIME)).Format("yyyy-MM-dd hh:mm:ss"))+"</td></tr>" +
						"<tr><td><b>[故障地点]</b>：<span id='aaa'></td></tr>" +
						//			"<tr><td><b>[维修人员]</b>："+(item.WXRY==null?"":item.WXRY)+"</td></tr>" +
						//			"<tr><td><b>[维修地点]</b>："+(item.RA_ADDR==null?"":item.RA_ADDR)+"</td></tr>" +
						//			"<tr><td><b>[维修类型]</b>："+(item.RT_TYPE==null?"":item.RT_TYPE)+"</td></tr>" +
						"<tr><td><b>[经纬度上传时间]</b>："+(item.STIME==null?"":(new Date(item.STIME)).Format("yyyy-MM-dd hh:mm:ss"))+"</td></tr>" +
						//			"<tr><td><b>[维修完成时间]</b>："+(item.RR_TIME_END==null?"":(new Date(item.RR_TIME_END)).Format("yyyy-MM-dd hh:mm:ss"))+"</td></tr>" +
						"";
					var info = [];
					info.push(txt);
					var inforWindowone = new AMap.InfoWindow({
						offset: new AMap.Pixel(3, 0),
						content: info.join("</table>")
					});
					inforWindowone.open(this.map, this.mapMarker.getPosition());
					AMap.event.addListener(this.mapMarker, "click", function (e) {
						inforWindowone.open(_this.map, _this.mapMarker.getPosition());
					});
				},
				/* 点击车辆图标,在地图上显示该车辆的当前信息 */
				vehicleMarker: function (vehicle) {
					var _this = this;
					_this.jqxhr=$.ajax({
						type: 'POST',
						url: '../../sbwx/vhicmarker',
						data: {
							vehi_no: vehicle
						},
						dataType: 'json',
						timeout: 180000,
						success: function (res) {
							if(res.length==0){
								_this.$message.error('查询不到该车！');
							}else{								
								_.each(res, function (item) {
									_this.addMapMarker(item,1)
								});
							}
						},
						error: function () {
						}
					});
				},

				/* 故障车辆显示 */
				handleGzclClick: function () {
					this.type = 1;
				},
				/* 分组监控显示 */
				handleFzjkClick: function () {
					this.type = 2;
				},
				/* 树形组件 */
				initTrueExpanded: function() {
					var list = [];
					_.each(this.realTimeMonitorTree, function (item) {
						list.push(item.id)
					});
					this.realTimeMonitorTreeExpanded = list;
				},
				//查找树
				findtree: function (){
					var _this=this;
					_this.realTimeMonitorTreeLoading=true;
					_this.jqxhr=$.ajax({
						type: "POST",
			 	        url:"../../sbwx/findtree",
			 	        data:{},
			 	        dataType: 'json',
			 			timeout : 3600000,
						success:function(data){
							if(""==data){
								_this.$message.error('没有分组！');
							}else{
								_this.realTimeMonitorTree=data;
								_this.initTrueExpanded();
							}
							_this.realTimeMonitorTreeLoading=false;
//							setTimeout(() => {
//							}, 2000);
						},
						error: function(){
						}         
					});
				},
				/* 树形组件-修改车辆组 */
				updCarGroup: function() {
					var _this = this;
					if(_this.vehicleList){
						_this.transfer.data=[];
						_.each(_this.vehicleList, function (item,index) {
							var t={};
							t.key=item.VEHI_NO;
							t.label=item.VEHI_NO;
							_this.transfer.data.push(t);
						})
					}
					var activeMenuItem = this.rightClickActiveMenuItem;
					$('#wxcljkDialog').modal('show');
					_this.dialog.title = '修改车组';
					_this.dialog.form.clzmc = activeMenuItem.data.label;
					_this.dialog.form.clzxx = [];
					_this.id =activeMenuItem.data.id;
					for (var i = 0; i < activeMenuItem.data.children.length; i++) {
//						console.info('123:', {id: i+1, label:activeMenuItem.data.children[i].label})
						_this.dialog.form.clzxx.push({id: i+1, label:activeMenuItem.data.children[i].label});
					}
				},
				/* 树形组件-删除车辆组 */
				delCarGroup: function() {
					var _this = this;
					var activeMenuItem = _this.rightClickActiveMenuItem;
					_this.id =activeMenuItem.data.id;
					this.$confirm('此操作将永久删除, 是否继续?', '系统提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
					}).then(function () {
						_this.jqxhr=$.ajax({
							type: "POST",
							url:"../../sbwx/removeTree",
							data:{
								"id" : _this.id
							},
							dataType: 'json',
							timeout : 3600000,
							success:function(data){
								if(data.msg=='success'){
									_this.$message({message: "删除车辆组成功!", type: 'success'});
									_this.findtree();
								}else if(data.msg=='数据异常'){
									_this.$message.error('删除车辆组失败！');
								}
							}
						})
					}).catch(function () {
					});
				},
				/* 树形组件-查询车辆组 */
				queryCarGroup: function() {
					var _this = this;
					var activeMenuItem = this.rightClickActiveMenuItem;
					var veh_array=new Array(); 
					for (var i = 0; i < activeMenuItem.data.children.length; i++) {
						veh_array.push("'"+activeMenuItem.data.children[i].label+"'");//向数组中添加元素  
					}
					var vehstr=veh_array.join(',');//将数组元素连接起来以构建一个字符串  
					_this.jqxhr=$.ajax('../../sbwx/findfzjk',{
						type: 'GET',
						dataType: 'json',
						data:{
			 	        	"status" :_this.queryServiceType,
			 	        	'vehstr':vehstr
			 	        },
					}).then(function (res) {
						_this.vehicle(res);
					})	
				},
				/* 树形组件-右键菜单显示内容 */
				rightContextmenu: function(event, data, node) {
					if (data.type) {
						this.rightClickMenu.data = [];
						this.rightClickMenu.display = true;
						if (data.type === 'group') {
							this.rightClickMenu.top = event.clientY;
							this.rightClickMenu.left = event.clientX;
							this.rightClickMenu.data.push(
								{id: 'updCarGroup', title: '修改车辆组', item: 'el-icon-edit', click: this.updCarGroup},
								{id: 'delCarGroup', title: '删除车辆组', item: 'el-icon-delete', click: this.delCarGroup},
								{id: 'queryCarGroup', title: '查询车辆组', item: 'el-icon-delete', click: this.queryCarGroup})
						}
						this.rightClickActiveMenuItem = {data: data, node: node}
					}
				},
				/* 树形组件-点击车辆显示车辆位置 */
				clickFind: function(data, node) {
					var _this=this;
					if (data.type === 'click') {
						_this.vehicleMarker(data.label);
					}
					
				},
				/* 树形组件-显示右键菜单 */
				handleRightContentMenuClick: function() {
					this.rightClickMenu.display = false;
				},
				handleRightContentMenuMouseLeave: function() {
					this.rightClickMenu.display = false;
				},
				/* 车辆故障-车牌号码查询按钮 */
				handleQueryLicensePlateClick: function() {
					var thisOne = this.queryLicensePlate;
					this.queryLicensePlate += 1;
					this.queryLicensePlate = thisOne;
				},
				/* 故障车辆-加载更多按钮 */
				handleVehicleLoadMoreClick: function() {
					this.currentPage++;
				},
				/* 故障车辆-跳转对应地图的车辆位置 */
				handleJumpVehicleClick: function (item) {
					this.addMapMarker(item.item,0);
				},
				/* 显示对话框 */
				handleAddVehicleGroupClick: function() {
					var _this=this;
					$('#wxcljkDialog').modal('show');
					this.dialog.title = '添加车组';
					_this.dialog.form.clzxx=[];
					if(_this.vehicleList){
						_this.transfer.data=[];
						_.each(_this.vehicleList, function (item,index) {
							var t={};
							t.key=item.VEHI_NO;
							t.label=item.VEHI_NO;
							_this.transfer.data.push(t);
						})
					}
				},
				/* 对话框-保存按钮 */
				handleDialogSaveClick: function() {
					
					var _this = this;
					if(_this.dialog.form.clzmc==''){
						_this.$message.error('名称必须填写！');
						return false;
					}
					if(_this.dialog.form.clzxx.length==0){
						_this.$message.error('车辆必须添加！');
						return false;
					}
	        		var veh_array=new Array();  
//	        		activeMenuItem.data.children[i].label
	        		for(var i=0;i<_this.dialog.form.clzxx.length;i++){
	        			veh_array.push(_this.dialog.form.clzxx[i].label);//向数组中添加元素  	        			
	        		}
	        		var vehstr=veh_array.join(';');//将数组元素连接起来以构建一个字符串  
	        		_this.jqxhr=$.ajax({
	    				type: "POST",
	    	 	        url:"../../sbwx/tree",
	    	 	        data:{
	    	 	        	"id" : _this.id,
	    	 	        	"tree_name" : _this.dialog.form.clzmc,
	    	 	        	"vehstr" : vehstr,
	    	 	        	"type":_this.dialog.title
	    	 	        },
	    	 	        dataType: 'json',
	    	 			timeout : 3600000,
						success:function(data){
							if(data.msg=='success'){
								$('#wxcljkDialog').modal('hide');
								_this.$message({message: "操作成功!", type: 'success'});
								_this.findtree();
							}else if(data.msg=='数据异常'){
								_this.$message.error('操作失败！');
							}else if(data.msg=='参数校验异常异常:%s'){
								_this.$message.error('操作车辆过多或分组名称重复！');
							}
						},
						error: function(){
						}         
					});
				},
				handleQueryClick: function () {
					var _this=this;
					_this.realTimeMonitorMapLoading=true;
					_this.jqxhr=$.ajax('../../sbwx/findfzjk',{
						type: 'GET',
						dataType: 'json',
						data:{
			 	        	"status" :_this.queryServiceType,
			 	        	'vehstr':''
			 	        },
					}).then(function (res) {
						_this.vehicle(res);
						_this.realTimeMonitorMapLoading=false;
					})	
				},
				hasYdwwyy: function(item) {
					return parseInt(item.NO_GPS) === 1
				},
				hasYyywdw: function (item) {
					return parseInt(item.NO_JJQ) === 1
				},
				hasYzpwdwwyy: function (item) {
					return parseInt(item.NO_GPS_JJQ) === 1
				},
				hasSevenwdwwyy: function (item) {
					return parseInt(item.SEVEN_GPS_JJQ) === 1
				},
				hasWkcwbh: function(item) {
					return parseInt(item.EMPTY_HEAVY) === 1
				}
			},
			watch: {
				queryLicensePlate: function () {
					this.currentPage = 1
				}
			},
			components:{
				transfer:Transfer,
			}
		})
	})(Vue, _, jQuery);
