package com.indusmart.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AIChatRequest {

    /**
     * User Message
     */
    private String message;

}