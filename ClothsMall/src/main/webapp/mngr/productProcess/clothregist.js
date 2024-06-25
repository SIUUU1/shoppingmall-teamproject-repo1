document.addEventListener('DOMContentLoaded', function() {
    // [옷등록] 버튼 클릭
    // 이미지를 포함한 상품등록
    let form = document.getElementById('upForm1');
    form.addEventListener('submit', function(event) {
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
	let bookMainButton = document.getElementById('clothMain');
	bookMainButton.addEventListener('click', function() {
		window.location.href = "/ClothsMall/mg/managerMain.do";
	});
	// [목록으로] 버튼 클릭
	let bookListButton = document.getElementById('clothList');
	bookListButton.addEventListener('click', function() {
		window.location.href = "/ClothsMall/mg/clothList.do?cloth_category=all";
	});
});