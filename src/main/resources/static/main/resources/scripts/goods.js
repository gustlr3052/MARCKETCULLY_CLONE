const form = window.document.getElementById('form');
const plusButton = document.querySelector('.plusButton');
const minusButton = document.querySelector('.minusButton');
const carButton = document.querySelector('.cartButton');

let result = document.querySelector('.moveCount');
let totalPrice = document.getElementById('totalPrice').innerText;



let count = 1;

let deleteComma = totalPrice.toLocaleString().replace(',','');
let price = parseInt(deleteComma);

plusButton.addEventListener('click',()=>{

     count++;
     result.innerText = count
     totalPrice = price * count;

     let totalCost = totalPrice.toLocaleString();
     document.getElementById('totalPrice').innerText = totalCost;
});


minusButton.addEventListener('click',()=>{

     if(count > 1){
          count--;
     }
     result.innerText = count;
     totalPrice = price * count;
     let totalCost = totalPrice.toLocaleString();
     document.getElementById('totalPrice').innerText = totalCost;
});



     form.onsubmit = e =>{
          e.preventDefault();
          const xhr = new XMLHttpRequest();
          const formData = new FormData();
          formData.append('name',document.querySelector('.last-item-name').innerText);
          formData.append('gid', new URL(window.location.href).searchParams.get('id'));
          formData.append('count', document.querySelector('.moveCount').innerText);
          formData.append('totalPrice',document.querySelector('.totalPrice-price').innerText);
          let keys =  formData.keys();
          for(const pair of keys){
               console.log(pair);
          }
          let values = formData.values();
          for(const pair of values){
               console.log(pair);
          }
          xhr.open('POST','/main/cart');
          xhr.onreadystatechange = () =>{
            if(xhr.readyState === XMLHttpRequest.DONE){
              if(xhr.status >= 200 && xhr.status <300) {
                   const responseObject = JSON.parse(xhr.responseText);
                   switch (responseObject['result']){
                        case 'success':
                             window.location.href='../cart/cart';
                             alert('성공');
                             break;
                        case 'not_allowed':
                             window.location.href='../member/login';
                             alert('로그인 후 이용해주세요');
                             break;
                        case 'failure':
                             alert('실패 DB에 못들어감');
                             break;
                   }
                   console.log('여기 까지 통과?');
              }else{
                   alert('서버와 통신 불가');
              }
            }
          };

          xhr.send(formData);
     };










// 버튼 클릭시 변경된 갯수를 컨트롤러로 넘겨서 백단의