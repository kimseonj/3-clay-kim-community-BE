const numbers = [1, 2, 3, 4, 5];

let sum = 0;
for(let i = 0; i < numbers.length; i++) {
    sum += numbers[i];
    console.log(`현재 합계: ${sum} (${numbers[i]}를 더함)`)
}

console.log(`최종 합계 : ${sum}`)