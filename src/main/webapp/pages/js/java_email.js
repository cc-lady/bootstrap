$(function(){
	$('#addButton').click(
		function(){
			$.ajax({
				url: contextRootPath+"/email/insert",//提交地址
				data: $('#addForm').serialize(),//将表单数据序列化
				type: 'POST',
				dataType: 'text',
				contentType:"application/x-www-form-urlencoded",
				success: function (result) {
					//var resultData = result.responseText;//var jsonData = eval("("+data.responseText+")");
					alert(result);
					//关闭当前窗口
					/*window.close();
					opener.location.reload(); */
					//bootstrap关闭模态窗口
					$("#myAdd").modal('hide');  //手动关闭
					//页面刷新
					location.reload()
				},
				error: function (xhr) {
					alert("错误提示： " + xhr.status + " " + xhr.statusText);
					//关闭当前窗口
					/*window.close();
					opener.location.reload();*/ 
					//bootstrap关闭模态窗口
					$("#myAdd").modal('hide');  //手动关闭
					//页面刷新
					location.reload()
				}
			});
		}
	);
	
	$('#mailTo').click(
		function(){
			$.ajax({
				url: contextRootPath+"/email/mailTo",//提交地址
				data: $('#mailList').serialize(),//将表单数据序列化
				type: 'POST',
				dataType: 'text',
				success: function (result) {
					//var resultData = result.responseText;//var jsonData = eval("("+data.responseText+")");
					alert(result);
				},
				error: function (xhr) {
					alert("错误提示： " + xhr.status + " " + xhr.statusText);
				}
			});
		}
	);
})

function del(){
	alert("删除一个email");
}

function modify(){
	alert("修改一个email");
}