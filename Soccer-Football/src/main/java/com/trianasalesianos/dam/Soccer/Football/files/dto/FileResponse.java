package com.trianasalesianos.dam.Soccer.Football.files.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class FileResponse {
    private String name;
    private String uri;
    private String type;
    private long size;
}
