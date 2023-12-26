public class IntNode
/**
 * This is maman 15
 *
 * @author itay lior
 * @version (10/06/2022)
 */
{
    private int _value;
    private IntNode _rightSon,_leftSon;
    public IntNode(int v, IntNode leftSon , IntNode rightSon)
    {
        _value = v;
        _rightSon = rightSon;
        _leftSon = leftSon;
    }
    public IntNode(){
        
    }

    public int getValue() {
        return _value;
    }

    public IntNode getRightSon() {
        return _rightSon;
    }
    public IntNode getLeftSon() {
        return _leftSon;
    }

    public void setValue(int v) {
        _value = v;
    }

    public void setNextRightSon(IntNode n) {
        _rightSon = n;
}
public void setNextLeftSon(IntNode n) {
        _leftSon = n;
}
}