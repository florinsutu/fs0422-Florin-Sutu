package com.florinsutu.capstone.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.florinsutu.capstone.dto.RegisterDTO;
import com.florinsutu.capstone.dto.UserUpdate;
import com.florinsutu.capstone.models.Image;
import com.florinsutu.capstone.models.Post;
import com.florinsutu.capstone.models.Role;
import com.florinsutu.capstone.models.RoleType;
import com.florinsutu.capstone.models.User;
import com.florinsutu.capstone.services.ImageService;
import com.florinsutu.capstone.services.PostService;
import com.florinsutu.capstone.services.RoleService;
import com.florinsutu.capstone.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PostService postService;

	@Autowired
	private ImageService imageService;

//---------------------------- Get ---------------------------------

	@GetMapping("page")
	public ResponseEntity<Page<User>> getPagedUserList(Pageable p) {

		Page<User> res = userService.getAllAndPaginate(p);

		if (res.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getUserList() {

		List<User> res = userService.getAll();

		if (res.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
	}

	@GetMapping("{id}")
	public User getUserById(@PathVariable("id") Long id) {
		return userService.getById(id);
	}

	@GetMapping("{id}/followers")
	public List<User> getFollowers(@PathVariable("id") Long id) {
		return userService.getAllFollowers(id);
	}

	@GetMapping("{id}/followed")
	public List<User> getFollowed(@PathVariable("id") Long id) {
		return userService.getAllFollowed(id);
	}

//---------------------------- Post --------------------------------

	@PostMapping("/register")
	public User registerUser(@RequestBody RegisterDTO dto) {

		User user = User.builder()
				.username(dto.getUsername())
				.email(dto.getEmail())
				.password(encoder.encode(dto.getPassword()))
				.active(true)
				.isOnline(false)
				.isPrivate(false)
				.registration(LocalDateTime.now())
				.roles(new HashSet<Role>())
				.followed(new HashSet<User>())
				.build();

		user.addRole(roleService.getByRole(RoleType.ROLE_USER));

		logger.info("Save User in UserController");
		return userService.save(user);
	}

//---------------------------- Put ---------------------------------

	@PutMapping("/{id}/add-role/{roleType}")
//	@PreAuthorize("hasRole('ADMIN')")
	public void addRole(@PathVariable("id") Long id, @PathVariable("roleType") RoleType roleType) {
		User user = userService.getById(id);
		user.addRole(roleService.getByRole(roleType));

		userService.save(user);
		logger.info("Role added");
	}

	@PutMapping("/{id}")
	public User updateUser(@PathVariable("id") Long id, @RequestBody UserUpdate update) {

		User user = userService.getById(id);

		if (update.getName() != null) user.setName(update.getName());
		if (update.getLastname() != null) user.setLastname(update.getLastname());
		if (update.getUsername() != null) user.setUsername(update.getUsername());
		if (update.getEmail() != null) user.setEmail(update.getEmail());
		if (update.getPassword() != null) user.setPassword(update.getPassword());
		if (update.getDescription() != null) user.setDescription(update.getDescription());
		if (update.getIsPrivate() != null) user.setIsPrivate(update.getIsPrivate());

		userService.save(user);
		return user;
	}
	
	@PutMapping(value="/{id}/updateProfilePic", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public User updateProfilePic(@PathVariable("id") Long id, @RequestPart("imageFile") MultipartFile file) {
		
		Image postImage = uploadImage(file);
    	imageService.save(postImage);
    	
    	User user = userService.getById(id);
    	user.setImage(postImage);
		userService.save(user);
		
    	return user;
	}
	
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

	@PutMapping("/{id}/follow") // TODO non dovresti poter seguir te stesso
	public User follow(@PathVariable("id") Long followerId, @RequestBody Long followedId) {

		User follower = userService.getById(followerId);
		User followed = userService.getById(followedId);

		if (follower.getFollowed().contains(followed))
			follower.unfollow(followed);
		else
			follower.follow(followed);

		userService.save(follower);

		return follower;

	}

// -------------------------- Delete -------------------------------

	@DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
	public void deleteUserById(@PathVariable("id") Long id) {
		List<Post> userPosts = postService.getByAuthorId(id);
		postService.deletePostList(userPosts);
		userService.deleteById(id);
//		return "User deleted successfully";
	}

	// ---------------------------- Tests --------------------------

//    @GetMapping("users-paginate-byname/{name}")
//    public Page<User> getByNameAndPaginate(@PathVariable("name")String name, Pageable p){
//        Page<User> res =  userService.getByNameAndPaginate(name, p);
//        return res;
//
//    }
//    
//    @GetMapping("test/test1")
//    public String test1(){
//        return "java";
//    }
//    
//    @GetMapping("test/test2")
//    public String test2(){
//        return "java";
//    }

}
