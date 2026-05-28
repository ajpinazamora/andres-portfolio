// Represents a polynomial with a name and a list of Term objects.
// Provides a method to add terms and return the polynomial as a formatted string.
public class Polynomial {
  String name;
  DLList<Term> terms = new DLList<>();

  public Polynomial(String name){
    this.name = name;
  }

  public void addTerm(Term t){
    terms.insertLast(t);
  }
    
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(name).append(" = ");
    terms.first();
    for (int i = 0; i < terms.size(); i++){
        String termStr = terms.dataRead().toString();
        if(!termStr.isEmpty()){
            sb.append(termStr).append(" ");
        }
        terms.next();
    }
    return sb.toString().trim();
  }
}