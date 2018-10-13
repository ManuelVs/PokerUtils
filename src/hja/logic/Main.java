package hja.logic;


import java.io.IOException;

public class Main {
	
	public static void main(String... args) {
		int option = Integer.parseInt(args[0]);
		OptionMode mode = createMode(option);
		
		try {
			mode.start(args);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static OptionMode createMode(int option) {
		switch (option) {
			case 1:
				return new HandValueOption();
			case 2:
				return new HoldEmOption();
			case 3:
				return new HoldEmMultiplayerOption();
			case 4:
				return new OmahaOption();
			default:
				return new GUIOption();
		}
	}
}
