document.addEventListener('DOMContentLoaded', function() {
    // [관리자 메인으로] 버튼 클릭
    let clothsMainButton = document.getElementById('clothsMain');
    clothsMainButton.addEventListener('click', function() {
        window.location.href = "/ClothsMall/mg/managerMain.do";
    });
});

function reply(replyBtn){
	//[답변하기]버튼 클릭
	let rStr = replyBtn.name;
	let query = "/ClothsMall/mg/qnaReplyForm.do?qna_id="+rStr;
	window.location.href = query;
}

function edit(editBtn){
	//[수정]버튼 클릭
	let rStr = editBtn.name;
	let query = "/ClothsMall/mg/qnaReplyUpdateForm.do?qna_id="+rStr;
	window.location.href = query;
}

function del(deleteBtn){
	//[수정]버튼 클릭
	let rStr = deleteBtn.name;
	let query = "/ClothsMall/mg/qnaReplyDeletePro.do?qna_id="+rStr;
	window.location.href = query;
}