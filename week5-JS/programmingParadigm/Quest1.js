const arr = [1, 2, 3, 4, 5];

const reduce = (arr) => {
    let sum = 0;
    arr.forEach(element => {
        sum += element;
    });

    return sum;
}

const sum = arr.reduce((accumulator, currentValue) => {
    return accumulator + currentValue;
});

console.log(reduce(arr));
console.log(sum);