package ru.nikola.diploma.cloudstorageapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data

public class FileDto {
    private long id;
    private String filename;
    private Date dateCreate;
    private Date dateEdit;
    private long size;
}
