package pacman;
public class PrincipalPacman{
  public static void main(String[] args){
    Pacman p = new Pacman();
    try{
      p.crearMatriz();
      p.imprimirMatriz();
      p.mover();
    } catch(Exception e){
      System.out.println(e.getMessage());
    }
  }
}
