document.addEventListener('DOMContentLoaded', function() {
    // [관리자 메인으로] 버튼 클릭
    var bookMainButton = document.getElementById('clothMain');
    bookMainButton.addEventListener('click', function() {
        window.location.href = "/ClothsMall/mg/managerMain.do";
    });
});

