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

}