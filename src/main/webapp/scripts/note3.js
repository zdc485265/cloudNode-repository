function saveNote(){
	$("#save_note").click(function(){
					//��ȡ����
					var $li=$("#note_ul a.checked").parent();
					//��ȡnoteId
					var noteId=$li.data("noteId");
					//��ȡ�ʼǵı��������
					var noteTitle=$("#input_note_title").val().trim();
					var noteBody=um.getContent();
					//ajax����
					$.ajax({
						url:path+"/note/update.do",
						type:"post",
						data:{"noteId":noteId,"title":noteTitle,"body":noteBody},
						dataType:"json",
						success:function(result){
							if(result.status==0){
								var str="";
								str+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
								//��str�滻��li��aԪ��
								$li.find("a").html(str);
								//��ʾ�ɹ�
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