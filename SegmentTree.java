Segment Tree :- Its binary rep (mostly)
a=[3, 5, 6, 9, 14, 1]


Identify the Problem :-
1.  Array/ String

2. Range Update or Range optimal

3. Two Recursive calls

4. There is no overlapping subproblem.

----------------------------------------------------------------------------------------------------------
Use Case :-
1. Range Update :- [2,4] :- 10
a=[3, 5, 6, 9, 14, 1]

Ans = [3,5,16, 19, 24, 1]

2. Range Optimal :- [2,4] :- min Val = [6,9,14] :- 6

----------------------------------------------------------------------------------------------------------
Built Segment Tree :- All your orig array elements will be at leaf node.
Examples:-
a=[1,3,5,7,9, 11]

size of array :- 6

Segment Tree Array :-
size:- [2^(logn +1) ] -1

class SegmentTree{
  int[]st;
  SegmentTree(int n) {
    int size = [2^(logn +1) -1 ] ;
    st = new int[size];
  }
  buildSegmentTree(a, 0, n-1, 0);
}

void buildSegmentTree(int[]a, int start, int end, int pos){
  if(start == end) {
    st[pos] = a[start];
    return;
  }
  int mid = start+ (end-start)/2;
  st[pos] = buildSegmentTree(a, start, mid, 2*pos+1) + buildSegmentTree(a, mid+1, end, 2*pos+2);
}
a=[1,3,5,7,9, 11]
st = [1, 3, 0, 4, 5, 0, 0, 0, 0, 0, 0]
start = 0, end = 5, mid= 2,
st[0] = bs(a, 0, 2, 1) + bs(a, 3, 5, 2)

bs(a, 0, 2, 1) ---> start = 0, end = 2 , pos = 1, mid = 1 ---> bs(a,0,1,3) + bs(a, 2, 2, 4) :-

bs(a,0,1,3)---> start =0, end= 1, pos=3, mid=0---> bs(a, 0, 0, 7) + bs(a, 1, 1, 8) --->
bs(a, 0, 0, 7) :- 1, bs(a, 1, 1, 8):- 3

st[3] = 4

--------------------------------------------------------------------------------------------------------------------------
Range Optimal :- Range(qStart, qEnd) :- Range(2,4):- ans = [2^(l+1)]-1 ----: st[idx]
Find the sum of range l to r:- [0-5]

int getSum(int l, int r, int n) {
  if(l < 0 || r > n-1 || l > r)
      return Integer.MAX_VALUE;

  getSumUtil(0, n-1, l, r, 0);
}

int getSumUtil(int start, int end, int qStart, qEnd, int pos) {
    // No Overlap
    if (start > qEnd || end < qStart)
      return;
    // Fully Overlap
    if (qStart >= start && qEnd <= end)
        return st[pos];

    // Partial Overlap
    int mid = (qStart + qEnd) /2;
    return getSumUtil(start, mid, qStart, qEnd, 2*pos+1) + getSumUtil(mid+1, end, qStart, qEnd, 2*pos+2);
}

--------------------------------------------------------------------------------------------------------------------------

Range Update :- Range(qStart, qEnd) :- add 10 in every element

a=[1,3,5,7, 9, 11] :- R(2,4) add 10 ----> a=[1,3, 15, 17, 19, 11]

Range [2,4] :- Sum

Update specific element :- index, newValue (add 10, sub 10):-

int update(int []a, int n, int index, int value) {
  if(index < 0 || index> n-1)
    return Integer.MAX_VALUE;

  a[index] = a[index] + value;
  updateUtil(0, n-1, index, value);
}

void updateUtil(int start, int end, int index, int value, int pos) {
  // No Overlap
  if(start > index || end < index)
    return;

  // update current node
  st[pos] = st[pos] + value;
  // Partial Overlap
  if(start != end) {
    int mid = (start+ end) /2;
    updateUtil(start, mid, index, value, 2*pos+1);
    updateUtil(mid+1, end, index, value, 2*pos+2);
  }
}

------------------------------------------------------------------------------------------------------------------------------
Range Update :- Range(l,r) :- add 10

void update(int start, int end, int qStart, int qEnd, int value, int pos) {
  // No Overlap
  if (start > qEnd || end < qStart || qStart > qEnd)
    return;

  // current node is a leaf node
  if(start == end) {
    st[pos] = st[pos] + value;
    return;
  }
  // Else Recur for all non-leaf nodes
  int mid = (start+ end)/2;
  update(start, mid, qStart, qEnd, value, 2*pos +1);
  update(mid+1, end, qStart, qEnd, value, 2*pos +2);

  // use the child recur call to update current node
  st[pos] = st[2*pos+1] + st[2*pos+2];
}

--------------------------------------------------------------------------------------------------------------------
