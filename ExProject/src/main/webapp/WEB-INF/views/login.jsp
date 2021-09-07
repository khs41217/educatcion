<!DOCTYPE html>
<html lang="en">

<head>
<title>ITKey 로그인</title>
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
<script>
	function check() {
		var user_id = $('#id').val();
		var user_pw = $('#pw').val();
		if($('input:checkbox[id="rememberMe"]').is(":checked") == true){
			var rememberMe='true';
		}else{
			var rememberMe='false';
		}
		$.ajax({
			url: 'loginMember',
			data: {'user_id': user_id, 'user_pw' : user_pw,'rememberMe' : rememberMe},
		    type: 'post',
				success: function(loginRs) {
					if (loginRs == -2 || loginRs== 0) {
						alert("아이디 또는 비밀번호가 일치하지 않습니다.");
					}else{
						// 로그인성공후 페이지 이동처리
						
					}
			}, error: function() {
				alert('서버오류입니다 관리자에게 문의하세요.');
			}
		});
		}
	}
</script>
</head>

<body>
	<div class="limiter animsition">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form" >
					<div class="text-center" style="width: 100%">
						<img src="resources/images/logo.png" width="50%">
					</div>
					<span class="login100-form-title p-b-34 p-t-27"> ITKey Edu<br>Project Login
					</span>					
					<form action="loginMember" method="post">
					<div class="wrap-input100 validate-input" data-validate="Enter username">
						<input class="input100" id="id" type="text" name="username" placeholder="ID">
						<span class="focus-input100" data-placeholder="&#xf207;"></span>
					</div>
					
					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<input class="input100" id="pw" type="password" name="pass" placeholder="비밀번호">
						<span class="focus-input100" data-placeholder="&#xf191;"></span>
					</div>

					<div class="contact100-form-checkbox">
						<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
						<label class="label-checkbox100" for="ckb1"> ID 저장 </label>
					</div>

					<div class="container-login100-form-btn">
						<a href="" class="login100-form-btn" onclick="check()">로그인</a> <a href="register.html" class="login100-form-btn">회원가입</a>
					</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="dropDownSelect1"></div>
</body>

</html>
