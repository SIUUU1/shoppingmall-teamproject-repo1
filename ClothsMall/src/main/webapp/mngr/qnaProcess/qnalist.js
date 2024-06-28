document.addEventListener('DOMContentLoaded', function() {
	// [관리자 메인으로] 버튼 클릭
	let clothsMainButton = document.getElementById('clothsMain');
	clothsMainButton.addEventListener('click', function() {
		window.location.href = "/ClothsMall/mg/managerMain.do";
	});
});

function reply(replyBtn) {
	//[답변하기]버튼 클릭
	let rStr = replyBtn.name;
	let query = "/ClothsMall/mg/qnaReplyForm.do?qna_id=" + rStr;
	window.location.href = query;
}

function edit(editBtn) {
	//[수정]버튼 클릭
	let rStr = editBtn.name;
	let query = "/ClothsMall/mg/qnaReplyUpdateForm.do?qna_id=" + rStr;
	window.location.href = query;
}

function del(delBtn) {
	// [삭제] 버튼 클릭
	alert("qna 삭제 진행");
	let rStr = delBtn.name;
	let arr = rStr.split(',');
	let query = { 
		qna_id: arr[0],
		group_id : arr[1],
		qora: arr[2]
		 };
	let xhr = new XMLHttpRequest();
	xhr.open('POST', '/ClothsMall/mg/qnaReplyDeletePro.do', true);
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
				let query = '/ClothsMall/mg/qnaList.do';
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