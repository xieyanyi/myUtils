<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ include file="/WEB-INF/jsp/common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>智慧工地</title>
<%@ include file="/WEB-INF/jsp/common/baseImport.jsp"%>
<style type="text/css"></style>
<script>
	seajs.use(["${path}/js/sys/new/treeIndex"], function(index){	
		index.main();
		window.index = index;
	});
</script>
</head>
<body>
	<div class="main">
		<h4 class="application-title clearfix" style="margin-bottom: 0;">
			<p class="pull-left">
				<i class="fa fa-check-square-o"></i> <p id = title>通知公告</p>	
			</p>
		</h4>
		<div class="main-left left-tree">
			<div align="center" id="noContent" hidden="hidden">
				<p >没有内容？点击下方创建</p>
				<a href="javascript:void(0);" class="btn btn-default"<i class="fa fa-plus"></i>创建</a>
			</div>
			<div id="tree" class="ztree"></div>
		</div>
		
		<div class="main-right tree-right">
			<div class="procedure-list" style="padding: 10px;"></div>
        </div>
	</div>
</body>
</html>
	
