Feature: Tic Tac Toe game

  Scenario: Player X wins the game
    Given the game is started
    When player X makes a move to row 0 column 0
    And player X makes a move to row 0 column 1
    And player X makes a move to row 0 column 2
    Then player X should be the winner

  Scenario: Game ends in a draw
    Given the game is started
    When players fill all cells without a winner
    Then the game should end in a draw
