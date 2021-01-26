package com.socialbooks.jra;

import com.socialbooks.jra.client.LivrosCliente;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

public class App {



    public static void main(String[] args) {

        LivrosCliente cliente = new LivrosCliente("http://localhost:8080","jose", "Senha@2019");
        List<Livro> livros = cliente.listar();

        for (Livro livro: livros) {
            System.out.println("Livro " + livro.getNome());
        }

        Livro livro = new Livro();
        livro.setNome("Git passo a passo");
        livro.setEditora("Minha Editora");
        livro.setResumo("Este livro aborda tecnicas de versionamento");

        String localizacao = cliente.salvar(livro);

        System.out.println("URI do livro salvo: ".concat(localizacao));


        livro = cliente.buscar(localizacao);

        System.out.println("Nome livro encontrado: ".concat(livro.getNome()));

    }
}
