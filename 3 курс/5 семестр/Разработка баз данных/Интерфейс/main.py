import tkinter as tk
from tkinter import ttk, messagebox
import mysql.connector

def connect_to_db(username, password):
    try:
        connection = mysql.connector.connect(
            host="localhost",
            user=username,
            password=password,
            database="coffeehouse"
        )
        return connection
    except mysql.connector.Error as e:
        messagebox.showerror("Ошибка", f"Ошибка подключения: {e}")
        return None

def check_user_role(connection, username):
    try:
        cursor = connection.cursor()
        cursor.execute(f"SHOW GRANTS FOR '{username}'@'localhost'")
        grants = cursor.fetchall()

        is_admin = any(
            "ALL PRIVILEGES" in grant[0] or "GRANT OPTION" in grant[0] for grant in grants
        )

        return is_admin
    except mysql.connector.Error as e:
        messagebox.showerror("Ошибка", f"Ошибка при проверке прав: {e}")
        return False

def fetch_table_data(connection, table_name):
    try:
        cursor = connection.cursor()
        cursor.execute(f"SELECT * FROM {table_name}")
        columns = [col[0] for col in cursor.description]
        rows = cursor.fetchall()
        cursor.close()
        return columns, rows
    except mysql.connector.Error as e:
        messagebox.showerror("Ошибка", f"Ошибка запроса: {e}")
        return [], []

def add_row_to_table(connection, table_name, columns, table_window, user_role):
    def save_row():
        values = [entry.get() for entry in entries]
        placeholders = ', '.join(['%s'] * len(values))
        query = f"INSERT INTO {table_name} ({', '.join(columns)}) VALUES ({placeholders})"

        try:
            cursor = connection.cursor()
            cursor.execute(query, tuple(values))
            connection.commit()
            messagebox.showinfo("Успех", "Запись успешно добавлена!")
            table_window.destroy()
            display_table(connection, table_name, user_role)
        except mysql.connector.Error as e:
            messagebox.showerror("Ошибка", f"Ошибка при добавлении: {e}")

    add_window = tk.Toplevel()
    add_window.title(f"Добавить запись в {table_name}")
    add_window.geometry("400x300")

    entries = []
    for col in columns:
        tk.Label(add_window, text=col).pack(pady=5)
        entry = tk.Entry(add_window)
        entry.pack(pady=5)
        entries.append(entry)

    tk.Button(add_window, text="Добавить", command=save_row).pack(pady=20)
    tk.Button(add_window, text="Закрыть", command=add_window.destroy).pack(pady=5)

def edit_row_in_table(connection, table_name, columns, row_id, table_window, user_role):
    def save_changes():
        values = [entry.get() for entry in entries]
        update_query = f"UPDATE {table_name} SET {', '.join([f'{col}=%s' for col in columns[1:]])} WHERE {columns[0]}=%s"

        try:
            cursor = connection.cursor()
            cursor.execute(update_query, tuple(values) + (row_id,))
            connection.commit()
            messagebox.showinfo("Успех", "Запись успешно отредактирована!")
            table_window.destroy()
            display_table(connection, table_name, user_role)
        except mysql.connector.Error as e:
            messagebox.showerror("Ошибка", f"Ошибка при редактировании: {e}")

    edit_window = tk.Toplevel()
    edit_window.title(f"Редактировать запись в {table_name}")
    edit_window.geometry("400x300")

    cursor = connection.cursor()
    cursor.execute(f"SELECT * FROM {table_name} WHERE {columns[0]} = %s", (row_id,))
    row_data = cursor.fetchone()
    cursor.close()

    if not row_data:
        messagebox.showerror("Ошибка", "Запись не найдена!")
        edit_window.destroy()
        return

    entries = []
    for idx, col in enumerate(columns[1:]):
        tk.Label(edit_window, text=col).pack(pady=5)
        entry = tk.Entry(edit_window)
        entry.insert(0, row_data[idx + 1])
        entry.pack(pady=5)
        entries.append(entry)

    tk.Button(edit_window, text="Сохранить изменения", command=save_changes).pack(pady=20)
    tk.Button(edit_window, text="Закрыть", command=edit_window.destroy).pack(pady=5)

def delete_row_from_table(connection, table_name, columns, row_id, table_window, user_role):
    try:
        cursor = connection.cursor()
        cursor.execute(f"DELETE FROM {table_name} WHERE {columns[0]}=%s", (row_id,))
        connection.commit()
        messagebox.showinfo("Успех", "Запись успешно удалена!")
        table_window.destroy()
        display_table(connection, table_name, user_role)
    except mysql.connector.Error as e:
        messagebox.showerror("Ошибка", f"Ошибка при удалении: {e}")

def search_table(connection, table_name, search_term1, column1, search_term2, column2):
    cursor = connection.cursor()

    # Формируем условие для поиска
    conditions = []
    params = []

    if search_term1 and column1:
        conditions.append(f"`{column1}` LIKE %s")
        params.append(f"%{search_term1}%")

    if search_term2 and column2:
        conditions.append(f"`{column2}` LIKE %s")
        params.append(f"%{search_term2}%")

    if not conditions:
        return []  # Если нет условий, возвращаем пустой результат

    query = f"SELECT * FROM {table_name} WHERE {' AND '.join(conditions)}"
    cursor.execute(query, tuple(params))
    rows = cursor.fetchall()
    return rows

def display_table(connection, table_name, user_role):
    columns, rows = fetch_table_data(connection, table_name)
    if not columns:
        return

    table_window = tk.Toplevel()
    table_window.title(f"Таблица: {table_name}")
    table_window.geometry("800x500")

    tree = ttk.Treeview(table_window, columns=columns, show="headings", selectmode="browse")
    tree.pack(fill="both", expand=True)

    for col in columns:
        tree.heading(col, text=col)
        tree.column(col, width=100)

    for row in rows:
        tree.insert("", "end", values=row)

    tk.Label(table_window, text="Поиск 1: Значение").pack(pady=2)
    search_entry1 = tk.Entry(table_window)
    search_entry1.pack(pady=2)

    tk.Label(table_window, text="Поиск 1: Столбец").pack(pady=2)
    search_column1 = ttk.Combobox(table_window, values=columns)
    search_column1.pack(pady=2)

    tk.Label(table_window, text="Поиск 2: Значение").pack(pady=2)
    search_entry2 = tk.Entry(table_window)
    search_entry2.pack(pady=2)

    tk.Label(table_window, text="Поиск 2: Столбец").pack(pady=2)
    search_column2 = ttk.Combobox(table_window, values=columns)
    search_column2.pack(pady=2)

    def search_action():
        search_term1 = search_entry1.get()
        column1 = search_column1.get()
        search_term2 = search_entry2.get()
        column2 = search_column2.get()

        search_results = search_table(connection, table_name, search_term1, column1, search_term2, column2)

        for row in tree.get_children():
            tree.delete(row)
        for row in search_results:
            tree.insert("", "end", values=row)

    tk.Button(table_window, text="Искать", command=search_action).pack(pady=10)

    def on_select(event):
        selected_item = tree.selection()
        if selected_item:
            row_id = tree.item(selected_item)["values"][0]
            show_edit_delete_buttons(row_id)

    tree.bind("<<TreeviewSelect>>", on_select)

    button_frame = tk.Frame(table_window)
    button_frame.pack(side="bottom", fill="x", pady=10)

    if user_role:
        tk.Button(button_frame, text="Добавить запись", command=lambda: add_row_to_table(connection, table_name, columns, table_window, user_role)).pack(side="left", padx=10)

        def show_edit_delete_buttons(row_id):
            for widget in button_frame.winfo_children():
                widget.destroy()

            if row_id:
                tk.Button(button_frame, text="Редактировать", command=lambda: edit_row_in_table(connection, table_name, columns, row_id, table_window, user_role)).pack(side="left", padx=10)
                tk.Button(button_frame, text="Удалить", command=lambda: delete_row_from_table(connection, table_name, columns, row_id, table_window, user_role)).pack(side="left", padx=10)


    tk.Button(button_frame, text="Закрыть", command=table_window.destroy).pack(side="right", padx=10)

def main_window(connection, username):
    user_role = check_user_role(connection, username)

    def show_table():
        table_name = table_selector.get()
        if table_name:
            display_table(connection, table_name, user_role)

    cursor = connection.cursor()
    cursor.execute("SHOW TABLES")
    tables = [table[0] for table in cursor.fetchall()]
    cursor.close()

    root = tk.Tk()
    root.title("Выбор таблицы")
    root.geometry("400x200")

    tk.Label(root, text="Выберите таблицу:").pack(pady=10)
    table_selector = ttk.Combobox(root, values=tables)
    table_selector.pack(pady=10)

    tk.Button(root, text="Показать таблицу", command=show_table).pack(pady=20)

    root.mainloop()

def login_window():
    def try_login():
        username = entry_username.get()
        password = entry_password.get()

        if not username or not password:
            messagebox.showerror("Ошибка", "Введите логин и пароль")
            return

        connection = connect_to_db(username, password)
        if connection:
            main_window(connection, username)
            login_win.destroy()

    login_win = tk.Tk()
    login_win.title("Вход в систему")
    login_win.geometry("300x200")

    tk.Label(login_win, text="Логин:").pack(pady=10)
    entry_username = tk.Entry(login_win)
    entry_username.pack(pady=5)

    tk.Label(login_win, text="Пароль:").pack(pady=10)
    entry_password = tk.Entry(login_win, show="*")
    entry_password.pack(pady=5)

    tk.Button(login_win, text="Войти", command=try_login).pack(pady=20)

    login_win.mainloop()

if __name__ == "__main__":
    login_window()
