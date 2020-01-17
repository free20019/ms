(function($) {
	$.fn.Tab = function(options, methods) {
		var _this = this;
		this.prototype={
			state: {
				displayType: 'horizontal'
			},
			methods: {}
		};
		$.extend(this.prototype.state, options);
		$.extend(this.prototype.methods, methods);
		var prevCheckTabItem = null, menuItemActive = null;
		var displayType = {
			horizontal: function (tags, item) {/*水平*/},
			vertical: function (tags, item) {/*垂直*/}
		};
		function setTabItem(toTag, item) {
			var icon = $('<icon />').addClass('ip-tabItemIcon');
			var label = $('<label />').addClass('ip-tabItemLabel').text(item.title);
			var tabItem = $('<li />').addClass('ip-tabBarItem').attr('skip', item.id).data('data-base', item).on('click', function () {
				$(this).addClass('active').siblings('.active').removeClass('active');
				toTag.siblings('.ip-tabBodyer').find('#'+$(this).attr('skip')).show().siblings().hide();
				var tabLength = $(this).parent().find('.ip-tabBarItem:not([type=active])').length;
				console.info($(this).index(), (100 / tabLength) * $(this).index(), (100 / tabLength) / 2)
				$(this).siblings('.ip-tabBarItem[type=active]').css({left: (100 / tabLength) * $(this).index() + (100 / tabLength) / 2 + '%'});
			});
			if (item && item.icon) {
				var className = 'iconfont ';
				if (item.icon.indexOf('icon-') > 0) className += item.icon;
				else className += 'icon-' + item.icon;
				icon.addClass(className);
			}
			tabItem.append(icon);
			tabItem.append(label);
			return toTag.append(tabItem)
		}

		return this.each(function() {
			var state = _this.prototype.state;
			var thisTab = $(this);
			var tabLength = thisTab.find('.ip-tabPanelItem').length;
			thisTab.find('.ip-tabPanelItem').each(function (index) {
				if (thisTab.find('.ip-tabBodyer').length === 0) thisTab.find('.ip-tabPanelItem').wrapAll($('<div />').addClass('ip-tabBodyer'));
				if (thisTab.find('.ip-tabHeader').length === 0) thisTab.prepend($('<ul />').addClass('ip-tabHeader'));
				var tabInfo = {};
				tabInfo.id = $(this).attr('id');
				tabInfo.title = $(this).attr('title');
				setTabItem(thisTab.find('.ip-tabHeader'), tabInfo);
			});
			thisTab.find('.ip-tabBarItem').css({width: 100 / tabLength + '%'});
			thisTab.find('.ip-tabHeader').append($('<li />').addClass('ip-tabBarItem').attr({type: 'active'}).css({left: (100 / tabLength) / 2 + '%'}));
			$(thisTab.find('.ip-tabBarItem').get(0)).addClass('active');
			$(thisTab.find('.ip-tabPanelItem').get(0)).show();
		});
	};
})(jQuery);