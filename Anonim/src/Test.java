import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

interface  AbleToEat{
    public void eat();
}
                ////////Анонимный класс///////////
public class Test {
    public static void main(String[] args) {
        AbleToEat ableToEat = new AbleToEat() {
            @Override
            public void eat() {
                System.out.println("Someone is eating");
            }

        };
        ableToEat.eat();
    }
}