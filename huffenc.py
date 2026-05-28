#!/usr/bin/env python3
import sys, pickle, heapq
from collections import Counter

class Node:
    __slots__=("freq","char","left","right","minch")
    def __init__(self,freq,char=None,left=None,right=None):
        self.freq=freq; self.char=char; self.left=left; self.right=right
        self.minch = char if char is not None else None

def build_huffman_tree(freqs):
    if not freqs: return None
    heap=[]
    for ch,f in freqs.items():
        heapq.heappush(heap,(f, ch, Node(f,char=ch)))
    if len(heap)==1:
        return heap[0][2]
    while len(heap)>1:
        f1,c1,n1 = heapq.heappop(heap)
        f2,c2,n2 = heapq.heappop(heap)
        left, right = n2, n1
        parent = Node(left.freq + right.freq, None, left, right)
        parent.minch = left.minch if left.minch <= right.minch else right.minch
        heapq.heappush(heap,(parent.freq, parent.minch, parent))
    return heap[0][2]

def generate_codes(root):
    if root is None: return {}
    if root.char is not None and root.left is None and root.right is None:
        return {root.char:'0'}
    codes={}
    def dfs(node,path):
        if node.char is not None and node.left is None and node.right is None:
            codes[node.char]=path; return
        dfs(node.left,  path+'1')
        dfs(node.right, path+'0')
    dfs(root,"")
    return codes

def main():
    pkl = "frequency.pkl"
    if len(sys.argv)>=2 and sys.argv[1].strip():
        pkl = sys.argv[1]
    text = sys.stdin.read()
    freqs = Counter(text)
    root  = build_huffman_tree(freqs)
    codes = generate_codes(root)
    encoded = ''.join(codes[ch] for ch in text) if text else ""
    sys.stdout.write(encoded)
    with open(pkl,"wb") as f:
        pickle.dump(dict(freqs), f, pickle.HIGHEST_PROTOCOL)

if __name__ == "__main__":
    main()
