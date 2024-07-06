document.addEventListener('DOMContentLoaded', function() {
	// 0. 버튼
	let mainBtn = document.getElementById('mainBtn');
	let eraseBtn = document.getElementById('eraseBtn');
	// 1 메인
	mainBtn.addEventListener('click', () => {
		window.location.href = "/ClothsMall/index.do";
	});
	// 2. 지우기
	eraseBtn.addEventListener('click', () => {
		let searchInput = document.getElementById('searchInput');
		searchInput.value = '';
	})
});

// < 검색 >
function searchFunc() {
	let searchInput = document.getElementById('searchInput').value.toLowerCase();
	let list = document.querySelectorAll('[class*="cat"]');
	console.log(list);
	list.forEach(e => {
		let title = e.id.toLowerCase();
		if (title.includes(searchInput)) {
			e.style.display = '';
		} else {
			e.style.display = 'none';
		}
	})
}

function enterFunc() {	// 엔터키
	if (window.event.keyCode == 13) {
		searchFunc();
	};
}

// 게시글 제목 클릭시 답변 출력
function showContent(e) {
	let no = e.name;
	let content = document.getElementById(no);
	if (!content.style.display || content.style.display == 'none') {
		content.style.display = 'block';
	} else {
		content.style.display = 'none';
	}
	console.log(`no= ${no}`)
}

// select 에 따라 카테고리에 맞는 게시글만 출력
function sortFaq(e) {
	let faq_category = e.value;
	let cat100 = document.querySelectorAll(".cat100")
	let cat200 = document.querySelectorAll(".cat200")
	let cat300 = document.querySelectorAll(".cat300")
	if (faq_category === 'all') {
		cat100.forEach((e) => {
			e.style.display = '';
		})
		cat200.forEach((e) => {
			e.style.display = '';
		})
		cat300.forEach((e) => {
			e.style.display = '';
		})
	} else if (faq_category == 100) {
		console.log("cat : 100")
		cat100.forEach((e) => {
			e.style.display = '';
		})
		cat200.forEach((e) => {
			e.style.display = 'none';
		})
		cat300.forEach((e) => {
			e.style.display = 'none';
		})
	} else if (faq_category == 200) {
		console.log("cat : 200")
		cat100.forEach((e) => {
			e.style.display = 'none';
		})
		cat200.forEach((e) => {
			e.style.display = '';
		})
		cat300.forEach((e) => {
			e.style.display = 'none';
		})
	} else if (faq_category == 300) {
		console.log("cat : 300")
		cat100.forEach((e) => {
			e.style.display = 'none';
		})
		cat200.forEach((e) => {
			e.style.display = 'none';
		})
		cat300.forEach((e) => {
			e.style.display = '';
		})
	} else {

	}
}