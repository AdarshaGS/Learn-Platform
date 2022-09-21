package com.website.User.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserResponse {
    private Long ResourceId;
    private String message;
}
