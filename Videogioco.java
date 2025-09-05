import java.util.ArrayList;

public class Videogioco{
    private String nome;
    private String console;
    private ArrayList<Videogioco> videogiochiMiglioriDiLui;
    private int size;

    public Videogioco(String nome,String console){
        this.nome = nome;
        this.console = console;
        videogiochiMiglioriDiLui = new ArrayList<>();
        size = 0;
    }

    public String getNome(){
        return this.nome;
    }

    public String getConsole(){
        return this.console;
    }

    public void insertVideogiocoMiglioreDiLui(Videogioco videogioco){
        if(!videogiochiMiglioriDiLui.contains(videogioco)){
            videogiochiMiglioriDiLui.add(videogioco);
        }
        
    }

    public boolean containsVideogiocoMigliore(Videogioco videogioco){
        return videogiochiMiglioriDiLui.contains(videogioco);
    }

    public ArrayList<Videogioco> getVideogiochiMigliori(){
        return videogiochiMiglioriDiLui;
    }

    public boolean equals(Videogioco v){
        if(!this.nome.equals(v.getNome())){
            return false;
        }
        if(!this.console.equals(v.getConsole())){
            return false;
        }
        return true;
    }

    public String toString(){
        return getNome() + " " + getConsole();
    }
}