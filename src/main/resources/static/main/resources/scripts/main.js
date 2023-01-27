const form = window.document.getElementById('form');


let slides = document.querySelector('.swiper-wrapper');
let slide = document.querySelectorAll('.swiper-slide');
let currentIdx = 0;
let slideCount = slide.length
let rightButton = document.querySelector('.rightButton');
let leftButton  = document.querySelector('.leftButton');


let slideMargin = 18;

// button은 sectionItems 안에 있음
rightButton.classList.add('visible');

// slide.style.width = (slideWidth + slideMargin) * slideCount - slideMargin + 'px';

// wrapper 길이는 1050px
// 한 슬라이드당 249 + 18  = 267px * 4 = 1068
function moveSlide(num){
    slides.style.left = -num * 1068 + 'px';
    currentIdx = num; // 1개 슬라이드
}

rightButton.addEventListener('click',function(){
    console.log(slideCount);

    if(currentIdx < slideCount%4) {      //페이지 갯수가 slideCount를 4로 나눈 몫 보다 커지면 중단
        moveSlide(currentIdx + 1);
        if (currentIdx > 1) {
            rightButton.classList.remove('visible');
        }
        console.log(currentIdx);
        leftButton.classList.add('visible');
    }
});
leftButton.addEventListener('click',function(){
    moveSlide(currentIdx -1);
    console.log(currentIdx);
    if(currentIdx === 0){
        leftButton.classList.remove('visible');
    }
    rightButton.classList.add('visible');
});