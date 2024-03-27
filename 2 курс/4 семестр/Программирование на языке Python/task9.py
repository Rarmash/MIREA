from json import loads


def remove_spacebar(option, pos):
    if pos == 1 and option[0] == " ":
        option = option[1:]
    elif pos == -1 and option[-1] == " ":
        option = option[:-1]
    return option


def main(string):
    string = string[1:][:-1].split(".")
    json_list = loads("{}")
    if string[-1] == " " or string[-1] == "":
        string.pop(-1)
    for option in string:
        option = (option.replace(" {{ ", "")
                  .replace(" }} ", "")
                  .replace("{{ ", "")
                  .replace(" }}", "")
                  .replace(" {{", "")
                  .replace(" }}", "")
                  .replace("{{", "")
                  .replace("}}", ""))
        option = option.split("<==")
        for opt in range(len(option)):
            option[opt] = remove_spacebar(option[opt], -1)
        option[0] = option[0].replace("\n", "")
        option[0] = option[0].replace("option", "")
        option[0] = remove_spacebar(option[0], 1)
        option[1] = int(remove_spacebar(str(option[1]), 1))
        json_list.update({f"{option[0]}": option[1]})
    return json_list


string1 = ("{{{ option atdi <==-7912}}.{{ option reis <==6919}}. "
           "{{option atxe_538<== -3130 }}. {{option raat <== 497 }}. }")
string2 = ("{ {{ option malabe <==-8947 }}. {{option ceteus_397<== -2614}}."
           "{{ option riri_141 <==934 }}. }")
string3 = ('{{{ option atdi <==-7912}}.{{ option reis <==6919}}. '
           '{{option\natxe_538<== -3130 }}. {{option raat <== 497 }}. }')
string4 = '{ {{ option ceceor_929 <== 184 }}.{{option bees <==-9953 }}.}'
print(main(string1))
print(main(string2))
print(main(string3))
print(main(string4))
