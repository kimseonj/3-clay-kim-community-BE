import readlineSyncModule from 'readline-sync';

let isCorrect = false;

const sentence = '열심히 배워서 최고의 개발자가 되어보자!'
console.log(`문장: ${sentence}`);

let input = readlineSyncModule.question('문장 입력: ');

if (input === sentence) {
    isCorrect = true;
}

if (isCorrect) {
    console.log('정답입니다.');
} else {
    console.log('실패입니다.');
}