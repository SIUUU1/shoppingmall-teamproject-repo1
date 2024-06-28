document.addEventListener('DOMContentLoaded', function() {
	let insertCartButton = document.getElementById('insertCart');
	insertCartButton.addEventListener('click', function() {
		alert("장바구니에 담았습니다.");
		let query = {
			cloth_id: document.getElementById('cloth_id').value,
			quantity: document.getElementById('quantity').value,
			cloth_image: document.getElementById('cloth_image').value,
			cloth_name: document.getElementById('cloth_name').value,
			cloth_gender: document.getElementById('cloth_gender').value,
			cloth_price: document.getElementById('cloth_price').value,
			cloth_size: document.getElementById('cloth_size').value,
			cloth_category: document.getElementById('cloth_category').valu,
			member_id: document.getElementById('member_id').value
		};
		let xhr = new XMLHttpRequest();
		xhr.open('POST', '/ClothsMall/insertCart.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				alert('장바구니에 담겼습니다.');
			} else if (xhr.readyState !== 4) {
				console.error('Error:', xhr.statusText);
			}
		};
		xhr.onerror = function() {
			console.error('Request failed');
		};
		let queryString = Object.keys(query)
			.map(function(key) {
				return encodeURIComponent(key) + '=' +
					encodeURIComponent(query[key]);
			})
			.join('&');
		xhr.send(queryString);
	});
	// [상품 QnA쓰기] 버튼 클릭
	let writeQnaButton = document.getElementById('writeQna');
	writeQnaButton.addEventListener('click', function() {
		let cloth_id = document.getElementById('cloth_id').value;
		let cloth_category = document.getElementById('cloth_category').value;
		let query = '/ClothsMall/qnaForm.do?cloth_id=' + cloth_id + '&cloth_category=' +
			cloth_category;
		window.location.href = query;
	});
});
//[목록으로]버튼 클릭
function list() {
	window.location.href = '/ClothsMall/list.do?cloth_category=all';
}
// [메인으로] 버튼 클릭
function shopMain() {
	window.location.href = '/ClothsMall/index.do';
}
function edit(editBtn) { // [수정] 버튼 클릭
	let rStr = editBtn.name;
	let arr = rStr.split(',');
	let query = '/ClothsMall/qnaUpdateForm.do?qna_id=' + arr[0] + '&cloth_category=' +
		arr[1];
	window.location.href = query;
}
// [삭제] 버튼 클릭
function del(delBtn) {
	alert("qna 삭제 진행");
	let rStr = delBtn.name;
	let arr = rStr.split(',');
	let query = { 
		qna_id: arr[0],
		group_id: arr[3],
		qora: arr[4]
		 };
	let xhr = new XMLHttpRequest();
	xhr.open('POST', '/ClothsMall/qnaDeletePro.do', true);
	xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			let data = xhr.responseText;
			let str1 = '<p id="ck">';
			let loc = data.indexOf(str1);
			let len = str1.length;
			let check = data.substr(loc + len, 1);
			if (check == '1') {
				alert('QnA가 삭제 되었습니다.');
				let query = '/ClothsMall/clothContent.do?cloth_id=' + arr[1] +
					'&cloth_category=' + arr[2];
				window.location.href = query;
			} else {
				alert('QnA 삭제 실패');
			}
		} else if (xhr.readyState !== 4) {
			console.error('Error:', xhr.statusText);
		}
	};
	xhr.onerror = function() {
		console.error('Request failed');
	};
	
	let queryString = Object.keys(query)
            .map(function(key) {
                return encodeURIComponent(key) + '=' + encodeURIComponent(query[key]);
            })
            .join('&');

    xhr.send(queryString);
}