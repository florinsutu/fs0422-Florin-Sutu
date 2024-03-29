package com.florinsutu.capstone.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.florinsutu.capstone.exceptions.NotFoundException;
import com.florinsutu.capstone.models.Message;
import com.florinsutu.capstone.models.User;
import com.florinsutu.capstone.repositories.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository repository;

    public Message save(Message x) {
        return repository.save(x);
    }

    public List<Message> getAll() {
        return repository.findAll();
    }

    public Message getById(Long id) {

        Optional<Message> message = repository.findById(id);

        if(!message.isPresent())
            throw new NotFoundException("Message Doesn't exist");

        return message.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    @Transactional
    public void deleteChat(Long id) {
    	repository.removeChatMessagesByUserId(id);
    }
    
  // ---------------------------- Paging --------------------------------  
    
	public Page<Message> getAllAndPaginate(Pageable p){
		Page<Message> pe = repository.findAll(p);
		return pe;
	}
	
//	---------------------------- Filtering --------------------------

	public Page<Message> getBySenderAndPaginate(Long senderId, Pageable p){
		Page<Message> pe = repository.findBySender(senderId, p);
		return pe;
	}
	
	public Page<Message> getByReceiverAndPaginate(Long receiverId, Pageable p){
		Page<Message> pe = repository.findByReceiver(receiverId, p);
		return pe;
	}

	public List<Message> getByChat(Long user1Id, Long user2Id) {
		
		return repository.findByChat(user1Id,user2Id);
	}

	public List<User> getUsersOfChatByUserId(Long id) {
		List<User> r = repository.getReceiversByUserId(id);
		List<User> s = repository.getSendersByUserId(id);
		
		return Stream.concat(s.stream(),r.stream()).distinct().collect(Collectors.toList());
	}
	
}
