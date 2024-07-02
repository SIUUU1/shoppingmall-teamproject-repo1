function accountCheck() {
	const bank = document.querySelector("#bank");
	const account = document.querySelector("#account");
	const info = document.querySelector("#accountInfo");
	const bankPattern = /^[가-힣]{2,10}$/;
	const accountPattern = /^\d{12,20}$/;
	let flag = true;

	// flagate bank name
	if (bank.value === "") {
		info.innerHTML = `은행 명은 필수 입력 항목 입니다.`;
		flag = false;
	} else if (!bank.value.match(bankPattern)) {
		info.innerHTML = `은행 명은 2~10자 이내 한글만 적어주세요.`;
		flag = false;
	} else {
		info.innerHTML = ``;
	}

	// flagate account number
	if (account.value === "") {
		info.innerHTML += `<br>계좌번호는 필수 입력 항목 입니다.`;
		flag = false;
	} else if (!account.value.match(accountPattern)) {
		info.innerHTML += `<br>계좌번호는 12자리 이상 20자리 이하 숫자만 입력해주세요.`;
		flag = false;
	} else if (info.innerHTML === "") {
		info.innerHTML = ``;
	}

	return flag;
}

function allCheck() {
	if (!accountCheck()) {
		alert(`내용을 다시 확인 해주세요.`);
		event.preventDefault();
	}else {
		alert(`정상적으로 충전 되었습니다.`);
		document.member.submit();
	}
}