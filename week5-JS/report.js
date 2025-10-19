import readlineSyncModule from 'readline-sync';
import Memo from './fileTest.js';

let showMenu = '1. 작성 2. 조회 3. 수정 4. 삭제 5. 추가기능 6. 종료';

const memoList = [];

let userSelect;
while (userSelect != 6) {
    console.log(showMenu);

    userSelect = parseInt(readlineSyncModule.question('사용자 입력: '), 10);
    // console.log(userSelect);
    
    switch (userSelect) {
        case 1:
            console.log('작성');
            let memoTitle = readlineSyncModule.question('메모 제목 입력: ');
            let memoContent = readlineSyncModule.question('메모 내용 입력: ');
            let memo = new Memo(memoTitle, memoContent);

            memoList.push(memo);
    
            console.log(`메모 제목 : ${memoTitle}`);
            console.log(`메모 내용 : ${memoContent}`);
            break;
        case 2:
            console.log('조회');
            memoList.forEach((m, idx) => {
                console.log(`[${idx + 1}] : ${m.title}`);
            })
            break;
        case 3:
            let memoNumber = parseInt(readlineSyncModule.question('수정 할 메모의 번호를 선택하세요. : '), 10);

            if (memoNumber <= 0 || memoNumber - 1 > memoList.length) {
                console.log(`${memoList[memonumber - 1]}.show()`);
            } else {
                console.log('문제');
            }


            break;
        case 4:
            console.log('삭제');
            break;
        case 5:
            console.log('추가 기능');
            break;
        case 6:
            console.log('종료');
            break;
        default:
            console.log('유효하지 않은 번호입니다.');
    }

    class Person {
        #name; // private 필드 선언

        constructor(name) {
            this.#name = name;
        }

        // getter
        getName() {
            return this.#name;
        }

        // setter
        setName(name) {
            this.#name = name;
        }
    }

    
}