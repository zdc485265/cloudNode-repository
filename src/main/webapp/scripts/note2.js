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
				//��ȡ���صıʼǱ���
				var title=result.data.cn_note_title;
				//��ȡ���رʼ�����
				var body=result.data.cn_note_body;
				//���ñʼǱ���
				$("#input_note_title").val(title);
				//���ñʼ�����
				um.setContent(body);
				}
			},
			error:function(){
				alert("error refer note");
			}
		});
	});
}