## 1.599 * (number of one sylabble words per 100 words)nosw - 1.015 * (avarage sentence length)sl - 31.517
import os

os.system("color a")
##path = input("Enter path to directory: ")
path = r"C:\Users\user\Desktop"

TxtF1 = str(input("First File name:"))
TxtF2 = str(input("Second File name:"))

with open(fr"{path}\{TxtF1}", 'r', encoding = "utf-8") as f1:
    TxtF1 = f1.read()
f1.close()

with open(fr"{path}\{TxtF2}", 'r', encoding = "utf-8") as f2:
    TxtF2 = f2.read()
f2.close()

TxtF1 = str(TxtF1)
TxtF2 = str(TxtF2)

TxtF1 = TxtF1.split(' ')
TxtF2 = TxtF2.split(' ')

TxtF1U = list(set(TxtF1))
TxtF2U = list(set(TxtF2))

Converge = []

for i in TxtF1U:
    if i not in Converge:
        Converge.append(i)

for i in TxtF2U:
    if i not in Converge:
        Converge.append(i)

##Unique words union in text 1 count
T1UC = 0
##Unique word in text 1 count
T1C = 0

##Unique words union in text 2 count
T2UC = 0
##Unique word in text 2 count
T2C = 0

##Unique Union Count
UUC = 0

for i in Converge:
    if i in TxtF1:
        T1UC+=1

for i in TxtF1U:
    if i in TxtF1:
        T1C+=1

for i in Converge:
    if i in TxtF2:
        T2UC += 1

for i in TxtF2U:
    if i in TxtF2:
        T2C+=1

for i in Converge:
    if i in TxtF1U and i in TxtF2U:
        UUC += 1

##Converge word count
Con_W_C = len(Converge)

Txt1_ratio = (T1UC / len(TxtF1)) * 100
Txt2_ratio = (T2UC / len(TxtF2)) * 100

print(f"1. Metin belgesinin, kesişime oranı {Txt1_ratio}")  
print(f"2. Metin belgesinin, kesişime oranı {Txt2_ratio}")  

os.system("pause")
