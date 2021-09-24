<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>ITKey 게시판</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="resources/images/icons/favicon.ico" />
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.origin.min.css">
<link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="resources/fonts/iconic/css/material-design-iconic-font.min.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css" href="resources/css/util.css">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<link rel="stylesheet" type="text/css" href="resources/css/table.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/fontawesome-free-5.8.2-web/css/all.min.css">

<script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="resources/vendor/animsition/js/animsition.min.js"></script>
<script src="resources/vendor/bootstrap/js/popper.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/vendor/select2/select2.min.js"></script>
<script src="resources/vendor/daterangepicker/moment.min.js"></script>
<script src="resources/vendor/daterangepicker/daterangepicker.js"></script>
<script src="resources/vendor/countdowntime/countdowntime.js"></script>
<script src="resources/js/main.js"></script>

</head>
	<script>
	function adminDelete(){
		var chk = confirm("회원을 탈퇴시키겠습니까?")
		if(chk){
			location.href='/sam/adminDelete';
		}else{
			return false;
		}
	}

	</script>

<form action="adminDelete">
<body>
	<div class="limiter animsition">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form">
				<div><h1>안녕하세요. 관리자님 <a href="/sam/logout" class="btn btn-default" style="float:right; margin-top: 0px;">로그아웃</a></h1></div>
					<div class="row panel-row">
						<div class="col-sm-3">
							<div class="overview-div">
								<h5 class="overview-title">총 게시글 수</h5>
								<h1 class="overview-content">${pageNum}</h1>
								<i class="far fa-file-alt"></i>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="overview-div">
								<h5 class="overview-title">총 가입자 수</h5>
								<h1 class="overview-content">${totalUser}</h1>
								<i class="fas fa-users"></i>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="overview-div">
								<h5 class="overview-title">오늘 게시글 수</h5>
								<h1 class="overview-content">${todayContent}</h1>
								<i class="fas fa-file-alt"></i>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="overview-div">
								<h5 class="overview-title">오늘 가입자 수</h5>
								<h1 class="overview-content">${todayMember}</h1>
								<i class="fas fa-user-circle"></i>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-4">
							<button type="button" onclick="deleteUser()" class="btn btn-default">선택탈퇴</button>
							<button type="button" onclick="location.href='/sam/adminMain'" class="btn btn-default">게시판관리</button>
						</div>
						<div class="col-sm-2"></div>
						<div class="col-sm-2">
							<select class="form-control" id="">
								<option>전체</option>
								<option>아이디</option>
								<option>이름</option>
							</select>
						</div>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="" placeholder="문자열을 입력해주세요.">
						</div>
						<div class="col-sm-1 text-right">
							<button type="button" class="btn btn-default btn-full">
								<i class="fas fa-search"></i> 검색
							</button>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<table class="table table-hover">
								<thead>
									<tr>
										<th style="width: 5%;"><input type="checkbox" /></th>
										<th style="width: 5%;">아이디</th>
										<th style="width: 5%;">이름</th>
										<th style="width: 10%;">핸드폰</th>
										<th style="width: 10%;">이메일</th>
										<th style="width: 5%;">탈퇴</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list }" var="list" varStatus="status">
										<tr >
											<td>
												<input type="checkbox" />
											</td>
											<td><input type="hidden" name="boardWriter" id="boardWriter" value="${list.boardWriter}">${list.boardWriter }</td>
											<td>${list.boardWriterName }</td>
											<td>${list.boardWriterPhone }</td>
											<td>${list.boardWriterEmail }</td>
											<td>
												<button type="button" onclick="adminDelete()"class="btn btn-default btn-full">탈퇴</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
							 <div class="row">
	                  <div class="col-sm-12 text-center">
	                     <ul class="pagination">
	                     	<li>
	                        	<a href="/sam/adminUser${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a>
	                        
	                        </li>
	                       <c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
	                           <li id="page${idx}">
	                           		<a href="/sam/adminUser${pageMaker.makeQuery(idx)}">${idx}</a>
	                           </li>
	                        </c:forEach>      
	                        	<li>
	                        	   <a href="/sam/adminUser${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
	                            </li>
	                     </ul>
	                  </div>
	               </div> 
					</div>
				</div>
			</div>
		</div>
	<div id="dropDownSelect1"></div>
</body>
</form>

</html>
