//���س���������������ĵ�һҳ
function sureSearchShare(event){
	var code=event.keyCode;
	if(code==13){
		$("#search_ul li").remove();
		//��ȡ�������
		var keyword=$("#search_note").val().trim();
		page=1;
		loadPageShare(keyword,page);
	}
}
//������࣬���������������һҳ
function moreSearchShare(){
	//��ȡ����
	var keyword=$("#search_note").val().trim();
	page=page+1;
	//����ajax��������б�
	loadPageShare(keyword,page);
};
//�����������
function loadPageShare(keyword,page){
	$.ajax({
		url:path+"/share/search.do",
		type:"post",
		data:{"keyword":keyword,"page":page},
		dataType:"json",
		success:function(result){
			//��ȡ����
			var shares=result.data;
			for(var i=0;i<shares.length;i++){
				//��ȡshareId
				var shareId=shares[i].cn_share_id;
				//��ȡshareTitle
				var shareTitle=shares[i].cn_share_title;
				//��ȡli����
				var sli="";
				sli+='<li class="online">';
				sli+="<a  class='checked2'>";
				sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+shareTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
				sli+='</a>';
				sli+='</li>';
				var $li=$(sli);
				//��shareId
				$li.data("shareId",shareId);
				//��li������ӵ�ul����
				$("#search_ul").append($li);
				//�л���ʾ��
				$("#pc_part_2").hide();//����
				$("#pc_part_6").show();//��ʾ
			}
		},
		error:function(){
			alert("����ʧ��");
		}
	});
};