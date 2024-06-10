import Personagem.*;
import Arma.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Scanner scanner = new Scanner(System.in);
        int nPer = 2;

        Personagem[] personagens = new Personagem[nPer];

        //Leitura das características dos personagens para sua criação
        for(int i = 0; i < nPer; i++){
            int tipo, arma;
            double saude, forca, destreza;

            tipo = scanner.nextInt();
            saude = scanner.nextDouble();
            forca = scanner.nextDouble();
            destreza = scanner.nextDouble();
            arma = scanner.nextInt();

            personagens[i] = criaPersonagem(tipo, saude, forca, destreza, arma);
        }
        //Loop principal do jogo / combate
        while (true) {
            int atacante, atacado;

            // Exibição dos status dos personagens
            for(int i = 0; i < nPer; i++){
                personagens[i].printStatus();
            }

            atacante = scanner.nextInt();
            
            // Condições de encerramento do combate
            if(atacante == 0)
                break;

            atacado = scanner.nextInt();
            
            if(atacado == 0)
                break;

            // Verificação se o personagem informado existe
            if(atacante < 1 || atacado < 1 || atacante > nPer || atacado > nPer){
                System.out.println("Personagem não existente não pode atacar");
                continue;
            }
            atacante -= 1;
            atacado -= 1;

            personagens[atacante].ataque(personagens[atacado]);
            System.out.println();
        }
    }
    /**
     * Método que cria o personagem com sua respectiva arma correta,
     * implementado afim de obter melhor organização no momento das instanciações.
     * @param tipo classe do personagem.
     * @param saude pontos de vida bas do personagem.
     * @param forca força do personagem.
     * @param destreza destreza do personagem.
     * @param arma arma do personagem, que varia conforme a classe deste.
     * @return Personagem com base nos parâmetros passados.
     * @throws Exception Exceção lançada caso não exista o tipo de personagem ou a arma especificados.
     */
    public static Personagem criaPersonagem(int tipo, double saude, double forca, double destreza, int arma) throws Exception{
        Personagem personagem;
        Arma armaP;

        
        switch (tipo) {
            case 1: //Mago
                if(arma == 1) 
                    armaP = new Transmutacao();
                else if (arma == 2)
                    armaP = new Psikappa();
                else
                    throw new Exception("Não existe arma com este identificador!");
                
                personagem = new Mago(saude, forca, destreza, armaP);
                break;
        
            case 2://Paladino
                if(arma == 1) 
                    armaP = new Espada();
                else if (arma == 2)
                    armaP = new Lanca();
                else
                    throw new Exception("Não existe arma com este identificador!");
                
                personagem = new Paladino(saude, forca, destreza, armaP);
                break;
            
            case 3://Clérigo
                if(arma == 1) 
                    armaP = new Martelo();
                else if (arma == 2)
                    armaP = new Maca();
                else
                    throw new Exception("Não existe arma com este identificador!");
                
                personagem = new Clerigo(saude, forca, destreza, armaP);
                
                break;

            default:
                throw new Exception("Não existe personagem com este identificador!");
        }

        return personagem;
    }
}
