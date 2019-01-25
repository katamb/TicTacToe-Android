package com.example.tictactoe.strategy;

import com.example.tictactoe.player.Player;

import java.util.LinkedList;
import java.util.List;

import static com.example.tictactoe.MainActivity.player1;
import static com.example.tictactoe.MainActivity.player2;
import static com.example.tictactoe.strategy.MiniMaxStrategy.Location.LOWER_LEFT_CORNER;
import static com.example.tictactoe.strategy.MiniMaxStrategy.Location.LOWER_MIDDLE;
import static com.example.tictactoe.strategy.MiniMaxStrategy.Location.LOWER_RIGHT_CORNER;
import static com.example.tictactoe.strategy.MiniMaxStrategy.Location.MIDDLE_CENTER;
import static com.example.tictactoe.strategy.MiniMaxStrategy.Location.MIDDLE_LEFT;
import static com.example.tictactoe.strategy.MiniMaxStrategy.Location.MIDDLE_RIGHT;
import static com.example.tictactoe.strategy.MiniMaxStrategy.Location.UPPER_LEFT_CORNER;
import static com.example.tictactoe.strategy.MiniMaxStrategy.Location.UPPER_MIDDLE;
import static com.example.tictactoe.strategy.MiniMaxStrategy.Location.UPPER_RIGHT_CORNER;

// Evaluation idea from: http://www.ntu.edu.sg/home/ehchua/programming/java/javagame_tictactoe_ai.html
public class MiniMaxStrategy implements Strategy {

    private Player[][] score;
    private Move bestMove;
    private int depth;
    enum Location {
        UPPER_LEFT_CORNER, UPPER_MIDDLE, UPPER_RIGHT_CORNER,
        MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT,
        LOWER_LEFT_CORNER, LOWER_MIDDLE, LOWER_RIGHT_CORNER
        }

    public MiniMaxStrategy(Player[][] score) {
        this.score = score;
    }

    @Override
    public int[] moveStrategy() {
        depth = 4;
        minimax(depth, player2, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[] {bestMove.getRow(), bestMove.getCol()};
    }

    private int minimax(int depth, Player player, int alpha, int beta) {
        List<Move> nextMoves = possibleMoves();

        int bestScore;
        int currentScore;

        if (nextMoves.isEmpty() || depth == 0 || gameOver()) {
            return evaluate();
        }

        if (player == player2) {
            bestScore = Integer.MIN_VALUE;
            for (Move move : nextMoves) {
                score[move.getRow()][move.getCol()] = player2;
                currentScore = minimax(depth - 1, player1, alpha, beta);
                if (currentScore > bestScore) {
                    bestScore = currentScore;
                    alpha = currentScore;
                    if (depth == this.depth) {
                        bestMove = move;
                    }
                }
                score[move.getRow()][move.getCol()] = null;
                if (beta <= alpha) {
                    return bestScore;
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (Move move : nextMoves) {
                score[move.getRow()][move.getCol()] = player1;
                currentScore = minimax(depth - 1, player2, alpha, beta);
                if (currentScore < bestScore) {
                    bestScore = currentScore;
                    beta = currentScore;
                    if (depth == this.depth) {
                        bestMove = move;
                    }
                }
                score[move.getRow()][move.getCol()] = null;
                if (beta <= alpha) {
                    return bestScore;
                }
            }
        }

        return bestScore;
    }

    private boolean gameOver() {
        if (gameOver(player1)) {
            return true;
        } else if (gameOver(player2)) {
            return true;
        }
        return false;
    }

    private boolean gameOver(Player player) {
        if (score[0][0] == player && score[0][1] == player && score[0][2] == player) {
            return true;
        }
        if (score[1][0] == player && score[1][1] == player && score[1][2] == player) {
            return true;
        }
        if (score[2][0] == player && score[2][1] == player && score[2][2] == player) {
            return true;
        }
        if (score[0][0] == player && score[1][1] == player && score[2][2] == player) {
            return true;
        }
        if (score[2][0] == player && score[1][1] == player && score[0][2] == player) {
            return true;
        }
        if (score[0][0] == player && score[1][0] == player && score[2][0] == player) {
            return true;
        }
        if (score[0][1] == player && score[1][1] == player && score[2][1] == player) {
            return true;
        }
        if (score[0][2] == player && score[1][2] == player && score[2][2] == player) {
            return true;
        }
        return false;
    }

    private int evaluate() {
        int score = 0;
        score += evaluateThreeCellsAtOnce(UPPER_LEFT_CORNER, UPPER_MIDDLE, UPPER_RIGHT_CORNER);
        score += evaluateThreeCellsAtOnce(MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT);
        score += evaluateThreeCellsAtOnce(LOWER_LEFT_CORNER, LOWER_MIDDLE, LOWER_RIGHT_CORNER);

        score += evaluateThreeCellsAtOnce(UPPER_LEFT_CORNER, MIDDLE_CENTER, LOWER_RIGHT_CORNER);
        score += evaluateThreeCellsAtOnce(LOWER_LEFT_CORNER, MIDDLE_CENTER, UPPER_RIGHT_CORNER);

        score += evaluateThreeCellsAtOnce(UPPER_LEFT_CORNER, MIDDLE_LEFT, LOWER_LEFT_CORNER);
        score += evaluateThreeCellsAtOnce(UPPER_MIDDLE, MIDDLE_CENTER, LOWER_MIDDLE);
        score += evaluateThreeCellsAtOnce(UPPER_RIGHT_CORNER, MIDDLE_RIGHT, LOWER_RIGHT_CORNER);

        return score;
    }

    private int evaluateThreeCellsAtOnce(Location location1, Location location2, Location location3) {
        int score = 0;

        if (getByLocation(location1) == player1) {
            score = -1;
        } else if (getByLocation(location1) == player2) {
            score = 1;
        }

        if (getByLocation(location2) == player1) {
            if (score == 1) {
                return 0;
            } else if (score == -1) {
                score = -10;
            } else {
                score = -1;
            }
        } else if (getByLocation(location2) == player2) {
            if (score == 1) {
                score = 10;
            } else if (score == -1) {
                return 0;
            } else {
                score = 1;
            }
        }

        if (getByLocation(location3) == player1) {
            if (score == 1 || score == 10) {
                return 0;
            } else if (score == -1) {
                score = -10;
            } else if (score == -10) {
                score = -100;
            } else {
                score = -1;
            }
        } else if (getByLocation(location3) == player2) {
            if (score == -1 || score == -10) {
                return 0;
            } else if (score == 1) {
                score = 10;
            } else if (score == 10) {
                score = 100;
            } else {
                score = 1;
            }
        }

        return score;
    }

    private Player getByLocation(Location location) {
        switch (location) {
            case UPPER_LEFT_CORNER: return score[0][0];
            case UPPER_MIDDLE: return score[0][1];
            case UPPER_RIGHT_CORNER: return score[0][2];
            case MIDDLE_LEFT: return score[1][0];
            case MIDDLE_CENTER: return score[1][1];
            case MIDDLE_RIGHT: return score[1][2];
            case LOWER_LEFT_CORNER: return score[2][0];
            case LOWER_MIDDLE: return score[2][1];
            case LOWER_RIGHT_CORNER: return score[2][2];
        }
        return null;
    }

    private List<Move> possibleMoves() {
        List<Move> moves = new LinkedList<>();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (score[row][col] == null) {
                    moves.add(new Move(row, col));
                }
            }
        }
        return moves;
    }
}
