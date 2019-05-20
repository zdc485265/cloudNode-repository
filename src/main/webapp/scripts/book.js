
//根据用户id显示笔记本列表
function loadUserBooks(){
	//获取userId
	var userId=getCookie("userId");

	//判断是否获取到有效userId
	if(userId==null){
		window.location.href("log_in.html");
	}else{
		//发送ajax请求
		$.ajax({
			url:path+"/book/loadBooks.do",
			type:"post",
			data:{"userId":userId},
			dataType:"json",
			success:function(result){
				//判断查询是否成功
				if(result.status==0){
					//获取笔记本集合
					var books=result.data;
					for(var i=0;i<books.length;i++){
						//获取笔记本ID
						var bookId=books[i].cn_notebook_id;
						//获取笔记本名称
						var bookName=books[i].cn_notebook_name;
						//创建一个笔记本列表的li元素
						creatBookLi(bookId,bookName);
					}
				}
			},
			error:function(){
				alert("笔记本加载失败！");
			}
		});
	}
	
};
//创建一个笔记本li元素
function creatBookLi(bookId,bookName){
	var sli='<li class="online">';
	sli+='<a>';
	sli+='<i class="fa fa-book" title="online" rel="tooltip-bottom">';
	sli+='</i>'+bookName+'</a></li>';
	//将sli字符串转换成jQuery对象li元素
	var $li=$(sli);
	//将bookId值与jquery对象绑定
	$li.data("bookId",bookId);
	//将li元素添加到笔记本ul列表区
	$("#book_ul").append($li);
}
/*
 * 事件绑定：
 * 静态绑定：元素.click(function(){});
 * 动态绑定：
 * 父元素.on("click","li",fn)
 * 
 * UEdit
 * 用js开发的开源组件
 * 
 * 为什么
 * 通过js代码实现表单功能的增强
 * 
 * 如何用？
 * 1.引用脚本
 * 2.实例化
 * 3.通过<srcipt>指定位置
 * 
 * 显示笔记信息
 * ##发送ajax请求
 * 发送事件："保存笔记"按钮点击时间（静态）
 * 请求参数：笔记id 标题title 内容 body
 * 请求地址：/note/update.do
 * ##服务器的处理
 * updateNoteController(id，title，body)
 * NoteService.updateNote(Note)
 * NoteDao.updateNote(Note)
 * cn_note(更新处理)
 * ##ajax回调处理
 * success：
 * 对被更新的笔记名称进行更新
 * <li><a class="checked"></a></li>
 * 
 * ##使用alert
 * #如何显示
 * 通过load（） 加载html
 * 通过hide（） 隐藏html
 * 
 * ##创建笔记本
 * #发生ajax请求
 * 发送事件：对话框中的"创建"按钮的单机(动态)
 * 请求参数：笔记名称和用户ID
 * 请求地址：/book/add.do
 * #服务器处理
 * AddBookController.execute
 * BookService.addBook(book)
 * BookDao.sava(book)
 * cn_notebook(insert)
 * json响应
 * #ajax回调请求
 * success:
 * 关闭对话框
 * 添加一个笔记本li
 * error:
 * 提示创建笔记本失败
 * 
 * 
 * ##创建笔记
 * #发生ajax请求
 * 发送事件：对话框中的"创建"按钮的单机(动态)
 * 请求参数：笔记名称,笔记本ID,用户ID
 * 请求地址：/note/add.do
 * #服务器处理
 * AddNoteController.execut
 * NoteService.addNote
 * NoteDao.save
 * cn_note(insert)
 * #ajax回调处理
 * success:
 * 关闭对话框
 * 解析JSON数据，生成一个li元素添加到笔记列表
 * 提示创建笔记成功
 * error:
 * 提示创建笔记本失败
 * 
 * 显示笔记下拉菜单
 * 1.通过点击‘箭头’按钮显示三个菜单
 * -获取div对象 slideDown（1000）
 * 
 * 笔记分享
 * 发送请求
 * -发送事件：点击“分享”按钮
 * -请求参数：noteId
 * -请求地址：/share/add.do
 * 服务器处理
 * 创建Share类
 * Controller
 * Service
 * Dao-insert
 * cn_share
 * 回调处理
 * 
 * 
 * 分享笔记搜索功能
 * 功能描述：用户搜索关键词，然后回车，触发查询
 * 
 * #发送ajax请求
 * ##发生事件：输入关键词后，点击回车
 * ##请求参数：输入的关键词
 * ##请求地址：/share/search.do
 * #服务处理
 * Controller
 * Service
 * Dao(select)
 * cn_share
 * 
 * select * from cn_share like %关键词%
 * select * from cn_share like #{title}
 * 
 * select * from cn_share limit m,n
 * m:记录的位置
 * n:每页显示的最大记录数
 * 
 * select * from cn_share like #{title} limit #{begin},3
 * 
 * ajax回调请求
 * success:
 * 显示搜索笔记结果列表（pc_part_6）
 * 将解析后的搜索结果，添加到列表中
 * error：
 * 提示搜索失败
 * 
 * $("#Input_id").keydown(function(event){
 * 	var code=event.keyCode;
 * 	if(code==13){
 * 		发送ajax请求
 * 	}
 * });
 * 
 * 17号 16点02分
 */