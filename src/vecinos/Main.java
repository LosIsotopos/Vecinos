package vecinos;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		Vecino v1 = new Vecino("vecinos.in");
		v1.resolver();
	}

}
