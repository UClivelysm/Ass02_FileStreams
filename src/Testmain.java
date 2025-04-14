public class Testmain {
    public static void main(String[] args) {
        //FilePicker.SmartFileWriter("Test of File writer");


        Product product1 = new Product("123456", "Widget", "A standrd widget with many features", 123456.12);
        Product product2 = new Product("654321", "Gizmo", "A compact gizmo that is very efficient", 98765.43);
        Product product3 = new Product("ABCDEF", "Super Deluxe Gadget", "An advanced gadget for the modern user with extended capabilities", 54321.00);

        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);
        Product product4 = Product.toProduct(product1.toString());

        System.out.println(product4.toXML());


    }
}
