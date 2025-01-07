# Профилирование приложения с использованием `VisualVM`
## Цель
Описать работу консольного приложения, выполняющего сортировку массива чисел тремя алгоритмами, используя различные сборщики мусора.
### Инструменты 
- `VisualVM`
### Сборщики (ссылки на разделы)
- [Parallel GC](#1-parallel-gc)
- [G1 GC](#2-g1-gc)
- [ZGC](#3-zgc)

---
## Методология
Для каждой конфигурации JVM, после запуска приложения, создаётся массив чисел на 250_000 единиц.

Далее последовательно запускаются 3 вида сортировки:
- Сортировка слиянием.
- Сортировка методом вставки.
- Сортировка пузырьком.

Для каждого вида анализируются визуальные графики использования ресурсов процессора и памяти (Heap), а также лог-файл сборщика мусора. Описывается время выполнения каждого алгоритма с отметкой на визуальном графике. 

---
## 1. Parallel GC
### Параметры JVM
`-XX:+UseParallelGC -Xmx12m -Xms12m -Xlog:gc:data/gc.log:time,level,tags`
### 0. Создание массива чисел
На вкладке `Sampler` видно как размещается массив `int`, после создания:
![memory_sampler.png](/data/img/memory_sampler.png)
Далее количество объектов `int[]` увеличится из-за создания копий при сортировке слиянием.
### 1. Сортировка слиянием
```chatinput
MergeSort Начало сортировки - 14:54:05.775976700
MergeSort Конец сортировки - 14:54:05.825507200
```
```chatinput
[2025-01-07T14:54:05.776+0500][info][gc] GC(60) Pause Young (Allocation Failure) 8M->6M(11M) 0.342ms
[2025-01-07T14:54:05.782+0500][info][gc] GC(61) Pause Young (Allocation Failure) 9M->6M(11M) 0.270ms
[2025-01-07T14:54:05.792+0500][info][gc] GC(62) Pause Young (Allocation Failure) 9M->6M(11M) 0.163ms
[2025-01-07T14:54:05.797+0500][info][gc] GC(63) Pause Young (Allocation Failure) 9M->7M(11M) 0.157ms
[2025-01-07T14:54:05.799+0500][info][gc] GC(64) Pause Young (Allocation Failure) 10M->7M(11M) 0.169ms
[2025-01-07T14:54:05.803+0500][info][gc] GC(65) Pause Young (Allocation Failure) 10M->7M(11M) 0.213ms
[2025-01-07T14:54:05.813+0500][info][gc] GC(66) Pause Full (Ergonomics) 7M->6M(11M) 10.915ms
[2025-01-07T14:54:05.816+0500][info][gc] GC(67) Pause Young (Allocation Failure) 9M->6M(11M) 0.137ms
[2025-01-07T14:54:05.819+0500][info][gc] GC(68) Pause Young (Allocation Failure) 9M->6M(11M) 0.067ms
[2025-01-07T14:54:05.821+0500][info][gc] GC(69) Pause Young (Allocation Failure) 9M->6M(11M) 0.077ms
[2025-01-07T14:54:05.824+0500][info][gc] GC(70) Pause Young (Allocation Failure) 9M->6M(11M) 0.088ms
```
### 2. Сортировка методом вставки
```chatinput
InsertSort Начало сортировки - 14:57:05.019806700
InsertSort Конец сортировки - 14:57:07.505369300
```
```chatinput
[2025-01-07T14:57:05.019+0500][info][gc] GC(78) Pause Young (Allocation Failure) 8M->6M(11M) 0.157ms
[2025-01-07T14:57:25.800+0500][info][gc] GC(79) Pause Young (Allocation Failure) 9M->6M(11M) 0.180ms
```
### 3. Сортировка пузырьком
```chatinput
BubbleSort Начало сортировки - 15:01:28.833899600
BubbleSort Конец сортировки - 15:02:19.731988400
```
```chatinput
[2025-01-07T15:01:28.833+0500][info][gc] GC(87) Pause Young (Allocation Failure) 9M->6M(11M) 0.174ms
[2025-01-07T15:01:48.962+0500][info][gc] GC(88) Pause Young (Allocation Failure) 9M->7M(11M) 0.223ms
[2025-01-07T15:01:48.970+0500][info][gc] GC(89) Pause Full (Ergonomics) 7M->5M(11M) 8.534ms
[2025-01-07T15:02:13.529+0500][info][gc] GC(90) Pause Young (Allocation Failure) 8M->5M(11M) 0.253ms
[2025-01-07T15:02:43.993+0500][info][gc] GC(91) Pause Young (Allocation Failure) 8M->5M(11M) 0.214ms
```
### Графики
![parallel_CPU_usage.png](/data/img/parallel_CPU_usage.png)
![parallel_RAM_usage.png](/data/img/parallel_RAM_usage.png)
---
## 2. G1 GC
### Параметры JVM
`-XX:+UseG1GC -Xmx12m -Xms12m -Xlog:gc:data/gc.log:time,level,tags`
### 1. Сортировка слиянием
```chatinput
MergeSort Начало сортировки - 15:35:03.710434500
MergeSort Конец сортировки - 15:35:03.760655
```
```chatinput
[2025-01-07T15:35:03.711+0500][info][gc] GC(10) Pause Young (Concurrent Start) (G1 Humongous Allocation) 7M->5M(12M) 0.876ms
[2025-01-07T15:35:03.711+0500][info][gc] GC(11) Concurrent Undo Cycle
[2025-01-07T15:35:03.711+0500][info][gc] GC(11) Concurrent Undo Cycle 0.075ms
[2025-01-07T15:35:03.715+0500][info][gc] GC(12) Pause Young (Normal) (G1 Evacuation Pause) 8M->6M(12M) 0.739ms
[2025-01-07T15:35:03.728+0500][info][gc] GC(13) Pause Young (Concurrent Start) (G1 Evacuation Pause) 8M->6M(12M) 0.565ms
[2025-01-07T15:35:03.728+0500][info][gc] GC(14) Concurrent Mark Cycle
[2025-01-07T15:35:03.732+0500][info][gc] GC(14) Pause Remark 7M->7M(12M) 1.965ms
[2025-01-07T15:35:03.732+0500][info][gc] GC(14) Pause Cleanup 8M->8M(12M) 0.011ms
[2025-01-07T15:35:03.733+0500][info][gc] GC(14) Concurrent Mark Cycle 4.692ms
[2025-01-07T15:35:03.733+0500][info][gc] GC(15) Pause Young (Normal) (G1 Evacuation Pause) 8M->6M(12M) 0.646ms
[2025-01-07T15:35:03.734+0500][info][gc] GC(16) Pause Young (Concurrent Start) (G1 Evacuation Pause) 7M->6M(12M) 0.297ms
[2025-01-07T15:35:03.734+0500][info][gc] GC(17) Concurrent Mark Cycle
[2025-01-07T15:35:03.735+0500][info][gc] GC(18) Pause Young (Normal) (G1 Evacuation Pause) 7M->6M(12M) 0.287ms
[2025-01-07T15:35:03.737+0500][info][gc] GC(17) Pause Remark 7M->7M(12M) 1.487ms
[2025-01-07T15:35:03.739+0500][info][gc] GC(19) Pause Young (Normal) (G1 Evacuation Pause) 7M->6M(12M) 0.417ms
[2025-01-07T15:35:03.739+0500][info][gc] GC(17) Pause Cleanup 6M->6M(12M) 0.009ms
[2025-01-07T15:35:03.739+0500][info][gc] GC(17) Concurrent Mark Cycle 4.511ms
[2025-01-07T15:35:03.740+0500][info][gc] GC(20) Pause Young (Prepare Mixed) (G1 Evacuation Pause) 7M->6M(12M) 0.276ms
[2025-01-07T15:35:03.742+0500][info][gc] GC(21) Pause Young (Mixed) (G1 Preventive Collection) 8M->6M(12M) 0.875ms
[2025-01-07T15:35:03.744+0500][info][gc] GC(22) Pause Young (Concurrent Start) (G1 Evacuation Pause) 8M->6M(12M) 0.293ms
[2025-01-07T15:35:03.744+0500][info][gc] GC(23) Concurrent Mark Cycle
[2025-01-07T15:35:03.746+0500][info][gc] GC(24) Pause Young (Normal) (G1 Evacuation Pause) 8M->6M(12M) 0.176ms
[2025-01-07T15:35:03.747+0500][info][gc] GC(23) Pause Remark 7M->7M(12M) 0.941ms
[2025-01-07T15:35:03.748+0500][info][gc] GC(23) Pause Cleanup 8M->8M(12M) 0.010ms
[2025-01-07T15:35:03.748+0500][info][gc] GC(23) Concurrent Mark Cycle 3.517ms
[2025-01-07T15:35:03.749+0500][info][gc] GC(25) Pause Young (Normal) (G1 Evacuation Pause) 8M->6M(12M) 0.104ms
[2025-01-07T15:35:03.751+0500][info][gc] GC(26) Pause Young (Concurrent Start) (G1 Evacuation Pause) 8M->6M(12M) 0.196ms
[2025-01-07T15:35:03.751+0500][info][gc] GC(27) Concurrent Mark Cycle
[2025-01-07T15:35:03.753+0500][info][gc] GC(27) Pause Remark 8M->8M(12M) 1.373ms
[2025-01-07T15:35:03.754+0500][info][gc] GC(28) Pause Young (Normal) (G1 Evacuation Pause) 8M->6M(12M) 0.153ms
[2025-01-07T15:35:03.754+0500][info][gc] GC(27) Pause Cleanup 7M->7M(12M) 0.010ms
[2025-01-07T15:35:03.754+0500][info][gc] GC(27) Concurrent Mark Cycle 3.888ms
[2025-01-07T15:35:03.755+0500][info][gc] GC(29) Pause Young (Normal) (G1 Evacuation Pause) 8M->7M(12M) 0.151ms
[2025-01-07T15:35:03.757+0500][info][gc] GC(30) Pause Young (Concurrent Start) (G1 Evacuation Pause) 8M->7M(12M) 0.177ms
[2025-01-07T15:35:03.757+0500][info][gc] GC(31) Concurrent Mark Cycle
[2025-01-07T15:35:03.760+0500][info][gc] GC(31) Pause Remark 8M->8M(12M) 0.685ms
[2025-01-07T15:35:03.760+0500][info][gc] GC(31) Pause Cleanup 8M->8M(12M) 0.010ms
[2025-01-07T15:35:03.760+0500][info][gc] GC(31) Concurrent Mark Cycle 2.764ms
[2025-01-07T15:35:12.602+0500][info][gc] GC(32) Pause Young (Normal) (G1 Preventive Collection) 10M->5M(12M) 0.517ms
```
### 2. Сортировка методом вставки
```chatinput
InsertSort Начало сортировки - 15:36:36.268345600
InsertSort Конец сортировки - 15:36:38.788753
```
```chatinput
[2025-01-07T15:36:36.268+0500][info][gc] GC(39) Pause Young (Concurrent Start) (G1 Humongous Allocation) 6M->5M(12M) 0.486ms
[2025-01-07T15:36:36.269+0500][info][gc] GC(40) Concurrent Mark Cycle
[2025-01-07T15:36:36.273+0500][info][gc] GC(40) Pause Remark 6M->6M(12M) 1.449ms
[2025-01-07T15:36:36.273+0500][info][gc] GC(40) Pause Cleanup 6M->6M(12M) 0.011ms
[2025-01-07T15:36:36.273+0500][info][gc] GC(40) Concurrent Mark Cycle 4.792ms
```
### 3. Сортировка пузырьком
```chatinput
BubbleSort Начало сортировки - 15:37:38.627408
BubbleSort Конец сортировки - 15:38:28.678741400
```
```chatinput
[2025-01-07T15:37:38.627+0500][info][gc] GC(45) Pause Young (Concurrent Start) (G1 Humongous Allocation) 6M->5M(12M) 0.441ms
[2025-01-07T15:37:38.627+0500][info][gc] GC(46) Concurrent Mark Cycle
[2025-01-07T15:37:38.630+0500][info][gc] GC(46) Pause Remark 6M->6M(12M) 1.356ms
[2025-01-07T15:37:38.631+0500][info][gc] GC(46) Pause Cleanup 6M->6M(12M) 0.012ms
[2025-01-07T15:37:38.631+0500][info][gc] GC(46) Concurrent Mark Cycle 4.174ms
[2025-01-07T15:37:57.704+0500][info][gc] GC(47) Pause Young (Normal) (G1 Evacuation Pause) 8M->6M(12M) 0.405ms
[2025-01-07T15:38:06.710+0500][info][gc] GC(48) Pause Young (Concurrent Start) (G1 Evacuation Pause) 7M->6M(12M) 0.566ms
[2025-01-07T15:38:06.710+0500][info][gc] GC(49) Concurrent Mark Cycle
[2025-01-07T15:38:06.713+0500][info][gc] GC(49) Pause Remark 6M->6M(12M) 1.366ms
[2025-01-07T15:38:06.714+0500][info][gc] GC(49) Pause Cleanup 6M->6M(12M) 0.010ms
[2025-01-07T15:38:06.714+0500][info][gc] GC(49) Concurrent Mark Cycle 4.137ms
[2025-01-07T15:38:16.718+0500][info][gc] GC(50) Pause Young (Normal) (G1 Evacuation Pause) 7M->6M(12M) 0.372ms
[2025-01-07T15:38:25.723+0500][info][gc] GC(51) Pause Young (Concurrent Start) (G1 Evacuation Pause) 7M->6M(12M) 0.398ms
[2025-01-07T15:38:25.723+0500][info][gc] GC(52) Concurrent Mark Cycle
[2025-01-07T15:38:25.727+0500][info][gc] GC(52) Pause Remark 6M->6M(12M) 1.363ms
[2025-01-07T15:38:25.727+0500][info][gc] GC(52) Pause Cleanup 6M->6M(12M) 0.011ms
[2025-01-07T15:38:25.727+0500][info][gc] GC(52) Concurrent Mark Cycle 4.043ms
[2025-01-07T15:38:35.728+0500][info][gc] GC(53) Pause Young (Normal) (G1 Evacuation Pause) 7M->5M(12M) 0.371ms
```
### Графики
![G1GC_CPU_usage.png](/data/img/G1GC_CPU_usage.png)
![G1GC_RAM_usage.png](/data/img/G1GC_RAM_usage.png)

---
## 3. ZGC
### Параметры JVM
`-XX:+UseZGC -Xmx30m -Xms30m -Xlog:gc:data/gc.log:time,level,tags`
### 1. Сортировка слиянием
```chatinput
MergeSort Начало сортировки - 15:51:24.973302400
MergeSort Конец сортировки - 15:51:25.018293200
```
```chatinput
[2025-01-07T15:51:25.010+0500][info][gc] Allocation Stall (main) 12.165ms
[2025-01-07T15:51:25.011+0500][info][gc] GC(9) Garbage Collection (Allocation Stall) 30M(100%)->14M(47%)
[2025-01-07T15:51:25.076+0500][info][gc] GC(10) Garbage Collection (Allocation Rate) 26M(87%)->8M(27%)
[2025-01-07T15:51:25.170+0500][info][gc] GC(11) Garbage Collection (Allocation Rate) 8M(27%)->8M(27%)
[2025-01-07T15:51:25.277+0500][info][gc] GC(12) Garbage Collection (Allocation Rate) 8M(27%)->8M(27%)
[2025-01-07T15:51:25.370+0500][info][gc] GC(13) Garbage Collection (Allocation Rate) 10M(33%)->10M(33%)
[2025-01-07T15:51:25.482+0500][info][gc] GC(14) Garbage Collection (Allocation Rate) 10M(33%)->8M(27%)
[2025-01-07T15:51:25.574+0500][info][gc] GC(15) Garbage Collection (Allocation Rate) 8M(27%)->8M(27%)
[2025-01-07T15:51:35.476+0500][info][gc] GC(16) Garbage Collection (Proactive) 12M(40%)->10M(33%)
```
### 2. Сортировка методом вставки
```chatinput
InsertSort Начало сортировки - 15:52:29.089928700
InsertSort Конец сортировки - 15:52:31.593412
```
```chatinput
[2025-01-07T15:52:29.174+0500][info][gc] GC(20) Garbage Collection (Proactive) 12M(40%)->10M(33%)
[2025-01-07T15:52:39.482+0500][info][gc] GC(21) Garbage Collection (Proactive) 14M(47%)->8M(27%)
```
### 3. Сортировка пузырьком
```chatinput
BubbleSort Начало сортировки - 15:53:09.641398300
BubbleSort Конец сортировки - 15:54:03.362044
```
```chatinput
[2025-01-07T15:53:09.477+0500][info][gc] GC(23) Garbage Collection (Proactive) 12M(40%)->8M(27%)
[2025-01-07T15:53:10.369+0500][info][gc] GC(24) Garbage Collection (Proactive) 12M(40%)->10M(33%)
[2025-01-07T15:53:24.470+0500][info][gc] GC(25) Garbage Collection (Proactive) 14M(47%)->10M(33%)
[2025-01-07T15:53:39.478+0500][info][gc] GC(26) Garbage Collection (Proactive) 14M(47%)->10M(33%)
[2025-01-07T15:53:54.476+0500][info][gc] GC(27) Garbage Collection (Proactive) 14M(47%)->10M(33%)
[2025-01-07T15:54:07.475+0500][info][gc] GC(28) Garbage Collection (Proactive) 14M(47%)->8M(27%)
```
### Графики
![ZGC_CPU_usage.png](/data/img/ZGC_CPU_usage.png)
![ZGC_RAM_usage.png](/data/img/ZGC_RAM_usage.png)

---