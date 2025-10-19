const jeju_form = '섬';

if (jeju_form !== '섬') {
    console.log('택배가 빨리 왔을텐데');
} else {
    console.log('비행기를 안 탔을텐데');
}

const jeju_temperature = 10;

if (jeju_temperature == 10) {
    console.log('야외에서 운동 했을 텐데');
    console.log('저장 테스트');
} else if (jeju_temperature == -1) {
    console.log('실내에서 운동 했을 텐데');
    
} else {
    console.log('운동 안 할 텐데');
}

const 개발실력 = '상';
switch (개발실력) {
    case '상':
        console.log('최고의 개발자');
        break;
    case '중':
        console.log('최고의 개발자');
        break;
    case '하':
        console.log('최고의 개발자');
        break;
    default:
        console.log('개발 포기자');
        break;
}