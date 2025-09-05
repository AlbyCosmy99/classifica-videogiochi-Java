import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.text.Position;

public class ClassificaVideogiochi{
    public static void main(String[] args){
        String simboloSeparatoreTraNomeEConsole = "&";
        int top = 50;

        Queue videogiochi = new Queue();

        String file1 = "giochi.txt";
        String file2 = "vincitori.txt";

        Scanner scanner = new Scanner(System.in);

        try{
            Scanner file = new Scanner(new FileReader(file1));
            while(file.hasNextLine()){
                Scanner line = new Scanner(file.nextLine());
                String name = "";
                String word = "";
                do{
                    if(line.hasNext()){
                        word = line.next();
                    }
                    if(!word.equals(simboloSeparatoreTraNomeEConsole)){
                        name = name + word + " ";
                    }
                }
                while(line.hasNext() && !word.equals(simboloSeparatoreTraNomeEConsole));

                name = name.trim();
                while(line.hasNext()){
                    String console = line.next();
                    videogiochi.enqueue(new Videogioco(name, console));
                }
               
            }
        }
        catch(FileNotFoundException e){
            System.out.println("file " + file1 + " non trovato. Termino.");
            System.exit(1);
        }

        int posizione = 0;
        Videogioco[] vincitoriFinora = new Videogioco[top];
        while(vincitoriFinora[top-1]==null){
            if(posizione!=0){
                System.out.println("POSIZIONE NUMERO " + posizione + " IDENTIFICATA IN CLASSIFICA!");
            }
            posizione++;
            int size = 0;

            try{
                Scanner file = new Scanner(new FileReader(file2));
                boolean posizioneTolta = false;
             while(file.hasNextLine()){
               
                    Scanner line = new Scanner(file.nextLine());
                    if(!posizioneTolta){
                        line.next();
                        line.next();
                        posizioneTolta = true;
                    }
                    String name = "";
                    String word = "";
                    do{   
                        if(line.hasNext()){
                            word = line.next();
                        }
                        if(!word.trim().equals(simboloSeparatoreTraNomeEConsole)){
                            name = name + word + " ";
                     }
                    }
                    while(line.hasNext() && !word.trim().equals(simboloSeparatoreTraNomeEConsole));

                    posizioneTolta = false;
                    name = name.trim();
                    while(line.hasNext()){
                        String console = line.next();
                        vincitoriFinora[size++] = new Videogioco(name, console); 
                    }

                }
            }
            catch(FileNotFoundException e){
                System.out.println("file " + file2 + " non trovato. Termino.");
                System.exit(1);
            }
            videogiochi.removeFromQueueElementsInArray(vincitoriFinora);
            //videogiochi.mixVideogiochi();
            
            Queue q = videogiochi.copy();

            if(q.size==0){
                System.out.println("Non ci sono videogiochi da confrontare. Termino.");
                System.exit(1);
            }
            if(q.size==1){
                Videogioco ultimoVideogioco = q.dequeue();
                System.out.println("Un solo gioco rimasto nella lista per essere confrontato, ossia: " + ultimoVideogioco);
                try{
                    FileWriter fw = new FileWriter(file2,true);
                    if(ultimoVideogioco!=null){
                        fw.append("VIDEOGIOCO ULTIMO IN CLASSIFICA: " + ultimoVideogioco.getNome() + " & " + ultimoVideogioco.getConsole() + "\n");
                    }
                    fw.close();
                }
                catch(IOException e){
                    System.out.println("Problema con scrittura sul file: " + file2 + ". Termino.");
                    System.exit(1);
                }
                System.exit(0);
            }

            boolean ok = false;

            Videogioco videogiocoScelto = null;
        
            while(q.getSize() != 1){
                System.out.println(q);
                ok = false;
            
                do{
                    Videogioco videogioco1 = q.dequeue();
                    Videogioco videogioco2 = q.dequeue();
                    while(videogioco1==null){
                        videogioco1 = q.dequeue();
                    }
                    while(videogioco2==null){
                        videogioco2 = q.dequeue();
                    }
                    
                    int scelta = 0;
                    if(videogioco1.containsVideogiocoMigliore(videogioco2)){
                        scelta = 2;
                    }
                    else if(videogioco2.containsVideogiocoMigliore(videogioco1)){
                        scelta = 1;
                    }
                    else{
                        do{
                            try{
                                System.out.println(videogioco1.getNome() + " [" + videogioco1.getConsole() + "] (1) o " + videogioco2.getNome() + " [" + videogioco2.getConsole() + "] (2) ?");
                                Scanner scanner1 = new Scanner(System.in);
                                scelta = scanner1.nextInt();
                            }catch(Exception e){
                                scelta = -1;
                            }finally{
                            }
                        }while(!SceltaAccettabile.sceltaAccettabile(scelta));
                    }
                    
                    if(scelta==1){
                        q.enqueue(videogioco1);
                        videogiocoScelto = videogioco1;
                        ok = true;
                        videogioco2.insertVideogiocoMiglioreDiLui(videogioco1);
                        System.out.println("SCELTA: ["+videogioco1+"]");

                    }
                    else if(scelta==2){
                        q.enqueue(videogioco2);
                        videogiocoScelto = videogioco2;
                        ok = true;
                        videogioco1.insertVideogiocoMiglioreDiLui(videogioco2);
                        System.out.println("SCELTA: ["+videogioco2+"]");
                }   
                }while(!ok);
            }

            try{
                FileWriter fw = new FileWriter(file2,true);
                if(videogiocoScelto!=null){
                    fw.append(posizione + " - " + videogiocoScelto.getNome() + " & " + videogiocoScelto.getConsole() + "\n");
                }
                fw.close();
            }
            catch(IOException e){
                System.out.println("Problema con scrittura sul file: " + file2 + ". Termino.");
                System.exit(1);
            }
        }  
    }
}