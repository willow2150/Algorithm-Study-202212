from itertools import permutations
import re


def toPostFix(tokens: list[str], priority: dict[str, int]) -> list[str]:
    stack: list[str] = []
    postfix = []

    for token in tokens:
        if token.isdigit():
            postfix.append(token)
        else:
            if not stack:
                stack.append(token)
            else:
                while stack:
                    if priority[token] <= priority[stack[-1]]:
                        postfix.append(stack.pop())
                    else:
                        break

                stack.append(token)

    while stack:
        postfix.append(stack.pop())

    return postfix


def calc(tokens: list[str]) -> int:
    stack = []

    for token in tokens:
        if token.isdigit():
            stack.append(int(token))
            continue

        num1 = stack.pop()
        num2 = stack.pop()
        if token == "*":
            stack.append(num2 * num1)
        elif token == "+":
            stack.append(num2 + num1)
        else:  # "-"
            stack.append(num2 - num1)

    return stack.pop()


def solution(expression: str) -> int:
    tokens = re.split(r"([-+*/()])|\s+", expression)
    operators = ["+", "-", "*"]
    answer = 0

    for op_list in permutations(operators):
        priority = {o: p for p, o in enumerate(op_list)}
        postfix = toPostFix(tokens, priority)
        answer = max(answer, abs(calc(postfix)))

    return answer


print(solution("100-200*300-500+20"))
print(solution("50*6-3*2"))

# 문제풀이 흐름
# 1. 숫자와 연산자를 모두 분리
# 2. 순열을 사용해 연산자 3개의 우선순위를 정함
# 3. 해당 우선순위를 기반으로 숫자를 계산
# 4. 나온 결과 중 가장 큰 숫자를 반환
#
