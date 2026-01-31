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
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Partnumbermasterlist.css">
<title>DailyMoldingWorkG</title>
</head>
<body>

<div class="container-fluid" style="margin-top: 40px;">

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

<form action="<%= request.getContextPath() %>/PartnumbermasterList" method="GET" class="form-inline">
  <div class="form-row align-items-center">
    <div class="form-group mr-3">
      <input type="text" id="searchInput" name="searchKeyword" class="form-control" style="width: 600px; margin-top: 10px;" placeholder="検索キーワードを入力してください">
    </div>
    <div class="form-group mx-sm-3">
      <button type="submit" class="btn btn-primary" style="margin-top: 10px;" >検索</button>
    </div>
  </div>
</form>

<form action="<%= request.getContextPath() %>/PartnumbermasterList" method="post" >

<header>
	<ul> 
	    <!-- loginUser分岐処理 -->
	    <li><a href="<%= request.getContextPath() %>/main">SingleData</a></li>
    	<% if(loginUser != null) {%>
    	<li><a href="<%= request.getContextPath() %>/PartnumbermasterForm">PartnumbermasterForm</a></li>
    	<li class="contact"><c:out value="${loginUser.name}"/>:ログイン中</li>
    	<% } else { %>
    	<li class="contact"><c:out value="localuser"/>:ログイン中</li>
    	<% } %>
	</ul>
</header>

<form action="<%= request.getContextPath() %>/PartnumbermasterList" method="POST">
  <% if(list != null && list.size() > 0) { %>
  
    <!-- 選択したデータに対するアクションボタン -->
    <button type="submit" name="action" value="deleteSelected" class="btn btn-danger" style="margin-top: 10px;" onclick="return confirm('選択した項目を削除してよろしいですか？');">選択項目 削除</button>
    
    <table class="table table-striped mt-4">
      <thead>
        <tr>
          <th><input type="checkbox" id="selectAll" onclick="toggleSelectAll(this)"> 全選択</th>
          <th>品番</th>
          <th>品名</th>
          <th>予備品番1</th>
          <th>予備品番2</th>
          <th>予備品番3</th>
          <th>取り数</th>
          <th>操作項目</th>
        </tr>
      </thead>
      <tbody>
        <% for(Partnumbermaster p : list) { %>
        <tr>
          <td><input type="checkbox" name="selectedIds" value="<%= p.getId() %>"></td>
          <td><%= p.getPartnumber() %></td>
          <td><%= p.getPartname() %></td>
          <td><%= p.getSparepartnumber1() %></td>
          <td><%= p.getSparepartnumber2() %></td>
          <td><%= p.getSparepartnumber3() %></td>
          <td><%= p.getTakenumber() %></td>
          <td>
            <a href="<%= request.getContextPath() %>/PartnumbermasterForm?action=update&id=<%= p.getId() %>" class="btn btn-primary">更新</a>
            <a href="<%= request.getContextPath() %>/PartnumbermasterList?action=delete&id=<%= p.getId() %>" class="btn btn-danger" onclick="return confirm('削除してよろしいですか？');">削除</a>
          </td>
        </tr>
        <% } %>
      </tbody>
    </table>
  <% } %>
</form>

<footer class="mt-5">
    <ul class="d-flex justify-content-between align-items-center">
        <li><a href="<%= request.getContextPath() %>/Logout">Logout</a></li>
        <div>
            <form action="<%= request.getContextPath() %>/PartnumbermasterList" method="GET" enctype="multipart/form-data" class="form-inline">
                <button type="submit" class="btn btn-danger" style="margin-right: 5px; margin-bottom: 5px; padding-top: 1px; padding-bottom: 1px;" name="action" value="download">ﾀﾞｳﾝﾛｰﾄﾞ</button>
            </form>
        </div>
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

<!-- リロード制御 -->
document.addEventListener('DOMContentLoaded', function() {
    // セッションストレージからリロードフラグを取得
    var isReloaded = sessionStorage.getItem('reloaded');

    if (!isReloaded) {
        // リロードフラグが存在しない場合、初回ロード
        sessionStorage.setItem('reloaded', 'true'); // リロードフラグを設定
        window.location.reload(); // ページをリロード
    } else {
        // リロード後の処理
        sessionStorage.removeItem('reloaded'); // リロードフラグを削除
        // 初回ロード後に実行する処理をここに記述
        console.log("初回ロード処理が完了しました。");
    }
});

//チェックボックスの全選択/全解除用
function toggleSelectAll(source) {
    const checkboxes = document.getElementsByName('selectedIds');
    for (let i = 0; i < checkboxes.length; i++) {
      checkboxes[i].checked = source.checked;
    }
  }

</script>
</body>