package Object;

public class SuperObjectDesign extends SuperObject {

    SuperObject obj;


    @Override
    public SuperObject CreateObject(Objects type) {

        switch(type) {
            case box1:
                obj = new DESIGN_Box1();
                break;
            case box2:
                obj = new DESIGN_Box2();
                break;
        }

        return obj;
    }
}
