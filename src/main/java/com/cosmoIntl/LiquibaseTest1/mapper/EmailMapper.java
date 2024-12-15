package com.cosmoIntl.LiquibaseTest1.mapper;

import com.cosmoIntl.LiquibaseTest1.dto.requestDTO.EmailRequestDto;
import com.cosmoIntl.LiquibaseTest1.entity.EmailDetail;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmailMapper {

 EmailDetail toEmailDetail(EmailRequestDto emailRequestDto);

}
