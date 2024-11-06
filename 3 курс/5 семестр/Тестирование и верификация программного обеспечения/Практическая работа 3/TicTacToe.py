class TicTacToe:
    def __init__(self):
        self.board = [[' '] * 3 for _ in range(3)]
        self.current_player = 'X'

    def display_board(self):
        for row in self.board:
            print('|'.join(row))
            print('-' * 5)

    def make_move(self, row, col, player):
        if self.board[row][col] != ' ':
            raise ValueError("Cell is already taken")
        self.board[row][col] = player

    def check_winner(self, player):
        # Проверка горизонталей, вертикалей и диагоналей
        for row in self.board:
            if all([cell == player for cell in row]):
                return True

        for col in range(3):
            if all([self.board[row][col] == player for row in range(3)]):
                return True

        if all([self.board[i][i] == player for i in range(3)]) or all([self.board[i][2 - i] == player for i in range(3)]):
            return True

        return False

    def is_draw(self):
        return all(cell != ' ' for row in self.board for cell in row)

    def switch_player(self):
        self.current_player = 'O' if self.current_player == 'X' else 'X'


def main():
    game = TicTacToe()
    print("Welcome to Tic-Tac-Toe!")
    game.display_board()

    while True:
        try:
            row = int(input(f"Player {game.current_player}, enter the row (0, 1, or 2): "))
            col = int(input(f"Player {game.current_player}, enter the column (0, 1, or 2): "))

            if row not in range(3) or col not in range(3):
                print("Invalid input. Please enter numbers between 0 and 2.")
                continue

            game.make_move(row, col, game.current_player)
            game.display_board()

            if game.check_winner(game.current_player):
                print(f"Player {game.current_player} wins!")
                break

            if game.is_draw():
                print("It's a draw!")
                break

            game.switch_player()
        except ValueError as e:
            print(e)

if __name__ == "__main__":
    main()
