package com.trianasalesianos.dam.Soccer.Football.post.service;


import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.comment.repository.CommentRepository;
import com.trianasalesianos.dam.Soccer.Football.exception.CommentNotFoundException;
import com.trianasalesianos.dam.Soccer.Football.exception.LeagueNotFoundException;
import com.trianasalesianos.dam.Soccer.Football.exception.PostNotFoundException;
import com.trianasalesianos.dam.Soccer.Football.exception.TeamNotFoundException;
//import com.trianasalesianos.dam.Soccer.Football.files.service.StorageService;
import com.trianasalesianos.dam.Soccer.Football.files.service.StorageService;
import com.trianasalesianos.dam.Soccer.Football.league.dto.GetLeagueDto;
import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.post.dto.GetPostDto;
import com.trianasalesianos.dam.Soccer.Football.post.dto.NewPostDto;
import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.post.repository.PostRepository;
import com.trianasalesianos.dam.Soccer.Football.search.spec.PostSpecificationBuilder;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.team.model.Team;
import com.trianasalesianos.dam.Soccer.Football.user.model.User;
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

    private final CommentRepository commentRepository;

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


    public Post saveNew(NewPostDto createPostDto) {

        Post post = repository.save(
                Post.builder()
                        .title(createPostDto.getTitle())
                        .build()
        );
        return post;
    }

    @Transactional
    public GetPostDto save(NewPostDto newPostDto, MultipartFile file, User user) {
        String filename = storageService.store(file);

        Post post = repository.save(
                Post.builder()
                        .title(newPostDto.getTitle())
                        .image(filename)
                        .user(user)
                        .build()
        );
        return GetPostDto.fromPost(post);
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

    public GetPostDto addTeam(Long postId, Long commentId){
        Post p = repository.findById(postId).orElseThrow(() -> new PostNotFoundException());
        Comment c = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException());

        p.addCommentToPost(c);


        return GetPostDto.fromPost(repository.save(p));
    }

}
