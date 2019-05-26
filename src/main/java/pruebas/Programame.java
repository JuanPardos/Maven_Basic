package pruebas;

import java.util.ArrayList;
import java.util.List;

public class Programame {
	String vocales[] = { "A", "a", "E", "e", "I", "i", "O", "o", "U", "u" };
	List<String> salida;

	//Máquina de cálculo.
	public List<String> problemaA(List<String> entrada) {
		ArrayList salida = null;
		salida = new ArrayList<>();
		if(entrada.size() < 1){   //Si la entrada es nula -> Error
			salida.add("ERROR !");
			return salida;
		}
		int nOperaciones = Integer.parseInt(entrada.get(0));  //Integer.parseInt para parsear un int a un String.
		for (int i = 1; i <= nOperaciones; i++) {
			String num[];
			num = new String[3];
			int x = entrada.get(i).indexOf(" ", 0);
			int y = 0;
			int index = 0;
			while (y != -1) { //Recorre la entrada
				num[index] = (entrada.get(i).substring(y, y));
				y++;
				y = entrada.get(i).indexOf(" ", y);
				index++;
			}
			num[index] = entrada.get(i).substring(y, entrada.get(i).length());
			
			//Operaciones
			if (num[1].equals("+")) //Suma
				salida.add("" + (Integer.parseInt(num[0]) + Integer.parseInt(num[2])));
			if (num[1].equals("-")) //Resta
				salida.add("" + (Integer.parseInt(num[0]) - Integer.parseInt(num[2])));
			if (num[1].equals("*")) //Multiplica
				salida.add("" + (Integer.parseInt(num[0]) * Integer.parseInt(num[2])));
			if (num[1].equals("/")) { //División
				if (num[0].equals("0") || num[2].equals("0")) //Si el numero que se divide o por el que se divide es 0 devuelve ERROR.
					salida.add("ERROR!");
				else
					salida.add("" + (Integer.parseInt(num[0]) / Integer.parseInt(num[2])));
			}
		}
		return salida; 
	}

	//Palabras ordenadas.
	public List<String> problemaB(List<String> entrada) {
		ArrayList salida = null;
		salida = new ArrayList<>();
		if (entrada.size() < 1) {
			salida.add("ERROR !");
			return salida;
		}
		int nOperaciones = Integer.parseInt(entrada.get(0));
		for (int i = 1; i <= nOperaciones; i++) {
			List<String> palabra = new ArrayList<>();
			int x = 0;
			int y = 1;
			for (int j = 0; j < entrada.get(i).length(); j++) {
				if (!esVocal(entrada.get(i).substring(x, y))) //Si la letra no es vocal...
					palabra.add((entrada.get(i).substring(x, y).toLowerCase()));
				x++;
				y++;
			}
			
			String letra = palabra.get(0);
			Boolean ordenada = true;
			if (palabra.size() > 1)
				for (int j = 1; j < palabra.size(); j++) {
					if (letra.compareTo(palabra.get(j)) > 0) {  //Compara el codigo ASCII para saber si la letra esta ordenada.
						ordenada = false;
					}
					letra = palabra.get(j);
				}
			if (ordenada) //No debe estar ordenada, muestra un error.
				salida.add("ERROR !");
			else
				salida.add("La palabra cumple los requisitos");
		}
		return salida;
	}
	
	public boolean esVocal(String letra) { //Devuelve True si la letra es una vocal, false si no lo es.
		for (int i = 0; i < vocales.length; i++) {
			if (letra.equals(vocales[i]))
				return true;
		}
		return false;
	}

	//Numeros primos.
	public List<String> problemaC(List<String> entrada) {
		ArrayList salida = null;
		salida = new ArrayList<>();
		if (entrada.size() < 1) {
			salida.add("ERROR !");
			return salida;
		}

		int nOperaciones = Integer.parseInt(entrada.get(0));
		for (int i = 1; i <= nOperaciones; i++) {
			List<String> contadorPrimos = new ArrayList<>();
			int num = Integer.parseInt(entrada.get(i));
			for (int x = num; x > 9; x--) {
				Boolean primo = true;
				for (int y = 2; y < x; y++)
					if (x % y == 0) { //Comprueba si es primo. Si no lo es hace un break.
						primo = false;
						break;
					}
				if (primo) //Si es primo suma al contador.
					contadorPrimos.add("" + x);
			}
			int cont = 0;
			for (int j = 0; j < contadorPrimos.size(); j++) {
				if (contadorPrimos.get(j).substring(0, 1).equals("1")) //Comprueba si la unica division posible es por 1 (es primo).
					cont++;
			}
			salida.add("" + cont);
		}
		return salida;
	}

	//Path check videojuego. Comprueba si la ruta entre dos habitaciones es posible.
	public List<String> problemaD(List<String> entrada) {
		ArrayList salida = null;
		return salida;
		
		//TODO
		//TODO
	}

	//Ordena naves segun peso y en caso de empate por nivel de oxígeno.
	public List<String> problemaE(List<String> entrada) {
		ArrayList salida = null;
		salida = new ArrayList<>();
		if (entrada.size() < 1) {
			salida.add("ERROR");
			return salida;
		}

		int nOperaciones = Integer.parseInt(entrada.get(0));
		for (int i = 1, i2 = 1; i <= nOperaciones; i++) {
			salida.add("Nave " + i + ":");
			int oxigeno = Integer.parseInt(entrada.get(i2));
			int nNaves = Integer.parseInt(entrada.get(i2 + 1));
			int valores[][] = new int[nNaves][2];
			for (int j = 0; j < nNaves; j++) {
				valores[j][0] = Integer.parseInt(entrada.get(j + i2 + 2).substring(0, entrada.get(j + i2 + 2).indexOf(" ")));
				valores[j][1] = Integer.parseInt(entrada.get(j + i2 + 2).substring(entrada.get(j + i2 + 2).indexOf(" ") + 1, entrada.get(j + i2 + 2).length()));
			}

			for (int x = 0; x < (valores.length - 1); x++) {  //Comparación peso.
				for (int j = x + 1; j < valores.length; j++) {
					if (valores[x][1] > valores[j][1]) { //Si el peso es mayor...
						//Intercambio de variables, guarda en aux para no sobreescribirla.
						int aux1 = valores[x][1];
						int aux2 = valores[x][0];
						valores[x][1] = valores[j][1];
						valores[x][0] = valores[j][0];
						valores[j][1] = aux1;
						valores[j][0] = aux2;
					}
				}
			}
			for (int x = valores.length - 1; x > 0; x--) {   //Comparación oxígeno.
				for (int j = x - 1; j > -1; j--) {
					if (valores[x][0] >= oxigeno && valores[x][0] > valores[j][0]) { //Si el peso es igual y el oxígeno mayor...
						int aux1 = valores[x][1];
						int aux2 = valores[x][0];
						valores[x][1] = valores[j][1];
						valores[x][0] = valores[j][0];
						valores[j][1] = aux1;
						valores[j][0] = aux2;
					}
				}
			}

			for (int j = 0; j < valores.length; j++) //Salida
				salida.add(valores[j][0] + " " + valores[j][1]);
			i2 += nNaves + 2;
		}
		return salida;
	}
}