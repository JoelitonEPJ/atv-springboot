package biblioteca.biblioteca.Service;

import biblioteca.biblioteca.Dto.LivroPostPutDto;
import biblioteca.biblioteca.Dto.LivroResponseDto;
import biblioteca.biblioteca.Model.Livro;
import biblioteca.biblioteca.Repository.LivroRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.ArrayList;

@Service
public class LivroCrudPadraoService implements LivroCrudService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LivroRepository livroRepository;

    @Override
    public LivroResponseDto criarLivro(LivroPostPutDto livroPostPutDto) {
        Livro livro = modelMapper.map(livroPostPutDto, Livro.class);
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public List<LivroResponseDto> buscarTodosLivros() {
        return livroRepository.findAll()
                .stream()
                .map(livro -> modelMapper.map(livro, LivroResponseDto.class))
                .toList();
    }

    @Override
    public LivroResponseDto buscarLivro(String isbn) {
        return modelMapper.map(obterLivroExistente(isbn), LivroResponseDto.class);
    }

    @Override
    public LivroResponseDto atualizarLivro(String isbn, LivroPostPutDto livroPostPutDto) {
        Livro livro = obterLivroExistente(isbn);
        livro.setAno(livroPostPutDto.getAno());
        livro.setAutor(livroPostPutDto.getAutor());
        livro.setEdicao(livroPostPutDto.getEdicao());
        livro.setTituloLivro(livroPostPutDto.getTituloLivro());
        return modelMapper.map(livroRepository.save(livro), LivroResponseDto.class);
    }

    @Override
    public void removerLivro(String isbn) {
        livroRepository.deleteById(isbn);
    }

    @Override
    public List<LivroResponseDto> buscaPorTitulo(String titulo) {
        List<Livro> livros = livroRepository.findByTituloLivroContainingIgnoreCase(titulo);
        List<LivroResponseDto> livrosDto = new ArrayList<>();
        for(Livro livro: livros){
            livrosDto.add(modelMapper.map(livro, LivroResponseDto.class));
        }
        return livrosDto;
    }
    
    @Override
    public List<LivroResponseDto> buscaPorIsbn(String isbn) {
        List<Livro> livros = livroRepository.findByIsbnContainingIgnoreCase(isbn);
        List<LivroResponseDto> livrosDto = new ArrayList<>();
        for(Livro livro: livros){
            livrosDto.add(modelMapper.map(livro, LivroResponseDto.class));
        }
        return livrosDto;
    }

    private Livro obterLivroExistente(String isbn) {
        return livroRepository.findById(isbn)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

}