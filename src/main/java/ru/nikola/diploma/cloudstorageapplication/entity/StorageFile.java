package ru.nikola.diploma.cloudstorageapplication.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "FILES")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FILE_NAME", nullable = false, unique = true)
    private String fileName;

    @Column(name = "DATE", columnDefinition = "bytea")
    private byte[] date;

    @Column(name = "SIZE", nullable = false)
    private long size;

    @UpdateTimestamp
    @Column(name = "DATE_CREATED")
    private Date dateCreate;

    @UpdateTimestamp
    @Column(name = "DATE_EDITED")
    private Date dateEdit;
}