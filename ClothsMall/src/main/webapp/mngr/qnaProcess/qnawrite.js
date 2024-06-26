document.addEventListener('DOMContentLoaded', function() {
	let replyProButton = document.getElementById('replyPro');
	replyProButton.addEventListener('click', function() {
		let query = {
			qna_content: document.getElementById('rContent').value,
			qna_writer: document.getElementById('qna_writer').value,
			cloth_name: document.getElementById('cloth_name').value,
			cloth_id: document.getElementById('cloth_id').value,
			qna_id: document.getElementById('cloth_id').value,
			qora: document.getElementById('qora').value
		};

		let xhr = new XMLHttpRequest();
		xhr.open('POST', '/ClothsMall/mg/qnaReplyPro.do', true);
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
		window.location.href = "/ClothsMall/mg/managerMain.do";
	});
});
