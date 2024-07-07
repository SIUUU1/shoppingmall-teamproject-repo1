// cart 삭제 버튼
function delList(delBtn) {
	let rStr = delBtn.name;
	let query = "/ClothsMall/deleteCart.do?list=" + rStr;
	window.location.href = query;
}
// cart 전체 삭제 버튼
function clearList(clearBtn) {
	let rStr = clearBtn.name;
	let query = "/ClothsMall/deleteCart.do?member_id=" + rStr;
	window.location.href = query;
}
// 구매하기 버튼
function cartBuy(buyrBtn) {
	let rStr = buyrBtn.name;
	let query = "/ClothsMall/buyForm.do?member_id=" + rStr;
	window.location.href = query;
}
//수량 증가 버튼 클릭
function plus (){
	let quantity = document.getElementById('quantity').value;
	document.getElementById('quantity').value = Number(quantity) + 1;
}
//수량 감소 버튼 클릭
function minus (){
	let quantity = document.getElementById('quantity').value;
	if(quantity==='1'){
		alert('최소 구매 수량은 1개 입니다.');
		return;
	}
	document.getElementById('quantity').value = Number(quantity) - 1;
}