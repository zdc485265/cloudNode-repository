function saveNote(){
	$("#save_note").click(function(){
					//获取参数
					var $li=$("#note_ul a.checked").parent();
					//获取noteId
					var noteId=$li.data("noteId");
					//获取笔记的标题和内容
					var noteTitle=$("#input_note_title").val().trim();
					var noteBody=um.getContent();
					//ajax请求
					$.ajax({
						url:path+"/note/update.do",
						type:"post",
						data:{"noteId":noteId,"title":noteTitle,"body":noteBody},
						dataType:"json",
						success:function(result){
							if(result.status==0){
								var str="";
								str+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
								//将str替换到li的a元素
								$li.find("a").html(str);
								//提示成功
								alert(result.msg);
							}else{
								
							}
							
						},
						error:function(){
							alert("error save note");
						}
					});
				});
}