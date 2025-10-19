// 제공 코드
const number = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

// 퀘스트 답안 작성
let sum = 0;
for (let i = 0; i < number.length; i++) {
    if (number[i]%2 === 0) {
        sum += number[i];

        console.log(`짝수 발견: ${number[i]}`)
    }
}

console.log(`짝수 합계: ${sum}`);