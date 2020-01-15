<%@ page language="java" pageEncoding="UTF-8" session="false"%>
<%@ include file="/WEB-INF/jsp/common/init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>智慧工地</title>
<%@ include file="/WEB-INF/jsp/common/baseImport.jsp"%>
<script>
	seajs.use(["${path}/js/##urlName##/toEdit"], function(edit){	
		edit.main();
		window.edit = edit;
	});
</script>
<style type="text/css">
</style>
</head>
<body style="background: #fff;">
	<!-- 
	<h4 class="application-title clearfix">
		<p class="pull-left">
	        <a href="javascript:window.history.go(-1);" class="fa fa-arrow-left"></a>
		  	<span> 返回  </span>
		</p>
	</h4>
	-->
	 
	<div class="main container-fluid">
      <div class="new-form">
	     <form id="formInfo" class="form-horizontal">
       		<input type="hidden" id="id" name="id" value="${form.id}" />
##formFields##

			<%-- 
			<div class="form-group">
				<label for="createUserName" class="control-label col-xs-2">创建人：</label>
				<div class="col-xs-3">
					<input type="text" id="createUserName" name="createUserName" class="form-control" placeholder="" maxlength="" value="${form.createUserName}" readonly/>
				</div>
				<label for="createTime" class="control-label col-xs-3">创建时间：</label>
				<div class="col-xs-3">
					<input type="text" id="createTime" name="createTime" class="form-control" placeholder="" maxlength="" value="${form.createTime}" readonly/>
				</div>
			</div>
			<div class="form-group">
				<label for="content" class="control-label col-xs-2">正文内容：</label>
				<div class="col-xs-9">
					<textarea rows="50" id="content" name="content"  maxlength="1024">${form.content}</textarea>
				</div>
			</div>
			 --%>
			 
		<!-- <button type="button" class="btn btn-primary pull-right toSel">选择[注意：按钮不加type会自动提交表单]</button> -->
       </form>
     </div>
	</div>
</body>
</html>