def reverse(text):
    text = str(text)
    length = len(text) - 1
    index = 0
    reverse =""
    while index < length + 1:
        reverse = reverse + text[length-index]
        index += 1
    return reverse
