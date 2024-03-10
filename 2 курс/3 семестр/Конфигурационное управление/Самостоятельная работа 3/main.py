from sly import Lexer, Parser
import json
import sys
from wheel.metadata import _


class ClassLexer(Lexer):
    IDENTIFIERS = r'[A-Za-z0-9-]+'
    tokens = {'IDENTIFIERS', 'STR_LITERAL', 'NUM_LITERAL', 'LPAREN', 'RPAREN'}
    ignore = ' \t,:.[]\n'
    LPAREN = r'\('
    RPAREN = r'\)'

    @_(r'"[^"]*"')
    def STR_LITERAL(self, t):
        t.value = t.value.strip('"')
        return t

    @_(r'\d+')
    def NUM_LITERAL(self, t):
        t.value = int(t.value)
        return t

    def error(self, t):
        print(f"Unrecognized character '{t.value[0]}'")
        self.index += 1


class ClassParser(Parser):
    tokens = ClassLexer.tokens

    @_('s_exp s_exp_list')
    def s_exp_list(self, p):
        return [p.s_exp] + p.s_exp_list

    @_('s_exp')
    def s_exp_list(self, p):
        return [p.s_exp]

    @_('LPAREN s_exp_list RPAREN')
    def s_exp(self, p):
        return p.s_exp_list

    @_('data')
    def s_exp(self, p):
        return p.data

    @_('NUM_LITERAL')
    def data(self, p):
        return p.NUM_LITERAL

    @_('STR_LITERAL')
    def data(self, p):
        return p.STR_LITERAL

    @_('IDENTIFIERS')
    def data(self, p):
        return p.IDENTIFIERS

    def error(self, p):
        print("Parsing error")


if __name__ == '__main__':
    if len(sys.argv) != 2:
        print("Usage: python config_parser.py <filename>")
        sys.exit(1)

    with open(sys.argv[1], 'r', encoding='utf-8') as file:
        data = file.read()

    lexer = ClassLexer()
    parser = ClassParser()
    tokens = lexer.tokenize(data)
    result = parser.parse(tokens)

    print(json.dumps(result, ensure_ascii=False, indent=2))
