# !/usr/bin/env python
from sympy import *
from random import randint

def miller_rabin(n, k):
    if n == 2 or n == 3:
        return True
    if n < 2 or n % 2 == 0:
        return False
    r, s = 0, n - 1
    while s % 2 == 0:
        r += 1
        s //= 2
    for _ in range(k):
        a = randint(2, n - 2)
        x = pow(a, s, n)
        if x == 1 or x == n - 1:
            continue
        for _ in range(r - 1):
            x = pow(x, 2, n)
            if x == n - 1:
                break
        else:
            return False

    return True

def factorize(n):
    k = int(log(n, 2))
    while True:
        p = randprime(10**(k-1), 10**k)
        q = randprime(10**(k-1), 10**k)
        if p*q >= n:
            break
    return p, q

n = randprime(10**49, 10**50)
p, q = factorize(n)

print(f"n: {n}")
print(f"p: {p}")
print(f"q: {q}")
