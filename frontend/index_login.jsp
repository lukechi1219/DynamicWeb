<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>title</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<script type="text/javascript" src="/js/jquery-1.9.1.js?v=20130826"></script>
<script type="text/javascript">

var $j = jQuery.noConflict();

$j(document).ready(function() {
	
	//帳號轉小寫	
	$j('#account').blur(function () {
		var userID = $j(this).val();
		userID = userID.toLowerCase();
		$j(this).val(userID);					
	});

	$j("input").keypress(function (e) {
		if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
			LoginHandler.playerLoginCheck();
			return false;
		} else {
			return true;
		}
	});
});

if (typeof (LoginHandler) == 'undefined') {
	LoginHandler = {};
}

(function() {
	/**
	 * 登入驗證
	 */
	LoginHandler.playerLoginCheck = function (){

		var account  = $j('#account').val();
		var password = $j('#password').val();

		postAjax({
			  'url' : '/ajax/login',
			  'data' : {
				'userID' : account,
				'pass' : password
			  },
			  'success' : function(response) {
				  console.info(response);

				  if (response.status == 200) {
					  alert('login success');
				  } else {
					  alert(response.message);
				  }
			  }
		});
	};
})();

var postAjax = function( options ) {
	options.type = "POST";
	options.dataType = "json";
	//
	options.error = function(response) {
		Trace.error(response.status + ' ' + response.statusText);
		Trace.error(response.responseText);
		if(response.responseText.indexOf(' html ') > -1) {
			// possible session timeout reload page
			location.reload(true);
		}
	};

	$j.ajax( options );
};

</script>
</head>

<body>
<div id="bg_horse"></div>
	<div class="main_wrap">
		<div id="login_wrap">
			<h1>Sample</h1>
				<div class="login">				
						<ul>
							<li class="name" id="account_li">
								<label for="name">userName</label>
								<input id="account" name="account" type="text" size="20" tabindex="1" placeholder="Username"/>
							</li>
							<li class="pass" id="password_li">
								<label for="pass">password</label>
								<input type="password" name="password" id="password" size="20" tabindex="2" placeholder="Password"/>
							</li>
							<li class="subm"><button type="button" onclick="javascript: return LoginHandler.playerLoginCheck();" tabindex="4">Login</button></li>
						</ul>
				</div>
			</div>
		<div id="footer"></div>
	</div>

</body>
</html>