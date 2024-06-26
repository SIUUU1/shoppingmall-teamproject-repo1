document.addEventListener('DOMContentLoaded', function() {
    let updateButton = document.getElementById('update');
    updateButton.addEventListener('click', function() {
        // [수정] 버튼 클릭
        let cloth_id = document.getElementById('cloth_id').value;
        let cloth_category = document.getElementById('cloth_category').value;

        let query = {
            qna_content: document.getElementById('updateCont').value,
            qna_id: document.getElementById('qna_id').value
        };

        let xhr = new XMLHttpRequest();
        xhr.open('POST', '/ClothsMall/qnaReplyUpdatePro.do', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let data = xhr.responseText;
                let str1 = '<p id="ck">';
                let loc = data.indexOf(str1);
                let len = str1.length;
                let check = data.substr(loc + len, 1);
                if (check == '1') {
                    alert('QnA has been updated.');
                    let query = '/ClothsMall/clothContent.do?cloth_id=' + cloth_id;
                    query += '&cloth_category=' + cloth_category;
                    window.location.href = query;
                } else {
                    alert('QnA update failed.');
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
    });

    let cancleButton = document.getElementById('cancle');
    cancleButton.addEventListener('click', function() {
        // [취소] 버튼 클릭
        let cloth_id = document.getElementById('cloth_id').value;
        let cloth_category = document.getElementById('cloth_category').value;
        let query = '/ClothsMall/clothContent.do?cloth_id=' + cloth_id;
        query += '&cloth_category=' + cloth_category;
        window.location.href = query;
    });
});