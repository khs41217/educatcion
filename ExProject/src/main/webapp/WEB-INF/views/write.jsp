<!DOCTYPE html>
<html lang="ko">

<head>
<title>ITKey 글상세</title>
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
	function checkAll(){
		var title = document.getElementById('boardTitle'); 
		var writer = document.getElementById('boardWriter'); 
		var contents = document.getElementById('boardContents');
		var publicFl = $('input:radio[name="radio"]:checked').val( ) ;
		$('#hidden').val(boardPublicFl);
		if(title.value == ''){
			alert("제목을 입력해 주세요");
			title.focus();
			return false;
		}
		if(contents == ''){
			alert("내용을 입력해 주세요");
			contents.focus();
			return false;
		}	
		return false;
	}
</script>

<body>
<form action="write" method="POST" enctype="multipart/form-data">
	<div class="limiter animsition">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form">
					<h4>
						<i class="fa fa-paper-plane" style="color: #b224ef;"></i> 새글 작성 / 수정
					</h4>
					<div class="row">
						<div class="col-sm-12">
							<table class="table table-bordered">
								<tbody>
									<tr>
										<th class="padding-lg">제 목</th>
										<td colspan="4">
											<input type="text" class="form-control write-form" id="boardTitle" name="boardTitle" placeholder="제목을 작성해 주세요.">
										</td>
									</tr>
									<tr>
										<th>작성자</th>
											<td>
												<input type="text" class="form-control wirte-form" id="boardWriter" name="boardWriter" value="${user_id}" disabled="disabled">
											</td>
										<th>공개여부</th>					
											<td>										
												<input type="radio" name="radio" value="Y" class="" checked="checked"/>공개
												<input type="radio" name="radio" value="N" class="" />비공개
												<input type="hidden" name="boardPublicFl" id="hidden"/>
											</td>			

									</tr>
									<tr>
										<td colspan="5">
											<div class="detail-content">
												<textarea class="form-control write-form" rows="14" id="boardContents" name="boardContents" placeholder="내용을 작성해 주세요."></textarea>
											</div>
										</td>
									</tr>
									<tr>
										<th class="padding-lg">첨부파일</th>
										<td colspan="4">
											<input type="file" class="form-control write-form file-form" id="file" name="file">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<div class="row">
						<div class="col-sm-2">
							<button type="button" class="btn btn-default btn-full" onclick="location.href='main';">목록</button>
						</div>
						<div class="col-sm-8"></div>
						<div class="col-sm-2">
							<button type="submit" class="btn btn-default btn-full" onclick ="return checkAll()">저장</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
			<div id="dropDownSelect1"></div>
</form>
</body>

</html>
