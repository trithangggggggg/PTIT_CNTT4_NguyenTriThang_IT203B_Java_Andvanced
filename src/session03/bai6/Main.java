package session03.bai6;

import java.util.*;

class Post {
    String title;
    List<String> tags;

    public Post(String title, List<String> tags) {
        this.title = title;
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
}

public class Main {
    public static void main(String[] args) {

        List<Post> posts = List.of(
                new Post("Java Post", List.of("java", "backend")),
                new Post("Python Post", List.of("python", "data"))
        );

        List<String> allTags = posts.stream()
                .flatMap(post -> post.getTags().stream())
                .toList();

        System.out.println(allTags);
    }
}