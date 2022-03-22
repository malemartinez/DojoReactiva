import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Chat {

    public List<String> palabras = new ArrayList<>();
    public List<String> malasPalabras = new ArrayList<>();

    public static final Logger log = LoggerFactory.getLogger(Application.class);

    public void Recibir(){
        Scanner input = new Scanner(System.in);
       int contador = 0;
       do{
           System.out.println("Ingresa palabra:");
            String palabra = input.nextLine();
            this.palabras.add(palabra);
           contador++;
        } while(contador < 3);

    }

    public void malasPalabras(){
        this.malasPalabras.add("Hijueputa");
        this.malasPalabras.add("jueputa");
        this.malasPalabras.add("careChimba");
        this.malasPalabras.add("careVerga");
        this.malasPalabras.add("marica");
        this.malasPalabras.add("Chimba");
        this.malasPalabras.add("gochornea");
        this.malasPalabras.add("mierda");
        this.malasPalabras.add("comaMierda");
        this.malasPalabras.add("gonorzofia");

    }

    public void reemplazarPalabras(){
        /*Flux.fromIterable(palabras)
                .map(
                        (palabra) -> {
                            this.palabras.stream().anyMatch( malaPalabra -> this.malasPalabras.equals(malaPalabra));

                            return palabra;
                        }
                ).subscribe( c -> log.info(c.toString()) );*/

        Flux.fromIterable(palabras)
                .filter( palabra -> palabra.equals( () -> Flux.fromStream(malasPalabras.parallelStream() ).cache().filter(
                        malasPalabra -> System.out.println(malasPalabra.equals(palabra))
                ) ) );
    }



    public static void main(String[] args) {
        Chat chat = new Chat();
        chat.Recibir();
        chat.malasPalabras();
        chat.reemplazarPalabras();
    }




}
