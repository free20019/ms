(function ($) {
		var serverMenu = [
			{name: 'xxfwygl', title: '信息服务与管理', icon: 'icon-car', menuList: 'xxfwMenu', displayState: true},
			{name: 'zhtjyfx', title: '综合统计与分析', icon: 'icon-car', menuList: 'zhtjMenu'},
			{name: 'wsfw', title: '网上服务', icon: 'icon-car', menuList: 'wsfwMenu'},
			{name: 'czgzyfx', title: '车载设备故障与分析', icon: 'icon-car', menuList: 'czgzMenu'},
			{name: 'czsbwx', title: '车载设备维修', icon: 'icon-car', menuList: 'czwxMenu'}
		];
		var menus = {
			xxfwMenu: [
				{name: 'hyzl', title: '行业总览', icon: 'icon-car', href: 'app/xxfwgl/hyzl.html', active: true},
				{name: 'clfb', title: '车辆分布', icon: 'icon-car', href: 'app/xxfwgl/clfb.html'},
				{name: 'cljkgz', title: '车辆监控跟踪', icon: 'icon-car', href: 'app/xxfwgl/cljkgz.html'},
				{name: 'gjhfgz', title: '轨迹回放改造', icon: 'icon-car', href: 'app/xxfwgl/gjhfgz.html'},
				{name: 'dcjk', title: '多车监控', icon: 'icon-car', href: 'app/xxfwgl/dcjk.html'},
				{name: 'zdgzcl', title: '重点关注车辆', icon: 'icon-car', href: 'app/xxfwgl/zdgzcl.html'},
				{name: 'zlclcx', title: '滞留车辆查询', icon: 'icon-car', href: 'app/xxfwgl/zlcljk.html'},
				{name: 'qyyjzl', title: '区域应急指令', icon: 'icon-car', href: ''},
				{name: 'tsclcx', title: '特殊车辆查找', icon: 'icon-car', href: 'app/xxfwgl/tsclcx.html'},
				{name: 'yjyccx', title: '运价异常查询', icon: 'icon-car', href: 'app/xxfwgl/yjyccx.html'},
				{name: 'czckfx', title: '出租乘运分析', icon: 'icon-car', href: 'app/xxfwgl/czckfx.html'},
				{name: 'clrylxfx', title: '车辆燃油类型分析', icon: 'icon-car', href: 'app/xxfwgl/clrylxfx.html'},
				{name: 'ysrlcx', title: '疑似绕路查询', icon: 'icon-car', href: ''},
				{name: 'spgl', title: '视频管理', icon: 'icon-car', href: ''},
				{name: 'bjgl', title: '报警管理', icon: 'icon-car', href: ''}
			],
			zhtjMenu: [
				{name: 'czcyxq', title: '出租乘运需求分析', icon: 'icon-car', displayState: true, children:[
						{name: 'dcrdfx', title: '打车热点分析', icon: 'icon-car', href: 'http://www.qq.com', active: true},
						{name: 'cklxfx', title: '乘客流向分析', icon: 'icon-car', href: ''}
					]},
				{name: 'yyycfx', title: '营运异常分析', icon: 'icon-car', href: 'https://www.imooc.com', children:[
						{name: 'ysrlfx', title: '疑似绕路分析', icon: 'icon-car', href: 'http://www.qq.com'},
						{name: 'yjycfx', title: '运价异常分析', icon: 'icon-car', href: 'http://www.qq.com'},
						{name: 'lcycfx', title: '营运里程异常分析', icon: 'icon-car', href: 'http://www.qq.com'},
						{name: 'cxycfx', title: '营运单次异常分析', icon: 'icon-car', href: ''}
					]},
				{name: 'clyyqk', title: '车辆营运情况分析', icon: 'icon-car', href: 'http://www.iqiyi.com', children:[
						{name: 'wljcfx', title: '营运围栏进出分析', icon: 'icon-car', href: 'http://www.qq.com'},
						{name: 'ryzbfx', title: '车辆燃油类型占比分析', icon: 'icon-car', href: 'http://www.qq.com'}
					]}
			],
			wsfwMenu: [
				{name: 'yyywsjtj', title: '营运业务数据统计', icon: 'icon-car', displayState: true, children:[
						{name: 'yyysfx', title: '营运收益分析', icon: 'icon-car', href: 'https://useiconic.com', active: true},
						{name: 'zklcfx', title: '载客里程分析', icon: 'icon-car', href: ''},
						{name: 'kslcfx', title: '空驶里程分析', icon: 'icon-car', href: ''},
						{name: 'yycsfx', title: '营运次数分析', icon: 'icon-car', href: ''},
						{name: 'zksjfx', title: '载客时间分析', icon: 'icon-car', href: ''},
						{name: 'ddsjfx', title: '载客等候时间分析', icon: 'icon-car', href: ''},
						{name: 'yysjfx', title: '营运数据查询', icon: 'icon-car', href: ''},
						{name: 'ycclfx', title: '异常车辆查询', icon: 'icon-car', href: ''},
						{name: 'clyysjtj', title: '车辆营运数据统计', icon: 'icon-car', href: ''},
						{name: 'yhyysjtj', title: '业户营运数据统计', icon: 'icon-car', href: ''},
						{name: 'ryyysjtj', title: '从业人员营运数据统计', icon: 'icon-car', href: ''},
						{name: 'sdqylctj', title: '单车速度曲线及里程统计', icon: 'icon-car', href: ''},
						{name: 'yybg', title: '营运报告', icon: 'icon-car', href: ''}
					]},
				{name: 'czqcfwxt', title: '出租汽车服务系统', icon: 'icon-car', children:[
						{name: 'yyxxjczl', title: '营运信息监测总览', icon: 'icon-car', href: 'https://v3.bootcss.com'},
						{name: 'fwzlxxcx', title: '服务质量信息查询', icon: 'icon-car', children:[
								{name: 'khxx', title: '考核信息', icon: 'icon-car', href: ''},
								{name: 'fwwz', title: '服务违章', icon: 'icon-car', href: ''},
								{name: 'fwts', title: '服务投诉', icon: 'icon-car', href: ''},
								{name: 'hrhs', title: '好人好事', icon: 'icon-car', href: ''},
								{name: 'rygl', title: '荣誉管理', icon: 'icon-car', href: ''},
								{name: 'wmcl', title: '文明车辆', icon: 'icon-car', href: ''},
								{name: 'bljl', title: '不良记录', icon: 'icon-car', href: ''}
							]},
						{name: 'aqxccx', title: '安全行车查询', icon: 'icon-car', children:[
								{name: 'jtwf', title: '交通违法', icon: 'icon-car', href: ''},
								{name: 'xcgs', title: '行车事故', icon: 'icon-car', href: ''}
							]},
						{name: 'swdj', title: '失物登记', icon: 'icon-car', children:[
								{name: 'swsj', title: '失物上交', icon: 'icon-car', href: ''},
								{name: 'szzw', title: '失主找失物', icon: 'icon-car', href: ''}
							]},
						{name: 'sjzp', title: '司机招聘', icon: 'icon-car', href: ''},
						{name: 'qyhd', title: '企管互动', icon: 'icon-car', children:[
								{name: 'tzgg', title: '通知公告', icon: 'icon-car', href: ''},
								{name: 'wjzl', title: '文件资料', icon: 'icon-car', href: ''},
								{name: 'hyzx', title: '行业咨询', icon: 'icon-car', href: ''},
								{name: 'qybs', title: '企业报送', icon: 'icon-car', href: ''},
								{name: 'sjyddcx', title: '司机移动端查询', icon: 'icon-car', href: ''},
								{name: 'qyyddcx', title: '企业移动端查询', icon: 'icon-car', href: ''}
							]}
					]}
			],
			czgzMenu: [
				{name: 'cpsbjrxt', title: '车辆号牌识别接入', icon: 'icon-car', href: 'https://ant.design/index-cn', active: true},
				{name: 'sbgzsbxt', title: '车载设备故障识别', icon: 'icon-car', href: ''},
				{name: 'clyysbxt', title: '异常车辆营运识别', icon: 'icon-car', href: ''},
				{name: 'zhtjcxxt', title: '综合统计与查询', icon: 'icon-car', children:[
						{name: 'czsbgzcx', title: '车载设备故障查询', icon: 'icon-car', href: ''},
						{name: 'cxsbgztj', title: '车载设备故障统计', icon: 'icon-car', href: ''}
					]}
			],
			czwxMenu: [
				{name: 'wxcljk', title: '维修车辆监控', icon: 'icon-car', href: 'http://element-cn.eleme.io/#/en-US', active: true},
				{name: 'wxclyj', title: '维修车辆预警', icon: 'icon-car', href: ''},
				{name: 'zhtjcx', title: '综合统计与查询', icon: 'icon-car', href: ''},
				{name: 'wxxxjr', title: '维修信息接入', icon: 'icon-car', children:[
						{name: 'djxxjr', title: '登记信息接入', icon: 'icon-car', href: ''},
						{name: 'jdxxjr', title: '进度信息接入', icon: 'icon-car', href: ''}
					]},
				{name: 'wxglcx', title: '维修管理移动查询', icon: 'icon-car', children:[
						{name: 'sjydcx', title: '司机移动端查询', icon: 'icon-car', href: ''},
						{name: 'qydadcx', title: '企业移动端查询', icon: 'icon-car', href: ''},
						{name: 'yddjk', title: '移动端监控', icon: 'icon-car', href: ''}
					]}
			]
		};
		$(function () {
			$('.scrollbar-macosx').scrollbar();
			$('#serverMenu').MenuList({menu: serverMenu, displayType: 'horizontal', offClickEvent: true},{
				resetMenuClick: function (item) {
					$('#tabWrapper').empty();
					$('#menuListBar').empty().MenuList({menu: menus[item.menuList], icon: 'icon', tabWrapper: '#tabWrapper', isTabBarDisplay: false});
				}
			});
			$('#menuListBar').MenuList({menu: menus.xxfwMenu, icon: 'icon', tabWrapper: '#tabWrapper'});
		})
	})(jQuery);