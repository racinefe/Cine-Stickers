import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.util.List;
import java.util.Map;


public class App {
      
    public static void main(String[] args) throws Exception {
        
        //fazer umaaa conexão HTTP e buscar os top 10 filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        
        //pegar só os dados que interessam (titulo, poster e classificação)
        var parser = new jsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //exibir e manupular os dados 
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println( filme.get("fullTitle"));
           // System.out.println(" "+filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }

    }
}
