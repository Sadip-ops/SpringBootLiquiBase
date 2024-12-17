package com.cosmoIntl.LiquibaseTest1.service;


import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.AuthorRequestDTO;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.AuthorResponseDTO;
import com.cosmoIntl.LiquibaseTest1.entity.Author;
import com.cosmoIntl.LiquibaseTest1.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;


    @Override
    public AuthorResponseDTO saveAuthor(AuthorRequestDTO authorRequestDTO){
        Author author = Author.builder()
                .name(authorRequestDTO.getName())
                .build();

        Author savedAuthor = authorRepository.save(author);

        return AuthorResponseDTO.builder()
                .id(savedAuthor.getId())
                .name(savedAuthor.getName())
                .build();
    }
}

