from sys import stdin


def find_max_brightness() -> int:
    brightness_sum = 0
    max_brightness_change = -5_000
    brightness_change = 0
    for is_on, brightness in zip(on_or_off, brightness_list):
        if is_on:
            brightness_sum += brightness
            brightness_change -= brightness
        else:
            brightness_change += brightness
        max_brightness_change = max(max_brightness_change, brightness_change)
        if brightness_change < 0:
            brightness_change = 0
    return brightness_sum + max_brightness_change


if __name__ == '__main__':
    N = int(stdin.readline())
    brightness_list = map(int, stdin.readline().split())
    on_or_off = map(int, stdin.readline().split())
    print(find_max_brightness())
