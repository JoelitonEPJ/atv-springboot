package biblioteca.biblioteca.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import biblioteca.biblioteca.Model.Autor;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivroPostPutDto {
    @JsonProperty("tituloLivro")
    @NotBlank(message="Título não pode ser vazio")
    private String tituloLivro;

    @JsonProperty("ano")
    @NotBlank(message="Ano não pode ser vazio")
    private int ano;

    @JsonProperty("edicao")
    @NotBlank(message="Edicao não pode ser vazio")
    private int edicao;
}