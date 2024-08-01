<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>    
<!DOCTYPE html>
<html>
<head>
<style>
    .enrollTable{
        font-size: 17px;
    }
</style>
<meta charset="UTF-8">
<title>Enroll Form</title>
</head>
<body>

	<jsp:include page="../common/menubar.jsp"/>
    <div class="outer">
        <h2 align="center">회원가입</h2>
        <form action="insert.me" method="post" id="enrollForm">
            <table align="center" class="enrollTable">
                <tr>
                    <td>* ID</td>
                    <td>
                        <input name="userId" id="id" required>
                        <div id="checkResult" style="font-size: 0.8em; display: none;" ></div>
                    </td>
                </tr>
                <tr>
                    <td>* PWD</td>
                    <td><input type="password" name="userPwd" required></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input name="userName"></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><input type="email" name="email"></td>
                </tr>
                <tr>
                    <td>Birthday</td>
                    <td><input name="birthday" placeholder="생년월일(6자리)"></td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td>
                        <input type="radio" name="gender" value="M">남&emsp;
                        <input type="radio" name="gender" value="F">여
                    </td>
                </tr>
                <tr>
                    <td>* Phone</td>
                    <td><input name="phone" required></td>
                </tr>
                <tr>
                    <td>* Address</td>
                    <td><input name="address" required></td>
                </tr>
                <tr></tr>
                <tr></tr>
                <tr></tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="reset" value="초기화">&emsp;
                        <input type="submit" value="회원가입">
                    </td>
                </tr>
            </table>
			
        </form>
        
    </div>
    <script>
    	$(()=>{
    		const $idInput = $("#id");
    		$idInput.keyup(function(){
    			
    			if($idInput.val().length >= 3){
    				$.ajax({
    					url : "idCheck.me",
    					data : {id : $idInput.val()},
    					success : function(result){
    						
    						
    						if(result == 'idN'){
    							$("#checkResult").show();
    							$("#checkResult").css("color","red").text("중복된 아이디가 있습니다");
    							/* $("#enrollForm :submit"),attr("disabled",true); */
    						}else{
    							$("#checkResult").show();
    							$("#checkResult").css("color","green").text("사용 가능한 아이디입니다");
    							$("#enrollForm :submit"),attr("disabled",false);
    						}
    					},
    					error : function(){
							console.log("아이디 중복체크 ajax 통신 실패22")
						}
    				})
    			}else{
					$("#checkResult").hide();
					/* $("#enrollForm :submit").attr("disabled",true); */
    			}
    		})
    	})
    </script>
</body>
</html>