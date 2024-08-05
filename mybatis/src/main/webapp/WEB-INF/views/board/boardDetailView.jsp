<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board List</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
	table {
		border:2px solid ;
		border-collapse : collapse;
	}
</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp" />
	<div class="outer" align="center">
		<h1 align="center">게시판 상세조회</h1>
		
		<table border="1">
			<tr>
				<td width="100">글번호</td>
				<td width="500">${b.boardNo }</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${b.boardTitle }</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${b.boardWriter}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${b.count}</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${b.createDate}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td height="100">${b.boardContent}</td>
			</tr>
		</table>
		<br>
		<!-- 로그인 전 -->
		<c:choose>
			<c:when test="${empty loginUser }">
				<table border="1">
					<tr>
						<th width="100">댓글작성</th>
						<th width="400"><textarea cols="53" rows="3" disabled></textarea></th>
						<th width="100"><button disabled>등록</button></th>
					</tr>
				</table>
			</c:when>
		
			<c:otherwise>
					<!-- 로그인 후 -->
				<form id="replyForm">
					<table border="1">
						<tr>
							<th width="100">댓글작성</th>
							<th width="400"><textarea cols="53" rows="3" name="inputReply" id="inputRe"></textarea></th>
							<th width="100"><button id="reBtn">등록</button></th>
							
						</tr>
					</table>
							<input type="hidden" name="replyWriter" value="${loginUser.userId }">
							<input type="hidden" name="boardNo" value="${b.boardNo }">
				</form>
			</c:otherwise>
		</c:choose>
		<div id="replyList">
            <table>
                <tr>
                    <th colspan="3" style="text-align:center">댓글(${rlist.size() })</th>
                </tr>
                <c:forEach var="r" items="${rlist }">
                    <tr>
                        <td>${r.replyWriter }</td>
                        <td>${r.replyContent }</td>
                        <td>${r.createDate.substring(0,10) }</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
	</div>
</body>

<script>
$(document).ready(function() {
    $("#reBtn").click(function(event) {
        event.preventDefault(); // 폼의 기본 제출을 방지
        						// .serialize() : form안의 input, select, textarea의 value값을 간단하게 표준 url인코딩 형태의 문자열로 만들어줌
        						//				ex) content=내용&bnum=2&userId=user02
        var formData = $("#replyForm").serialize();
        $.ajax({
            type: "POST",
            url: "reply.re", // 댓글을 추가하는 서블릿
            data: formData,
            success: function(response) {
                // 댓글 작성 후, 댓글 목록을 새로 고침
                loadReplyList();
                // 입력된 댓글 초기화
                $("#inputRe").val("");
            },
            error: function() {
                alert("댓글 작성에 실패했습니다.");
            }
        });
    });

    function loadReplyList() {
        $.ajax({
            type: "GET",
            url: "getReplies.bo", // 댓글 목록을 가져오는 서블릿
            data: { boardNo: $("#replyForm input[name='boardNo']").val() },
            success: function(response) {
                var replyListHtml = "";
                $.each(response, function(index, reply) {
                    replyListHtml += "<tr><td>" + reply.replyWriter + "</td>" +
                                     "<td>" + reply.replyContent + "</td>" +
                                     "<td>" + reply.createDate.substring(0,10) + "</td></tr>";
                });
                $("#replyList table").html(replyListHtml);
            },
            error: function(xhr, status, error) {
                alert("댓글 목록을 불러오는데 실패했습니다.");
            }
        });
    }
});
</script>




</html>