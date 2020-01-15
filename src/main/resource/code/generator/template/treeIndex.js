define(function(require, exports, module) {
	"use strict";

	require("/{path}/js/common/baseCommon");
	require('ztree');
	require.async("bootstrap");
	require("layer-js");
	var $ = jQuery = require('jquery');
	var dialog = require("/{path}/js/common/dialog");
	var treeObj = null, 
	
	createRoot = function(){
		var path = contextPath + "/sys/new/classify/" + projUUID + "/toEdit.html";
		dialog.openFrameBtnDialg("新增", path, 600, 300, function(layeri, layero) {
			layero.find('iframe')[0].contentWindow.edit.save(function(returnData) {
				if(returnData.success){
					$("#noContent").hide();
				}
				var treeObj = $.fn.zTree.getZTreeObj("tree");
				treeObj.reAsyncChildNodes(null, "refresh"), dialog.close(layeri);
			});
		});
	},
	
	initTree = function() {
		var getFirstChild = function(node){
			return node && node.children && node.children.length > 0 ? getFirstChild(node.children[0]) : node;
		},
		setting = {
			view : {
				selectedMulti : false,
				addHoverDom : function(treeId, treeNode) {
					var sObj = $("#" + treeNode.tId + "_span");
					if (treeNode.editNameFlag || $("#" + treeNode.tId + "_add").length > 0 || treeNode.level >= 2) return;
					sObj.after("<span class='button add' id='" + treeNode.tId + "_add' title='新增分类' onfocus='this.blur();'></span>");
					var btn = $("#" + treeNode.tId + "_add");
					btn && btn.bind("click", function(){
						var path = contextPath + "/sys/new/classify/" + projUUID + "/toEdit.html?parentId=" +  treeNode.id;
						dialog.openFrameBtnDialg("新增", path, 600, 300, function(layeri, layero) {
							layero.find('iframe')[0].contentWindow.edit.save(function(returnData) {
								treeObj.reAsyncChildNodes(null, "refresh"), dialog.close(layeri);
							});
						});
					});
				},
				removeHoverDom : function(treeId, treeNode) {
					$("#" + treeNode.tId + "_add").unbind().remove();
				}
			},
			
			edit : {
				drag : {
					isCopy : false, // 禁止拖拽操作
					isMove : false
				},
				enable : true, // 设置 zTree 是否处于编辑状态
				editNameSelectAll : true, // 节点编辑名称 input 初次显示时,设置 txt
				// 内容是否为全选状态。 [
				removeTitle: "删除分类",
				renameTitle : "编辑分类",
				showAddBtn :  function(treeId, treeNode) {
					return !treeNode.isParent;
				},
				showRemoveBtn :  function(treeId, treeNode) {
					return !treeNode.isParent && treeNode.level != 0;
				},
				showRenameBtn : function(treeId, treeNode) {
					return true;
				}
			},
			async : {
				enable : true,
				url : contextPath + '/sys/new/classify/' + projUUID + '/list.json',
				type : "POST",
				dataFilter : function ajaxDataFilter(treeId, parentNode, responseData) {
					if(responseData.rows.length <= 0){
						$("#noContent").show();
					}
					return responseData.rows;
				}
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "parentId",
					rootPId : null
				},
				key : {
					name: "name"
				}
			},
			callback : {
				beforeEditName : function(treeId, treeNode) {
					var path = contextPath + "/sys/new/classify/" + projUUID + "/toEdit.html?id=" +  treeNode.id;
					dialog.openFrameBtnDialg("编辑", path, 600, 300, function(layeri, layero) {
						layero.find('iframe')[0].contentWindow.edit.save(function(returnData) {
							treeObj.reAsyncChildNodes(null, "refresh"), dialog.close(layeri);
						});
					});
					return false;
				},
				beforeRemove : function(treeId, treeNode) {
					if (treeNode.isParent) {
						dialog.warning("请先删除该分类下的子分类");
					} else {
						dialog.question("确认要删除【" + treeNode.name + "】吗？", function() {
							var path = contextPath + "/sys/new/classify/" + projUUID + "/delete.json";
							ajaxCallBack(path, { id : treeNode.id }, function(returnData) {
								returnData && returnData.success ? treeObj.reAsyncChildNodes(null, "refresh") : dialog.error(returnData.msg);
							});
						});
					}
					return false;
				},
				onClick : function(event, treeId, treeNode, clickFlag) {
					//debugger;
					if(!treeNode) return ;
					if (treeNode.isParent) {
						treeObj.expandNode(treeNode);
					}else{
						var classifyId = treeNode.id ;
						if(!classifyId) $(".procedure-list").empty();
						$(".procedure-list").load(contextPath + '/sys/new/'+projUUID+'/index.html?classifyId='+classifyId, function(){
							layer.photos({
						    	photos: '.spic',
						    	shade: false,
					        	closeBtn: 1,
					        	anim: 5 
							});
						});
						
					}
				},
				onAsyncSuccess : function(event, treeId, treeNode, msg) {
					var nodes = treeObj.getNodes(), node = getFirstChild(treeObj.getNodes()[0]);
					treeObj.expandAll(true);
					treeObj.selectNode(node);                                                                                                                                                                                                      
					treeObj.setting.callback.onClick(null, treeId, node);
				}
			}
		};
		treeObj = $.fn.zTree.init($("#tree"), setting);
	}, 
	setSize = function(){
		var h = $(window).height() - $(".application-title").height() - 10;
		$(".main-left,.main-right").height(h);
	},
	refreshTree = function(){
		treeObj.reAsyncChildNodes(null, "refresh");
	},
	main = function() {
		initTree();
		setSize();
		$(window).resize(setSize);
		
		$("#noContent").on('click', createRoot);
	}
	
	exports.main = main;
	exports.refreshTree = refreshTree;
	
});