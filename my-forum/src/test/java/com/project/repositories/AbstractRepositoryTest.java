package com.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AbstractRepositoryTest {
    @Autowired protected GameRepository gameRepository;
    @Autowired protected GenreRepository genreRepository;
    @Autowired protected PostRepository postRepository;
    @Autowired protected ProblemTopicRepository problemTopicRepository;
    @Autowired protected RoleRepository roleRepository;
    @Autowired protected ThreadRepository threadRepository;
    @Autowired protected UserRepository userRepository;
}
