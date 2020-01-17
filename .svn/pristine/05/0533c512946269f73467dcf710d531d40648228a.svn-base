basePath = "http://"+self.location.host+"/lklh/";

/*静态变量*/
var ERROR_SELECT_MANY = '只支持单条数据操作';
var ERROR_SELECT_NOT = '必须选中一条数据才可操作';
var POINT_DELETE_CONFIRM = '确认是否删除';
var POINT_UPDATE_CONFIRM = '是否修改记录';


var formatYYYYMMDD = function(d){
	if(d != ''){
		return new Date(d).Format("yyyy-MM-dd");
	}else{
		return ''
	}
}
var formatYYYYMMDDHHMISS = function (d){
	if(d != ''){
		return new Date(d).Format("yyyy-MM-dd hh:mm:ss");
	}else{
		return '';
	}
}

/**
 * 样式配置
 * @type {{space: number, titlePackUpWidth: number}}
 */
var styleConfig = {
	space: 15,/*间距*/
	titlePackUpWidth: 30/*标题收起宽度*/
};
/**
 * 时间格式
 * @param fmt yyyy-MM-dd
 * @returns {*}
 * @constructor
 */
Date.prototype.Format = function (fmt) { //author: meizz
	var o = {
		"M+": this.getMonth() + 1,                 //月份
		"d+": this.getDate(),                    //日
		"h+": this.getHours(),                   //小时
		"m+": this.getMinutes(),                 //分
		"s+": this.getSeconds(),                 //秒
		"q+": Math.floor((this.getMonth() + 3) / 3), //季度
		"S": this.getMilliseconds()             //毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};
/**
 * 删除数组指定下标或指定对象
 * @param obj
 */
Array.prototype.remove=function(obj){
	for(var i =0;i <this.length;i++){
		var temp = this[i];
		if (!isNaN(obj)) temp = i;
		if (temp === obj) {
			for (var j = i; j < this.length; j++) this[j] = this[j + 1];
			this.length = this.length - 1;
		}
	}
};


var fxzh = function (obj){
	if(obj==0||obj==360){
		return "正北";
	}else if(obj==90){
		return "正东";
	}else if(obj==180){
		return "正南";
	}else if(obj==270){
		return "正西";
	}else if(obj>0&&obj<90){
		return "东北";
	}else if(obj>90&&obj<180){
		return "东南";
	}else if(obj>180&&obj<270){
		return "西南";
	}else if(obj>270&&obj<360){
		return "西北";
	}
}


//bootstrap-datetimepicker default option
var dateDefaultOption = {
  language:  'zh-CN',
  format: 'yyyy-mm-dd',
  autoclose: 1,
  startView: 2,
  minView: 2
};
var datetimeDefaultOption = {
  language:  'zh-CN',
  format: 'yyyy-mm-dd hh:ii:ss',
  autoclose: 1
};
var beforeDay  = function(datetime,num) {
	var y = datetime.getFullYear();
	var m = datetime.getMonth();
	var d = datetime.getDate();
	return new Date(y, m, d - num);
}
var beforeDay1  = function(datetime,num) {
	var y = datetime.getFullYear();
	var m = datetime.getMonth();
	var d = datetime.getDate();
	var h = datetime.getHours();
	var mi = datetime.getMinutes();
	var s = datetime.getSeconds();
	return new Date(y, m, d - num,h,mi,s);
}
/**
 * 配合bootstrap-datetimepicker使用
 * 显示时间控件
 * @inputTag：绑定的输入框
 * @idName：时间控件的id 格式：idName + 'DateTimePicker'
 * @datetimeOption: 自定义时间控件的属性
 */
function showDateTimePicker(inputTag, idName, option) {
	var dateTimePickerTag = $('<div class="ip-datetimepicker datetimepicker datetimepicker-top-right datetimepicker-iPeakUI" id="' + idName + 'DateTimePicker">');
	dateTimePickerTag.css(dateTimePickerPosition({inputTag: inputTag, dateTimePickerTag: dateTimePickerTag}, option));
	dateTimePickerTag.show();
	var modalDateTimePicker = $('<div class="modal-dateTimePicker">').on('click', function () {
		dateTimePickerTag.remove();
		modalDateTimePicker.remove();
	});
	$(inputTag).after(dateTimePickerTag);
	if (!dateTimePickerTag.prev().hasClass('modal-dateTimePicker')) dateTimePickerTag.before(modalDateTimePicker);
	dateTimePickerTag.datetimepicker(option.datetimeOption).on('changeDate', function (ev) {
		$(inputTag).data('date', ev).val(ev.date.Format($(inputTag).attr('data-format')));
		dateTimePickerTag.remove();
		modalDateTimePicker.remove();
	}).find('.icon-arrow-left').addClass('glyphicon-arrow-left').end().find('.icon-arrow-right').addClass('glyphicon-arrow-right');
}

/**
 * bootstrap-datetimepicker位置定位
 * @param tags：输入框
 * @param option：bs-datetimepicker定义属性
 * @returns {{left: number | * | jQuery}}：返回样式
 */
function dateTimePickerPosition(tags, option) {
	var style = {
		left: $(tags.inputTag).get(0).offsetLeft
	};
	switch (option.position){
		case 'top':
		case 'top-left':
		case 'topLeft':
			style.left = $(tags.inputTag).get(0).offsetLeft;
			style.bottom = 'calc(100% + 3px)';
			break;
		case 'top-right':
		case 'topRight':
			break;
		case 'bottom':
			break;
		case 'bottom-left':
		case 'bottomLeft':
			break;
		case 'bottom-right':
		case 'bottomRight':
			break;
	}
	return style;
}

/**
 * 添加到多选框列表
 * @param item：要添加的数据
 * @param selectedItems：添加到要插入的多选框列表
 */
var selectItem = function(item, selectedItems) {
	selectedItems.push(item);
};
/**
 * 删除某一条多选框列表
 * @param item：要删除的数据
 * @param selectedItems：要从那个多选框列表中删除
 */
var unselectItem = function(item, selectedItems) {
	selectedItems.remove(item);
};

/**
 * 标题面板伸缩功能（暂时支持左右两边的面板）
 * @param id：面板id
 * @param state：属性
 */
function titlePanelScaling(id, state) {
	$(id + ' .icon-arrowTria').on('click', function () {
		var thisPanel = $(this).parents('.panel-layout[layout]');
		var widthASpace = (state.width ? state.width : 0) + (styleConfig.space ? styleConfig.space : 0);
		if ('left' === $(this).attr('ip-type')) {/*箭头朝左*/
			$(this).attr('ip-type', 'right');
			$(this).parent().attr('ip-type', 'attach');
			if ('left' === thisPanel.attr('layout')) {/*左面板*/
				thisPanel.css({left: 0, width: styleConfig.titlePackUpWidth + 'px'});
			} else if ('right' === thisPanel.attr('layout')) {/*右面板*/
				thisPanel.css({right: 0, width: styleConfig.titlePackUpWidth + 'px'});
			}
			thisPanel.siblings('.panel-layout[layout="center"]').css({width: 'calc(100% - ' + styleConfig.space + 'px)', marginLeft: styleConfig.space + 'px'});
		} else if ('right' === $(this).attr('ip-type')) {/*箭头朝右*/
			$(this).attr('ip-type', 'left');
			$(this).parent().removeAttr('ip-type');
			if ('left' === thisPanel.attr('layout')) {/*左面板*/
				thisPanel.css({left: styleConfig.space + 'px', width: state.width + 'px'});
			} else if ('right' === thisPanel.attr('layout')) {/*右面板*/
				thisPanel.css({right: styleConfig.space + 'px', width: state.width + 'px'});
			}
			thisPanel.siblings('.panel-layout[layout="center"]').css({width: 'calc(100% - ' + widthASpace + 'px)', marginLeft: widthASpace + 'px'})
		}
	});
}