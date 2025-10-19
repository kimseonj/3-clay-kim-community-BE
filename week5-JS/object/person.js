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

const person = new Person('John');
// private 변수를 직접 변경했을 경우 반영되지 않음
person.name = 'Park';
// John
console.log(person.getName());
person.setName('Jane');
// Jane
console.log(person.getName());