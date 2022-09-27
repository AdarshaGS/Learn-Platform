package com.website.User.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserResponse {
    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public boolean isActive;
    public String message;
}
