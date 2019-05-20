
//�����û�id��ʾ�ʼǱ��б�
function loadUserBooks(){
	//��ȡuserId
	var userId=getCookie("userId");

	//�ж��Ƿ��ȡ����ЧuserId
	if(userId==null){
		window.location.href("log_in.html");
	}else{
		//����ajax����
		$.ajax({
			url:path+"/book/loadBooks.do",
			type:"post",
			data:{"userId":userId},
			dataType:"json",
			success:function(result){
				//�жϲ�ѯ�Ƿ�ɹ�
				if(result.status==0){
					//��ȡ�ʼǱ�����
					var books=result.data;
					for(var i=0;i<books.length;i++){
						//��ȡ�ʼǱ�ID
						var bookId=books[i].cn_notebook_id;
						//��ȡ�ʼǱ�����
						var bookName=books[i].cn_notebook_name;
						//����һ���ʼǱ��б��liԪ��
						creatBookLi(bookId,bookName);
					}
				}
			},
			error:function(){
				alert("�ʼǱ�����ʧ�ܣ�");
			}
		});
	}
	
};
//����һ���ʼǱ�liԪ��
function creatBookLi(bookId,bookName){
	var sli='<li class="online">';
	sli+='<a>';
	sli+='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli+='</i>'+bookName+'</a></li>';
	//��sli�ַ���ת����jQuery����liԪ��
	var $li=$(sli);
	//��bookIdֵ��jquery�����
	$li.data("bookId",bookId);
	//��liԪ����ӵ��ʼǱ�ul�б���
	$("#book_ul").append($li);
}
/*
 * �¼��󶨣�
 * ��̬�󶨣�Ԫ��.click(function(){});
 * ��̬�󶨣�
 * ��Ԫ��.on("click","li",fn)
 * 
 * UEdit
 * ��js�����Ŀ�Դ���
 * 
 * Ϊʲô
 * ͨ��js����ʵ�ֱ����ܵ���ǿ
 * 
 * ����ã�
 * 1.���ýű�
 * 2.ʵ����
 * 3.ͨ��<srcipt>ָ��λ��
 * 
 * ��ʾ�ʼ���Ϣ
 * ##����ajax����
 * �����¼���"����ʼ�"��ť���ʱ�䣨��̬��
 * ����������ʼ�id ����title ���� body
 * �����ַ��/note/update.do
 * ##�������Ĵ���
 * updateNoteController(id��title��body)
 * NoteService.updateNote(Note)
 * NoteDao.updateNote(Note)
 * cn_note(���´���)
 * ##ajax�ص�����
 * success��
 * �Ա����µıʼ����ƽ��и���
 * <li><a class="checked"></a></li>
 * 
 * ##ʹ��alert
 * #�����ʾ
 * ͨ��load���� ����html
 * ͨ��hide���� ����html
 * 
 * ##�����ʼǱ�
 * #����ajax����
 * �����¼����Ի����е�"����"��ť�ĵ���(��̬)
 * ����������ʼ����ƺ��û�ID
 * �����ַ��/book/add.do
 * #����������
 * AddBookController.execute
 * BookService.addBook(book)
 * BookDao.sava(book)
 * cn_notebook(insert)
 * json��Ӧ
 * #ajax�ص�����
 * success:
 * �رնԻ���
 * ���һ���ʼǱ�li
 * error:
 * ��ʾ�����ʼǱ�ʧ��
 * 
 * 
 * ##�����ʼ�
 * #����ajax����
 * �����¼����Ի����е�"����"��ť�ĵ���(��̬)
 * ����������ʼ�����,�ʼǱ�ID,�û�ID
 * �����ַ��/note/add.do
 * #����������
 * AddNoteController.execut
 * NoteService.addNote
 * NoteDao.save
 * cn_note(insert)
 * #ajax�ص�����
 * success:
 * �رնԻ���
 * ����JSON���ݣ�����һ��liԪ����ӵ��ʼ��б�
 * ��ʾ�����ʼǳɹ�
 * error:
 * ��ʾ�����ʼǱ�ʧ��
 * 
 * ��ʾ�ʼ������˵�
 * 1.ͨ���������ͷ����ť��ʾ�����˵�
 * -��ȡdiv���� slideDown��1000��
 * 
 * �ʼǷ���
 * ��������
 * -�����¼��������������ť
 * -���������noteId
 * -�����ַ��/share/add.do
 * ����������
 * ����Share��
 * Controller
 * Service
 * Dao-insert
 * cn_share
 * �ص�����
 * 
 * 
 * ����ʼ���������
 * �����������û������ؼ��ʣ�Ȼ��س���������ѯ
 * 
 * #����ajax����
 * ##�����¼�������ؼ��ʺ󣬵���س�
 * ##�������������Ĺؼ���
 * ##�����ַ��/share/search.do
 * #������
 * Controller
 * Service
 * Dao(select)
 * cn_share
 * 
 * select * from cn_share like %�ؼ���%
 * select * from cn_share like #{title}
 * 
 * select * from cn_share limit m,n
 * m:��¼��λ��
 * n:ÿҳ��ʾ������¼��
 * 
 * select * from cn_share like #{title} limit #{begin},3
 * 
 * ajax�ص�����
 * success:
 * ��ʾ�����ʼǽ���б�pc_part_6��
 * ��������������������ӵ��б���
 * error��
 * ��ʾ����ʧ��
 * 
 * $("#Input_id").keydown(function(event){
 * 	var code=event.keyCode;
 * 	if(code==13){
 * 		����ajax����
 * 	}
 * });
 * 
 * 17�� 16��02��
 */