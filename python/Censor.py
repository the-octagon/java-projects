def censor(text,word):
    text = str(text)
    word = str(word)
    while text.find(word,0) != -1:
        start_position = text.find(word,0)
        end_position = start_position+len(word)
        text = text[0:start_position] + ("*" * len(word)) + text[end_position:]
    return text
