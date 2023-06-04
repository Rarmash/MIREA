import os
import shutil
from operator import itemgetter

def convert_to_time(seconds):
    h = seconds // 3600
    m = (seconds % 3600) // 60
    s = (seconds % 3600) % 60
    return f"{h:02}|{m:02}|{s:02}"

def create_folders():
    base_path = os.getcwd()
    folder_name = "Ознакомительная папка"
    subfolders = ["Тема A", "Тема B"]

    # Создаем главную папку
    folder_path = os.path.join(base_path, folder_name)
    if not os.path.exists(folder_path):
        os.mkdir(folder_path)

    # Создаем подпапки
    for subfolder in subfolders:
        subfolder_path = os.path.join(folder_path, subfolder)
        if not os.path.exists(subfolder_path):
            os.mkdir(subfolder_path)
def move_files():
    base_path = os.getcwd()
    folder_name = "Ознакомительная папка"
    subfolders = ["Тема A", "Тема B"]

    # Получаем список всех файлов в текущей директории
    files = os.listdir(base_path)

    for file in files:
        if file.startswith("task_") and file.endswith(".py"):
            # Определяем в какой подпапке должен находиться файл
            if "A" in file:
                subfolder = subfolders[0]
            elif "B" in file:
                subfolder = subfolders[1]
            else:
                continue  # Если ни одна буква из списка не найдена, пропускаем файл

            # Перемещаем файл в подпапку
            src_path = os.path.join(base_path, file)
            dst_folder_path = os.path.join(base_path, folder_name, subfolder)
            dst_path = os.path.join(dst_folder_path, file)
            shutil.move(src_path, dst_path)

def run_files(folder_path):
    for root, dirs, files in os.walk(folder_path):
        for file in files:
            if file.startswith("task_") and file.endswith(".py"):
                file_path = os.path.join(root, file)
                with open(file_path, 'r', encoding='utf-8') as f:
                    contents = f.read()
                    exec(contents)
                    print(f"File {file} executed successfully")
create_folders()
move_files()
run_files(os.path.join(os.getcwd(), "Ознакомительная папка"))
