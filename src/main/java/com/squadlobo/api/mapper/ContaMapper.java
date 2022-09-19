package com.squadlobo.api.mapper;

import com.squadlobo.api.dto.ContaRequestDTO;
import com.squadlobo.api.dto.ContaResponseDTO;
import com.squadlobo.api.model.Conta;
import com.squadlobo.api.model.ContaCorrente;
import com.squadlobo.api.model.ContaEspecial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    ContaEspecial toContaEspecial(ContaRequestDTO dto);

    ContaCorrente toContaCorrente(ContaRequestDTO dto);

    ContaRequestDTO toContaRequestDto(Conta model);

    ContaResponseDTO toContaResponseDto(Conta model);
}
