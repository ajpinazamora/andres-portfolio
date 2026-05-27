// Manages a list of Polynomial objects and supports operations such as
// insert, delete, search, and quit. Each command modifies or queries the polynomial list.
public class PolyList {
    DLList<Polynomial> polys = new DLList<>();
    
    public void insert(String input){
        String[] parts = input.split(" ", 3);
        String name = parts[1];
        String[] termParts = parts[2].split(" ");
        Polynomial poly = new Polynomial(name);

        for(String termStr : termParts){
            String[] nums = termStr.split(",");
            int c = Integer.parseInt(nums[0]);
            int x = Integer.parseInt(nums[1]);
            int y = Integer.parseInt(nums[2]);
            int z = Integer.parseInt(nums[3]);
            poly.addTerm(new Term(c,x,y,z));
        }
        polys.insertLast(poly);
    }

    public void delete(String name){
        polys.first();
        for(int i = 0; i < polys.size(); i++){
            if (polys.dataRead().name.equals(name)){
                polys.deleteAtCurrent();
                System.out.println("Polynomial " + name + " deleted");
                return;
            }
            polys.next();
        }
        System.out.println("Polynomial " + name + " does not exist");
    }

    public void search(String name){
        polys.first();
        for(int i = 0; i < polys.size(); i++){
            if(polys.dataRead().name.equals(name)){
                System.out.println(polys.dataRead());
                return;
            }
            polys.next();
        }
        System.out.println("Polynomial " + name + " does not exist");
    }

    public void quit(){
        System.out.println("Program has stop");
    }
}