(function($) {
	$.fn.MenuList = function(options, methods) {
		var _this = this;
		this.prototype={
			state: {
				displayType: 'vertical',
				menu: [],
				icon: 'none',
				isTabBarDisplay: true,
				resetHtml: true,
				tabWrapper: '#tabWrapper',
				tabBarWidth: 3,
				offClickEvent: false
			},
			methods: {}
		};
		$.extend(this.prototype.state, options);
		$.extend(this.prototype.methods, methods);
		var prevCheckTabItem = null, menuItemActive = null;
		var displayType = {
			horizontal: function (tags, item) {/*水平*/
				return tags.tagLi;
			},
			vertical: function (tags, item) {/*垂直*/
				var state = _this.prototype.state;
				if (item && item.children) {
					tags.tagA.removeAttr('data-skip');
					tags.tagA.append(tags.arrow);
					tags.tagLi.append(tags.tagUl);
					tags.tagLi.addClass('ip-subMenuPanel');
					if (item.displayState && ('none' === state.icon || 'iconText' === state.icon)) tags.tagUl.show();
					var liList = setMenuHtml(item.children);
					$.each(liList, function (i, dataItem) {
						tags.tagUl.append(dataItem);
					});
				}
				return tags.tagLi;
			}
		};
		function setMenuHtml(data) {
			var state = _this.prototype.state;
			var html = [];
			$.each(data, function (index, item) {
				var tagLi = $('<li>').addClass('ip-menuItem').data('database', item);
				var tagA = $('<a data-skip="' + item.name + '" class="ip-menuTitle"></a>');
				var iconfont = $('<i class="iconfont ' + item.icon + '" ipClass="iconfont ' + item.icon + '"></i>');
				var arrow = $('<i class="ip-fontArrow ip-iconfont ip-icon-chevron-down"><!--&vee;--></i>');
				var title = $('<span class="ip-menuLabel">' + item.title + '</span>');
				var tagUl = $('<ul class="ip-menuChildrenPanel"></ul>');
				if ('none' === state.icon || 'iconText' === state.icon) tagUl.hide();
				tagLi.append(tagA);
				if ('icon' === state.icon || 'iconText' === state.icon) tagA.append(iconfont);
				tagA.append(title);
				if (item && item.active) menuItemActive = item.name;
				if (item && item.displayState) tagLi.addClass('active');
				html.push(displayType[state.displayType]({tagLi: tagLi, tagA: tagA, iconfont: iconfont, arrow: arrow, title: title, tagUl: tagUl}, item));
			});
			return html;
		}
		function newTabWrapperHtml() {
			var state = _this.prototype.state;
			var tabWrapper = $(state.tabWrapper).addClass('ip-tabWrapper');
			var tabHeader = $('<div>').addClass('ip-tabHeader');
			var tabBarWrapper = $('<div>').addClass('ip-tabBarWrapper');
			var tabScrollBar = $('<ul>').addClass('ip-tabScrollBar');
			var tabBodyer = $('<div>').addClass('ip-tabBodyer');
			if (false === state.isTabBarDisplay) tabWrapper.addClass('ip-noTabHeader');
			if (tabWrapper.find('.ip-tabHeader').length < 1) {
				tabBarWrapper.append(tabScrollBar);
				tabHeader.append(tabBarWrapper);
				tabWrapper.append(tabHeader);
			}
			if (tabWrapper.find('.ip-tabBodyer').length < 1) tabWrapper.append(tabBodyer);
		}

		function setTabItem(item, selected) {
			var state = _this.prototype.state;
			var tabBarItem = $('<li data-name="' + item.name + '">').addClass('ip-tabBarItem').data('data-item', item);
			var tabItemIcon = $('<i>').addClass('ip-tabItemIcon iconfont ' + item.icon);
			var tabItemLabel = $('<span>').addClass('ip-tabItemLabel').text(item.title);
			var tabItemRemove = $('<i>').addClass('ip-tabItemRemove ip-iconfont ip-icon-remove');
			if (true === selected) tabBarItem.addClass('active');
			if ('icon' === state.icon || 'iconText' === state.icon) tabBarItem.append(tabItemIcon);
			tabBarItem.append(tabItemLabel);
			tabBarItem.append(tabItemRemove);
			return tabBarItem;
		}
		function setTabPanel(item){
			var state = _this.prototype.state;
			var tabWrapper = $(state.tabWrapper);
			var tabBodyer = tabWrapper.find('.ip-tabBodyer');
			return $('<iframe>').attr({
				name: 'iframe' + tabBodyer.length,
				id: item.name,
				src: item.href,
				frameborder: 0
			}).addClass('ip-tabPanelItem').show();
		}

		function resetTabScrollBarWidth() {
			var state = _this.prototype.state;
			var tabWrapper = $(state.tabWrapper);
			var tabBarWrapper = tabWrapper.find('.ip-tabBarWrapper');
			var tabScrollBar = tabBarWrapper.find('.ip-tabScrollBar');
			var skipLength = tabBarWrapper.width() / 2.4;
			var excessWidth = $(_this).parent().outerWidth();
			var selectedItem = tabScrollBar.find('.ip-tabBarItem.active');
			var offsetLeft = selectedItem.offset().left - excessWidth;
			if (tabBarWrapper.width() < tabScrollBar.width()) {
				if (tabBarWrapper.siblings('.ip-tabButton').length === 0) {
					var leftButton = $('<div class="ip-tabButton ip-iconfont ip-icon-chevron-left">').on('click', function () {
						tabBarWrapper.stop().animate({scrollLeft: tabBarWrapper.scrollLeft() - skipLength}, 800);
					});
					var rightButton = $('<div class="ip-tabButton ip-iconfont ip-icon-chevron-right">').on('click', function () {
						tabBarWrapper.stop().animate({scrollLeft: tabBarWrapper.scrollLeft() + skipLength}, 800);
					});
					tabBarWrapper.before(leftButton).after(rightButton).css({display: 'inline-block', width: 'calc(100% - 38px)', marginLeft: '19px'})
				}
				if (tabBarWrapper.next().hasClass('ip-tabButton')) offsetLeft -= 20;
			} else {
				tabBarWrapper.css({display: 'block', width: 'auto', marginLeft: '0'}).siblings('.ip-tabButton').remove();
			}
			tabBarWrapper.stop().animate({scrollLeft: offsetLeft}, 800);
		}

		/*菜单栏点击事件*/
		function setEvent() {
			var state = _this.prototype.state;
			var selectedMenuBtn = null;
			var tabWrapper = $(state.tabWrapper);
			$(_this).find('.ip-menuTitle').off('click').on({click: function () {
				if ($(this).find('i').hasClass('glyphicon-refresh')) {
					$('#' + $(this).attr('data-skip')).get(0).contentWindow.location.reload(true);
				} else {
					if(true === state.offClickEvent) {
						var item = $(this).parent().data('database');
						$(this).parent().addClass('active').siblings('.active').removeClass('active');
						methods.resetMenuClick(item);
						return;
					}
					prevCheckTabItem = $(this).attr('data-skip');
					var item = $(this).parent().data('database');
					tabWrapper = $(state.tabWrapper);
					var tabScrollBar = tabWrapper.find('.ip-tabScrollBar');
					var tabBodyer = tabWrapper.find('.ip-tabBodyer');
					/*判断是否有二级菜单*/
					if ($(this).parent().hasClass('ip-subMenuPanel')) {
						if ($(this).parent().hasClass('active')) {
							$(this).parent().removeClass('active');
							if ('icon' !== state.icon) {
								$(this).siblings('.ip-menuChildrenPanel').hide();
							}
						} else {
							$(this).parent().addClass('active').siblings('.active').removeClass('active');
							if ('icon' !== state.icon) {
								$(this).parent().siblings('.ip-subMenuPanel').find('ul.ip-menuChildrenPanel').hide();
								$(this).siblings('.ip-menuChildrenPanel').show();
							}
						}
					} else {
						/*取消当先选中的选项卡并隐藏iframe*/
						tabScrollBar.find('.active').removeClass('active');
						tabBodyer.find('iframe').hide();

						var childrenPanel = $(this).parent().parent();
						/*把记录的二级菜单 清除选中状态*/
						if (selectedMenuBtn) {
							$('[data-skip=' + selectedMenuBtn + ']').parent().removeClass('active');
							selectedMenuBtn = null;
						}
						/*判断是否是二级菜单 把二级菜单记录下来*/
						if (childrenPanel[0].className.indexOf('ip-menuChildrenPanel') >= 0) selectedMenuBtn = prevCheckTabItem;
						/*选中并清除同一级其他选中的标签*/
						if ('none' === state.icon || 'iconText' === state.icon) {
							$(this).parent().addClass('active').siblings('.active').removeClass('active').find('.ip-menuChildrenPanel').hide().find('.active').removeClass('active');
						} else {
							$(this).parent().addClass('active').siblings('.active').removeClass('active').find('.ip-menuChildrenPanel').find('.active').removeClass('active');
						}
						/*判断是否打开连接*/
						if (tabBodyer.find('#' + item.name).length > 0) {
							tabScrollBar.find('.ip-tabBarItem[data-name=' + item.name + ']').addClass('active');
							tabBodyer.find('#' + item.name).show();
							setTimeout(function () {
								tabWrapper = $(state.tabWrapper);
								tabScrollBar = tabWrapper.find('.ip-tabScrollBar');
								var excessWidth = tabWrapper.find('.ip-tabHeader').outerWidth();
								var selectedItem = tabScrollBar.find('.ip-tabBarItem.active');
								var offsetLeft = selectedItem.offset().left - excessWidth;
								if(tabScrollBar.next().hasClass('ip-tabButton')) offsetLeft -= 20;
								tabScrollBar.stop().animate({scrollLeft: offsetLeft}, 800);
							}, 0);
						} else {
							var tabItem = setTabItem(item, true);
							tabScrollBar.append(tabItem);
							state.tabBarWidth += tabItem.outerWidth();
							tabScrollBar.css({width: state.tabBarWidth + 'px'});
							tabScrollBar.stop().animate({scrollLeft: tabScrollBar.find('.ip-tabBarItem.active').offset().left}, 800);
							resetTabScrollBarWidth();
							setTabPanel(item).appendTo(tabBodyer);
						}
					}

					/*菜单栏-标签卡点击事件*/
					$('.ip-tabBarItem').off('click').on('click', function () {
						$(this).addClass('active').siblings('.active').removeClass('active');
						tabBodyer.find('iframe').hide();
						tabBodyer.find('#' + $(this).attr('data-name')).show();
						prevCheckTabItem = $(this).attr('data-name');
					});
					/*菜单栏-标签卡的删除事件*/
					$('.ip-tabBarItem .ip-tabItemRemove').off('click').on('click', function () {
						event.stopPropagation();
						var id = $(this).parent().attr('data-name');
						var content = tabBodyer.find('#' + id);
						/*判断选中的选项卡是不是要删除的选项卡*/
						if (prevCheckTabItem && id === prevCheckTabItem) {
							/*判断选项卡的位置*/
							if (content.prev().length > 0) {
								prevCheckTabItem = $(this).parent().prev().addClass('active').attr('data-name');
								content.prev().show();
							} else if (content.next().length > 0) {
								prevCheckTabItem = $(this).parent().next().addClass('active').attr('data-name');
								content.next().show()
							}
						} else {
							$('.ip-tabBarItem[data-name=' + prevCheckTabItem + ']').addClass('active');
							console.info('hehe:', tabBodyer, prevCheckTabItem)
							tabBodyer.find('#' + prevCheckTabItem).show();
						}
						var tabItem = $(this).parent();
						state.tabBarWidth -= tabItem.outerWidth();
						tabScrollBar.css({width: state.tabBarWidth + 'px'});
						resetTabScrollBarWidth();
						tabItem.remove();
						content.remove();
					});
				}

				}
			}).hover(function () {
				var icon = $($(this).find('i'));
				if ($(this).parent().hasClass('active') && $(this).siblings('.ip-menuChildrenPanel').length === 0 && state.resetHtml === true) {
					icon.removeClass().addClass('glyphicon glyphicon-refresh');
				}
			}, function () {
				var icon = $($(this).find('i'));
				if ($(this).parent().hasClass('active') && $(this).siblings('.ip-menuChildrenPanel').length === 0 && state.resetHtml === true) {
					icon.removeClass().addClass(icon.attr('ipClass'));
				}
			});
			$(window).resize(function () {resetTabScrollBarWidth();});
		}

		return this.each(function() {
			var state = _this.prototype.state;
			$.each(setMenuHtml(_this.prototype.state.menu), function (index, item) {
				$(item).appendTo(_this);
			});
			$(this).addClass('ip-menu');
			if ('horizontal' === state.displayType) {
				$(this).addClass('ip-horizontal').removeClass('ip-vertical');
			} else if ('vertical' === state.displayType) {
				$(this).addClass('ip-vertical').removeClass('ip-horizontal');
			}
			if ('icon' === state.icon) {
				$(this).addClass('ip-typeIcon');
				if ('vertical' === state.displayType) $(this).removeClass('ip-vertical');
			}
			else $(this).removeClass('ip-typeIcon');

			newTabWrapperHtml();
			setEvent();
			if (menuItemActive) {
				$(this).find('a[data-skip=' + menuItemActive + ']').trigger('click');
			}
		});
	};
})(jQuery);