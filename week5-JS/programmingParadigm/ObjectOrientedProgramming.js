import readlineSyncModule from 'readline-sync';

class Calculator {
    constructor() {
        this.number1 = 0;
        this.number2 = 0;
    }

    // 사용자로부터 두 수를 입력 받는 메서드
    getInput() {
        this.number1 = parseFloat(readlineSyncModule.question('첫 번째 숫자 입력 : '));
        this.number2 = parseFloat(readlineSyncModule.question('두 번째 숫자 입력 : '));    
    }

    // 두 수를 더하는 메서드
    add() {
        return this.number1 + this.number2;
    }

    // 결과를 출력하는 메서드
    displayResult(result) {
        console.log('sum = ' + result);
    }

    // 계산기 작동을 위한 메서드
    run() {
        this.getInput();
        const result = this.add();
        this.displayResult(result);
    }
}

// Calculator 클래스의 인스턴스 생성 및 실행
const calculator = new Calculator();
calculator.run();