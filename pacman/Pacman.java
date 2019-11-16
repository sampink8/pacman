package pacman;

import java.util.*;
import java.util.Random;

public class Pacman {
	int m, n, x, y, filas, columnas, a, b, previousx, previousy;
	int i = 0;
	int j = 0;
	String matriz[][];
	//List<String> posiciones = new ArrayList<String>();
	String opc, nuevo;
	Random rd = new Random();
	Scanner sc = new Scanner(System.in);

	public Pacman() {
		this.n = n;
		this.m = m;
	}

	public int preguntar() throws Exception {
		int numero;
		while (true) {
			sc = new Scanner(System.in);
			System.out.println("Ingrese el tamanio n de la matriz: ");
			try {
				numero = sc.nextInt();
				if (numero <= 5 || numero > 20) {
					try {
						throw new Exception("No es valido ese tamanio");
					} catch (final Exception e) {
						System.out.println(e.getMessage());
						numero = preguntar();
					}
				}
				return numero;
			} catch (final Exception expc) {
				System.out.println("No ingresaste un numero entero");
				sc = new Scanner(System.in);
			}
		}
	}

	public void crearMatriz() throws Exception {
		n = preguntar();
		m = preguntar();
		matriz = new String[n][m];
		matriz();
	}

	public void matriz() throws Exception {
		try {
			for (i = 0; i < matriz.length; i++) {
				for (j = 0; j < matriz[i].length; j++) {
					matriz[i][j] = "." + "    ";
					paredes();
					pacman();
				}
			}
			fantasmas();
			frutita();

		} catch (final Exception ex) {
			System.out.println(ex.getMessage() + "------");
			preguntar();
		}
	}

	public void imprimirMatriz() {
		for (i = 0; i < matriz.length; i++) {
			for (j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j]);
			}
			System.out.println();
		}
	}

	public void paredes() {
		matriz[1][2] = " ____";
		matriz[2][3] = "|    ";
		matriz[5][2] = "|    ";
		matriz[4][2] = "|    ";
		matriz[3][2] = "|    ";
		matriz[2][2] = "|    ";
		matriz[5][0] = " ____";
		matriz[5][1] = " ____";
		
		  /*posiciones.add(matriz[1][2]); 
		  posiciones.add(matriz[2][3]);
		  posiciones.add(matriz[5][2]); 
		  posiciones.add(matriz[4][2]); 
		  posiciones.add(matriz[3][2]);
		  posiciones.add(matriz[2][2]); 
		  posiciones.add(matriz[5][0]); 
		  posiciones.add(matriz[5][1]);*/
		 
	}

	public void pacman() {
		try {
			x = n / 2;
			y = m / 2;
			Math.ceil(x);
			Math.ceil(y);
			matriz[x][y] = "O    ";
		} catch (final Exception exe) {
			System.out.println(exe.getMessage() + "#######");
		}
	}

	public void fantasmas() {
		a = rd.nextInt(matriz.length);
		b = rd.nextInt(matriz.length);
		if (matriz[a][b] == "." + "    ") {
			matriz[a][b] = "U    ";
		}
	}

	public void frutita() {
		a = rd.nextInt(matriz.length);
		b = rd.nextInt(matriz.length);
		if (matriz[a][b] == "." + "    ") {
			matriz[a][b] = "+    ";
		}
	}

	public void puntos() {
		if (matriz[x][y] == "." + "    ") {
			a = 100;
			b = b + a;
			System.out.println("Total: " + b);
		}
	}

	public boolean recorrer(int p, int q) throws Exception {

		Boolean findYoshi = false;

		for (i = 0; i < matriz.length; i++) {
			for (j = 0; j < matriz[i].length; j++) {

				String ratakta = matriz[p][q];
				System.out.println(ratakta);
				if (ratakta.equals(" ____")) {
					findYoshi = true;
				}
				if (ratakta.equals("|    ")) {
					findYoshi = true;
				}
			}

		}
		System.out.println(findYoshi);

		return findYoshi;
	}

	public void mover() throws Exception {
		while (true) {
			sc = new Scanner(System.in);
			System.out.println("Hacia donde quiere mover a pacman?");
			System.out.println("T: Arriba");
			System.out.println("D: Abajo");
			System.out.println("R: Derecha");
			System.out.println("L: Izquierda");
			opc = sc.nextLine();

			// matriz[x][y] = "      ";
			imprimirMatriz();
			matriz[previousx][previousy] = "      ";

			try {
				switch (opc) {
				case "T":
					x = x - 1;
					y = y;

					final Boolean stopper = recorrer(x, y);

					if (stopper) {
						x = x + 1;
					}

					if (!stopper) {
						previousx = x;
						previousy = y;
						matriz[x][y] = "O     ";

						puntos();
						imprimirMatriz();
					}
					break;

				case "D":
					x = x + 1;
					y = y;

					final Boolean stopper2 = recorrer(x, y);

					if (stopper2) {
						x = x + 1;
					}
					if (!stopper2) {
						previousx = x;
						previousy = y;
						matriz[x][y] = "O     ";

						puntos();
						imprimirMatriz();
					}
					break;

				case "R":
					y = y + 1;
					x = x;

					final Boolean stopper3 = recorrer(x, y);

					if (stopper3) {
						x = x - 1;
					}
					
					if (!stopper3) {
						previousx = x;
						previousy = y;
						matriz[x][y] = "O     ";

						puntos();
						imprimirMatriz();
					}
					break;

				case "L":
					y = y - 1;
					x = x;

					final Boolean stopper4 = recorrer(x, y);

					if (stopper4) {
						x = x + 1;
					}
					
					if (!stopper4) {
						previousx = x;
						previousy = y;
						matriz[x][y] = "O     ";

						puntos();
						imprimirMatriz();
					}
					break;

				default:
					throw new Exception("Ese comando no es posible");
				}
			} catch (final Exception expc) {
				System.out.println(expc.getMessage());
				sc = new Scanner(System.in);
			}
		}
	}
}
