public class Test {
    
    public Test() {
        BDDStack<Integer> stack1 = new ArrayStack<>(10);
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        BDDStack<Integer> stack2 = stack1.newInstance();
        BDDStack<Integer> stack3 = stack1.copy();
        for(int i = 0; i < 3; i++) {
            System.out.println(stack1.pop()); // Should print 3 2 1
        }
        System.out.println("----------");
        System.out.println(stack2.depth()); // Should print 0
        System.out.println(stack2.capacity()); // Should print 10
        System.out.println("----------");
        for(int i = 0; i < 3; i++) {
            System.out.println(stack3.pop()); // Should print 3 2 1
        }
        System.out.println("----------");
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        stack1.flip();
        for(int i = 0; i < 3; i++) {
            System.out.println(stack1.pop()); // Should print 1 2 3
        }
        System.out.println("----------");
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        for(int i : stack1) {
            System.out.println(i); // Should print 1 2 3
        }
        System.out.println("--------------------");
        stack1 = new ListStack<>(10);
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        stack2 = stack1.newInstance();
        stack3 = stack1.copy();
        for(int i = 0; i < 3; i++) {
            System.out.println(stack1.pop()); // Should print 3 2 1
        }
        System.out.println("----------");
        System.out.println(stack2.depth()); // Should print 0
        System.out.println(stack2.capacity()); // Should print 10
        System.out.println("----------");
        for(int i = 0; i < 3; i++) {
            System.out.println(stack3.pop()); // Should print 3 2 1
        }
        System.out.println("----------");
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        stack1.flip();
        for(int i = 0; i < 3; i++) {
            System.out.println(stack1.pop()); // Should print 1 2 3
        }
        System.out.println("----------");
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        for(int i : stack1) {
            System.out.println(i); // Should print 1 2 3
        }
    }
    
    public static void main(String args[]) {
        new Test();
    }
}
