package com.florinsutu.capstone.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.florinsutu.capstone.exceptions.NotFoundException;
import com.florinsutu.capstone.models.Image;
import com.florinsutu.capstone.repositories.ImageRepository;

@Service
public class ImageService {

	@Autowired
	ImageRepository repository;
	
	public Image save(Image image) {
        return repository.save(image);
    }

    public List<Image> getAll() {
        return repository.findAll();
    }

    public Image getById(Long id) {

        Optional<Image> image = repository.findById(id);

        if(!image.isPresent())
            throw new NotFoundException("Image Doesn't exist");

        return image.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
