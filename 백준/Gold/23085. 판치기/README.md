# [Gold IV] 판치기 - 23085 

[문제 링크](https://www.acmicpc.net/problem/23085) 

### 성능 요약

메모리: 14952 KB, 시간: 136 ms

### 분류

너비 우선 탐색, 그래프 이론, 그래프 탐색

### 제출 일자

2025년 1월 22일 09:32:42

### 문제 설명

<p>판치기는 \(N\)개의 동전을 바닥에 놓고, 임의의 동전들을 뒤집는 것을 반복하여 모두 뒷면이 보이는 상태로 바꾸면 이기는 게임이다.</p>

<p>판치기 경력 20년에 빛나는 치훈이는 판치기 최고의 극의, "\(K\)-뒤집기"를 시전할 수 있게 되었다. "\(K\)-뒤집기"는 원하는 서로 다른 \(K\)개의 동전을 한 번에 뒤집는 능력이다.</p>

<p>초기 동전의 상태가 주어진다. "\(K\)-뒤집기"만 사용해 게임을 이기려면 최소 몇 번 사용해야 이길 수 있을까?</p>

### 입력 

 <p>첫째 줄에 \(N\), \(K\)가 주어진다.</p>

<p>두번째 줄에 초기 동전의 상태를 나타내는 문자열 \(S\)가 주어진다.</p>

<p>\(S\)의 \(i\)번째 문자가 '<code>H</code>'면 \(i\)번째 동전이 앞면, '<code>T</code>'면 \(i\)번째 동전이 뒷면이 보이는 상태를 나타낸다.</p>

### 출력 

 <p>첫째 줄에 문제의 답을 출력한다.</p>

<p>모두 뒷면이 보이는 상태로 바꿀 수 없다면 대신 <code>-1</code>을 출력한다.</p>

