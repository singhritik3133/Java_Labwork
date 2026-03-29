package Lab_1_2_3;

import java.util.*;

public class SLBoardTest {
	public static void main(String[] args) {
		Map<Integer, Integer> snakes = new HashMap<>();
        snakes.put(16, 6);   snakes.put(47, 26);
        snakes.put(49, 11);  snakes.put(56, 53);
        snakes.put(62, 19);  snakes.put(64, 60);
        snakes.put(87, 24);  snakes.put(93, 73);
        snakes.put(98, 78);

        Map<Integer, Integer> ladders = new HashMap<>();
        ladders.put(2, 38);  ladders.put(7, 14);
        ladders.put(8, 31);  ladders.put(15, 26);
        ladders.put(21, 42); ladders.put(28, 84);
        ladders.put(36, 44); ladders.put(51, 67);
        ladders.put(71, 91); ladders.put(78, 98);

        SnakeAndLadderBoard board = new SnakeAndLadderBoard(101, ladders, snakes);

        System.out.println("Minimum rolls to win: " + board.findMinimumRolls());

 
        System.out.println("Snake positions:  " + board.getSnakePositions());
        System.out.println("Ladder positions: " + board.getLadderPositions());
        System.out.println("Dangerous snakes: " + board.countDangerousSquares());

        board.findBestLadder().ifPresent(sq ->
            System.out.println("Best ladder at square: " + sq)
        );

        board.getJumpDestination(16).ifPresent(dest ->
            System.out.println("Square 16 -> " + dest)
        );

 
        int dest = board.getJumpDestination(50).orElse(50);
        System.out.println("Square 50 destination: " + dest);


        try {
            Map<Integer, Integer> bad = new HashMap<>();
            bad.put(100, 50);
            new SnakeAndLadderBoard(101, new HashMap<>(), bad);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }


	
}
