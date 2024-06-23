function login() {
	alert("로그인");
	let query = {
		id: document.getElementById("id").value,
		passwd: document.getElementById("passwd").value
	};
	let xhr = new XMLHttpRequest();
	xhr.open("POST", "/ClothsMall/mg/managerLoginPro.do", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
			window.location.href = "/ClothsMall/mg/managerMain.do";
		}
	};
	xhr.send("id=" + encodeURIComponent(query.id) + "&passwd=" + encodeURIComponent(query.passwd));
}
function logout() {
	alert("로그아웃");
	let xhr = new XMLHttpRequest();
	xhr.open("POST", "/ClothsMall/mg/managerLogout.do", true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
			window.location.href = "/ClothsMall/mg/managerMain.do";
		}
	};
	xhr.send();
}