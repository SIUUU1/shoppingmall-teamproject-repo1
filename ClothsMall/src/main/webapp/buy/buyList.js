document.addEventListener('DOMContentLoaded', function() {
	// 1-2. 계속 쇼핑	
	var conShoppingButton = document.getElementById('conShopping');
	conShoppingButton.addEventListener('click', function() {
		window.location.href = "/ClothsMall/list.do?cloth_category=all";
	});
	// 1-2. 메인
	var shopMainButton = document.getElementById('shopMain');
	shopMainButton.addEventListener('click', function() {
		window.location.href = "/ClothsMall/index.do";
	});
});