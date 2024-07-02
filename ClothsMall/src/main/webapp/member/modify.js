document.addEventListener('DOMContentLoaded', function() {
	// [취소] 버튼 클릭 이벤트
	let cancelButton = document.getElementById('cancel');
	cancelButton.addEventListener('click', function() {
		window.location.href = '/ClothsMall/modify.do';
	});

	// [수정] 버튼 클릭 이벤트
	let modifyButton = document.getElementById('modifyProcess');
	modifyButton.addEventListener('click', function() {

		//회원정보 수정 내용 보내기 전 입력내용 체크
		let passwdValue = document.getElementById('member_passwd').value;
		const userPwPettern = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^*+=-]).{6,16}$/;
		if (passwdValue === "") {
			alert(`비밀번호를 입력해주세요.`);
			document.getElementById('member_passwd').focus();
			return;
		} else if (!passwdValue.match(userPwPettern)) {
			alert(`6~16자 영문자와 숫자,특수기호를 조합해 입력해주세요`);
			document.getElementById('member_passwd').focus();
			return;
		}

		let pwCheckValue = document.getElementById('repass').value;
		if (pwCheckValue === "") {
			alert(`비밀번호를 재입력하세요.`);
			document.getElementById('repass').focus();
			return;
		}
		if (pwCheckValue !== passwdValue) {
			alert(`비밀번호가 맞는지 확인해주세요.`);
			document.getElementById('repass').focus();
			return;
		}

		let nameValue = document.getElementById('member_name').value;
		const namePattern = /^[가-힣]{2,4}$/;
		if (nameValue === "") {
			alert(`이름을 입력해주세요.`);
			document.getElementById('member_name').focus();
			return;
		} else if (!nameValue.match(namePattern)) {
			alert(`2~4자 이내 한글만 적어주세요.`);
			document.getElementById('member_name').focus();
			return;
		}

		let postalCodeValue = document.getElementById('member_postal_code').value;
		let adressValue = document.getElementById('member_address').value;
		let detailAddValue = document.getElementById('member_detailed_address').value;
		if (postalCodeValue === "" || adressValue === "" || detailAddValue === "") {
			alert(`주소를 입력해주세요.`);
			document.getElementById('member_detailed_address').focus();
			return;
		}

		let phoneValue = document.getElementById('member_tel').value;
		const phoneNumPattern = /^01([0|1|6|7|8|9]?)-([0-9]{3,4})-([0-9]{4})$/;
		if (phoneValue === "") {
			alert(`전화번호를 입력해주세요.`);
			document.getElementById('member_tel').focus();
			return;
		} else if (!phoneValue.match(phoneNumPattern)) {
			alert(`올바르게 입력해주세요 ex)010-1234-1234`);
			document.getElementById('member_tel').focus();
			return;
		}

		// 성별 선택 값 확인
		let genderElements = document.getElementsByName('member_gender');
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
			detailed_add: document.getElementById('member_detailed_address').value,
			tel: document.getElementById('member_tel').value,
			gender: document.getElementById('member_gender').value,
		};

		let xhr = new XMLHttpRequest();
		xhr.open('POST', '/ClothsMall/modifyPro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

		xhr.onreadystatechange = function() {

			if (xhr.readyState === 4 && xhr.status === 200) {
				let data = xhr.responseText;
				let str1 = '<p id="ck">';
				let loc = data.indexOf(str1);
				let len = str1.length;
				let check = data.substr(loc + len, 1);
				if (check == '1') {
					alert('회원정보가 수정되었습니다.');
					window.location.href = '/ClothsMall/modify.do';
				} else {
					alert('비밀번호 틀림.');
					document.getElementById('member_passwd').value = '';
					document.getElementById('member_passwd').focus();
				}
			} else if (xhr.readyState === 4) {
				console.error('Error:', xhr.statusText);
			}
		};

		xhr.onerror = function() {
			console.error('Request failed');
		};

		let params = "member_id=" + encodeURIComponent(query.id) +
			"&member_passwd=" + encodeURIComponent(query.passwd) +
			"&member_name=" + encodeURIComponent(query.name) +
			"&member_address=" + encodeURIComponent(query.address) +
			"&member_postal_code=" + encodeURIComponent(query.code) +
			"&member_detailed_address=" + encodeURIComponent(query.detailed_add) +
			"&member_tel=" + encodeURIComponent(query.tel) +
			"&member_gender=" + encodeURIComponent(query.gender);

		xhr.send(params);
	});
});

//[회원탈퇴] 버튼 클릭 이벤트
function onDelete() {
	let idValue = document.getElementById('member_id').value;
	if(window.confirm('정말로 탈퇴하시겠습니까?')){
	window.location.href = `/ClothsMall/deletePro.do?member_id=${idValue}`;		
	}
}

// [주소검색] 버튼 클릭 이벤트
function openDaumPostcode() {
	new daum.Postcode({
		oncomplete: function(data) {
			let fullAddress = data.address; // 선택한 주소 전체
			document.getElementById('member_postal_code').value = data.zonecode; // 우편번호 입력
			document.getElementById('member_address').value = fullAddress; // 주소 입력
			document.getElementById('member_detailed_address').focus(); // 상세주소 입력란으로 포커스 이동
		}
	}).open();
}

