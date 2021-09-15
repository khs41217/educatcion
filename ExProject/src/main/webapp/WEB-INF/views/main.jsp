<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
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
$(function(){
	setPerPageNumSelect();
	setSearchTypeSelect();
	
	//prev 버튼 처리
	var canPrev = '${pageMaker.prev}';
	if(canPrev !== 'true'){
		$('#page-prev').addClass('disabled');
	}
	
	//next 버튼 처리
	var canNext = '${pageMaker.next}';
	if(canNext !== 'true'){
		$('#page-next').addClass('disabled');
	}
	var thisPage = '${pageMaker.cri.page}';
	$('#page'+thisPage).addClass('active');
})

function setPerPageNumSelect(){
	var perPageNum = "${pageMaker.cri.perPageNum}";
	var $perPageSel = $('#perPageSel');
	var thisPage = '${pageMaker.cri.page}';
	$perPageSel.val(perPageNum).prop("selected",true);
	$perPageSel.on('change',function(){
		window.location.href = "/sam/main?page="+thisPage+"&perPageNum="+$perPageSel.val();
	})
}

	function setSearchTypeSelect(){
	var $searchTypeSel = $('#searchType');
	var $keyword = $('#keyword');
	
	$searchTypeSel.val('${pageMaker.cri.searchType}').prop("selected", true);
	
	$('#searchBtn').on('click', function(){
		var searchTypeVal = $searchTypeSel.val();
		var keywordVal = $keyword.val();
		if(!searchTypeVal){
			alert("검색 조건을 선택하세요");
			$searchTypeSel.focus();
			return;
		}else if(!keywordVal || kewordVal < 2){
			alert("검색어는 2자 이상입니다.");
			$('#keyword').focus();
			return;
			
		}
	
		var url = "/sam/main?page=1"
				+ "&perPageNum=" + "${pageMaker.cri.perPageNum}"
				+ "&searchType=" + searchTypeVal
				+ "&keyword=" + encodeURIComponent(keywordVal);
				
				window.location.href = url;
				
	})
} 
</script>
<body>

	<div class="limiter animsition">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form">
					<c:if test="${user_id ne null }">
						<div>${user_id} 님 어서오세요 <button type="button" class="btn btn-default" style="float:right; margin-top: -15px;" onclick=" ">로그아웃</button></div>
					</c:if>
					<div class="row panel-row">
						<div class="col-sm-3">
							<div class="overview-div">
								<h5 class="overview-title">총 게시글 수</h5>
								<h1 class="overview-content">${totalContent}</h1>
								<i class="far fa-file-alt"></i>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="overview-div">
								<h5 class="overview-title">총 가입자 수</h5>
								<h1 class="overview-content">${totalMember}</h1>
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
							<button type="button" class="btn btn-default" onclick="location.href='write'">
								<i class="fas fa-plus"></i> 새글 추가
							</button>
							<button type="button" class="btn btn-default" onclick="location.href='chat'">채팅하기</button>
							<button type="button" class="btn btn-default" onclick="location.href='modify'">정보수정</button>
						</div>
						<div class="col-sm-2"></div>
						<div class="col-sm-2">
							<select class="form-control" id="">
								<option>전체</option>
								<option>작성자</option>
								<option>글내용</option>
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
										<th style="width: 5%;">순번</th>
										<th style="width: 10%;">작성자</th>
										<th style="width: 5%;">공개</th>
										<th style="width: 35%:">제목</th>
										<th style="width: 40%;">작성일자</th>
										<th style="width: 5%;">조회수</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${list}" var="list" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${list.boardWriter}</td>
									<td><i class="fas fa-lock"></i></td>
									<!-- <i class="fas fa-lock-open"></i> -->
									<td><a href="/sam/detail?boardIdx=${list.boardIdx}">${list.boardTitle}</a></td>
									<fmt:formatDate var="formatRegDate" value="${list.boardWriteDate}" pattern="yyyy-MM-dd"/>
									<td>${formatRegDate}</td>
									<td>${list.boardViewCount}</td>
								</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<!--  페이징  -->
					<div class="row">
						<div class="col-sm-12 text-center">
							<ul class="pagination">
								<li>
									<a href="/sam/main${pageMaker.makeQuery(pageMaker.startPage - 1)}">이전</a>
								</li>
							<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}" var="idx">
								<li id="page${idx}">
									<a href ="/sam/main${pageMaker.makeQuery(idx)}">${idx}</a>
								</li>
							</c:forEach>
								<li>
									<a href ="/sam/main${pageMaker.makeQuery(pageMaker.endPage + 1)}">다음</a>
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

</html>
