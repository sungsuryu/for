
	var PhNumber = function(msg, numTxt) {
		if (numTxt == "") {
				alert(msg+"번호를 입력해주세요.");
				return false;
			}
			var chkStyle = /[0-9]{9,12}$/;
			if (!chkStyle.test(numTxt)) {
				alert(msg+"번호는 숫자만 입력해주세요.");
				return false;
			}
			if (numTxt.length < 9) {
				alert(msg+"번호를 확인해주세요.");
				return false;
			}
			return true;
	};

	var CnkEmail = function(e) {
		var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		var eml = e;
		if (eml == '' || !re.test(eml)) {
			return false;
		}
		return true;
	};
	
	var gotoLogin = function() {
		location.href = "login.do";
	};

	var ChkPwd = function(e) {
		var regPwd = /(?=.*\d{1,20})(?=.*[~`!@#$%\^&*()-+=]{1,20})(?=.*[a-zA-Z]{1,20}).{8,20}$/;
		if (e == '' || !regPwd.test(e)) {
			return false;
		}
		return true;
	};
	
	var ChkId = function(e) {
		var regId = /^[a-zA-z][a-zA-z0-9]{7,19}$/gi;
		if (e == '' || !regId.test(e)) {
			return false;
		}
		return true;
	};
	
	var stck = function(str, limit) {
	    var o, d, p, n = 0, l = limit == null ? 4 : limit;
	    for (var i = 0; i < str.length; i++) {
	        var c = str.charCodeAt(i);
	        if (i > 0 && (p = o - c) > -2 && p < 2 && (n = p == d ? n + 1 : 0) > l - 3) 
	            return false;
	            d = p, o = c;
	    }
	    return true;
	};