import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import java.util.List;
import java.util.Map;
import java.util.jar.Attributes.Name;


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

        //exibir e manipular os dados 
        for (int i = 0; i <10; i++) {
            Map<String,String> filme = listaDeFilmes.get(i);
            System.out.println("\n");

            System.out.println( "\u001b[1m Título:\u001b[m"+  "\u001b[46m \u001b[120m "+filme.get("fullTitle")+"\u001b[m");
            System.out.println("\u001b[1m IMG:\u001b[m " + filme.get("image"));
            System.out.println("\u001b[1m Nota:\u001b[m " + filme.get("imDbRating"));

            

            double nota = Double.parseDouble(filme.get("imDbRating"));
            int star = (int)nota;
            for (int n= 1; n <= star; n++) {
                System.out.print("🌟");
            }
            System.out.println("\n");
            
        }

    }
}
