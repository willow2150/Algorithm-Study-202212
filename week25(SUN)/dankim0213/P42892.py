import sys

sys.setrecursionlimit(10**6)


class Node(object):
    def __init__(self, info):
        self.num = info[2]
        self.pos = info[:2]
        self.left = None
        self.right = None


def solution(nodeinfo):
    for idx in range(len(nodeinfo)):
        nodeinfo[idx].append(idx + 1)

    nodeinfo.sort(key=lambda x: (-x[1], x[0]))
    tree = Node(nodeinfo[0])

    for info in nodeinfo[1:]:
        add_node(tree, info)

    return [pre_order(tree), post_order(tree)]


def pre_order(curr):
    path = [curr.num]
    if curr.left:
        path += pre_order(curr.left)
    if curr.right:
        path += pre_order(curr.right)
    return path


def post_order(curr):
    path = []
    if curr.left:
        path += post_order(curr.left)
    if curr.right:
        path += post_order(curr.right)
    path.append(curr.num)
    return path


def add_node(parent, info):
    if parent.pos[0] > info[0]:
        if parent.left:
            add_node(parent.left, info)
        else:
            parent.left = Node(info)
    else:
        if parent.right:
            add_node(parent.right, info)
        else:
            parent.right = Node(info)
