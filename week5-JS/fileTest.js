class Memo {
    constructor(title, content) {
        this.title = title;
        this.content = content;
    }

    show() {
        console.log(`제목 : ${this.title}`);
        console.log(`내용 : ${this.content}`);
    }
}

export default Memo;