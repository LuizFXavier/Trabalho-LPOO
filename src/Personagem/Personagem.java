package Personagem;
import Arma.Arma;

public abstract class Personagem {
    private String nomeTipo;
    private double saude;
    private double forca;
    private double destreza;
    private Arma arma;

    public Personagem(String nome, double saude, double forca, double destreza, Arma arma){
        this.nomeTipo = nome;
        this.saude = saude;
        this.destreza = destreza;
        this.forca = forca;
        this.arma = arma;
    }

    /**
     * Método que imprime os atributos do personagem, assim como seu tipo, arma equipada e vida atual.
     */
    public void printStatus(){
        
        if(estaMorto()){
            System.out.printf("%s [Morreu, Forca: %.1f, Destreza: %.1f, %s]%n", nomeTipo, forca, destreza, arma.getNome());
        }
        else{
            System.out.printf("%s [Saude: %.1f, Forca: %.1f, Destreza: %.1f, %s]%n", nomeTipo, saude, forca, destreza, arma.getNome());
        }
    }

    /**
     * Método que computa um ataque desferido contra um outro personagens,
     * determinando sua ocorrência, efetividade e efeito.
     * @param b Personagem alvo do ataque.
     */
    public void ataque(Personagem b){
        
        //Se A estiver morto, o ataque não ocorre
        if(this.estaMorto()){
            System.out.printf("O %s não consegue atacar, pois está morto.%n", nomeTipo);
            return;
        }
        //Se B estiver morto, o ataque não ocorre
        if(b.estaMorto()){
            System.out.printf("Pare! O %s já está morto.%n", b.nomeTipo);
            return;
        }

        //O ataque acontecerá:
        System.out.printf("O %s ataca o %s com %s.%n", nomeTipo, b.nomeTipo, arma.getNome());

        //O resultado do ataque dependerá da destreza dos personagens
        if(this.destreza > b.destreza){

            double dano = calculaDano();
            b.recebeDano(dano);

            System.out.printf("O ataque foi efetivo com %.1f pontos de dano!\n", dano);
        }
        else if(this.destreza < b.destreza){
            
            double dano = b.calculaDano();
            this.recebeDano(dano);

            System.out.printf("O ataque foi inefetivo e revidado com %.1f pontos de dano!\n", dano);

        }
        else{
            System.out.println("O ataque foi defendido, ninguem se machucou!");
        }
    }
    

    /**
     * Método que calcula o dano do golpe que será desferido com base,
     * na arma equipada e na força do personagem
     * @return dano que foi calculado.
     */
    private double calculaDano(){

        return arma.getModificadorDano() * forca;
    }
    
    /**
     * Método que verifica se a saúde atual do personagem é menor que 1 e
     * considera-o morto caso sim.
     * @return Estado atual do personagem, sendo 0 para vivo e 1 para morto.
     */
    private boolean estaMorto(){
        return saude < 1;
    }
    
    /**
     * Método que aplica dano no personagem, diminuindo sua saúde.
     * @param pontosDano Dano que será aplicado no personagem.
     */
    private void recebeDano(double pontosDano){
        saude -= pontosDano;
    }
}
