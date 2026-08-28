package com.indusmart.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatResponseDTO {

    /**
     * Bot Reply
     */
    private String response;

}