import readlineSyncModule from 'readline-sync';

// 두 수를 더하는 코드를 함수형 프로그래밍으로 작성

// 두 수를 더하는 순수 함수
const add = (a, b) => a + b;

// 사용자로부터 두 수를 입력받고 결과를 출력하는 함수
const performCalculation = () => {
    const number1 = parseInt(readlineSyncModule.question('첫 번째 숫자를 입력하세요 : '), 10);
    const number2 = parseInt(readlineSyncModule.question('두 번째 숫자를 입력하세요 : '), 10);

    const sum = add(number1, number2);
    console.log('sum : ' + sum);
};

// 계산 수행
performCalculation();