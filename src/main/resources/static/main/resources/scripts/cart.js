const form = window.document.getElementById('form');
let result = document.querySelector('.moveCount');




// document.querySelectorAll('.li-list').forEach(wrapper=>{
//     let unitPrice = parseInt(wrapper.dataset.unitPrice.replace(',',''));
//     wrapper.querySelector('.moveCount').innerText = count;
//     document.querySelector('[rel="finalPrice"]').innerText = (parseInt(count) * parseInt(unitPrice)).toLocaleString();
//
// });



// document.querySelectorAll('.li-list').forEach(wrapper=>{
//
//     sum += deleteCommaUnit;
//     let toLocalString = sum.toLocaleString();
//     document.querySelector('[rel="finalPrice"]').innerText = toLocalString;
// });
//


    // sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
// let result = sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
// console.log(result);




let sum = 0;
document.querySelectorAll('.li-list').forEach(wrapper => {
    let unitPrice = wrapper.querySelector('.unitPrice').innerText;
    let deleteCommaUnit = parseFloat(unitPrice.replace(',',''));
    sum += deleteCommaUnit;
    document.querySelector('[rel="finalPrice"]').innerText = sum.toLocaleString();
    document.querySelector('.finalAmount-text-number').innerText = sum.toLocaleString();

    wrapper.querySelector('[rel="plusButton"]').addEventListener('click', () => {
        console.log(wrapper.dataset.index);
        let count = parseInt(wrapper.dataset.count);
        let unitPrice = parseInt(wrapper.dataset.unitPrice.replace(',', ''));
        count++;

        wrapper.dataset.count = count + '';
        wrapper.querySelector('.moveCount').innerText = count;

        wrapper.querySelector('.unitPrice').innerText = (parseInt(count) * parseInt(unitPrice)).toLocaleString();

        sum += unitPrice;
        document.querySelector('[rel="finalPrice"]').innerText = sum.toLocaleString();
        document.querySelector('.finalAmount-text-number').innerText = sum.toLocaleString();

    });
    wrapper.querySelector('[rel="minusButton"]').addEventListener('click', () => {

        let count = parseInt(wrapper.dataset.count);
        if (count > 1) {
            count--;
        }
        let unitPrice =parseInt(wrapper.dataset.unitPrice.replace(',',''));
        wrapper.dataset.count = count + '';
        wrapper.querySelector('.moveCount').innerText = count;
        wrapper.querySelector('.unitPrice').innerText = (parseInt(count) * parseInt(unitPrice)).toLocaleString();

        sum -=unitPrice;
        document.querySelector('[rel="finalPrice"]').innerText = sum.toLocaleString();
        document.querySelector('.finalAmount-text-number').innerText = sum.toLocaleString();
    });
})






// document.querySelectorAll('.li-list').forEach(wrapper=>{
//     let unitPrice = parseInt(wrapper.dataset.unitPrice.replace(',',''));
//     finalPrice = unitPrice + unitPrice;
//     finalPrice = finalPrice.toLocaleString();
//     wrapper.querySelector('[rel="finalPrice"]').innerText = finalPrice;
//
// })

// 최종 합계 구하기
// 1. final가격 정수로 바꾸고
// 2. 인덱스 별로의 final가격을 가져와서 합 (처리한다)
// lilist에서 인덱스 하나하나 wrapper 처럼
// let liIndex = document.querySelector('.li-list');
// let totalPrice= function(prices){
//     for(let i = 1 ; i < liIndex.length; i++){
//         prices = i++;
//         prices = document.querySelector('.unit-price').innerText
//         prices.replace(',','');
//         console.log(prices);
//         prices = prices + prices;
//         prices.toLocaleString();
//
//     }
//     return ;
// }
// document.querySelectorAll('[rel="finalPrice"]').innerText =totalPrice()


const checkAll = document.querySelector('.checkBoxAll');
const checkArr = document.querySelectorAll('.checkBox');
checkAll.addEventListener('click', () => {
    checkArr.forEach(ck => {
        ck.checked = checkAll.checked;
        console.log(ck.dataset.index);
    });
});
checkArr.forEach(ck => {
    ck.addEventListener('click', () => {
        let cnt = 0;
        checkArr.forEach(ck => {
            if (ck.checked) {
                cnt++;

            }
        });
        if (cnt === checkArr.length) {
            checkAll.checked = true;
        } else {
            checkAll.checked = false;
        }
        console.log(ck.dataset.index);

    });
});



document.querySelector('.checked-delete').addEventListener('click',e =>{

    const query = 'input[name="chk"]:checked'
    const selectedElements = document.querySelectorAll(query)

    const selectedElementsCnt = selectedElements.length;

    if(selectedElementsCnt === 0){
        alert('삭제할 항목을 선택해주세요');
        return false
    }
    else{
        if(confirm("정말로 삭제하시겠습니까?")){
            const arr = new Array(selectedElementsCnt);


            const formData = new FormData();
            // li-list에 대해서 forEach문을 돌리고 check의 유무를 확인한다음 check가 되어있으면 그 체크된 인덱스를 넘겨서 삭제한다.
            document.querySelectorAll('.li-list').forEach(function(v,i){
                if(v.querySelector('input[name="chk"]').checked){ // 여기서 v는 매개변수로 check박스 check유무를 위해 사용
                    arr[i] = v.dataset.index;
                    formData.append('index',v.dataset.index);
                    console.log(arr);
                }
            });
            const xhr = new XMLHttpRequest();
            xhr.open('DELETE', window.location.href);
            xhr.onreadystatechange = () =>{
                if(xhr.readyState === XMLHttpRequest.DONE){
                    if(xhr.status >= 200 && xhr.status <300) {
                        const responseObject = JSON.parse(xhr.responseText);
                        switch (responseObject['result']){
                            case'success':
                                window.location.href = './cart'
                                alert('성공');
                                break;
                            case'failure':
                                alert('실패');
                                break;default:
                                alert('알 수 없는 이유로 상품삭제 불가');
                                break;
                        }
                    }else{
                        alert('서버통신 x');
                    }
                }
            };
            xhr.send(formData);
        }
    }
})





const deleteButtons = document.querySelectorAll('[rel="deleteButton"]');
for (let deleteButton of deleteButtons) {
    deleteButton?.addEventListener('click', e => {
        console.log(deleteButton.dataset.index)
        e.preventDefault();
        if (!confirm('선택한 상품을 삭제하시겠습니까?')) {
            return;
        }

        const xhr = new XMLHttpRequest();
        const formData = new FormData();
        formData.append('index', deleteButton.dataset.index);
        xhr.open('DELETE', window.location.href);
        xhr.onreadystatechange = () => {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status >= 200 && xhr.status < 300) {
                    const responseObject = JSON.parse(xhr.responseText);
                    console.log('여기까지 통과테스트');
                    switch (responseObject['result']) {
                        case'success':
                            window.location.href = './cart'
                            alert('삭제 성공');
                            break;
                        case 'failure':
                            alert('실패');
                            break;
                        default:
                            alert('알 수 없는 이유로 상품삭제 불가');
                            break;
                    }
                } else {
                    alert('서버 통신 x');
                }
            }
        };
        xhr.send(formData);
    })
}


 // document.querySelector('.order').addEventListener('click',()=>{
 //     const query = 'input[name="chk"]:checked'
 //     const selectedElements = document.querySelectorAll(query)
 //
 //     const selectedElementsCnt = selectedElements.length;
 //
 //     if(selectedElementsCnt === 0){
 //         alert('삭제할 항목을 선택해주세요');
 //         return false
 //     }
 //     else{
 //         if(confirm("정말로 삭제하시겠습니까?")){
 //             const arr = new Array(selectedElementsCnt);
 //
 //             const xhr = new XMLHttpRequest();
 //     const formData = new FormData();
 //     let a = document.querySelector('[rel="orderCount"]').forEach()
 //     console.log(a.innerText);
 //     xhr.open('POST', window.location.href);
 //     xhr.onreadystatechange = () =>{
 //       if(xhr.readyState === XMLHttpRequest.DONE){
 //         if(xhr.status >= 200 && xhr.status <300) {
 //             const responseObject = JSON.parse(xhr.responseText);
 //             console.log(responseObject);
 //             switch (responseObject['result']){
 //                 case'success':
 //                     window.location.href='cart/order'
 //                     alert('최종 주문하기 완료');
 //                     break;
 //                 case 'failure':
 //                     alert('실패');
 //                     break;
 //             }
 //         }else{
 //             alert('서버와 통신하지 못하였습니다.')
 //         }
 //       }
 //     };
 //     xhr.send(formData);
 // })

document.querySelector('.order').addEventListener('click',e =>{

    const query = 'input[name="chk"]:checked'
    const selectedElements = document.querySelectorAll(query)

    const selectedElementsCnt = selectedElements.length;

    if(selectedElementsCnt === 0){
        return false
    }
    else{
        if(confirm("정말로 주문하시겠습니까?")){
            const arr = new Array(selectedElementsCnt);
            const formData = new FormData();
            // li-list에 대해서 forEach문을 돌리고 check의 유무를 확인한다음 check가 되어있으면 그 체크된 인덱스를 넘겨서 삭제한다.
            document.querySelectorAll('.li-list').forEach(function(v,i){
                if(v.querySelector('input[name="chk"]').checked){ // 여기서 v는 매개변수로 check박스 check유무를 위해 사용
                    arr[i] = v.dataset.index;
                    formData.append('index',v.dataset.index);
                    // formData.append('cartList',form['cartList'].value);
                    console.log(arr);
                }
            });
            const xhr = new XMLHttpRequest();
            xhr.open('POST', './order');
            xhr.onreadystatechange = () =>{
                if(xhr.readyState === XMLHttpRequest.DONE){
                    if(xhr.status >= 200 && xhr.status <300) {
                        const responseObject = JSON.parse(xhr.responseText);
                        switch (responseObject['result']){
                            case'success':
                                window.location.href = './order'
                                alert('성공');
                                break;
                            case'failure':
                                alert('실패');
                                break;default:
                                alert('알 수 없는 이유로 상품삭제 불가');
                                break;
                        }
                    }else{
                        alert('서버통신 x');
                    }
                }
            };
            xhr.send(formData);
        }
    }
})



