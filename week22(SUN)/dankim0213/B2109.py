def schedule_lectures(lectures):
    search_idx = dict()
    vals = [0] * 10001
    lectures.sort(key=lambda x: -x[0])  # Sort by descending order of payment

    for lecture in lectures:
        payment, deadline = lecture

        idx = search_idx.setdefault(deadline, deadline)
        while vals[idx] > 0 and idx > 0:
            idx -= 1

        if idx == 0:
            continue

        search_idx[deadline] = idx
        vals[idx] = payment

    return sum(vals[1:])


def main():
    n = int(input())
    lectures = []

    for _ in range(n):
        payment, deadline = map(int, input().split())
        lectures.append((payment, deadline))

    result = schedule_lectures(lectures)
    print(result)


if __name__ == "__main__":
    main()
