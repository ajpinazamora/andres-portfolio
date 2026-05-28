// This class represents a single term of a polynomial, including its coefficient
// and the exponents of x, y, and z. It provides a formatted string output for display.
public class Term {
    int coeff, xExp, yExp, zExp;

    public Term(int coeff, int xExp, int yExp, int zExp){
        this.coeff = coeff;
        this.xExp = xExp;
        this.yExp = yExp;
        this.zExp = zExp;
    }

    public String toString(){
        if (coeff == 0) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(coeff > 0 ? "+ " : "- ");
        if(Math.abs(coeff) != 1 || (xExp == 0 && yExp == 0 && zExp == 0))
        sb.append(Math.abs(coeff));
        if(xExp > 0) sb.append("(x^" + xExp +")");
        if(yExp > 0) sb.append("(y^" + yExp +")");
        if(zExp > 0) sb.append("(z^" + zExp +")");
        return sb.toString().trim();
    }
}