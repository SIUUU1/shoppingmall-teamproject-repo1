function searchFAQ() {
    let input = document.getElementById('searchInput');
    let filter = input.value.toUpperCase();
    let ul = document.getElementsByClassName('qna')[0];
    let li = ul.getElementsByTagName('li');

    for (let i = 0; i < li.length; i++) {
        let label = li[i].getElementsByTagName('label')[0];
        let txtValue = label.textContent || label.innerText;

        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            li[i].style.display = '';
        } else {
            li[i].style.display = 'none';
        }
    }
}
