The notion of local search suggests we use greedy descent by selecting the neighbour with the lowest cost. 
However, a problem with selecting the best neighbour is that we are only minimizing a locally optimal solution, not necessarily the global optimal solution.
Stochastic local search aims to alleviate the issue of getting "trapped" in a local minimum by using randomization. In these implementations, 
I implement two algorithms of stochastic local search: greedy descent with random walks and greedy descent with random restarts, based on a given scheduler problem.