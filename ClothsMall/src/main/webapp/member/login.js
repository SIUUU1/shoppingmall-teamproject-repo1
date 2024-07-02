document.addEventListener('DOMContentLoaded', function() {
	// [회원가입] 버튼 클릭
	let uResButton = document.getElementById('uRes');
	uResButton.addEventListener('click', function() {
		window.location.href = '/ClothsMall/registerForm.do';
	});
	
	// [로그인] 버튼 클릭
	let uLoginButton = document.getElementById('uLogin');
	uLoginButton.addEventListener('click', function() {
		
		let idValue = document.getElementById('member_id').value;
		if (idValue === "") {
			alert(`아이디를 입력해주세요.`);
			document.getElementById('member_id').focus();
			return;
		}
		
		let pwValue = document.getElementById('member_passwd').value;
		if (pwValue === "") {
			alert(`비밀번호를 입력해주세요.`);
			document.getElementById('member_passwd').focus();
			return;
		}
		
		let query = {
			id: document.getElementById('member_id').value,
			passwd: document.getElementById('member_passwd').value
		};

		let xhr = new XMLHttpRequest();
		xhr.open('POST', '/ClothsMall/loginPro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
				let data = xhr.responseText;
				let str1 = '<p id="ck">';
				let loc = data.indexOf(str1);
				let len = str1.length;
				let check = data.substr(loc + len, 1);
				if (check == '1') {
					window.location.href = '/ClothsMall/index.do';
				} else if (check == '0') {
					alert('비밀번호를 잘못 입력하셨습니다.');
					document.getElementById('member_passwd').value = '';
				} else {
					alert('아이디를 잘못 입력하셨습니다.');
					document.getElementById('member_id').value = '';
					document.getElementById('member_passwd').value = '';
				}
			} else if (xhr.readyState !== 4) {
				console.error('Error:', xhr.statusText);
			}
		};

		xhr.onerror = function() {
			console.error('Request failed');
		};

		xhr.send("member_id="+encodeURIComponent(query.id)+
		 "&member_passwd="+encodeURIComponent(query.passwd));
	});
});
	
// [회원 정보 변경] 버튼 클릭
function uUpdate(){
	window.location.href = '/ClothsMall/modify.do';
}

// [로그아웃] 버튼 클릭
function uLogout(){
		alert("로그아웃");
		fetch('/ClothsMall/logout.do', {
			method: 'POST'
		}).then(function(response) {
			return response.text();
		}).then(function(data) {
			window.location.href = '/ClothsMall/index.do';
		}).catch(function(error) {
			console.error('Error:', error);
		});
}
