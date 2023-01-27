const form = window.document.getElementById('form');





let sum = 0;
document.querySelectorAll('.itemList').forEach(wrapper => {
    let unitPrice = wrapper.querySelector('.price-text').innerText;
    let deleteCommaUnit = parseFloat(unitPrice.replace(',',''));
    sum += deleteCommaUnit;
    document.querySelector('[rel="finalPrice"]').innerText = sum.toLocaleString();
    document.querySelector('.finalAmount-text-number').innerText = sum.toLocaleString();
})