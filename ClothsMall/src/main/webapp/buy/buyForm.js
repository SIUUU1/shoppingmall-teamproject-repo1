function addressDefault() {
	document.getElementById('member_name').value = document.getElementById('member_name_h').value;
	document.getElementById('member_tel').value = document.getElementById('member_tel_h').value;
	document.getElementById('member_postal_code').value = document.getElementById('member_postal_code_h').value;
	document.getElementById('member_address').value = document.getElementById('member_address_h').value;
	document.getElementById('member_detailed_address').value = document.getElementById('member_detailed_address_h').value;
}

function useMileageCheck() {
	let mileage = parseInt(document.getElementById('mileage_h').value);
	let total = parseInt(document.getElementById('total_h').value);
	let useMileageInput = parseInt(document.getElementById("useMileage").value);
	let mileageInfo = document.getElementById("mileageInfo");
	let ms = "";
	if(isNaN(useMileageInput)){
		ms ='숫자를 입력하세요.';
		document.getElementById("useMileage").value = 0;
	}
	if (useMileageInput > mileage) {
		ms = "사용할 마일리지가 보유 마일리지보다 많습니다.";
		document.getElementById("useMileage").value = mileage;
		useMileageInput = mileage;
	}
	if (useMileageInput < 0) {
		ms = "사용할 마일리지는 0보다 작을 수 없습니다.";
		document.getElementById("useMileage").value = 0;
		useMileageInput = 0;

	}
	if (useMileageInput > total) {
		ms = "마일리지는 결제할 금액을 초과해 사용할 수 없습니다.";
		document.getElementById("useMileage").value = total;
		useMileageInput = total;
	}

	mileageInfo.textContent = ms;

	document.getElementById("useMileageSpan").textContent = useMileageInput;
	let totalPriceAfterMileage = total - useMileageInput;
	document.getElementById("totalPriceSpan").textContent = totalPriceAfterMileage;
	document.getElementById("usePointSpan").textContent = totalPriceAfterMileage;
	document.getElementById("totalPrice").value = totalPriceAfterMileage;
	document.getElementById("usePoint").value = totalPriceAfterMileage;

	calculatePoint();
}

window.onload = function() {
	calculatePoint();
};


function calculatePoint() {
	const calPointElement = document.getElementById('calPoint');
	const calPointInfoElement = document.getElementById('calPointInfo');

	if (!calPointElement || !calPointInfoElement) {
		return; // Ensure elements exist before proceeding
	}

	const totalPrice = parseInt(document.getElementById('totalPriceSpan').textContent);
	const memberPoint = parseInt(document.getElementById('point_h').value);

	const calPoint = Math.floor(memberPoint - totalPrice);

	calPointElement.innerText =calPoint;

	if (calPoint < 0) {
		calPointInfoElement.innerText = "포인트를 충전해야 결제가 가능합니다!";
		document.querySelector('button[onclick="allCheck(event)"]').style.display = 'none';
		return false;
	} else {
		calPointInfoElement.innerText = "";
		document.querySelector('button[onclick="allCheck(event)"]').style.display = 'block';
		return true;
	}
}

document.addEventListener('DOMContentLoaded', function() {
	var cancleButton = document.getElementById('cancle');

	// [취소] 버튼 클릭
	cancleButton.addEventListener('click', function(event) {
		alert('주문을 취소합니다.');
		event.preventDefault();
		window.location.href = "/ClothsMall/index.do";
	});
});


function member_name_Check() {
	const member_name = document.querySelector("#member_name");
	const member_name_Info = document.querySelector("#member_name_Info");
	const namePattern = /^[가-힣]{2,10}$/;
	if (member_name.value === "") {
		member_name_Info.innerHTML = `필수 입력 항목 입니다.`;
		return false;
	} else if (!member_name.value.match(namePattern)) {
		member_name_Info.innerHTML = `2~10자 이내 한글만 적어주세요.`;
		return false;
	} else {
		member_name_Info.innerHTML = ``;
		return true;
	}
}
function member_postal_code_Check() {
	const member_postal_code = document.querySelector("#member_postal_code");
	const member_postal_code_Info = document.querySelector("#member_postal_code_Info");
	if (member_postal_code.value === "") {
		member_postal_code_Info.innerHTML = `필수 입력 항목 입니다.`;
		return false;
	} else {
		member_postal_code_Info.innerHTML = ``;
		return true;
	}
}
function member_address_Check() {
	const member_address = document.querySelector("#member_address");
	const member_detailed_address = document.querySelector("#member_detailed_address");
	const adress_Info = document.querySelector("#adress_Info");
	if (member_address.value === "" || member_detailed_address.value === "") {
		adress_Info.innerHTML = `필수 입력 항목 입니다.`;
		return false;
	} else {
		adress_Info.innerHTML = ``;
		return true;
	}
}
function member_tel_Check() {
	const member_tel = document.querySelector("#member_tel");
	const member_tel_Info = document.querySelector("#member_tel_Info");
	const phoneNumPattern = /^01([0|1|6|7|8|9]?)-([0-9]{3,4})-([0-9]{4})$/;
	if (member_tel.value === "") {
		member_tel_Info.innerHTML = `필수 입력 항목 입니다.`;
		return false;
	} else if (!member_tel.value.match(phoneNumPattern)) {
		member_tel_Info.innerHTML = `올바르게 입력해주세요 ex)010-1234-1234`;
		return false;
	} else {
		member_tel_Info.innerHTML = ``;
		return true;
	}
}

// 주소 검색 버튼 클릭 이벤트
function searchNum() {
	new daum.Postcode({
		oncomplete: function(data) {
			let fullAddress = data.address; // 선택한 주소 전체
			document.getElementById('member_postal_code').value = data.zonecode; // 우편번호 입력
			document.getElementById('member_address').value = fullAddress; // 주소 입력
			document.getElementById('member_detailed_address').focus(); // 상세주소 입력란으로 포커스 이동
		}
	}).open();
}



function allCheck(event) {
	if (calculatePoint()&&member_tel_Check()&&member_address_Check()&&member_postal_code_Check()&&member_name_Check()) {
		alert(`정상적으로 구매 되었습니다.`);
		document.member.submit();
		return true;
	} else {
		alert(`내용을 다시 확인 해주세요.`);
		event.preventDefault();
	}
}
