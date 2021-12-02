import java.util.*;
import java.util.function.IntBinaryOperator;

public class Set {
  private ArrayList<Integer> a;

  public Set() {
    a = new ArrayList<>();
  }

  public int[] toArray() {
    int[] ia = new int[a.size()];
    for (int i = 0; i < ia.length; i++) {
      ia[i] = a.get(i);
    }
    return ia;
  }

  public void insert(int x) {
    for (int i = 0; i < a.size(); i++)
      if (a.get(i) > x) {
        a.add(i, x);
        return;
      } else if (a.get(i) == x)
          return;
    a.add(x);
  }

  public boolean member(int x) {
    boolean isMember = false;
    for (Integer n : a)
      if (n == x) {
        isMember = true;
        break;
      }
    return isMember;
  }

  public void intersect(Set s) {
    int i = 0;
    int j = 0;
    while(i < a.size() && j < s.a.size()) {
      if (a.get(i).equals(s.a.get(j))){
        i++;
        j++;
      } else {
        if (a.get(i) < s.a.get(j)) {
          a.remove(i);
          i++;
        } else {
          a.remove(j);
          j++;
        }
      }
    }
    while(i < a.size()){
      a.remove(i);
      i++;
    }
  }

  // Try with:
  //   (a, b) -> a + b;
  //   (a, b) -> a - b;
  public boolean distinctClosed(IntBinaryOperator f) {
    int vi, vj;
    for (Integer i : a)
      for (Integer j : a) {
        vi = i;
        vj = j;
        if (this.member(f.applyAsInt(vi, vj)) && vi != vj)
          return true;
      }
    return false;
  }
}
