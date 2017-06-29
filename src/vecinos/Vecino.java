package vecinos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Vecino {
	private int cantNodos;
	private int cantAristas;
	private int vecinoX;
	private int vecinoY;
	private int[][] matAdy;
	private int[] vecX;
	private int[] vecY;
	private static final int COSTOMAXIMO = 100;
	private int cantidadX = 0;
	private int cantidadY = 0;
	public Vecino(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		cantNodos = sc.nextInt();
		cantAristas = sc.nextInt();
		vecinoX = sc.nextInt();
		vecinoY = sc.nextInt();
		matAdy = new int[cantNodos][cantNodos];
		vecX = new int[cantNodos];
		vecY = new int[cantNodos];
		int origen;
		int fin;
		int costo;
		for (int i = 0; i < matAdy.length; i++) {
			for (int j = 0; j < matAdy.length; j++) {
				matAdy[i][j] = 999;
			}
		}
		for (int i = 0; i < cantAristas; i++) {
			origen = sc.nextInt() -1;
			fin = sc.nextInt() -1;
			costo = sc.nextInt();
			matAdy[origen][fin] = costo;
			matAdy[fin][origen] = costo;
		}
		sc.close();
	}
	
	private void solveDijkstra(int nodoInicio, int[] vecS) {
		ArrayList<Integer> conjS = new ArrayList<Integer>();
		ArrayList<Integer> conjNS = new ArrayList<Integer>();
		
		
		for (int i = 0; i < vecS.length; i++) {
			vecS[i] = matAdy[nodoInicio-1][i];
			conjNS.add(i);
		}
		int w;
		vecS[nodoInicio-1] = -1;
		conjS.add(nodoInicio-1);
		conjNS.remove((Object) (nodoInicio-1));
		while (!conjNS.isEmpty()) {
			w = findLower(vecS, conjNS);
			conjS.add(w);
			conjNS.remove((Object) w);
			if(buscarAdyacencia(w,conjNS)) {
				for (int i = 0; i < vecS.length; i++) {
					if(matAdy[w][i] != 999 && vecS[i] > vecS[w] + matAdy[w][i]){
						vecS[i] = vecS[w] + matAdy[w][i];
					}
				}
			}
		}
		
	}

	private boolean buscarAdyacencia(int w, ArrayList<Integer> conjNS) {
		for (int i = 0; i < matAdy.length; i++) {
			if(conjNS.contains(i) && matAdy[w][i] != 999) {
				return true;
			}
		}
		return false;
	}

	private int findLower(int[] costo, ArrayList<Integer> conjNS) {
		int menor = Integer.MAX_VALUE;
		int retorno = -1;
		for (int i = 0; i < costo.length; i++) {
			if(conjNS.contains(i) && menor > costo[i]) {
				retorno = i;
				menor = costo[i];
			}
		}
		return retorno;
	}
	
	public void resolver() {

		solveDijkstra(vecinoX, vecX);
		solveDijkstra(vecinoY, vecY);
		System.out.println("VECINO X");
		for (int i = 0; i < matAdy.length; i++) {
			System.out.print(vecX[i] + " ");
		}
		System.out.println();
		System.out.println("VECINO Y");
		for (int i = 0; i < matAdy.length; i++) {
			System.out.print(vecY[i] + " ");
		}
		System.out.println("\n------------------");
		for (int i = 0; i < matAdy.length; i++) {
			if(vecX[i] != -1 && vecY[i] != -1 && vecX[i] != vecY[i] ) {
				if(vecX[i] > vecY[i]) {
					cantidadX++;
				} else {
					cantidadY++;
				}
			}
		}
		
		System.out.println("Cantidad X: " + cantidadX);
		System.out.println("Cantidad Y: " + cantidadY);
	}
	
	
}
