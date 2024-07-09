// cart 삭제 버튼
function delList(delBtn) {
	let rStr = delBtn.name;
	let query = "/ClothsMall/deleteCart.do?cloth_id=" + rStr;
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
function plus() {
	let quantity = document.getElementById('quantity').value;
	let cart_id = document.getElementById('cart_id').value;
	let member_id = document.getElementById('member_id').value;
	document.getElementById('quantity').value = Number(quantity) + 1;
	let query = {
		quantity: document.getElementById('quantity').value,
		cart_id: cart_id
	};

	let xhr = new XMLHttpRequest();
	xhr.open("POST", "/ClothsMall/cartUpdatePro.do", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				window.location.href = `/ClothsMall/cartList.do?member_id=${member_id}`; // 장바구니로 이동
			} else {
				alert('장바구니 수량 수정 실패.'); // 장바구니 수량 수정 실패 시 메시지 표시
				console.error('Error:', xhr.statusText);
			}
		}
	};

	xhr.send("quantity=" + encodeURIComponent(query.quantity) +
		"&cart_id=" + encodeURIComponent(query.cart_id)
	);
}
//수량 감소 버튼 클릭
function minus() {
	let quantity = document.getElementById('quantity').value;
	let cart_id = document.getElementById('cart_id').value;
	let member_id = document.getElementById('member_id').value;
	if (quantity === '1') {
		alert('최소 구매 수량은 1개 입니다.');
		return;
	}
	document.getElementById('quantity').value = Number(quantity) - 1;
	let query = {
		quantity: document.getElementById('quantity').value,
		cart_id: cart_id
	};

	let xhr = new XMLHttpRequest();
	xhr.open("POST", "/ClothsMall/cartUpdatePro.do", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === XMLHttpRequest.DONE) {
			if (xhr.status === 200) {
				window.location.href = `/ClothsMall/cartList.do?member_id=${member_id}`; // 장바구니로 이동
			} else {
				alert('장바구니 수량 수정 실패.'); // 장바구니 수량 수정 실패 시 메시지 표시
				console.error('Error:', xhr.statusText);
			}
		}
	};

	xhr.send("quantity=" + encodeURIComponent(query.quantity) +
		"&cart_id=" + encodeURIComponent(query.cart_id)
	);
}