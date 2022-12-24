const form = window.document.getElementById('form'); //getElementById = html.form. id 값

form['login'].addEventListener('click',()=>{
    if(form['email'].value === ''){
        alert('이메일을 입력해주세요.');
        form['email'].focus();
        return;
    }
    if(form['password'].value ===''){
        alert('비밀번호를 입력해주세요.');
        form['password'].focus();
        form['password'].select();
        return;
    }

    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('email',form['email'].value);
    formData.append('password',form['password'].value);
    xhr.open('POST', 'login');
    xhr.onreadystatechange = () =>{
      if(xhr.readyState === XMLHttpRequest.DONE){
        if(xhr.status >= 200 && xhr.status <300) {
    	    const responseObject = JSON.parse(xhr.responseText);
            switch (responseObject['result']){
                case 'success':
                    alert('로그인 성공');
                    window.location.href='/main/main'; // 로그인 성공시 이동할 주소
                    break;
                default:
                    alert('로그인 실패');
                    form['email'].focus();
                    form['email'].select();
                    break;
            }
        }else{
            alert('서버와 통신하지 못하였습습니다. 잠시후 다시 시도해주세요.');

        }
      }
    };
    xhr.send(formData);



});