def remove_none_columns(table):
    empty_columns = [i for i, column in enumerate(table[0])
                     if all(row[i] is None for row in table)]

    for column_index in reversed(empty_columns):
        for row in table:
            del row[column_index]

    return table


def remove_duplicated_rows(table):
    unique_rows = []
    seen_rows = set()
    for row in table:
        row_tuple = tuple(row)
        if row_tuple not in seen_rows:
            unique_rows.append(row)
            seen_rows.add(row_tuple)
    return unique_rows


def remove_none_rows(tablet):
    return [row for row in tablet if not all(value is None for value in row)]


def format_data(table):
    for row in table:
        row[0] = f"{int(float(row[0]) * 100)}%"
        date = row[1].split("/")
        row[1] = f"{date[2]}-{date[1]}-{date[0]}"
        row[2] = "Не выполнено" if int(row[2]) == 0 else "Выполнено"
    return table


def main(table):
    arr = [row[:] for row in table]
    arr = remove_none_columns(arr)
    arr = remove_duplicated_rows(arr)
    arr = remove_none_rows(arr)
    arr = format_data(arr)
    return arr


table = [
    [None, 0.66, '99/02/16', None, 0],
    [None, 0.66, '99/02/16', None, 0],
    [None, None, None, None, None],
    [None, 0.53, '02/03/25', None, 0],
    [None, None, None, None, None],
    [None, 0.31, '03/05/10', None, 0]
]

print(main(table))
