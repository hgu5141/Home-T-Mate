package com.example.demo.controller;

import com.example.demo.config.S3Uploader;
import com.example.demo.dto.TodolistRequestDto;
import com.example.demo.dto.postsdto.PostsDeleteRequestDto;
import com.example.demo.dto.postsdto.PostCreateResponseDto;
import com.example.demo.dto.postsdto.PostResponseDto;
import com.example.demo.model.User;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final S3Uploader s3Uploader;
    private final PostService postService;


    // 게시글 전체 조회
    @GetMapping("/api/posts")
    public List<PostResponseDto> getPost(@RequestParam("page") int page,
                                         @RequestParam("size") int size) {
        page = page - 1;
        return postService.getPost(page, size);
    }


    @GetMapping("/api/posts/myposts")
    public ResponseEntity<List<PostResponseDto>> getMyPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return ResponseEntity.ok().body(postService.getMyPosts(user));
    }

    // 게시글 작성
    @PostMapping("/api/posts")
    public ResponseEntity<PostCreateResponseDto> createpost(@RequestPart(value = "content") String content,
                                                        @RequestPart(value = "imageUrl", required = false) List<MultipartFile> multipartFile,
                                                        @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException
    {
        User user = userDetails.getUser();
        PostCreateResponseDto postCreateResponseDto =  postService.createPost(content, multipartFile, user);
        return ResponseEntity.ok().body(postCreateResponseDto);
    }



//     게시글 수정
    @PutMapping("api/posts/{postId}")
    public void updatePost(@RequestPart(value = "image", required = false) List<MultipartFile> multipartFiles,
                           @RequestPart(value = "content", required = false) String content,
                           @PathVariable Long postId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails
    ) throws IOException {
        User user = userDetails.getUser();
        postService.updatePost(multipartFiles, content, postId, user);
    }

    // 게시글 하나 삭제
    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<TodolistRequestDto.Response> deletePost(@PathVariable Long postId,
                                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails);

        TodolistRequestDto.Response response = new TodolistRequestDto.Response();
        response.setResult(true);
        return ResponseEntity.ok().body(response);
    }

    // 게시글 여러개 삭제
    @DeleteMapping("/api/posts")
    @ResponseBody
    public void deletePost(@RequestBody List<PostsDeleteRequestDto> postsDeleteRequestDtos,
                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePosts(postsDeleteRequestDtos, userDetails);
    }
}
