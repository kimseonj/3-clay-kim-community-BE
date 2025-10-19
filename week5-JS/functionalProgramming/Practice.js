function functionCalculator(w, h) {
    return w * h;
}

const functionCalculator2 = function(w, h) {
    return w * h;
}

const calculatorArea = (w, h)  => w * h;

const area1 = calculatorArea(10, 20);
const area2 = calculatorArea(20, 30);
const area3 = calculatorArea(30, 40);

console.log(area1);
console.log(area2);
console.log(area3);

function getNumber() {
    return [1, 5, 10, 15, 30];
}

const numbers = getNumber();
const [n1, n2, n3, n4, n5] = numbers;
console.log(n1);