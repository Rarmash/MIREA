from behave import given, when, then
from TicTacToe import TicTacToe

@given('the game is started')
def step_impl(context):
    context.game = TicTacToe()

@when('player {player} makes a move to row {row} column {col}')
def step_impl(context, player, row, col):
    context.game.make_move(int(row), int(col), player)

@then('player {player} should be the winner')
def step_impl(context, player):
    assert context.game.check_winner(player), f"{player} should be the winner"

@then('the game should end in a draw')
def step_impl(context):
    assert context.game.is_draw(), "The game should be a draw"

@when('players fill all cells without a winner')
def step_impl(context):
    context.game.board = [
        ['X', 'O', 'X'],
        ['X', 'X', 'O'],
        ['O', 'X', 'O']
    ]

