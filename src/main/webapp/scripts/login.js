function userLogin(){
				//���԰��¼�
				//alert("�����ˣ�");
				//��ȡ����
				var name=$("#count").val().trim();
				var password=$("#password").val().trim();
				//alert(name+","+password);
				$("#count_span").html("");
				$("#password_span").html("");
				//��ʽ���
				var ok=true;
				if(name==""){
					$("#count_span").html("�û�����Ϊ��");
					ok=false;
				}
				if(password==""){
					$("#password_span").html("���벻��Ϊ��");
					ok=false;
				}
				//��������
				if(ok){//����ʽͨ��
					//����ajax����
					$.ajax({
						url:path+"/user/login.do",
						type:"post",
						data:{"name":name,"password":password},
						dataType:"json",
						success:function(result){
							//result�Ƿ��������ص�JSON���
							console.log(result.status);
							
							if(result.status==0){
								//���û���Ϣ���浽cookie
								var userId=result.data.cn_user_id;
								addCookie("userId",userId,2);
								window.location.href="edit.html";
								
							}else if(result.status==1){
								//�û�������
								$("#count_span").html(result.msg);
							}else if(result.status==2){
								$("#password_span").html(result.msg);
							}
						},
						error:function(){
							alert("��¼ʧ�ܣ�");
						}
					});
				}
			}