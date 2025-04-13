import re
import sys

def count_letters(text):
    # 将所有字母转为小写
    text = text.lower()

    # 只保留字母字符
    letters = re.findall(r'[a-z]', text)

    # 统计每个字母的出现次数
    letter_counts = {}
    for letter in letters:
        if letter in letter_counts:
            letter_counts[letter] += 1
        else:
            letter_counts[letter] = 1

    # 找出出现次数为奇数的字母
    odd_count_letters = {letter: count for letter, count in letter_counts.items() if count % 2 == 1}

    return letter_counts, odd_count_letters

if __name__ == "__main__":
    # 从标准输入读取文本
    text = sys.stdin.read()

    # 统计字母
    all_counts, odd_counts = count_letters(text)

    # 输出结果
    print("字母出现次数统计:")
    for letter in sorted(all_counts.keys()):
        print(f"{letter}: {all_counts[letter]}")

    print("\n出现次数为奇数的字母:")
    for letter in sorted(odd_counts.keys()):
        print(f"{letter}: {odd_counts[letter]}")
