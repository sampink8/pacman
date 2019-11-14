package pacman;
import java.util.*;
import java.util.Random;
public class Pacman{
	int m,n,x,y,filas,columnas,a,b,previousx,previousy;
	int i = 0;
	int j = 0;
	String matriz[][];
	String opc,nuevo;
	String anterior[][];
	Random rd = new Random();
	Scanner sc = new Scanner(System.in);

	public Pacman(){
		this.n = n;
		this.m = m;
	}

	public int preguntar()throws Exception{
		int numero;
		while(true){
			sc = new Scanner(System.in);
			System.out.println("Ingrese el tamanio n de la matriz: ");
			try{
				numero = sc.nextInt();
				if (numero<6 || numero>20){
					try{
						throw new Exception("No es valido ese tamanio");
					}catch(Exception e){
						System.out.println(e.getMessage());
						numero=preguntar();
					}
				}
				return numero;
			}catch(Exception expc){
				System.out.println("No ingresaste un numero entero");
				sc = new Scanner(System.in);
			}
		}
	}

	public void crearMatriz()throws Exception{
		n=preguntar();
		m=preguntar();
		matriz=new String[n][m];
		matriz();
	}

	public void matriz()throws Exception{
		try{
			for( i = 0; i < matriz.length; i++){
				for( j = 0 ; j < matriz[i].length; j++){
					matriz[i][j]="." + "    ";
					paredes();
					pacman();
					puntos();
					//fantasmas();
				}
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage()+"------");
			preguntar();
		}
	}

	public void imprimirMatriz(){
		for( i = 0; i < matriz.length; i++){
			for( j = 0 ; j < matriz[i].length; j++){
				System.out.print(matriz[i][j]);
			}
			System.out.println();
		}
	}


	public void paredes(){
		matriz[1][2] = " ____";
		matriz[2][3] = "|    ";
		matriz[5][2] = "|    ";
		matriz[4][2] = "|    ";
		matriz[3][2] = "|    ";
		matriz[2][2] = "|    ";
		matriz[4][6] = " ____";
		matriz[4][5] = " ____";
	}

	public void pacman(){
		try{
			x=n/2;
			y=m/2;
			Math.ceil(x);
			Math.ceil(y);
			matriz[x][y]="O    ";
		}catch(Exception exe){
			System.out.println(exe.getMessage()+"#######");
		}
	}

	public void fantasmas(){
			a=rd.nextInt(matriz.length);
			b=rd.nextInt(matriz.length);
			matriz[a][b] = "+";
	}

	public void frutita(){
			a=rd.nextInt(matriz.length);
			b=rd.nextInt(matriz.length);
			matriz[a][b] = "U";
	}

	public void puntos(){
   if(matriz[i][j] == matriz[x][y]){
		 matriz[i][j] = "  ";
	 }
	}

	public void mover()throws Exception{
		while(true){
			sc = new Scanner(System.in);
			System.out.println("Hacia donde quiere mover a pacman?");
			System.out.println("T: Arriba");
			System.out.println("D: Abajo");
			System.out.println("R: Derecha");
			System.out.println("L: Izquierda");
			opc = sc.nextLine();
			anterior = new String[a][b];

		
          try{
			previousx=x;
			previousy=y;
			anterior=matriz;
			matriz[previousx][previousy]=" ";
		  } catch(Exception px){
			System.out.println(px.getMessage());
		  }


			try{
		   switch(opc){
				 case "T":
         //matriz[x][y] = matriz[x-1][y];
			 	 matriz[x-1][y] = "O    ";
				 matriz[x][y] = matriz[x-1][y];
				 imprimirMatriz();
				 break;

				 case "D":
				 //matriz[x][y] = matriz[x+1][y];
			 	 matriz[x+1][y] = "O    ";
				 matriz[x][y] = matriz[x+1][y];
				 imprimirMatriz();
				 break;

				 case "R":
				 //matriz[x][y] = matriz[x][y+1];
			 	 matriz[x][y+1] = "O    ";
				 matriz[x][y] = matriz[x][y+1];
				 imprimirMatriz();
				 break;

				 case "L":
				 //matriz[x][y] = matriz[x][y-1];
			 	 matriz[x][y-1] = "O    ";
				 matriz[x][y] = matriz[x][y-1];
				 imprimirMatriz();
				 break;

				 default:
				 throw new Exception("Ese comando no es posible");
			 }
			} catch(Exception expc){
				System.out.println(expc.getMessage());
				sc = new Scanner(System.in);
			}
		}
	}

}
