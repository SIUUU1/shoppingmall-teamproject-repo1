var status = true;
document.addEventListener("DOMContentLoaded", function() {
	//[상품등록]버튼 클릭
	document.getElementById("registProduct").addEventListener("click", function() {
		window.location.href = "/ClothsMall/mg/clothRegisterForm.do";
	});
	//[상품수정/삭제]버튼 클릭
	document.getElementById("updateProduct").addEventListener("click", function() {
		window.location.href = "/ClothsMall/mg/clothList.do?cloth_category=all";
	});
	//[회원수정/삭제]버튼 클릭
	document.getElementById("updateMember").addEventListener("click", function() {
		window.location.href = "/ClothsMall/mg/memberList.do";
	});
	//[전체구매목록 확인]버튼 클릭
	document.getElementById("orderedProduct").addEventListener("click", function() {
		window.location.href = "/ClothsMall/mg/orderList.do";
	});
	//[상품 QnA답변]버튼 클릭
	document.getElementById("qna").addEventListener("click", function() {
		window.location.href = "/ClothsMall/mg/qnaList.do";
	});
});