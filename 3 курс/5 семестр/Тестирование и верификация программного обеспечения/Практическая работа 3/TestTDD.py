import unittest
from TicTacToe import TicTacToe

class TestTicTacToe(unittest.TestCase):

    def setUp(self):
        self.game = TicTacToe()

    def test_initial_state(self):
        # Тест начального состояния игры
        self.assertEqual(self.game.board, [[' '] * 3 for _ in range(3)])

    def test_valid_move(self):
        # Тест правильного хода
        self.game.make_move(0, 0, 'X')
        self.assertEqual(self.game.board[0][0], 'X')

    def test_invalid_move(self):
        # Тест невозможности хода на занятое поле
        self.game.make_move(0, 0, 'X')
        with self.assertRaises(ValueError):
            self.game.make_move(0, 0, 'O')

    def test_winner(self):
        # Тест на проверку победителя
        self.game.board = [
            ['X', 'X', 'X'],
            ['O', ' ', 'O'],
            [' ', ' ', ' ']
        ]
        self.assertTrue(self.game.check_winner('X'))

    def test_draw(self):
        # Тест на ничью
        self.game.board = [
            ['X', 'O', 'X'],
            ['X', 'X', 'O'],
            ['O', 'X', 'O']
        ]
        self.assertTrue(self.game.is_draw())
