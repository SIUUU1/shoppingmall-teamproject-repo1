let flag = false;		//아이디 중복 확인
document.addEventListener('DOMContentLoaded', function() {

	// [ID중복확인] 버튼 클릭 이벤트
	let checkIdButton = document.getElementById('checkId');
	checkIdButton.addEventListener('click', function() {
		let idValue = document.getElementById('member_id').value;
		const userIdPettern = /^[a-z0-9]{4,10}$/;
		if (!idValue.match(userIdPettern)) {
			alert(`아이디 4~10자 이내 알파벳와 숫자만 입력하세요.`);
			document.getElementById('member_id').focus();
			return;
		}

		if (idValue) {
			// 서버로 중복 확인 요청
			let xhr = new XMLHttpRequest();
			xhr.open("POST", "/ClothsMall/confirmId.do", true);
			xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			xhr.onload = function() {
				if (xhr.status === 200) {
					let data = xhr.responseText;
					if (data.trim() === '1') {
						alert('사용할 수 없는 아이디입니다.');
						document.getElementById('member_id').value = '';
					} else {
						flag = true;
						alert('사용할 수 있는 아이디입니다.');
					}
				} else {
					alert('서버 오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
					console.error('Error:', xhr.statusText);
				}
			};
			xhr.send("member_id=" + encodeURIComponent(idValue));
		} else {
			alert('아이디를 입력해주세요.');
		}
	});

	// [취소] 버튼 클릭 이벤트
	let cancelButton = document.getElementById('cancel');
	cancelButton.addEventListener('click', function() {
		window.location.href = '/ClothsMall/index.do';
	});

});

// 가입하기 버튼 클릭 이벤트
function process() {
	if (flag === false) {
		alert('ID중복 확인 해주세요');
	}
	//비밀번호 체크
	let passwdValue = document.getElementById('member_passwd').value;
	const userPwPettern = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^*+=-]).{6,16}$/;
	if (passwdValue === "") {
		alert(`비밀번호를 입력해주세요.`);
		document.getElementById('member_passwd').focus();
		return;
	} else if (!passwdValue.match(userPwPettern)) {
		alert(`비밀번호 6~16자 영문자와 숫자,특수기호를 조합해 입력해주세요`);
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
		alert(`이름 2~4자 이내 한글만 적어주세요.`);
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
		alert(`전화번호를 올바르게 입력해주세요 ex)010-1234-1234`);
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

	// 가입 정보를 서버로 전송
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
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				window.location.href = "/ClothsMall/index.do"; // 가입 성공 시 메인 페이지로 이동
			} else {
				alert('가입 실패. 입력 정보를 다시 확인해주세요.'); // 가입 실패 시 메시지 표시
				console.error('Error:', xhr.statusText);
			}
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

// 주소 검색 버튼 클릭 이벤트
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
