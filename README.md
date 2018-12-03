# gs-matching
stable matching using the GS (Gale–Shapley) algorithm

## Stable Marriage Problem
In mathematics, economics, and computer science, the stable marriage problem (also stable matching problem or SMP) is the problem of finding a stable matching between two equally sized sets of elements given an ordering of preferences for each element. A matching is a mapping from the elements of one set to the elements of the other set. A matching is not stable if:

There is an element A of the first matched set which prefers some given element B of the second matched set over the element to which A is already matched, and B also prefers A over the element to which B is already matched. In other words, a matching is stable when there does not exist any match (A, B) by which both A and B would be individually better off than they are with the element to which they are currently matched.

The stable marriage problem has been stated as follows:

Given n men and n women, where each person has ranked all members of the opposite sex in order of preference, marry the men and women together such that there are no two people of opposite sex who would both rather have each other than their current partners. When there are no such pairs of people, the set of marriages is deemed stable.

## Gale-Shapley Algorithm

```
function stableMatching {
    Initialize all m ∈ M and w ∈ W to free
    while ∃ free man m who still has a woman w to propose to {
       w = first woman on m’s list to whom m has not yet proposed
       if w is free
         (m, w) become engaged
       else some pair (m', w) already exists
         if w prefers m to m'
            m' becomes free
           (m, w) become engaged 
         else
           (m', w) remain engaged
    }
}
```

## EE 382V Social Computing - Assignment 2 - #4

```
4. (50 points) This question requires you to implement stable marriage matching algorithm.
The goal is to understand how the algorithm works; we will not evaluate your submission on
the running time. The input to your program would be a 2n×n pref erence matrix of numbers
from 1 to n. If 1 ≤ i ≤n, the row i of the matrix gives the preferences of man i. If 
n+1 ≤ i ≤ 2n, the row i of the matrix gives the preferences of woman i. The name of your
program should be SMP (Stable Marriage Problem).  We will run the program as
java SMP “inputFileName” ”m/w”.
A sample input and output is shown below.

input.txt
--------
4  // the value of n
2 3 1 4 // preference list of man 1
3 1 4 2
4 2 3 1
1 4 2 3
1 4 2 3 // preference list of woman 1
1 4 3 2
3 2 4 1
2 1 3 4

The program should produce the man-optimal stable marriage matching (man,woman) if m is selected
and woman-optimal stable marriage matching (woman,man) if w is selected.

java SMP input.txt m
--------------------
(1,2)
(2,3)
(3,4)
(4,1)

java SMP input.txt w
--------------------
(1,4)
(2,1)
(3,3)
(4,2)
```
