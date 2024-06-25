document.addEventListener('DOMContentLoaded', function() {
	// [옷등록] 버튼 클릭
	let registButton = document.getElementById('regist');
	registButton.addEventListener('click', function() {
		window.location.href = "/ClothsMall/mg/clothRegisterForm.do";
	});
	// [관리자 메인으로] 버튼 클릭
	let bookMainButton = document.getElementById('clothMain');
	bookMainButton.addEventListener('click', function() {
		window.location.href = "/ClothsMall/mg/managerMain.do";
	});
});
//[수정]버튼을 클릭하면 자동실행
function edit(editBtn) {
	let rStr = editBtn.name;
	let arr = rStr.split(",");
	let query = "/ClothsMall/mg/clothUpdateForm.do?cloth_id=" + arr[0];
	query += "&cloth_category=" + arr[1];
	query += "&cloth_gender=" + arr[2];
	query += "&cloth_size=" + arr[3];
	window.location.href = query;
}
//[삭제]버튼을 클릭하면 자동실행
function del(delBtn) {
	let rStr = delBtn.name;
	let arr = rStr.split(",");
	let query = "/ClothsMall/mg/clothDeletePro.do?cloth_id=" + arr[0];
	query += "&cloth_category=" + arr[1];
	window.location.href = query;
}