//弹出新建笔记本对话框
function alertAddBookWindow(){
					//弹出新建笔记本对话框
					$("#can").load("alert/alert_notebook.html");
					//显示背景
					$(".opacity_bg").show();
};

//关闭对话框
function closeAlertWindow(){
	//清空div内容
	$("#can").html("");
	//隐藏背景色
	$(".opacity_bg").hide();
};

//创建笔记
function createNote(){
	var bookName=$("#input_notebook").val();
	var userId=getCookie("userId");
	//ajax
	$.ajax({
		url:path+"/book/add.do",
		type:"post",
		data:{"bookName":bookName,"userId":userId},
		dataType:"json",
		success:function(result){
			closeAlertWindow();
			$("#book_ul").empty("");
			loadUserBooks();
		},
		error:function(){
			alert("error create book");
		}
	});
};

function alertAddNoteWindow(){
	//判断是否有笔记被选中
	var $li=$("#book_ul a.checked").parent();
	if($li.length==0){
		alert("请选择笔记");
	}else{
	//弹出新建笔记对话框
	$("#can").load("alert/alert_note.html");
	//显示背景
	$(".opacity_bg").show();
	}
};
function sure_addNote(){
	//获取请求参数
	//获取笔记标题
	var title=$("#input_note").val().trim();
	//获取用户ID
	var userId=getCookie("userId");
	//获取笔记本ID
	var $li=$("#book_ul a.checked").parent();
	var bookId=$li.data("bookId");
	//数据格式检查
	var ok=true;
	if(title==""){
		//判断是否为空
		ok=false;
		$("#title_span").html("标题不能为空");
	}
	if(userId==null){
		//检查是否失效
		ok=false;
		window.location.href="log_in.html";
	}
	//发送ajax请求
	if(ok){
		$.ajax({
			url:path+"/note/add.do",
			type:"post",
			data:{"userId":userId,"bookId":bookId,"title":title},
			dataType:"json",
			success:function(result){
				var noteId=result.data.cn_note_id;
				closeAlertWindow();
				createNoteLi(noteId,title);
			},
			error:function(){
				alert("创建笔记失败");
			}
		});
	}
}