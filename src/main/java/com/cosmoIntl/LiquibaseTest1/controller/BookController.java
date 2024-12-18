package com.cosmoIntl.LiquibaseTest1.controller;


import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.BookRequestDTO;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.ApiResponse;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.BookResponseDTO;
import com.cosmoIntl.LiquibaseTest1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/api/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public ApiResponse<?> saveBook(@RequestPart("book") BookRequestDTO bookRequestDTO,
                                                                @RequestPart("file") MultipartFile file) {

        return bookService.saveBook(bookRequestDTO, file, false);
    }

//    @PostMapping("/create-external")
//    public ResponseEntity<BookResponseDTO> saveBookExternal(@RequestPart("book") BookRequestDTO bookRequestDTO,
//                                                            @RequestPart("file") MultipartFile file) {
//
//        BookResponseDTO responseDTO = bookService.saveBook(bookRequestDTO, file, true);
//        return ResponseEntity.ok().body(responseDTO);
//    }
}
