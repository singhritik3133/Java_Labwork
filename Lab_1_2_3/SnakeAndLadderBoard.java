package Lab_1_2_3;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.*;

public class SnakeAndLadderBoard extends Graph {
    Map<Integer,Integer> Ladder = new HashMap<>();
    Map<Integer,Integer> Snake = new HashMap<>();
    private static final int Board_Size = 100;

    SnakeAndLadderBoard(int numVertices, Map<Integer,Integer> Ladder, Map<Integer,Integer> Snake) {
        super(100);
        this.Ladder = Ladder;
        this.Snake = Snake;
        validateBoard(Ladder, Snake); 
    }

    private void validateBoard(Map<Integer,Integer> Ladder, Map<Integer,Integer> Snake) {
        Stream<Entry<Integer,Integer>> SnakeStream = Snake.entrySet().stream();   
        Stream<Entry<Integer,Integer>> LadderStream = Ladder.entrySet().stream(); 

        SnakeStream.allMatch(entry -> entry.getKey() > entry.getValue() && entry.getKey() != 100 && !Ladder.containsKey(entry.getKey()));
        LadderStream.allMatch(entry -> entry.getKey() < entry.getValue() && entry.getKey() != 1 && entry.getValue() <= 100 && entry.getKey() >= 1);

        Snake.forEach((start, end) -> {
            if (!isValidSquare(start) || !isValidSquare(end))
                throw new IllegalArgumentException("invalid snake position");
            if (start == Board_Size)
                throw new IllegalArgumentException("snake cannot start at position 100");
        });

        Ladder.forEach((start, end) -> {
            if (!isValidSquare(start) || !isValidSquare(end))
                throw new IllegalArgumentException("invalid ladders position");
            if (start == Board_Size)
                throw new IllegalArgumentException("ladder cannot start at position 100");
        });

        Snake.keySet().forEach(square -> {
            if (Ladder.containsKey(square))
                throw new IllegalArgumentException("both cannot have same start position");
        });
    }

    public boolean isValidSquare(int square) {
        return square >= 1 && square <= 100; 
    }

    public int getJumDestination(int square) {
        if (Ladder.containsKey(square)) {
            return Ladder.get(square); 
        } else if (Snake.containsKey(square)) {
            return Snake.get(square);  
        } else {
            return square;
        }
    }

    public List<Integer> getSnakePositions() {
        return Snake.keySet().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<Integer> getLadderPositions() {
        return Ladder.keySet().stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public long countDangerousSquares() {
        return Snake.entrySet().stream()
                .filter(entry -> (entry.getKey() - entry.getValue()) > 10)
                .count();
    }

    public Optional<Integer> findBestLadder() {
        return Ladder.entrySet().stream()
                .max(Comparator.comparingInt(entry -> entry.getValue() - entry.getKey()))
                .map(Map.Entry::getKey);
    }
    public Optional<Integer> getJumpDestination(int square) {
        if (Ladder.containsKey(square)) {
            return Optional.of(Ladder.get(square));
        } else if (Snake.containsKey(square)) {
            return Optional.of(Snake.get(square));
        }
        return Optional.empty();
    }
    public List<Integer> getPossibleMoves(int currentSquare) {
        return IntStream.rangeClosed(1, 6)  
                .map(dice -> currentSquare + dice)
                .filter(this::isValidSquare)
                .map(square -> getJumDestination(square))
                .boxed()
                .collect(Collectors.toList());
    }

    private Map<Integer, List<Integer>> buildGraph() { 
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            List<Integer> moves = new ArrayList<>();
            for (int dice = 1; dice <= 6; dice++) {
                int next = i + dice;
                if (next <= 100) {
                    Optional<Integer> jump = Optional.of(getJumDestination(next));
                    if (jump.isPresent()) {
                        next = jump.get();
                    }
                    moves.add(next);
                }
            }
            graph.put(i, moves);
        }
        return graph;
    }

    public int findMinimumRolls() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        int[] distance = new int[101];
        queue.offer(1);
        visited[1] = true;
        distance[1] = 0;

        while (queue.size() > 0) {
            int current = queue.remove();
            if (current == 100) {
                return distance[current];
            }
            List<Integer> moves = getPossibleMoves(current);
            for (int next : moves) {
                if (!visited[next]) {
                    visited[next] = true;
                    distance[next] = distance[current] + 1;
                    queue.add(next);
                }
            }
        }
        return -1;
    }
}