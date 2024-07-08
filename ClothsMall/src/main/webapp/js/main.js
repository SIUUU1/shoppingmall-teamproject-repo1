//메인 메뉴에 마우스 올려놓을 때 메뉴 펼치기
//메인 메뉴에 마우스 다른 곳에 둘 때 메뉴 접기
function displayMenu(state) {
	const categoryGridContent = document.querySelector("#categoryGridContent");
	switch (state) {
		case "mouseover":
			categoryGridContent.style.display = `flex`;
			break;
		case "mouseout":
			categoryGridContent.style.display = `none`;
			break;
		default: break;
	}
}
// [로그아웃] 버튼 클릭

function uLogout(event) {
	
	alert("로그아웃");
	event.preventDefault();
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