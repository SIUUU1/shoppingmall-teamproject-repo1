document.addEventListener('DOMContentLoaded', function() {
	let updateButton = document.getElementById('update');
	updateButton.addEventListener('click', function() {
		//답변내용이 없는 경우
		if (document.getElementById('uRContent').value === '') {
			alert('답변내용을 입력하세요.');
			document.getElementById('uRContent').focus();
			return;
		}
		let query = {
			qna_content: document.getElementById('uRContent').value,
			qna_id: document.getElementById('qna_id').value
		};

		let xhr = new XMLHttpRequest();
		xhr.open('POST', '/ClothsMall/mg/qnaReplyUpdatePro.do', true);
		xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4 && xhr.status === 200) {
				window.location.href = '/ClothsMall/mg/qnaList.do';
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
	});


	// [취소] 버튼 클릭
	let cancelButton = document.getElementById('cancle');
	cancelButton.addEventListener('click', function() {
		window.location.href = "/ClothsMall/mg/qnaList.do";
	});
});

