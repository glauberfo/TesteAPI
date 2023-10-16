package DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ProdutoDTO {

    public String name;
    public String username;
    public String email;
    public String street;
    public String phone;
    public String website;
    public String brand;
    public String category;
    public String thumbnail;
}
