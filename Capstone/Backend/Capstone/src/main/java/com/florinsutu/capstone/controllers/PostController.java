package com.florinsutu.capstone.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.florinsutu.capstone.dto.PostDTO;
import com.florinsutu.capstone.models.Comment;
import com.florinsutu.capstone.models.Image;
import com.florinsutu.capstone.models.Post;
import com.florinsutu.capstone.models.Post;
import com.florinsutu.capstone.models.User;
import com.florinsutu.capstone.services.ImageService;
import com.florinsutu.capstone.services.PostService;
import com.florinsutu.capstone.services.UserService;

@RestController
@RequestMapping("/api/posts") 
@CrossOrigin("*")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ImageService imageService;
	

//---------------------------- Get ---------------------------------
    
    @GetMapping("page")
    public ResponseEntity<Page<Post>> getPagedPostList(Pageable p) {
    	
    	Page<Post> res = postService.getAllAndPaginate(p);
        
    	 if (res.isEmpty()){
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         } else{
             return new ResponseEntity<>(res, HttpStatus.OK);
         }
    }

    @GetMapping("{id}/followed")
    public List<Post> getFollowedPostList(@PathVariable("id") Long id) {
    	
    	return postService.getAllFollowedPostsByUserId(id);
        
    	//TODO
//    	 if (res.isEmpty()){
//             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//         } else{
//             return new ResponseEntity<>(res, HttpStatus.OK);
//         }
    }
    
    @GetMapping("{id}/explore")
    public List<Post> getUnfollowedPostList(@PathVariable("id") Long id) {
    	
    	return postService.getAllUnfollowedPostsByUserId(id);
        
    	//TODO
//    	 if (res.isEmpty()){
//             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//         } else{
//             return new ResponseEntity<>(res, HttpStatus.OK);
//         }
    }
    
    @GetMapping("{id}")
    public Post getPostById(@PathVariable("id") Long id) {
        return postService.getById(id);
    }
    
    @GetMapping("/author/{authorId}")
    public List<Post> getPostByAuthorId(@PathVariable("authorId")Long id) {
        return postService.getByAuthorId(id);
    }
    
    @GetMapping("{id}/likers")
    	public List<User> getPostLikers(@PathVariable("id") Long id){
    	return postService.getPostLikers(id);
    }
    
//---------------------------- Post --------------------------------

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Post savePost( @RequestPart("post") PostDTO dto, @RequestPart("imageFile") MultipartFile file) {
    	
    	Image postImage = uploadImage(file);
    	
    	imageService.save(postImage);
    	
        Post post = Post.builder()
        		.title(dto.getTitle())
        		.text(dto.getText())
        		.author(userService.getById(dto.getAuthorId()))
        		.date(LocalDateTime.now())
        		.likes(new HashSet<User>())
        		.edited(false)
        		.image(postImage)
        		.comments(new HashSet<Comment>())
        		.build();
        		
        return postService.save(post);
    }
    
	// TODO forse è meglio un exception handler
    public Image uploadImage(MultipartFile file) {
    	
    	try {
			Image img = Image.builder()
					.name(file.getOriginalFilename())
					.type(file.getContentType())
					.imgBytes(file.getBytes())
					.build();
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    	
    }
    
  //---------------------------- Put ---------------------------------
    
    @PutMapping("/{id}") //TODO un post in teoria ha anche un immagine
    public Post updatePost(@PathVariable("id") Long id, @RequestBody String text ) {

        Post post = postService.getById(id);
        post.setText(text);
        post.setEdited(true);
        		
        return postService.save(post);
    }
    
	@PutMapping("/{id}/like")
    public Post like(@PathVariable("id") Long likedId, @RequestBody Long likerId){
		
    	Post liked = postService.getById(likedId);
    	User liker = userService.getById(likerId);
    	
    	if(liked.getLikes().contains(liker)) 
    		liked.removeLike(liker);
    	else
    		liked.addLike(liker);

    	postService.save(liked);
    	
    	return liked;
    	
    }
    
 // -------------------------- Delete -------------------------------

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public void deletePostById(@PathVariable("id") Long id) { 
    	
    	postService.deleteById(id);

    }
	
}
