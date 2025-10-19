import readlineSyncModule from 'readline-sync';

const number1 = parseInt(readlineSyncModule.question('첫 번째 숫자 입력 : '), 10);
const number2 = parseInt(readlineSyncModule.question('두 번째 숫자 입력 : '), 10);

// 두 수를 더하여 결과 계산
const sum = number1 + number2;

// 결과 출력
// alert("합계 : " +  sum);
console.log('합계 : ' + sum)