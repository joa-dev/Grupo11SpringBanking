package com.grupo11.mappers;

import com.grupo11.entities.Transfer;
import com.grupo11.entities.dtos.TransferDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {
    //PREGUNTAR A CRISTIAN
    /* el dto que ingresa solo tiene id y la clase tiene objetos. deberia buscarlos aca? o no uso este mapper de dto a objeto y solo uso el inverso?
    public Transfer dtoToTransfer(TransferDto dto){
        return Transfer.builder()
                .amount(dto.getAmount())
                .date(dto.getDate())
                .origin(dto.getOrigin())
                .target(dto.getTarget())
                .build();
    }
     */

    public TransferDto transferToDto(Transfer transfer){
        return TransferDto.builder()
                .id(transfer.getId())
                .amount(transfer.getAmount())
                .target(transfer.getTarget().getId())
                .origin(transfer.getOrigin().getId())
                .date(transfer.getDate())
                .build();
    }
}
