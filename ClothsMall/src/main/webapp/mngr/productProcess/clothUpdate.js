document.addEventListener('DOMContentLoaded', function() {
	// [옷수정] 버튼 클릭
	let form = document.getElementById('upForm1');
	form.addEventListener('submit', function(event) {
		
		//보내기 전에 입력내용 점검
		if (document.getElementById('cloth_name').value === '') {
			alert('옷이름을 입력하세요.');
			document.getElementById('cloth_name').focus();
			return;
		}
		if (document.getElementById('cloth_price').value === '') {
			alert('가격을 입력하세요.');
			document.getElementById('cloth_price').focus();
			return;
		}
		if (document.getElementById('cloth_count').value === '') {
			alert('수량을 입력하세요.');
			document.getElementById('cloth_count').focus();
			return;
		}
		if (document.getElementById('cloth_brand').value === '') {
			alert('브랜드를 입력하세요.');
			document.getElementById('cloth_brand').focus();
			return;
		}
		if (document.getElementById('cloth_image').value === '') {
			alert('옷 이미지를 삽입하세요.');
			return;
		}
		if (document.getElementById('cloth_content').value === '') {
			alert('내용을 입력하세요.');
			document.getElementById('cloth_content').focus();
			return;
		}
		if (document.getElementById('discount_rate').value === '') {
			alert('할인율을 입력하세요.');
			document.getElementById('discount_rate').focus();
			return;
		}
		
		event.preventDefault();
		let formData = new FormData(form);
		let xhr = new XMLHttpRequest();
		xhr.open('POST', form.action, true);
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				window.location.href = "/ClothsMall/mg/clothList.do?cloth_category=all";
			} else if (xhr.readyState !== 4) {
				console.error('Error:', xhr.statusText);
			}
		};
		xhr.onerror = function() {
			console.error('Request failed');
		};
		xhr.send(formData);
	});
	// [관리자 메인으로] 버튼 클릭
	let clothMainButton = document.getElementById('clothMain');
	clothMainButton.addEventListener('click', function() {
		window.location.href = "/ClothsMall/mg/managerMain.do";
	});
	// [목록으로] 버튼 클릭
	let clothListButton = document.getElementById('clothList');
	clothListButton.addEventListener('click', function() {
		window.location.href = "/ClothsMall/mg/clothList.do?cloth_category=all";
	});
});