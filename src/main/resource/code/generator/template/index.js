define(function(require, exports, module) {
	"use strict";
	require("/{path}/js/common/baseCommon");
	require('bootstrap-table-zh');
	//require('bootstrap-table-treegrid');
	var $ = jQuery = require('jquery'),
	path = contextPath+"/##urlName##/"+projUUID,
	dialog = require("/{path}/js/common/dialog"), 
	initTable = function(){
		$('#tab').bootstrapTable({
			height : initHeight(),
			url : path + '/list.json?projId='+projId,
            columns : [ {
				title : 'ID',
				field : 'id',
				width : '220px',
				halign : 'center',
				align : 'center',
				visible : false,
				formatter: function (value, row, index) {  
                    return value ;  
                }
			},{
				title : '操作',
				field : 'operation',
				width : '100px',
				align : 'center',
				cellStyle : function(value, row, index) {
					return { classes : 'td-btns' }
				},
				formatter : function(value, row, index) {
					return [
		                '<a class="fa fa-pencil toEdit" href="#" title="编辑"></a>', 
		                '<a class="fa fa-trash-o toDel" href="#" title="删除"></a>'
					].join("");
					//return operator.join("");
				},
				events : {
					'click .toEdit' : function(e, value, row, index) {
						toEdit(row.id);
					},
					'click .toDel' : function(e, value, row, index) {
						dialog.question("确定要删除该记录吗？", function() {
							ajaxCallBack(path + "/delete.json", {id : row.id }, function(returnData) {
								returnData && returnData.success ? $('#tab').bootstrapTable("refresh") : dialog.error(returnData.msg);
							});
						});
					}
				}
			}],
			rowStyle: function (row, index) {
				 return {} ;//{ css: {"background-color": "#f6f7fc"} };
			},
			onLoadSuccess : function(data){

			}
        });
	},
	toEdit = function(id){
		var url = path + "/toEdit.html";
		var title = "新增" ;
		if(id){
			url = path + "/toEdit.html?id="+id;
			title = "编辑" ;
		} 
		dialog.openFrameBtnDialg(title, url, 680, 450,function(index, layero) {
			layero.find('iframe')[0].contentWindow.edit.save(function(returnData) {
				returnData && returnData.success ? $('#tab').bootstrapTable("refresh") : dialog.error(returnData.msg);
				dialog.close(index);
			});
		});
	},
	initHeight = function(){
		var hHeight = $(".btn-row").height();
		return $(window).height() - 70;
	},
	main = function() {
		initTable();
		
		$("#toAdd").on("click",function(){
			toEdit();
		});
		
		$(".dropdown .dropdown-menu a").click(function(){
			var atext = $(this).text(), data = $(this).data("value");
			$(this).parents(".dropdown").find("span").text(atext).data("value", data);
		});
		
		$("#searchBtn").click(function(){
			//var type = $(".dropdown span").eq(0).data("value");
			var searchKey = $("#searchKey").val();
			var option = {'projId':projId};
			$('#tab').bootstrapTable('refreshOptions', {
				url : path + "/list.json",
				queryParams : function(params) { return $.extend({}, { pageNo : 1, pageSize : 15 }, option); }
			});
		});
	};
	exports.main = main;
});
