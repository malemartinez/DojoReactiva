import Clases.Correo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application {

     public static final Logger log = LoggerFactory.getLogger(Application.class);
        public List<Correo> correos;
    public Application() {
        this.correos = new ArrayList<>();
//        List<Correo> correos = new ArrayList<>();
        correos.add(new Correo("maleja" , "hotmail.com" , "Aleja", false , "Trabajo de Reactividad"));
        correos.add(new Correo("carlos" , "outlook.com" , "carlos", false , "Trabajo de Reactividad"));
        correos.add(new Correo("carlos" , "outlook.com" , "carlos", false , "Trabajo de Reactividad"));
        correos.add(new Correo("soyLina" , "hotmail.com" , "Lina", true , "Trabajo de Reactividad"));
        correos.add(new Correo("herokirim" , "hotmail.com" , "Luis", false , "Trabajo de Reactividad"));
        correos.add(new Correo("kira" , "gmail.com" , "Mayra", false , "Trabajo de Reactividad")) ;
        correos.add(new Correo("Eduard003" , "outlook.com" , "Eduardo", false , "Trabajo de Reactivid;"));
        correos.add(new Correo("titotao" , "hotmail.com" , "Tito", true , "Trabajo de Reactividad"));
        correos.add(new Correo("silverash" , "outlook.com" , "", true , "Trabajo de Reactividad"))  ;
        correos.add(new Correo("maamartinezsa" , "yahoo.com" , "Aleja", false , "Trabajo de Reactivid;")) ;
        correos.add(new Correo("platinum" , "outlook.com" , "", false , "Trabajo de Reactividad"));
        correos.add(new Correo("limarsa" , "@gmail.com" , "linilla", true , "Trabajo de Reactividad"));


    }

    public void distint(){
       Flux.fromIterable(correos).distinct()
                .subscribe( c -> log.info(c.toString()) );
    }


    public void filter(){
        System.out.println("fILTRADO DE CORREO HOTMAIL CON CANTIDAD");
        Flux<Correo> fx = Flux.fromIterable(correos);
               Flux<Correo> lista =  fx
                .filter( correo -> correo.getDomino().contains("hotmail.com")  );
               lista.subscribe( c -> log.info(c.toString()) );

        System.out.println(lista.count().subscribe(
                c -> log.info("cantidad: " + c)
        ));

        System.out.println("fILTRADO DE CORREO GMAIL CON CANTIDAD");

        Flux<Correo> lista1 = fx
                .filter( correo -> correo.getDomino().contains("gmail.com")  );
                lista1
                .subscribe( c -> log.info(c.toString()) );

        System.out.println(lista1.count().subscribe(
                c -> log.info("cantidad: " + c)
        ));

        System.out.println("fILTRADO DE CORREO OUTLOOK CON CANTIDAD");
        Flux<Correo> lista2 = fx
                .filter( correo -> correo.getDomino().contains("outlook.com")  );
                lista2
                .subscribe( c -> log.info(c.toString()) );

        System.out.println(lista2.count().subscribe(
                c -> log.info("cantidad: " + c)
        ));
    }

    public void map(){

        //Check if the email is valid
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Pattern dominio = Pattern.compile("");
        Flux.fromIterable(correos)
                .map( correo -> {
                   correo.setCorreoCompleto(correo.getUsername().concat("@" + correo.getDomino()));

                           return correo;
                } )
                .map(
                        correo -> {
                          Matcher mat = pattern.matcher(correo.getCorreoCompleto());
                           if(mat.matches() && !correo.getDomino().contains("yahoo")){
                               System.out.println("Correo Válido");
                           } else {
                               System.out.println("Correo Inválido");
                           }
                             return correo;
                        }
                )
                .subscribe( correo -> log.info(correo.toString()) );


    }

    public void cantidadCorreos(){
        Flux.fromIterable(correos)
                .count()
                .subscribe( correo -> log.info(correo.toString()) );
    }

    public void enviados(){
        Flux.fromIterable(correos)
                .flatMap(correo -> {
                    if(correo.isEnviado() == false){
                        correo.setEnviado(true);
                    }
                    return Flux.just(correo);
                } )
                .subscribe( correo -> log.info(correo.toString()) );
    }

    public static void main(String[] args) {
        Application application = new Application();
//        application.distint();
//        application.filter();
//        application.map();
//        application.cantidadCorreos();
        application.enviados();
    }



}
