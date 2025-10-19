const PostManager = {
    data: {
        posts: [],
        currentId: 0,
    },
    createPost: (title, content) => {
        const newPost = {
            id: ++PostManager.data.currentId,
            title: title,
            content: content,
            cratedAt: new Date(),
        };
        PostManager.data.posts.push(newPost);
        return newPost;
    },
    readPosts: () => {
        return PostManager.data.posts;
    },
    updatePost: (id, newContent) => {
        const post = PostManager.data.posts.find((post) => post.id === id);
        if (post) {
            post.content = newContent;
            return true;
        }
        return false;
    },
    deletePost: (id) => {
        const index = PostManager.data.posts.findIndex((post) => post.id === id);
        if (index !== -1) {
            PostManager.data.posts.splice(index, 1);
            return true;
        }
        return false;
    },
};

console.log('-------------------------------')
console.log('---------두 개의 게시글을 업로드 합니다.---------')

PostManager.createPost('안녕 세계', '이것은 나의 첫 게시글입니다.');
PostManager.createPost('두 번째 게시글', '다른 흥미로운 게시글입니다.');
console.log(PostManager.readPosts());

console.log('-------------------------------')
console.log('---------한 개의 게시글을 삭제하고, 업데이트 합니다.---------')

PostManager.updatePost(1, "첫 게시글의 내용을 업데이트했습니다.");
PostManager.deletePost(2);
console.log(PostManager.readPosts());
console.log('-------------------------------')