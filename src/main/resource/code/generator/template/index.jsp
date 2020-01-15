<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ include file="/WEB-INF/jsp/common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>智慧工地</title>
<%@ include file="/WEB-INF/jsp/common/baseImport.jsp"%>
<style type="text/css"></style>
<script>
	var projUUID = "${projUUID}",projId = "${projId}";
	seajs.use(["${path}/js/##urlName##/index"], function(index){	
		index.main();
		window.index = index;
	});
</script>
</head>
<body> 
	<!--用于下载-->
	<iframe id="downloadFrame" name="downloadFrame" style="display:none;"></iframe>
	<h4 class="application-title clearfix">
        <p class="pull-left">
            <i class="fa fa-archive"></i> 标题XXXX
        </p>
        <div class="pull-right search-bar">
        	<a id="toAdd" href="javascript:void(0);" class="btn btn-success pull-right" style="margin-left: 10px;">
                <i class="fa fa-plus"></i>新增
            </a>
            <div class="input-group pull-right">
            	<input id="searchKey" name="searchKey" type="text" class="form-control" placeholder="输入查询条件"/> 
            	<span class="input-group-btn">
            		<a id="searchBtn" href="javascript:;" class="btn btn-primary"><i class="fa fa-search"></i></a>
            	</span>
            </div>
        </div>
    </h4>
    <div class="main table-main">
    	<table id="tab" class="list-table"></table>
    </div>
</body> 	
</html>
	