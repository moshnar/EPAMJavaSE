package UNIT02;


public class Pen {
    private String InkColor;
    private float thickness;
    private String type;
    private long price;

    public Pen() {
        this.InkColor = "Blue";
        this.thickness = 0.5F;
        this.type = "simple";
        this.price = 50L;
    }

    public Pen(String _colorOfInk, float _thicknessOfLine, String _type, long _price) {
        if(!_colorOfInk.equals("") && _colorOfInk != null) {
            if(_thicknessOfLine < 0.1F) {
                throw new IllegalArgumentException("Input thickness is Invalid!");
            } else if(_price <= 0L) {
                throw new IllegalArgumentException("Input price is Ivvalid");
            } else if(_type.equals("") || _type != null) {
                throw new IllegalArgumentException("Type of pen can\'t be empty!");
            } else {
                this.InkColor = _colorOfInk;
                this.thickness = _thicknessOfLine;
                this.type = _type;
                this.price = _price;
            }
        } else {
            throw new IllegalArgumentException("Illegal color of ink! It have to be not empty");
        }
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else if(this.getClass() != obj.getClass()) {
            return false;
        }
        Pen pen = (Pen)obj;
        if(thickness != pen.thickness){
            return false;
        }
        if(price != pen.price){
            return false;
        }
        if(!this.InkColor.equals(pen.InkColor)){
            return false;
        }
        if(!this.type.equals(pen.type)){
            return false;
        }
        return true;

    }

    public int hashCode() {
        int hash = (int)price * 47
                + (int)thickness * 47
                + InkColor.hashCode()
                + type.hashCode();
        return hash;
    }

    public String toString() {
        String info = "{Price: " + this.price
                + "; ink Color: " + this.InkColor
                + "; Type: " + this.type
                + "; Thickness : " + this.thickness
                + "}";
        return info;
    }
}
