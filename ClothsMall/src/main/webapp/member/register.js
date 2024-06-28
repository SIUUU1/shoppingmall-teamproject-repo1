document.addEventListener('DOMContentLoaded', function() {
	// [ID중복확인] 버튼 클릭
	let checkIdButton = document.getElementById('checkId');
	checkIdButton.addEventListener('click', function() {
		let idValue = document.getElementById('member_id').value;
		if (idValue) {
			let query = { id: idValue };
			let xhr = new XMLHttpRequest();
			xhr.open("POST", "/ClothsMall/confirmId.do", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
					let data = xhr.responseText;
					let str1 = '<p id="ck">';
					let loc = data.indexOf(str1);
					let len = str1.length;
					let check = data.substr(loc + len, 1);
					console.log(check);
					console.log(str1);
					if (check === '1') {
						alert('사용할 수 없는 아이디');
						document.getElementById('member_id').value = '';
					} else {
						alert('사용할 수 있는 아이디');
					}
				} else {
					console.error('Error:', xhr.statusText);
				}
			};
			xhr.send("member_id=" + encodeURIComponent(query.id));
			console.log(query.id);
		} else { // 아이디를 입력하지 않고 [ID중복확인] 버튼을 클릭한 경우
			alert('사용할 아이디를 입력');
			document.getElementById('member_id').focus();
		}
	});


	// [취소] 버튼 클릭
	let cancelButton = document.getElementById('cancle');
	cancelButton.addEventListener('click', function() {
		window.location.href = '/ClothsMall/index.do';
	});
});

function process() {
	// [가입하기] 버튼 클릭
	alert('가입하기3');
	let genderElements = document.getElementsByName('member_gender');

	// 선택된 성별 값을 담을 변수를 초기화합니다.
	let genderValue = "";
	for (let i = 0; i < genderElements.length; i++) {
		if (genderElements[i].checked) {
			genderValue = genderElements[i].value;
			break; 
		}
	}
	let query = {
		id: document.getElementById('member_id').value,
		passwd: document.getElementById('member_passwd').value,
		name: document.getElementById('member_name').value,
		address: document.getElementById('member_address').value,
		code: document.getElementById('member_postal_code').value,
		detailed_ad: document.getElementById('member_detailed_address').value,
		tel: document.getElementById('member_tel').value,
		gender: genderValue
	};

	let xhr = new XMLHttpRequest();
	xhr.open("POST", "/ClothsMall/registerPro.do", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
			window.location.href = "/ClothsMall/index.do";
		} else {
			console.error('Error:', xhr.statusText);
		}
	};
	xhr.send("member_id=" + encodeURIComponent(query.id) +
		"&member_passwd=" + encodeURIComponent(query.passwd) +
		"&member_name=" + encodeURIComponent(query.name) +
		"&member_address=" + encodeURIComponent(query.address) +
		"&member_postal_code=" + encodeURIComponent(query.code) +
		"&member_detailed_address=" + encodeURIComponent(query.detailed_ad) +
		"&member_tel=" + encodeURIComponent(query.tel) +
		"&member_gender=" + encodeURIComponent(query.gender)
	);
}
