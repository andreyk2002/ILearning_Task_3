package by.itransition.ilearning.package by.itransition.ilearning.task_3;

import java.util.*;

import java.security.*;

public class Game {

	public static boolean checkArguments(String[]argc) {
		if (argc.length < 3) {
			System.out.println("usage : java -jar Game.jar arguments");
			System.out.println("count of arguments should an odd number >= 2");
			return false;
		}
		if (argc.length % 2 == 0) {
			System.out.println("count of arguments should be an odd number");
			return false;
		}
		if (findEquals(argc)) {
			System.out.println("All paramenters should be different!");
			return false;
		}
		return true;
	}
	
	public static void main(String[] argc) throws NoSuchAlgorithmException {
		if(!checkArguments(argc)) {
			return;
		}
		int choice;
		int computerMove = new Random().nextInt(argc.length - 1) + 1;
		Encryptor e = new Encryptor();
		try (Scanner s = new Scanner(System.in)) {
			String hmac = e.bytesToHex(e.getHmac(argc[computerMove - 1]));
			System.out.println("HMAC :\n" + hmac);
			showMenu(argc);
			choice = readInt(s, "Enter your move : ", argc.length);
			if (choice == 0) {
				System.out.println("Program ended by user");
				return;
			}
			if (win(choice, computerMove, argc.length))
				System.out.println("You win!");
			else if (computerMove == choice)
				System.out.println("It's a draw!");
			else
				System.out.println("Computer win :(");
			System.out.println("Computer turn : " + argc[computerMove - 1]);
			String key = e.bytesToHex(e.getKey().getEncoded());
			System.out.println("Key : " + key);
		} catch (InvalidKeyException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
	}

	public static void showMenu(String[] moves) {
		System.out.println("Available moves:");
		for (int i = 0; i < moves.length; i++) {
			System.out.println((i + 1) + " - " + moves[i]);
		}
		System.out.println("0 - exit");
	}

	public static boolean findEquals(String[] moves) {
		for (int i = 0; i < moves.length; i++) {
			for (int j = i + 1; j < moves.length; j++) {
				if (moves[i].equals(moves[j])) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean win(int player, int computer, int count) {
		return ((player > computer && player - computer <= (count - 1) / 2)
				|| player < computer && computer - player > (count - 1) / 2);
	}

	public static int readInt(Scanner s, String message, int max) {
		int choice = -1;
		while (choice > max || choice < 0) {
			System.out.print(message);
			if (s.hasNextInt()) {
				choice = s.nextInt();
			} else {
				s.next();
			}
		}
		return choice;
	}
}
