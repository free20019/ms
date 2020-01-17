var Transfer = Vue.extend({
	name: 'transfer',
	template: '<div class="el-transfer">' +
		'<div class="el-transfer-panel" titles="" buttontexts="" filterplaceholder="请输入城市拼音1" leftdefaultchecked="" rightdefaultchecked="" value="" targetorder="original">' +
		'<p class="el-transfer-panel__header">' +
		'<label role="checkbox" class="el-checkbox">' +
		'<span class="el-checkbox__label">{{totalTTitle}}<span>{{currentTTCount}}/{{currentTTDisplayCount}}/{{totalTCount}}</span></span>' +
		'</label>' +
		'</p>' +
		'<div class="el-transfer-panel__body">' +
		'<div class="el-transfer-panel__filter el-input el-input--small el-input--prefix">' +
		'<input type="text" autocomplete="off" :placeholder="filterPlaceholder" v-model="totalTransfer.query" class="el-input__inner">' +
		'<span class="el-input__prefix"><i class="el-input__icon el-icon-search"></i></span>' +
		'</div>' +
		'<div role="group" aria-label="checkbox-group" class="el-checkbox-group el-transfer-panel__list is-filterable">' +
		'<label role="checkbox" class="el-checkbox el-transfer-panel__item" v-for="(item, index) in filterTotalTransfer" :key="\'totalTransfer_\' + item.id" v-if="index < 500" @click.prevent="handleTotalTransferClick(item)">' +
		'<span aria-checked="mixed" class="el-checkbox__input" :class="{\'is-checked\': item.checked}">' +
		'<span class="el-checkbox__inner"></span>' +
		'<input type="checkbox" aria-hidden="true" class="el-checkbox__original" value="0">' +
		'</span>' +
		'<span class="el-checkbox__label"><span>{{item.label}}</span></span>' +
		'</label>' +
		'</div>' +
		'<p class="el-transfer-panel__empty" style="display: none;">无匹配数据</p>' +
		'<p class="el-transfer-panel__empty" style="display: none;">无数据</p></div></div>' +
		'<div class="el-transfer__buttons">' +
		'<button :disabled="hasGTDisabled" type="button" class="el-button el-button--primary el-transfer__button" :class="{\'is-disabled\': hasGTDisabled}" @click="handleRemoveTransferClick">' +
		'<span><i class="el-icon-arrow-left"></i></span>' +
		'</button>' +
		'<button :disabled="hasTTDisabled" type="button" class="el-button el-button--primary el-transfer__button" :class="{\'is-disabled\': hasTTDisabled}" @click="handleAddTransferClick">' +
		'<span><i class="el-icon-arrow-right"></i></span>' +
		'</button>' +
		'</div>' +
		'<div class="el-transfer-panel" titles="" buttontexts="" filterplaceholder="请输入城市拼音22" leftdefaultchecked=""' +
		' rightdefaultchecked="" value="" targetorder="original">' +
		'<p class="el-transfer-panel__header">' +
		'<label role="checkbox" class="el-checkbox" @click.prevent="handleGroupCheckClick">' +
		'<span aria-checked="mixed" class="el-checkbox__input" :class="{\'is-checked\': groupTransfer.totalCheck === 2}">' +
		'<span class="el-checkbox__inner"></span>' +
		'<input type="checkbox" aria-hidden="true" class="el-checkbox__original" value="">' +
		'</span>' +
		'<span class="el-checkbox__label">{{groupTTitle}}<span>{{currentGTCount}}/{{groupTCount}}</span></span>' +
		'</label>' +
		'</p>' +
		'<div class="el-transfer-panel__body">' +
		'<div class="el-transfer-panel__filter el-input el-input--small el-input--prefix">' +
		'<input type="text" autocomplete="off" :placeholder="filterPlaceholder" v-model="groupTransfer.query" class="el-input__inner">' +
		'<span class="el-input__prefix"><i class="el-input__icon el-icon-search"></i></span>' +
		'</div>' +
		'<div role="group" aria-label="checkbox-group" class="el-checkbox-group el-transfer-panel__list is-filterable" :style="{display: value.length<=0 ? \'none\' : \'block\'}">' +
		'<label role="checkbox" class="el-checkbox el-transfer-panel__item" v-for="(item, index) in filterGroupTransfer" :key="\'groupTransfer_\' + item.id" @click.prevent="handleGroupTransferClick(item)">' +
		'<span aria-checked="mixed" class="el-checkbox__input" :class="{\'is-checked\': hasGTChecked(item)}">' +
		'<span class="el-checkbox__inner"></span>' +
		'<input type="checkbox" aria-hidden="true" class="el-checkbox__original" value="0">' +
		'</span>' +
		'<span class="el-checkbox__label"><span>{{item.label}}</span></span>' +
		'</label>' +
		'</div>' +
		'<p class="el-transfer-panel__empty" style="display: none;">无匹配数据</p>' +
		'<p class="el-transfer-panel__empty">无数据</p>' +
		'</div>' +
		'</div>' +
		'</div>',
	data: function () {
		return {
			totalTransfer: {
				selectList: [],
				query: ''
			},
			groupTransfer: {
				totalCheck: 0,
				data: [],
				selectList: [],
				query: ''
			}
			// totalSelectTransferList: [],

		}
	},
	props: {
		data: Array,
		titles: {
			type: Array,
			default: ['列表 1', '列表 2']
		},
		'filter-placeholder': {
			type: String,
			default: '请输入搜索内容'
		},
		value: Array
	},
	computed: {
		/* 总列表-选中的数量 */
		currentTTCount: function() {
			return this.totalTransfer.selectList.length;
		},
		/* 总列表-显示数量 */
		currentTTDisplayCount: function() {
			return this.filterTotalTransfer.length > 500 ? 500 : this.filterTotalTransfer.length;
		},
		/* 总列表-总数量 */
		totalTCount: function() {
			return this.data.length;
		},
		/* 总列表-名称 */
		totalTTitle: function() {
			return this.titles[0];
		},
		/* 是否警用添加按钮 */
		hasTTDisabled: function() {
			return this.totalTransfer.selectList.length <= 0
		},
		hasGTDisabled: function() {
			return this.groupTransfer.selectList.length <= 0
		},
		/* 组列表-选中的数量 */
		currentGTCount: function() {
			return this.groupTransfer.selectList.length;
		},
		/* 组列表-总数量 */
		groupTCount: function() {
			return this.value.length;
		},
		/* 组列表-名称 */
		groupTTitle: function() {
			return this.titles[1];
		},
		filterTotalTransfer: function() {
			var data = this.data;
			var query = this.totalTransfer.query;
			var groupList = this.value;
			return _.filter(data, function (item) {
				if (groupList.length > 0) {
					var pretreatment = _.filter(groupList, function (groupItem) {
						return item.id === groupItem.id && item.label === groupItem.label
					});
					if (pretreatment.length > 0) return false;
				}
				if (!query) return true;
				return item.label.indexOf(query) > -1;
			});
		},
		filterGroupTransfer: function() {
			var data = this.value;
			var query = this.groupTransfer.query;
			return _.filter(data, function (item) {
				if (!query) return true;
				return item.label.indexOf(query) > -1;
			});
		}
	},
	methods: {
		/* 总列表-点击事件 */
		handleTotalTransferClick: function (item) {
			var selectList = this.totalTransfer.selectList;
			item.checked = !item.checked;
			if (selectList.length === 0) {
				selectList.push({id: item.id, label: item.label, checked: false})
			} else {
				var flag = false;
				for (var i = 0; i < selectList.length; i++) {
					var it = selectList[i];
					if (item.id === it.id && item.label === it.label) {
						selectList.splice(i, 1);
						flag = true;
						break;
					}
				}
				if (!flag) selectList.push({id: item.id, label: item.label, checked: false});
			}
		},
		/* 组列表-点击事件 */
		handleGroupTransferClick: function (item) {
			var selectList = this.groupTransfer.selectList;
			item.checked = !item.checked;
			if (selectList.length === 0) {
				selectList.push({id: item.id, label: item.label, checked: false})
			} else {
				var flag = false;
				for (var i = 0; i < selectList.length; i++) {
					var it = selectList[i];
					if (item.id === it.id && item.label === it.label) {
						selectList.splice(i, 1);
						flag = true;
						break;
					}
				}
				if (!flag) selectList.push({id: item.id, label: item.label, checked: false});
			}
		},
		resetTransferList: function(data, bool) {
			_.each(data, function (item) {
				if (item.checked !== bool) item.checked = bool;
			});
		},
		/* 添加 */
		handleAddTransferClick: function () {
			var _this = this;
			var selectList = this.totalTransfer.selectList;
			_.each(selectList, function (item) {
				_this.value.push(item);
			})
			this.totalTransfer.selectList = []
			this.resetTransferList(this.data, false);
		},
		/* 删除 */
		handleRemoveTransferClick: function () {
			var data = this.value;
			var selectList = this.groupTransfer.selectList;
			_.each(selectList, function (item) {
				for (var i = 0; i < data.length; i++) {
					var totalItem = data[i];
					if (totalItem.id === item.id && totalItem.label === item.label) {
						data.splice(i, 1);
						break;
					}
				}
			})
			this.groupTransfer.selectList = [];
			this.resetTransferList(this.data, false)
		},
		handleGroupCheckClick: function () {
			if (this.groupTransfer.totalCheck === 0) this.groupTransfer.totalCheck = 2
			else this.groupTransfer.totalCheck = 0
		},
		hasGTChecked: function (item) {
			var selectList = this.groupTransfer.selectList;
			for(var i=0;i<selectList.length;i++){
				var it = selectList[i];
				if (item.id === it.id && item.label === it.label) return true;
			}
			return false;
		}
	},
	watch: {
		'groupTransfer.totalCheck': function (val) {
			switch (val) {
				case 0:
					this.groupTransfer.selectList = [];
					this.resetTransferList(this.value, false);
					break;
				case 1:
					break;
				case 2:
					var data = this.value;
					this.groupTransfer.selectList = [];
					for (var i = 0; i < data.length; i++) {
						var item = data[i];
						this.groupTransfer.selectList.push({id: item.id, label: item.label, checked: false})
					}
					this.resetTransferList(this.value, true);
					break;
			}
		}
	}
});
