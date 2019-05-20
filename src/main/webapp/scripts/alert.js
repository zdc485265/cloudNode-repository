//�����½��ʼǱ��Ի���
function alertAddBookWindow(){
					//�����½��ʼǱ��Ի���
					$("#can").load("alert/alert_notebook.html");
					//��ʾ����
					$(".opacity_bg").show();
};

//�رնԻ���
function closeAlertWindow(){
	//���div����
	$("#can").html("");
	//���ر���ɫ
	$(".opacity_bg").hide();
};

//�����ʼ�
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
	//�ж��Ƿ��бʼǱ�ѡ��
	var $li=$("#book_ul a.checked").parent();
	if($li.length==0){
		alert("��ѡ��ʼ�");
	}else{
	//�����½��ʼǶԻ���
	$("#can").load("alert/alert_note.html");
	//��ʾ����
	$(".opacity_bg").show();
	}
};
function sure_addNote(){
	//��ȡ�������
	//��ȡ�ʼǱ���
	var title=$("#input_note").val().trim();
	//��ȡ�û�ID
	var userId=getCookie("userId");
	//��ȡ�ʼǱ�ID
	var $li=$("#book_ul a.checked").parent();
	var bookId=$li.data("bookId");
	//���ݸ�ʽ���
	var ok=true;
	if(title==""){
		//�ж��Ƿ�Ϊ��
		ok=false;
		$("#title_span").html("���ⲻ��Ϊ��");
	}
	if(userId==null){
		//����Ƿ�ʧЧ
		ok=false;
		window.location.href="log_in.html";
	}
	//����ajax����
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
				alert("�����ʼ�ʧ��");
			}
		});
	}
}