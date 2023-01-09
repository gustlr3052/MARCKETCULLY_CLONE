const form = window.document.getElementById('form');





form['back'].addEventListener('click',()=> window.history.length <2 ? window.close():window.history.back());

form.onsubmit = e =>{
    e.preventDefault();

    if(form['title'].value === ''){
        alert('제목을 입력해주세요.');
        form['title'].focus();
        return false;
    }
    if(form['content'].value === ''){
        alert('내용을 입력해 주세요.');
        form['content'].focus();
        return false;
    }
    alert('게시글을 작성하고 있습니다. \n\n 잠시만 기다려 주세요.');


    // 제목, 작성일, 답변상태는 list에서 불러오는 값
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('bid',form['bid'].value);
    formData.append('title',form['title'].value);
    formData.append('content',form['content'].value);
    xhr.open('POST', './write');
    xhr.onreadystatechange = () =>{
      if(xhr.readyState === XMLHttpRequest.DONE){
          console.log(xhr);
        if(xhr.status >= 200 && xhr.status <300) {
            const responseObject = JSON.parse(xhr.responseText);
            console.log(responseObject);
            switch (responseObject['result']){
                case 'not_allowed':
                    alert('게시글을 작성할 수 있는 권한이 없거나 로그아웃 되었습니다. 확인 후 다시 시도해주세요.')
                    break;
                case 'success':

                   window.location.href='otoWriteList?bid=oto'// 아직 잘 모르겠음
                 break;
                default:
                    alert('알 수 없는 이유로 게시글을 작성하지 못하였습니다. 잠시후 다시 시도해주세요.')
            }
        }else{
            alert('서버와 통신하지 못하였습니다. 잠시 후 다시 시도해주세요.');
        }
      }
    };
    xhr.send(formData);
}