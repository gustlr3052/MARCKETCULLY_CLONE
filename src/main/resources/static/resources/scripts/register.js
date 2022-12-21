const form = window.document.getElementById("form");
//elementId : html에서 id값이 들어간다.



form['emailSend'].addEventListener('click',()=> { // form['name 값']
    if (form['email'].value === '') { //인증하기를 클릭하였을때
        alert('이메일을 입력해주세요.');// 값이 비어있으면 이메일을 입력해주세요.라면서
        form['email'].focus();// 이메일주소 입력하기에 포커스 두기
    }
    if (!new RegExp('^(?=.{9,50}$)([\\da-zA-Z\\-_.]{4,})@([\\da-z\\-]{2,}\\.)?([\\da-z\\-]{2,})\\.([a-z]{2,15})(\\.[a-z]{2})?$').test(form['email'].value)) { //올바른 이메일 주소가 아닐때
        alert('올바른 이메일 주소를 입력해주세요.');                    //올바른 이메일 주소 입력하라는 '알림'
        form['email'].focus();                                    // 이메일 입력창에 포커스를 둔다.
    }
    alert('인증번호를 전송하고 있습니다. \n\n 잠시만 기다려 주세요.');

    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('email',form['email'].value);

    xhr.open('POST', './email');
    xhr.onreadystatechange = () =>{
      if(xhr.readyState === XMLHttpRequest.DONE){
        if(xhr.status >= 200 && xhr.status <300) {
            const responseObject = JSON.parse(xhr.responseText); // responseText 객체화시켜 문자열을 객체화
            switch (responseObject['result']){
                case 'success':
                    form['email'].setAttribute('disabled','disabled');
                    form['emailSend'].setAttribute('disabled','disabled');
                    form['emailAuthCode'].setAttribute('disabled','disabled');
                    form['emailAuthSalt'].value = responseObject['salt'];
                    form['emailAuthCode'].focus();
                    form['emailVerify'].removeAttribute('disabled');
                    break;
                case 'email_duplicated':
                    alert('해당 이메일은 이미 사용 중입니다.');
                    form['email'].focus();
                    form['email'].select();
                    break;
                default:
                    alert('알 수 없는 이유로 인증 번호를 전송하지 못 하였습니다. 잠시 후 다시 시도해 주세요.');
                    form['email'].focus();
                    form['email'].select();
            }
        }else{
            alert('서버와 통신하지 못하였습니다. 잠시후 다시 시도해 주세요.');
        }
      }
    };
    xhr.send(formData);

});

form['emailVerify'].addEventListener('click',()=>{
    if(form['emailVerify'].value === ''){
        alert('인증번호를 입력해 주세요.');
        form['emailAuthCode'].focus();
        return;
    }
    if (!new RegExp('^(\\d{6})$').test(form['emailAuthCode'].value)) {
       alert('올바른 인증 번호를 입력해주세요.');
        form['emailAuthCode'].focus();
        form['emailAuthCode'].select();
        return;
    }

    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('email', form['email'].value);
    formData.append('code', form['emailAuthCode'].value);
    formData.append('salt', form['emailAuthSalt'].value);
    xhr.open('PATCH', 'email');
    xhr.onreadystatechange = () =>{
      if(xhr.readyState === XMLHttpRequest.DONE){
        if(xhr.status >= 200 && xhr.status <300) {
            const responseObject = JSON.parse(xhr.responseText);
            switch (responseObject['result']){
                case 'expired':
                    alert('인증 정보가 만료되었습니다. 다시 시도해 주세요.');
                    form['email'].removeAttribute('disabled');
                    form['email'].focus();
                    form['email'].select();
                    form['emailSend'].removeAttribute('disabled');
                    form['emailAuthCode'].value = '';
                    form['emailAuthCode'].setAttribute('disabled','disabled');
                    form['emailAuthSalt'].value = '';
                    form['emailVerify'].setAttribute('disabled','disabled');
                    break;
                case 'success':
                    alert('이메일이 정상적으로 인증되었습니다.');
                    form['emailAuthCode'].setAttribute('disabled','disabled');
                    form['emailVerify'].setAttribute('disabled','disabled');
                    form['password'].focus();
                    break;
                default:
                    alert('인증 번호가 올바르지 않습니다.');
                    form['emailAuthCode'].focus();
                    form['emailAuthCode'].select();
            }

        }else{
            alert('서버와 통신하지 못하였습니다. 잠시후 다시 시도해 주세요,');
        }
      }
    };
    xhr.send(formData);

})





form['addressFind'].addEventListener('click', () => {
    new daum.Postcode({ //MPI 사용해서 없애야 하는건데 이건 교육과정에서 안배움
        oncomplete: e => {
            form.querySelector('[rel="addressFindPanel"]').classList.remove('visible'); //
            form['addressPostal'].value = e['zonecode'];    //우편번호 넣기
            form['addressPrimary'].value = e['address'];    //기본주소 넣기
            form['addressSecondary'].value = '';            //상세주소 비워두기
            form['addressSecondary'].focus();               //상세주소 초점
        }
    }).embed(form.querySelector('[rel = "addressFindPanelDialog"]'));
    form.querySelector('[rel="addressFindPanel"]').classList.add('visible') //패널 보이기

});
form.querySelector('[rel= "addressFindPanel"]').addEventListener('click', () => {
    form.querySelector('[rel="addressFindPanel"]').classList.remove('visible');
}); // 패널클릭하게 되면 패널이 사라지기


form.querySelector('[rel="buttonJoin"]').addEventListener('click',()=>{

    if (!form['emailSend'].disabled || !form['emailVerify'].disabled) {
        alert('이메일 인증을 완료해 주세요.');
        return;
    }
    if(form['password'].value !== form['passwordCheck'].value){ // 패스워드와  패스워드확인 값이 다르다면
        alert('비밀번호가 일치하지 않습니다.');                      // 비밀번호가 일치하지 않다는 '알림'
        form['passwordCheck'].focus();                          // 패스워드 확인에 초점
        form['passwordCheck'].select();
    }
    if(form['name'].value ===''){
        alert('이름을 입력해주세요');
        form['name'].focus();
    }
    if(form['nickname'].value ===''){
        alert('닉네임을 입력해주세요');
        form['nickname'].focus();
    }
    if(form['contact'].value ===''){
        alert('휴대폰번호를 입력해주세요');
        form['contact'].focus();
    }
    if(form['addressPostal'].value ===''){
        alert('올바른 주소를 입력해주세요');
        form['addressPostal'].focus();
    }
});