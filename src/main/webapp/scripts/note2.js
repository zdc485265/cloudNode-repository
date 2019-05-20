function loadNote(){
	$("#note_ul").on("click","li",function(){
		$("#note_ul a").removeClass("checked");
		$(this).find("a").addClass("checked");
		var noteId=$(this).data("noteId");
		$.ajax({
			url:path+"/note/load.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
				//获取返回的笔记标题
				var title=result.data.cn_note_title;
				//获取返回笔记内容
				var body=result.data.cn_note_body;
				//设置笔记标题
				$("#input_note_title").val(title);
				//设置笔记内容
				um.setContent(body);
				}
			},
			error:function(){
				alert("error refer note");
			}
		});
	});
}