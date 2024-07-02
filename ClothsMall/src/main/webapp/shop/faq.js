function searchFAQ() {
    var input = document.getElementById('searchInput');
    var filter = input.value.toUpperCase();
    var ul = document.getElementsByClassName('qna')[0];
    var li = ul.getElementsByTagName('li');

    for (var i = 0; i < li.length; i++) {
        var label = li[i].getElementsByTagName('label')[0];
        var txtValue = label.textContent || label.innerText;

        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = '';
        } else {
            li[i].style.display = 'none';
        }
    }
}
