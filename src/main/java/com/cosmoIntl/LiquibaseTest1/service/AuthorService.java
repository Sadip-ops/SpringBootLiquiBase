package com.cosmoIntl.LiquibaseTest1.service;

import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.AuthorRequestDTO;
import com.cosmoIntl.LiquibaseTest1.dto.responseDTO.AuthorResponseDTO;

public interface AuthorService {

    AuthorResponseDTO saveAuthor(AuthorRequestDTO authorRequestDTO);
}
