define(function(require, exports, module) {
	"use strict";
	require("/{path}/js/common/baseCommon");
	require("bootstrap");
	var $ = jQuery = require('jquery'),
	path = contextPath+"/##urlName##",
	dialog = require("/{path}/js/common/dialog"), 
	layerIndex = parent.layer.getFrameIndex(window.name),
	save = function(end){
		var data = converFormData($('#formInfo')), url = path + "/saveUpdate.json";
		dialog.question("确定要保存吗？", function() {
			ajaxCallBack(url, data, function(returnData) {
				returnData && returnData.success ? (end && end(returnData)) : dialog.error(returnData.msg);
			});
		});
	},
	main = function(){
		
	};
	exports.main = main;
	exports.save = save;
});
