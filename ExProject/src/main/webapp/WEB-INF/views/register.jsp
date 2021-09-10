<!DOCTYPE html>
<html lang="en">

<head>
<title>ITKey 회원가입</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="resources/images/icons/favicon.ico" />
<link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="resources/fonts/iconic/css/material-design-iconic-font.min.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet" type="text/css" href="resources/css/util.css">
<link rel="stylesheet" type="text/css" href="resources/css/main.css">

<script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="resources/vendor/animsition/js/animsition.min.js"></script>
<script src="resources/vendor/bootstrap/js/popper.js"></script>
<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/vendor/select2/select2.min.js"></script>
<script src="resources/vendor/daterangepicker/moment.min.js"></script>
<script src="resources/vendor/daterangepicker/daterangepicker.js"></script>
<script src="resources/vendor/countdowntime/countdowntime.js"></script>
<script src="resources/js/main.js"></script>

<script type="text/javascript">
	/*핸드폰 번호 하이픈 정규식*/
	function phoneNumber(){
	$(document).on("keyup", "#boardWriterPhone", function(){
		$(this).val($(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-"));		
	})				
	}
	/*--핸드폰 번호 하이픈 정규식--*/
	
	/*이미지 미리보기*/
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#img').attr('src', e.target.result);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
	/*--이미지 미리보기--*/
	
	function checkEmail(){
		var useremail = document.getElementById('boardWriterEmail');	// 이메일
		var checkemail = document.getElementById('checkEmail');	//	이메일체크
	}
	/*회원가입 유효성 검사*/
	function checkAll(){
	 	//이메일 정규식
	 	var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	 	//공백 정규식
	 	var emptyChk = /\s/g;
	 	
		var id = document.getElementById('boardWriter');	// 아이디
		var pw = document.getElementById('boardWriterPw');	// 비밀번호
		var name = document.getElementById('boardWriterName');	// 이름
		var phone = document.getElementById('boardWriterPhone');	// 핸드폰번호		
		var email = document.getElementById('boardWriterEmail'); 	// 이메일
		var checkemail = document.getElementById('checkEmail');	//	이메일체크
			

			if(id.value == ""){
				alert("아이디를 입력해주세요");
				id.focus();
				return false; 
			}
			if(pw.value == ""){
				alert("비밀번호를 입력해주세요");
				pw.focus();
				return false;
			}
			if(name.value == ""){
				alert("이름을 입력해주세요");
				name.focus();
				return false; 
			}
			if(phone.value == ""){
				alert("핸드폰 번호를 정확히 입력해주세요")
				phone.focus();
				return false;
			}
			if(email.value == ""){
				alert("이메일을 입력해주세요");
				email.focus();
				return false;
			}
			if(email.value != checkemail.value){
				alert("이메일이 일치하지 않습니다");
				checkemail.focus();
				return false; 
			}
			if(emailPattern.test(email.value) == false){
				alert("이메일 형식이 올바르지 않습니다.");
				emial.focus();
				return false;
			}
			if(emailPattern.tert(checkemail.value) == false){
				alert("이메일 형식이 올바르지 않습니다.")
				checkemail.focus();
				return false;
			}	
	}
	//*--회원가입 유효성 검사--*//
	
	function check(){
		var result = confirm("이대로 회원가입을 진행 하시겠습니까?");
		
		if(result){
			
			
		} else{
			
		}
	}

</script>
</head>

<body>
<form action= "membersignup" id= "resgisterform" method="post" enctype="multipart/form-data">
	<div class="limiter animsition">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form validate-form">
					<span class="login100-form-title p-b-27 p-t-15">회원가입</span>
					<div class="row text-center">
						<div class="col-sm-12">
							<img src="resources/images/noImage.png" id="img" class="img-circle" style="width: 180px; height: 180px; border-radius: 100%;">
						</div>
					</div>
					<div class="form-group" style="margin-bottom: 50px; margin-top: 10px;">
						<div class="input-group">
							<input type="text" class="form-control" readonly>
							<div class="input-group-btn">
								<span class="fileUpload btn login100-form-btn"> <span class="upl" id="upload">업로드</span> <input type="file" class="upload up" id="up" name="file"onchange="readURL(this);" />
								</span>
								<!-- btn-orange -->
							</div>
							<!-- btn -->
						</div>
						<!-- group -->
					</div>
					<!-- form-group -->

					<div class="row">
						<div class="col-sm-6">
							<div class="wrap-input100 validate-input" data-validate="Enter userid">
								<input class="input100" type="text" id="boardWriter" name="boardWriter" placeholder="ID">
								<span class="focus-input100" data-placeholder="&#xf207;"></span>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="wrap-input100 validate-input" data-validate="Enter password">
								<input class="input100" type="password" id="boardWriterPw" name="boardWriterPw" placeholder="Password">
								<span class="focus-input100" data-placeholder="&#xf191;"></span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="wrap-input100 validate-input" data-validate="Enter username">
								<input class="input100" type="text" id="boardWriterName" name="boardWriterName" placeholder="이름 입력란">
								<span class="focus-input100" data-placeholder="&#xf205;"></span>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="wrap-input100 validate-input" data-validate="Enter userphone">
								<input class="input100" type="text" id="boardWriterPhone" name="boardWriterPhone" onkeyup="phoneNumber()" placeholder="전화번호 입력란">
								<span class="focus-input100" data-placeholder="&#xf2be;"></span>
							</div>
						</div>
					</div>
					<div class="wrap-input100 validate-input" data-validate="Enter useremail">
						<input class="input100" type="text" name="boardWriterEmail" id="boardWriterEmail" onkeyup="checkemail()" placeholder="이메일을 입력해주세요.">
						<span class="focus-input100" data-placeholder="&#xf15a;"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Enter checkemail">
						<input class="input100" type="text" name="checkEmail" id="checkEmail" placeholder="이메일을 다시한번 입력해주세요.">
						<span class="focus-input100" data-placeholder="&#xf159;"></span>
					</div>

					<div class="container-login100-form-btn">
						<button type="submit" class="login100-form-btn" onclick="return checkAll()">가입</button> <a href="login" class="login100-form-btn">취소</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="dropDownSelect1"></div>
</form>
</body>

</html>
