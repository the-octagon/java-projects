def is_prime(x):
    if x <= 1:
        return False
    prime = True
    index = x - 1
    while index > 1 and prime == True:
        if x % index == 0:
            return False
        else:
            index -= 1
    return prime
