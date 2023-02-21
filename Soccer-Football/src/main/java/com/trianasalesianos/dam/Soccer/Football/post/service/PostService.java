package com.trianasalesianos.dam.Soccer.Football.post.service;


import com.trianasalesianos.dam.Soccer.Football.exception.PostNotFoundException;
import com.trianasalesianos.dam.Soccer.Football.files.service.StorageService;
import com.trianasalesianos.dam.Soccer.Football.post.dto.NewPostDto;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.post.repository.PostRepository;
import com.trianasalesianos.dam.Soccer.Football.search.spec.PostSpecificationBuilder;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository repository;

    private final StorageService storageService;


    public List<Post> findAll() {

        List<Post> result = repository.findAll();

        if (result.isEmpty())
            throw new EntityNotFoundException("No users with this search criteria");

        return repository.findAll();
    }

    public Post findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user with id: " + id));

    }

    @Transactional
    public Post save(NewPostDto createPostDto, MultipartFile file) {
        String filename = storageService.store(file);

        Post post = repository.save(
                Post.builder()
                        .title(createPostDto.getTitle())
                        .image(filename)
                        .build()
        );
        return post;
    }

    public Page<Post> search(List<SearchCriteria> params, Pageable pageable) {
        PostSpecificationBuilder postSpecificationBuilder =
                new PostSpecificationBuilder(params);
        //GenericSpecificationBuilder<Person> personSpecificationBuilder =
        //        new GenericSpecificationBuilder<>(params, Person.class);
        Specification<Post> spec =  postSpecificationBuilder.build();
        return repository.findAll(spec, pageable);
    }


    public Post editDetails(Long id, Post edited) {
        return repository.findById(id)
                .map(note -> {
                    note.setTitle(edited.getTitle());
                    note.setImage(edited.getImage());
                    return repository.save(note);
                })
                .orElseThrow(() -> new PostNotFoundException());
    }

    public void delete(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }

}
