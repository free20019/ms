var BSTable = Vue.extend({
	template: '<table class="table" :class="{\'table-bordered\': bordered}"><thead><tr style="white-space: nowrap"><th v-for="item in fields" :key="item.id" v-text="item.title"></th></tr></thead><tbody><tr v-for="(item, index) in data" :key="index"><td v-for="fieldItem in fields" :key="fieldItem.title" v-text="item[fieldItem.id]" :style="setStyle(fieldItem)"></td></tr></tbody></table>',
	props: {
		bordered: {
			type: Boolean,
			default: false
		},
		fields: Array,
		data: Array,
	},
	methods: {
		setStyle: function (item) {
			var style = {};
			var alignType = ['left', 'center', 'right'];
			var whiteSpaceType = ['nowrap', 'normal'];
			if (item.width) {
				if (typeof item.width === 'number') style.width = item.width + 'px';
				else style.width = item.width;
			}
			if (item.align && alignType.indexOf(item.align) >= 0) style.align = item.align;
			if (item.whiteSpace && whiteSpaceType.indexOf(item.whiteSpace) >= 0) style.whiteSpace = item.whiteSpace;
			return style;
		}
	}
});