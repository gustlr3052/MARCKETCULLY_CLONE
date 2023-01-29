const form = document.getElementById('form');


form['questionButton'].addEventListener('click',()=>{
   window.location.href = '/bbs/write?bid=oto';
});




document.querySelectorAll('.right-content-content').forEach(wrapper => {
   wrapper.querySelector(':scope > .title')?.addEventListener('click', () => {
      if(wrapper.querySelector(':scope > .userContent').classList.contains('visible')){
         wrapper.querySelector(':scope > .userContent').classList.remove('visible');
      } else {
         wrapper.querySelector(':scope > .userContent').classList.add('visible');
      }
   });
});

const deleteButtons = document.querySelectorAll('[rel="deleteButton"]');
for (let deleteButton of deleteButtons) {
   deleteButton?.addEventListener('click',e =>{
      console.log(deleteButton.dataset.index)

      e.preventDefault();
      if(!confirm('정말로 게시글을 삭제할까요?')){
         return;
      }

      const xhr = new XMLHttpRequest();
      const formData = new FormData(); // html의 index값을 불러와야한다.
     //formData.append? 어떻게 data-index 값을 담아서 보내야하나?
      formData.append('index', deleteButton.dataset.index);
      console.log(formData);
      xhr.open('DELETE', window.location.href);
      xhr.onreadystatechange = () =>{
         if(xhr.readyState === XMLHttpRequest.DONE){
            if(xhr.status >= 200 && xhr.status <300) {
               const responseObject = JSON.parse(xhr.responseText);
               console.log('여기까진 통과하나?');
               switch (responseObject['result']){
                  case 'success':
                     window.location.href='otoWriteList?bid=oto';

                     alert('해당 문의 글을 삭제하였습니다.');
                     break;
                  case 'failure':

                     alert('삭제 권한이 없습니다.');
                     break;
                  default:
                     alert('알 수 없는 이유로 게시글을 삭제하지 못하였습니다.');
                     break;
               }
            }else{
               alert('서버와 통신하지 못하였습니다. \n\n 잠시후 다시 시도해주세요')
            }
         }
      };
      xhr.send(formData);
   });
}

// const deleteButton = window.document.getElementById('deleteButton') // delete 버튼 하나에 대한 게시글 삭제 처리이다. 이건 삭제한번에만 해당한다 따라서 반복이 필요하다.
// deleteButton?.addEventListener('click',e =>{
//    console.log(deleteButton.dataset.index)
//
//    e.preventDefault();
//    if(confirm('정말로 게시글을 삭제할까요?')){
//       return;
//    }
//    const xhr = new XMLHttpRequest();
//
//    xhr.open('DELETE', window.location.href);
//    xhr.onreadystatechange = () =>{
//      if(xhr.readyState === XMLHttpRequest.DONE){
//        if(xhr.status >= 200 && xhr.status <300) {
//           const responseObject = JSON.parse(xhr.responseText);
//           console.log('여기까진 통과하나?');
//           switch (responseObject['result']){
//              case 'success':
//                 // const bid = responseObject['bid'];
//                 // window.location.href=`otoWriteList?bid=${bid}`;
//                 alert('성공');
//                 break;
//              case 'failure':
//                 alert('실패');
//                 break;
//              default:
//                 alert('알 수 없는 이유로 게시글을 삭제하지 못하였습니다.');
//                 break;
//           }
//        }else{
//           alert('서버와 통신하지 못하였습니다. \n\n 잠시후 다시 시도해주세요')
//        }
//      }
//    };
//    xhr.send();
// });
