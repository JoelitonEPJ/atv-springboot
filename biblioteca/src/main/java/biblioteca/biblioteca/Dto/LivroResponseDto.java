package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import biblioteca.biblioteca.Model.Autor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroResponseDto {
    @JsonProperty("isbn")
    private Long isbn;

    @JsonProperty("tituloLivro")
    private String tituloLivro;

    @JsonProperty("autor")
    private Autor autor;

    @JsonProperty("ano")
    private int ano;

    @JsonProperty("edicao")
    private int edicao;
}