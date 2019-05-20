function loadBookNotes(){
	//获取参数
	$("#book_ul").on("click","li",function(){
		//设置选中效果
		$("#book_ul a").removeClass("checked");
		$(this).find("a").addClass("checked");
		//获取参数
	var bookId=$(this).data("bookId");
	//发送ajax请求
	$.ajax({
		url:path+"/note/loadNotes.do",
		type:"post",
		data:{"bookId":bookId},
		dataType:"json",
		success:function(result){
			//获取笔记信息
			var notes=result.data;
			//清除原列表信息
			$("#note_ul").empty();
			//$("#note_ul li").remove();
			//循环添加li
			for(var i=0;i<notes.length;i++){
				var noteId=notes[i].cn_note_id;
				var noteTitle=notes[i].cn_note_title;
				createNoteLi(noteId,noteTitle);
				$("#note_ul a").removeClass("checked");
				$(this).find("a").addClass("checked");
			}
		},
		error:function(){
			alert("error load note");
		}
	});
	});
};
function createNoteLi(noteId,noteTitle){
	var sli="";
	sli+='<li class="online">';
	sli+="<a  class='checked'>";
	sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
	sli+='</a>';
	sli+='<div class="note_menu" ';
	sli+="tabindex='-1'>";
	sli+='<dl>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_move" ';sli+="title='remove...'>";sli+='<i class="fa fa-random"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_share" ';sli+="title='share'>";sli+='<i class="fa fa-sitemap"></i></button></dt>';
	sli+='<dt><button type="button" class="btn btn-default btn-xs btn_delete" ';sli+="itle='delete'>";sli+='<i class="fa fa-times"></i></button></dt>';
	sli+='</dl>';
	sli+='</div>';
	sli+='</li>';
	var $li=$(sli);
	$li.data("noteId",noteId);
	$("#note_ul").append($li);
}