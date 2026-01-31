<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<Backlog> list=(List<Backlog>)request.getAttribute("list");
	Backlog backlog=(Backlog)request.getAttribute("backlog");
	
	String id=backlog==null ? "":String.valueOf(backlog.getId());
	String arrangementNumber=backlog == null ? "":String.valueOf(backlog.getArrangementNumber());
	String itemCode=backlog == null ? "":String.valueOf(backlog.getItemCode());
	String itemName=backlog == null ? "":String.valueOf(backlog.getItemName());
	
	String err=(String)request.getAttribute("err");
	String msg=(String)request.getAttribute("msg");
	
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Backlog.css">
<title>DailyMoldingWork</title>
</head>
<body>

<% if(err !=null){%>
<div class="alert alert-danger" id="errorMessage" role="alert">
<%=err %>
</div>
<%} %>

<% if(msg !=null){%>
<div class="alert alert-success" id="successMessage" role="alert">
<%=msg %>
</div>
<%} %>


<div class="container-fluid" style="margin-top:20px;">

<header>
	<ul> 
	    <!-- loginUser分岐処理 -->
    	<li><a href="<%= request.getContextPath() %>/main">SingleData</a></li>
    	<% if(loginUser != null) {%>
    	<li><a href="<%= request.getContextPath() %>/Ingestiondata">Log</a></li>
    	<li><a href="<%= request.getContextPath() %>/PartnumbermasterList">PartnumbermasterList</a></li>
    	<li><a href="<%= request.getContextPath() %>/BacklogList">Backlog</a></li>
    	<li class="contact"><c:out value="${loginUser.name}"/>:ログイン中</li>
    	<% } else { %>
    	<li class="contact"><c:out value="localuser"/>:ログイン中</li>
    	<% } %>
	</ul>
<!-- ボタンを押したときにフォームの必須フィールドのバリデーションがトリガーされないようにする  type="button -->
</header>

<!-- 検索フォーム -->
<form action="<%= request.getContextPath() %>/BacklogList" method="GET" class="form-inline">
  <div class="form-row align-items-center">
    <div class="form-group mr-3">
      <input type="text" id="searchInput" name="searchKeyword" class="form-control" style="width: 600px; margin-top: 10px;" placeholder="検索キーワードを入力してください">
    </div>
    <div class="form-group mx-sm-3">
      <button type="submit" class="btn btn-primary" style="margin-top: 10px;">検索</button>
    </div>
  </div>
</form>

<!-- CSVインポートフォーム -->
<form action="<%= request.getContextPath() %>/BacklogList" method="POST" enctype="multipart/form-data" class="form-inline">
    <footer class="mt-5">
        <ul class="d-flex justify-content-between align-items-center">
            <!-- ログアウトリンク -->
            <li><a href="<%= request.getContextPath() %>/Logout">Logout</a></li>

            <!-- フォーム部分 -->
            <li class="d-flex align-items-center">
                <label for="csvFiles" class="mr-2">CSVｲﾝﾎﾟｰﾄ:</label>
                <div class="form-group mr-2">
                    <input type="file" id="csvFiles" name="csvFiles" class="form-control-file" style="margin-right: 5px; margin-bottom: 5px; padding-top: 1px; padding-bottom: 1px;" multiple>
                </div>
                <button type="submit" class="btn btn-success mr-2" name="action" value="import"
                        style="padding-top: 1px; padding-bottom: 1px;">
                    ｲﾝﾎﾟｰﾄ
                </button>
            </li>
        </ul>
    </footer>
</form>

<!-- テーブル表示用 (フォームなし) -->
<% if (list != null && !list.isEmpty()) { %>
    <table class="table table-striped mt-4">
        <thead>
            <tr>
                <th>手配番号</th>
                <th>品番</th>
                <th>品名</th>
            </tr>
        </thead>
        <tbody>
            <% for (Backlog b : list) { %>
            <tr>
                <td><%= b.getArrangementNumber() %></td>
                <td><%= b.getItemCode() %></td>
                <td><%= b.getItemName() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
<% } %>

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
window.addEventListener('DOMContentLoaded', () => {
    // メッセージ要素の取得
    const errorMessage = document.getElementById('errorMessage');
    const successMessage = document.getElementById('successMessage');

    // エラーメッセージの保存と復元
    if (errorMessage) {
        sessionStorage.setItem('errorMessage', errorMessage.innerHTML);
    } else if (sessionStorage.getItem('errorMessage')) {
        const errorDiv = document.createElement('div');
        errorDiv.className = 'alert alert-danger';
        errorDiv.innerHTML = sessionStorage.getItem('errorMessage');
        document.body.prepend(errorDiv);
        sessionStorage.removeItem('errorMessage');  // ここで削除
    }

    // 成功メッセージの保存と復元
    if (successMessage) {
        sessionStorage.setItem('successMessage', successMessage.innerHTML);
    } else if (sessionStorage.getItem('successMessage')) {
        const successDiv = document.createElement('div');
        successDiv.className = 'alert alert-success';
        successDiv.innerHTML = sessionStorage.getItem('successMessage');
        document.body.prepend(successDiv);
        sessionStorage.removeItem('successMessage');  // ここで削除
    }

    // 不要なリロード処理を削除
    sessionStorage.removeItem('reloaded'); // ついでに削除
});

</script>
</body>