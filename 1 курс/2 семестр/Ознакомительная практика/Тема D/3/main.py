import re

def encrypt_numbers(text):
    pattern = r'\d+'
    encrypted_text = re.sub(pattern, lambda match: str(int(match.group()) ** 3), text)
    return encrypted_text

input_text = "Было закуплено 12 единиц техники по 410.37 рублей."
encrypted_text = encrypt_numbers(input_text)
print(encrypted_text)