const hour = 18;

if (hour >= 7 && hour <9) {
    console.log('아침 식사 시간');
} else if (hour >= 12 && hour < 14) {
    console.log('점심 식사 시간');
} else if (hour >= 18 && hour < 20) {
    console.log('저녁 식사 시간');
} else {
    console.log('식사 금지');
}

switch (true) {
    case (hour >= 7 && hour <9):
        console.log('아침 식사 시간');
        break;
    case (hour >= 12 && hour < 14):
        console.log('점심 식사 시간');
        break;
    case(hour >= 18 && hour < 20):
        console.log('저녁 식사 시간');
        break;
    default:
        console.log('식사 금지');
}