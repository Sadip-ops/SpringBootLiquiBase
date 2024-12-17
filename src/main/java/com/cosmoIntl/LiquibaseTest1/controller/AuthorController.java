package com.cosmoIntl.LiquibaseTest1.controller;


import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.AuthorRequestDTO;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.AuthorResponseDTO;
import com.cosmoIntl.LiquibaseTest1.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/create")
    public ResponseEntity<AuthorResponseDTO> saveAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        return ResponseEntity.ok(authorService.saveAuthor(authorRequestDTO));
    }
}
