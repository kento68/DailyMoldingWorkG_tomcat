<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	List<Product> list=(List<Product>)request.getAttribute("list");
	Product product=(Product)request.getAttribute("product");
	
	String flag=product == null ? "":String.valueOf(product.getFlag());
	String arrangementnumber=product == null ? "":String.valueOf(product.getArrangementnumber());
	String workperformancedate=product == null ? "":product.getWorkperformancedate();
	String id=product==null ? "":String.valueOf(product.getId());
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Ingestiondata.css">
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

<form action="<%= request.getContextPath() %>/Ingestiondata" method="GET" class="form-inline">
  <div class="form-row align-items-center">
    <div class="form-group mr-3">
      <input type="text" id="searchInput" name="searchKeyword" class="form-control" style="width: 600px; margin-top: 10px;" placeholder="検索キーワードを入力してください">
    </div>
    <div class="form-group mx-sm-3">
      <button type="submit" class="btn btn-primary" style="margin-top: 10px;" >検索</button>
    </div>
  </div>
</form>

<form action="<%= request.getContextPath() %>/Ingestiondata" method="post" >

<header>
	<ul>
    	<li><a href="<%= request.getContextPath() %>/main">SingleData</a></li>
    	<li><a href="<%= request.getContextPath() %>/Ingestiondata">Log</a></li>
    	<li><a href="<%= request.getContextPath() %>/PartnumbermasterList">PartnumbermasterList</a></li>
    	<li><a href="<%= request.getContextPath() %>/BacklogList">Backlog</a></li>
    	<li class="contact"><c:out value="${loginUser.name}"/>:ログイン中</li>
	</ul>
</header>

<form action="<%= request.getContextPath() %>/Ingestiondata" method="POST">
  <% if(list != null && list.size() > 0) { %>
  
    <!-- 選択したデータに対するアクションボタン -->
     <button type="submit" name="action" value="deleteSelected" class="btn btn-danger" style="margin-top: 10px;" onclick="return confirm('選択した項目を削除してよろしいですか？');">
     	選択項目 削除
     </button>
     <button type="submit" name="action" value="downloadSelected" class="btn btn-success" style="margin-top: 10px;" onclick="return confirmAndDisableSelected();">
     	選択項目 CSV
     </button>
     <table class="table table-striped mt-4">
      <thead>
        <tr>
          <th><input type="checkbox" id="selectAll" onclick="toggleSelectAll(this)"> 全選択</th>
          <th>作業実績日</th>
          <th>手配番号</th>
          <th>品番</th>
          <th>機械コード</th>
          <th>日勤夜勤区分</th>
          <th>備考</th>
          <th>操作項目</th>
        </tr>
      </thead>
      <tbody>
        <% for(Product p : list) { %>
        <tr>
          <td><input type="checkbox" name="selectedIds" value="<%= p.getId() %>"></td>
          <td><%= p.getWorkperformancedate() %></td>
          <td><%= p.getArrangementnumber() %></td>
          <td><%= p.getPartnumber() %></td>
          <td><%= p.getMachinecode() %></td>
          <td><%= p.getWorkinghours() %></td>
          <td><%= p.getRemarks() %></td>
          <td>
            <a href="<%= request.getContextPath() %>/main?action=update&id=<%= p.getId() %>" class="btn btn-primary">更新</a>
            <a href="<%= request.getContextPath() %>/Ingestiondata?action=delete&id=<%= p.getId() %>" class="btn btn-danger" onclick="return confirm('削除してよろしいですか？');">削除</a>
            <a href="<%= request.getContextPath() %>/Ingestiondata?action=download&id=<%= p.getId() %>" id="downloadBtn_<%= p.getId() %>" class="btn btn-success" onclick="return confirmDownload('ダウンロードしてよろしいですか？', '<%= p.getId() %>');"> CSV </a>
          </td>
        </tr>
        <% } %>
      </tbody>
    </table>
  <% } %>
</form>

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

<!-- リロード制御 -->
if (!sessionStorage.getItem('reloaded')) {
  sessionStorage.setItem('reloaded', 'true');
  window.location.reload();
} else {
  sessionStorage.removeItem('reloaded');
}

<!-- ダウンロードを確認する関数  -->
function confirmDownload(message, id) {
    // 確認ダイアログを表示し、結果を取得する
    var confirmed = confirm(message);
    // ダウンロードがキャンセルされた場合はfalseを返す
    if (!confirmed) {
        return false;
    }
    // ボタンを非アクティブにする
    var downloadBtn = document.getElementById('downloadBtn_' + id);
    if (downloadBtn) {
        downloadBtn.classList.add('disabled');
        downloadBtn.classList.remove('btn-success');
        downloadBtn.classList.add('btn-dark');
        downloadBtn.style.backgroundColor = 'black';
    }
    // ボタンの状態をセッションストレージに記録する
    sessionStorage.setItem('downloadBtnDisabled_' + id, 'true');
    return true;
}

<!-- ページロード時にボタンの状態を復元する  -->
window.onload = function() {
    <% for(Product p : list) { %>
        var isDisabled_<%= p.getId() %> = sessionStorage.getItem('downloadBtnDisabled_<%= p.getId() %>');
        if (isDisabled_<%= p.getId() %> === 'true') {
            var downloadBtn_<%= p.getId() %> = document.getElementById('downloadBtn_<%= p.getId() %>');
            if (downloadBtn_<%= p.getId() %>) {
                downloadBtn_<%= p.getId() %>.classList.add('disabled');
                downloadBtn_<%= p.getId() %>.classList.remove('btn-success');
                downloadBtn_<%= p.getId() %>.classList.add('btn-dark');
                downloadBtn_<%= p.getId() %>.style.backgroundColor = 'black';
            }
        }
    <% } %>
};

//チェックボックスの全選択/全解除用
function toggleSelectAll(source) {
    const checkboxes = document.getElementsByName('selectedIds');
    for (let i = 0; i < checkboxes.length; i++) {
      checkboxes[i].checked = source.checked;
    }
  }

<!-- チェックボックスのダウンロードを確認する関数  -->
function confirmAndDisableSelected() {
    // 確認ダイアログを表示
    var confirmed = confirm('選択した項目をダウンロードしてよろしいですか？');
    
    // ユーザーがキャンセルした場合は処理を終了
    if (!confirmed) {
        return false;
    }
    
    // チェックされたチェックボックスを取得
    var selectedCheckboxes = document.querySelectorAll('input[name="selectedIds"]:checked');
    
    selectedCheckboxes.forEach(function(checkbox) {
        // チェックボックスの値（id）を取得し、それに対応するボタンを無効化
        var id = checkbox.value;
        var downloadBtn = document.getElementById('downloadBtn_' + id);
        
        if (downloadBtn) {
            // ボタンを無効化し、スタイルを変更
            downloadBtn.classList.add('disabled');
            downloadBtn.classList.remove('btn-success');
            downloadBtn.classList.add('btn-dark');
            downloadBtn.style.backgroundColor = 'black';
            
            // 状態をセッションストレージに記録
            sessionStorage.setItem('downloadBtnDisabled_' + id, 'true');
        }
    });
    
    return true; // ダウンロード処理を続行
}

<!-- ページロード時にボタンの状態を復元する  -->
window.onload = function() {
    // すべての商品IDに対応するボタンの状態を確認して復元
    var allProducts = document.querySelectorAll('input[name="selectedIds"]');
    allProducts.forEach(function(product) {
        var id = product.value;
        var isDisabled = sessionStorage.getItem('downloadBtnDisabled_' + id);
        if (isDisabled === 'true') {
            var downloadBtn = document.getElementById('downloadBtn_' + id);
            if (downloadBtn) {
                downloadBtn.classList.add('disabled');
                downloadBtn.classList.remove('btn-success');
                downloadBtn.classList.add('btn-dark');
                downloadBtn.style.backgroundColor = 'black';
            }
        }
    });
};

</script>
</body>