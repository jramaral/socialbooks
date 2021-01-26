package com.socialbooks.jra.client;

import com.socialbooks.jra.Livro;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class LivrosCliente {

    private RestTemplate restTemplate;
    private String Uri_Base;
    private String Urn_Base = "/livros";
    private String credencial;

    public LivrosCliente(String url, String usuario, String senha) {
        restTemplate = new RestTemplate();

        Uri_Base = url.concat(Urn_Base);
        String credencialAux = usuario + ":" + senha;
        credencial = "Basic " + Base64.getEncoder().encodeToString(credencialAux.getBytes());

    }

    public List<Livro> listar(){
        RequestEntity<Void> request = RequestEntity.get(URI.create(Uri_Base))
                .header("Authorization", credencial).build();

            ResponseEntity<Livro[]> response = restTemplate.exchange(request, Livro[].class);

            return Arrays.asList(response.getBody());

    }

    public String salvar(Livro livro){
        RequestEntity<Livro> request = RequestEntity.post(URI.create(Uri_Base))
                .header("Authorization", credencial).body(livro);

        ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
        return  response.getHeaders().getLocation().toString();
    }

    public Livro buscar(String uri){
       RequestEntity<Void> request = RequestEntity.get(URI.create(uri))
                .header("Authorization", "Basic am9zZTpTZW5oYUAyMDE5")
                .build();

        ResponseEntity<Livro> response = restTemplate.exchange(request, Livro.class);
        return  response.getBody();
    }
}
