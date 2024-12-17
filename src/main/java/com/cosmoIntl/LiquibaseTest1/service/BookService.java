package com.cosmoIntl.LiquibaseTest1.service;

import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.BookRequestDTO;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.BookResponseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface BookService {

    BookResponseDTO saveBook(BookRequestDTO requestDTO, MultipartFile file,boolean isExternal);
}
