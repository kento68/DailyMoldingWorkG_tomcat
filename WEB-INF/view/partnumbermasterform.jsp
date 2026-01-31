<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<Partnumbermaster> list=(List<Partnumbermaster>)request.getAttribute("list");
    Partnumbermaster partnumbermaster=(Partnumbermaster)request.getAttribute("partnumbermaster");
	
	String id=partnumbermaster==null ? "":String.valueOf(partnumbermaster.getId());
	String partnumber=partnumbermaster == null ? "":String.valueOf(partnumbermaster.getPartnumber());
	String partname=partnumbermaster == null ? "":String.valueOf(partnumbermaster.getPartname());
    String sparepartnumber1=partnumbermaster == null ? "":String.valueOf(partnumbermaster.getSparepartnumber1());
    String sparepartnumber2=partnumbermaster == null ? "":String.valueOf(partnumbermaster.getSparepartnumber2());
    String sparepartnumber3=partnumbermaster == null ? "":String.valueOf(partnumbermaster.getSparepartnumber3());
    String takenumber=partnumbermaster == null ? "":String.valueOf(partnumbermaster.getTakenumber());

	String err=(String)request.getAttribute("err");
	String msg=(String)request.getAttribute("msg");
	String arrangementLabel=(String)request.getAttribute("arrangementLabel");
	
	// セッションスコープからユーザー情報を取得
	User loginUser = (User) session.getAttribute("loginUser");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Partnumbermasterform.css">
<title>DailyMoldingWorkG</title>
</head>
<body>

<% if(err !=null){%>
<div class="alert alert-danger" role="alert">
<%=err %>
</div>
<%} %>
<% if(msg !=null){%>
<div class="alert alert-success" role="alert">
<%=msg %>
</div>
<%} %>

<div class="container-fluid" style="margin-top:20px;">

<form action="<%= request.getContextPath() %>/PartnumbermasterForm" method="post" >

<header>
	<ul> 
	    <!-- loginUser分岐処理 -->
    	<li><a href="<%= request.getContextPath() %>/main">SingleData</a></li>
    	<% if(loginUser != null) {%>
    	<li><a href="<%= request.getContextPath() %>/PartnumbermasterList">PartnumbermasterList</a></li>
    	<li class="contact"><c:out value="${loginUser.name}"/>:ログイン中</li>
    	<% } else { %>
    	<li class="contact"><c:out value="localuser"/>:ログイン中</li>
    	<% } %>
	</ul>
</header>

<div class="form-row">
  <div class="form-group col-sm-6">
    <label for="partnumber" class="required-label" id="partnumber_label"><b>品番:</b></label>
    <input type="text" class="form-control" id="partnumber" name="partnumber" value="<%=partnumber%>" placeholder="品番を入力してください" required>
 </div>
</div>

<div class="form-row">
  <div class="form-group col-sm-6">
    <label for="partname" class="required-label" id="partname_label"><b>品名:</b></label>
    <input type="text" class="form-control" id="partname" name="partname" value="<%=partname%>" placeholder="品名を入力してください" required>
 </div>
</div>

<div class="form-row">
  <div class="form-group col-sm-6">
    <label for="sparepartnumber1" class="" id="sparepartnumber1_label"><b>予備品番1:</b></label>
    <input type="text" class="form-control" id="sparepartnumber1" name="sparepartnumber1" value="<%=sparepartnumber1%>" placeholder="予備品番1を入力してください">
 </div>
</div>

<div class="form-row">
  <div class="form-group col-sm-6">
    <label for="sparepartnumber2" class="" id="sparepartnumber2_label"><b>予備品番2:</b></label>
    <input type="text" class="form-control" id="sparepartnumber2" name="sparepartnumber2" value="<%=sparepartnumber2%>" placeholder="予備品番2を入力してください">
 </div>
</div>

<div class="form-row">
  <div class="form-group col-sm-6">
    <label for="sparepartnumber3" class="" id="sparepartnumber3_label"><b>予備品番3:</b></label>
    <input type="text" class="form-control" id="sparepartnumber3" name="sparepartnumber3" value="<%=sparepartnumber3%>" placeholder="予備品番3を入力してください">
 </div>
</div>

<div class="form-row">
  <div class="form-group col-sm-6">
    <label for="takenumber" class="required-label" id="takenumber_label"><b>取り数:</b></label>
    <input type="number" class="form-control" id="takenumber" name="takenumber" value="<%=takenumber%>" placeholder="取り数を入力してください" required>
 </div>
</div>

<%if(!id.isEmpty()) {%>
<input type="hidden" name="id" value="<%=id %>">
<%} %>

<button type="submit" class="btn btn-primary custom-btn" id="registration" style="margin-left: 15px;" onclick="submitForm()"><%=id.isEmpty()?"登録":"更新" %></button>
 
<footer>
	<ul>
    	<li><a href="<%= request.getContextPath() %>/Logout">Logout</a></li>
    </ul>
	
</footer>
 
</form>
</tr>

<script>

const forms=document.getElementsByClassName("form-control");
const alerts=document.getElementsByClassName("alert");
for(let i=0;i<forms.length;i++){
	forms[i].addEventListener("focus",()=>{
		for(let j=0;j<alerts.length;j++){
			alerts[j].style.display="none";
		}
	});
}
//イベントリスナーを追加
document.getElementById('partnumber').addEventListener('change', function () {
    preventDuplicate(this);
});

<!-- 品番名と品名登録制限 -->
document.getElementById('partname').addEventListener('input', function() {
    var partnumberInput = document.getElementById('partnumber');
    var partnameInput = document.getElementById('partname');

    if (partnumberInput.value.trim() !== "" && partnumberInput.value.trim() === partnameInput.value.trim()) {
        alert('品番と品名が同じです。別の名前を入力してください。');

        // 入力フォームの値をクリア
        partnumberInput.value = "";
        partnameInput.value = "";

        // フォーカスを戻す（必要に応じて）
        partnameInput.focus();
    }
});

</script>
</body>