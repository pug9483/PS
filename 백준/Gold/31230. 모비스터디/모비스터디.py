import sys
import heapq

def dijkstra(src, graph, N):
    INF = 10**16
    dist = [INF] * N
    dist[src] = 0
    pq = [(0, src)]  # (거리, 노드)
    
    while pq:
        w, v = heapq.heappop(pq)
        if dist[v] < w:
            continue
        for next_v, next_w in graph[v]:
            if dist[next_v] > dist[v] + next_w:
                dist[next_v] = dist[v] + next_w
                heapq.heappush(pq, (dist[next_v], next_v))
    
    return dist

def main():
    INF = 987654321987659875454
    input = sys.stdin.read
    data = input().splitlines()
    
    N, M, A, B = map(int, data[0].split())
    A -= 1
    B -= 1
    
    graph = [[] for _ in range(N)]
    
    for i in range(1, M + 1):
        u, v, w = map(int, data[i].split())
        u -= 1
        v -= 1
        graph[u].append((v, w))
        graph[v].append((u, w))
    
    Adist = dijkstra(A, graph, N)
    Bdist = dijkstra(B, graph, N)
    
    minDist = Adist[B]
    result = []
    
    for i in range(N):
        if Adist[i] == INF or Bdist[i] == INF:
            continue
        if Adist[i] + Bdist[i] == minDist:
            result.append(i + 1)
    
    print(len(result))
    print(" ".join(map(str, result)))

if __name__ == "__main__":
    main()