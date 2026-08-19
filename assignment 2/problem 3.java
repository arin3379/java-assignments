import java.io.*;
import java.util.*;
abstract class Shape{
    abstract void area();
    abstract void parameter();
}
class Circle extends Shape{
    double r;
    Circle(double a){
        r=a;
    }
    @Override
    void area(){
        System.out.println("area of circle=" + 3.14*r*r);
    }
    @Override
    void parameter(){
        System.out.println("parameter of circle=" + 2*3.14*r);
    }
}
class Rectangle extends Shape{
    int l,b;
    Rectangle(int a, int c){
        l=a;
        b=c;
    }
    @Override
    void area(){
        System.out.println("area of rectangle=" + l*b);
    }
    @Override
    void parameter(){
        System.out.println("parameter of rectangle=" + 2*(l+b));
    }
}
class Triangle extends Shape{
    int a,b,c;
    Triangle(int x, int y, int z){
        a=x;
        b=y;
        c=z;
    }
    @Override
    void area(){
        System.out.println("area of triangle=" + 0.5*a*b);
    }
    @Override
    void parameter(){
        System.out.println("parameter of triangle=" + a+b+c);
    }
}
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Shape[] s = new Shape[3];
        System.out.println("enter the type of shape:");
        System.out.println("1. circle");
        System.out.println("2. rectangle");
        System.out.println("3. triangle");
        int ch = Integer.parseInt(br.readLine());
        switch(ch)
        {
            case 1:
            System.out.println("enter the radius of circle:");
            double r = Double.parseDouble(br.readLine());
            s[0] = new Circle(r);
            s[0].area();
            s[0].parameter();
            break;
            case 2:
            System.out.println("enter the length and breadth of rectangle:");
            int l = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());
            s[1] = new Rectangle(l,b);
            s[1].area();
            s[1].parameter();
            break;
            case 3:
            System.out.println("enter the three sides of triangle:");
            int a = Integer.parseInt(br.readLine());
            int b1 = Integer.parseInt(br.readLine());
            int c = Integer.parseInt(br.readLine());
            s[2] = new Triangle(a,b1,c);
            s[2].area();
            s[2].parameter();
            break;
            default:
            System.out.println("Invalid choice");
        }
    }
}