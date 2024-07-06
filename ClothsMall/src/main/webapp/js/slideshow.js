//콜백함수(body onlod call back func)
function call_js(){
  //UI 객체 참조변수 선언
  let slidshow_imgs = document.querySelector(".slidshow_imgs");
  let imgsArr = document.querySelectorAll(".slidshow_imgs a");
  let indicators = document.querySelectorAll(".indicator a");
  let prev = document.querySelector("#prev");
  let next = document.querySelector("#next");
  //회전목마의 현재위치값
  let currentIndex = 0;
  let timer = null;
  let slideCount = imgsArr.length;

  //이미지 우측으로 위치시키기
  for (let i = 0; i < slideCount; i++) {
    imgsArr[i].style.left = `${i * 100}%`;
  }

  //이미지 움직이기(slidshow_imgs 왼쪽으로 -100% 이동)
  function goToSlide(index) {
    currentIndex = index;
    slidshow_imgs.style.left = `${currentIndex * -100}%`;
    indicators.forEach(e => {
      e.classList.remove("active");
    });
    indicators[currentIndex].classList.add("active");
  }
  //index=(0번부터 3번까지) 3초간 goToSlide(index)
  goToSlide(0);
  function startTimer(){
    timer = setInterval(()=>{
      currentIndex++;
      let index = currentIndex % slideCount;
      goToSlide(index);
    },3000);
  }
  startTimer();

  //이벤트 처리
  slidshow_imgs.addEventListener("mouseenter",function(){
    clearInterval(timer);
  });
  slidshow_imgs.addEventListener("mouseleave",function(){
    startTimer();
  });
  prev.addEventListener("mouseenter", function () {
    clearInterval(timer);
  });
  next.addEventListener("mouseenter", function () {
    clearInterval(timer);
  });
  prev.addEventListener("click", function (e) {
    e.preventDefault();
    currentIndex--;
    if (currentIndex<0) { 
      currentIndex = slideCount-1; 
    }
    index = currentIndex % slideCount;
    goToSlide(index);
  });
  next.addEventListener("click", function (e) {
    e.preventDefault();
    currentIndex++;
    index = currentIndex % slideCount;
    goToSlide(index);
  });
  indicators.forEach(e=>{e.addEventListener("mouseenter",()=>{
    clearInterval(timer);
  });
  });
  for(let i=0;i<indicators.length;i++){
    indicators[i].addEventListener("click",(e)=>{
      e.preventDefault();
      goToSlide(i);
    });
  }
}//end of call back func