document.addEventListener('DOMContentLoaded', function() {
    let registButton = document.getElementById('regist');
    registButton.addEventListener('click', function() {
        // [등록] 버튼 클릭
        let cloth_category = document.getElementById('cloth_category').value;
        let cloth_id = document.getElementById('cloth_id').value;

        let query = {
            qna_content: document.getElementById('qnaCont').value,
            qna_writer: document.getElementById('qna_writer').value,
            cloth_name: document.getElementById('cloth_name').value,
            cloth_id: cloth_id,
            qora: document.getElementById('qora').value
        };

        let xhr = new XMLHttpRequest();
        xhr.open('POST', '/ClothsMall/qnaPro.do', true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let data = xhr.responseText;
                let str1 = '<p id="ck">';
                let loc = data.indexOf(str1);
                let len = str1.length;
                let check = data.substr(loc + len, 1);
                if (check == '1') {
                    alert('QnA has been registered.');
                    let query = '/ClothsMall/clothContent.do?cloth_id=' + cloth_id;
                    query += '&cloth_category=' + cloth_category;
                    window.location.href = query;
                } else {
                    alert('QnA registration failed.');
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
        let cloth_category = document.getElementById('cloth_category').value;
        let cloth_id = document.getElementById('cloth_id').value;
        let query = '/ClothsMall/clothContent.do?cloth_id=' + cloth_id;
        query += '&cloth_category=' + cloth_category;
        window.location.href = query;
    });
});