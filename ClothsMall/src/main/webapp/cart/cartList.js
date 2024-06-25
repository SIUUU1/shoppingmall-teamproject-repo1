document.addEventListener('DOMContentLoaded', function() {
	// 1-2. 계속 쇼핑	
	var conShoppingButton = document.getElementById('conShopping');
	conShoppingButton.addEventListener('click', function() {
		window.location.href = "/ClothsMall/list.do?book_kind=all";
	});
	// 1-2. 메인
	var shopMainButton = document.getElementById('shopMain');
	shopMainButton.addEventListener('click', function() {
		window.location.href = "/ClothsMall/index.do";
	});
});

// 2. cart 의 quantity 수정
function editSu(editBtn) {
	var rStr = editBtn.name;
	var arr = rStr.split(",");
	var query = "/ClothsMall/cartUpdateForm.do?cart_id=" + arr[0];
	query += "&quantity=" + arr[1];
	window.location.href = query;
}
// 3. cart 삭제
function delList(delBtn) {
	var rStr = delBtn.name;
	var query = "/ClothsMall/deleteCart.do?list=" + rStr;
	window.location.href = query;
}
