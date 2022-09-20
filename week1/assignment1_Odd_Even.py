"""
@Author : Saman Salimzade
@university : NYIT (cybersecurity)
@Student ID : 1314389
@Group No. : 2

"""

# Print all the even and odd numbers less than a maximum number.

max = int(input("Enter the maximum number: "))

even_numbers = []
odd_numbers = []

for i in range (1, max):

    if i % 2 == 0:
        even_numbers.append(i)
    if i % 2 == 1:
        odd_numbers.append(i)

print("even numbers : ", even_numbers)
print("odd numbers : ", odd_numbers)