package pl.robert.api.app.post.domain;

import pl.robert.api.app.post.query.PostQuery;

class PostQueryFactory {

    static PostQuery queryPost(Post post) {
        return PostQuery.builder()
                .id(String.valueOf(post.getId()))
                .title(post.getTitle())
                .description(post.getDescription())
                .date(String.valueOf(post.getDate()).substring(0, 10))
                .time(String.valueOf(post.getDate()).substring(11, 19))
                .upVote(String.valueOf(post.getUpVote()))
                .downVote(String.valueOf(post.getDownVote()))
                .username(post.getUser().getUsername())
                .build();
    }
}
