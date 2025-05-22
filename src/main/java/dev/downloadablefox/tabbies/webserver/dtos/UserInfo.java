package dev.downloadablefox.tabbies.webserver.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    public Long id;
    public String email;
    public String role;
}
