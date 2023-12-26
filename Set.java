public class Set 
/**
 * This is maman 15
 *
 * @author itay lior
 * @version (10/06/2022)
 */
{
    private IntNode _head;
    private int _length;

    /**
     * isEmpty function to check if the set is empty 
     * Time complexity - o(1)
     * Memory complexity - o(1)
     * @return True if set is empty 
     */
    public boolean isEmpty() 
    {
        return _head == null;
    }

    /**
     * check if a number is apart of the set
     * Time complexity - o(n)
     * Memory complexity - o(1)
     * @param num The number to check
     * @return True if the number is in the set
     */
    public boolean isMember(int num) 
    {
        IntNode p = _head;
        while (p != null)
        {
            if (p.getValue() == num)
                return true;
            if (p.getValue() > num)
                return false;
            p = p.getNext();
        }
        return false;
    }

    /**
     * check if 2 sets are the equal
     * * Time complexity - o(n)
     * * Memory complexity - o(1)
     * @param other the other set to check if equal 
     * @return true if equls 
     */
    public boolean equals(Set other) 
    {
        if (other == null)
            return false;
        if ( other.numOfElements() !=  this.numOfElements())
            return false;
        IntNode p1 = this._head;
        IntNode p2 = other._head;
        while (p1 != null && p2 != null)
        {
            if (p1.getValue() != p2.getValue())
                return false;
            p1 = p1.getNext();
            p2 = p2.getNext();
        }
        return true;
    }

    /**
     * returns length
     * Time complexity - o(1)
     * Memory complexity - o(1)
     * @return lenght of the nodechain
     */
    public int numOfElements() 
    {
        return _length;
    }

    /**
     * check if the other set is a subset to this set 
     * Time complexity - o(n)
     * Memory complexity - o(1)
     * @param other the set to compare to this subset 
     * @return true if the other set is a subset to this set
     */
    public boolean subSet(Set other) 
    {
        if (other == null){
            return false;
        }
        if(other.isEmpty()){
            return true;
        }
        if (this.numOfElements() < other.numOfElements()){
            return false;
        }
        IntNode p1 = this._head;
        IntNode p2 = other._head;
        boolean flag = false;
        while (p1 != null && p2 != null)
        {
            if (p1.getValue() > p2.getValue())
                return false;
            if (p1.getValue() == p2.getValue())
            {
                p1 = p1.getNext();
                p2 = p2.getNext();
            }
            else
            {
                p1 = p1.getNext();
            }
        }
        if(p2==null) return true;
        else return false;
    }

    /**
     * adds a number to a new set 
     * Time complexity - o(n)
     * Memory complexity - o(1)
     * @param x the number to add
     */
    public void addToSet(int x) 
    {
        if (x % 2 == 1 && x > 0)
        {
            if (this.isEmpty())
            {
                _head = new IntNode(x, null);
                _length++;
                return;
            }
            IntNode p = _head;
            if (_head.getValue() > x)
            {
                _head = new IntNode(x, _head);
                _length++;
                return;
            }
            while (p.getNext() != null)
            {
                if (p.getValue() == x)
                    return;
                if (p.getNext().getValue() > x)
                {
                    IntNode temp = new IntNode(x, p.getNext());
                    p.setNext(temp);
                    _length++;
                    return;
                }
                p = p.getNext();
            }
            if (p.getValue() != x)
            {
                p.setNext(new IntNode(x, null));
                _length++;
            }
        }
    }

    /**
     * remove a number from set 
     * Time complexity - o(n)
     * Memory complexity - o(1)
     * @param x the number to remove
     */
    public void removeFromSet(int x) 
    {
        if (!isMember(x))
            return;
        IntNode p = _head;
        IntNode next = p.getNext();
        if (p.getValue() == x)
        {
            _head = _head.getNext();
            _length--;
            return;
        }
        while (p != null && next != null)
        {
            if (next.getValue() == x)
            {
                p.setNext(next.getNext());
                _length--;
                return;
            }
            p = p.getNext();
            next = next.getNext();
        }
    }

    /**
     * makes the set into a string
     * Time complexity - o(n)
     * Memory complexity - o(n)
     * @return a string description to the set
     */
    public String toString() 
    {
        String temp = new String("{");
        IntNode p = _head;
        while (p != null)
        {
            if (p.getNext() == null)
                temp += p.getValue();
            else
                temp += p.getValue() + ",";
            p = p.getNext();
        }
        temp += "}";
        return new String(temp);
    }

    /**
     * Time complexity - o(1)
     * Memory complexity - o(1)
     */
    private IntNode addParamToSet(IntNode p, int x)
    {
        if (p != null)
        {
            p.setNext(new IntNode(x, null));
            return p.getNext();
        }
        return null;
    }

    /**
     * Time complexity - o(1)
     * Memory complexity - o(1)
     */
    private IntNode addCurrHeadSet(int x)
    {
        if (this.isEmpty())
        {
            this._head = new IntNode(x, null);
        }
        return this._head;

    }

    /**
     * returns the intersection set to this set 
     * Time complexity - o(n)
     * Memory complexity - o(n)
     * @param other the set to intersect with
     * @return a new set object which is the intersection of both sets
     */
    public Set intersection(Set other) 
    {
        if (other == null)
            return null;
        IntNode pThis = this._head; //pointer of this head
        IntNode pOther = other._head; // pointer of other head 
        Set temp = new Set();
        IntNode pTemp = temp._head;//pointer temp head 
        while (pThis != null && pOther != null)
        {
            if (pThis.getValue() == pOther.getValue())
            {
                if (temp.isEmpty())
                    pTemp = temp.addCurrHeadSet(pThis.getValue()); // adding the value to temp if it doesnt have a node
                else
                    pTemp = temp.addParamToSet(pTemp, pThis.getValue()); // adding if there is a value allready 
                temp._length++; //updating length
                pThis = pThis.getNext();
                pOther = pOther.getNext();
            }
            else
            {
                if (pThis.getValue() > pOther.getValue()) // checking with node we should move next from the set 
                    pOther = pOther.getNext();
                else
                    pThis = pThis.getNext();
            }
        }
        return temp;
    }

    /**
     * the union set of this and other set 
     * Time complexity - o(n)
     * Memory complexity - o(n)
     * @param other the set to get the unions with
     * @return a new set which is the union of this and other set 
     */
    public Set union(Set other) 
    {
        if (other == null)
            return null;
        IntNode pThis = this._head; //pointer of this head
        IntNode pOther = other._head; // pointer of other head 
        Set temp = new Set();
        IntNode pTemp = temp._head;// pointer of temp head 

        while (pThis != null && pOther != null)
        {
            if (pThis.getValue() == pOther.getValue())
            {
                if (temp.isEmpty())
                    pTemp = temp.addCurrHeadSet(pThis.getValue()); //adds head to temp set 
                else
                {
                    pTemp = temp.addParamToSet(pTemp, pThis.getValue()); // adds new value to temp set 
                }
                pThis = pThis.getNext();
                pOther = pOther.getNext();
            }
            else
            {
                if (pThis.getValue() > pOther.getValue()) // if this value bigger then other 
                {
                    if (temp.isEmpty())
                        pTemp = temp.addCurrHeadSet(pOther.getValue()); //add to temp the head of other value if head is empty 
                    else
                        pTemp = temp.addParamToSet(pTemp, pOther.getValue()); //add to temp the chain of other value
                    pOther = pOther.getNext();
                }
                else
                {
                    if (temp.isEmpty())
                        pTemp = temp.addCurrHeadSet(pThis.getValue()); //add to temp the head of this value if head is empty 
                    else
                        pTemp = temp.addParamToSet(pTemp, pThis.getValue());//add to temp the chain of this value
                    pThis = pThis.getNext();
                }
            }
            temp._length++; // update length
        }

        while (pThis != null)
        {
            if (temp.isEmpty())
                pTemp = temp.addCurrHeadSet(pThis.getValue()); //adds head to temp set
            else
                pTemp = temp.addParamToSet(pTemp, pThis.getValue()); // adds new value to temp set 
            pThis = pThis.getNext();
            temp._length++; //update length
        }

        while (pOther != null) //same with other 
        {
            if (temp.isEmpty())
                pTemp = temp.addCurrHeadSet(pOther.getValue());
            else
                pTemp = temp.addParamToSet(pTemp, pOther.getValue());
            pOther = pOther.getNext();
            temp._length++;
        }
        return temp;
    }

    /**
     * the difference of both sets 
     * Time complexity - o(n)
     * Memory complexity - o(n)
     * @param other the set to get difference with
     * @return a new set which is the difference between other and this 
     */
    public Set difference(Set other) 
    {
        if (other == null)
            return null;
        IntNode pThis = this._head;
        IntNode pOther = other._head;
        Set temp = new Set();
        IntNode pTemp = temp._head;

        while (pOther != null && pThis != null)
        {
            if (pOther.getValue() == pThis.getValue())
            {
                pThis = pThis.getNext();
                pOther = pOther.getNext();
            }
            else
            {
                if (pThis.getValue() > pOther.getValue())
                    pOther = pOther.getNext();
                else
                {
                    if (temp.isEmpty())
                        pTemp = temp.addCurrHeadSet(pThis.getValue());//adds head to temp set if different and other is smaller than this
                    else
                        pTemp = temp.addParamToSet(pTemp, pThis.getValue()); //adds value to the chain of temp set if different
                    pThis = pThis.getNext();
                    temp._length++; // update length
                }
            }
        }
        while (pThis != null)
        {
            if (temp.isEmpty())
                pTemp = temp.addCurrHeadSet(pThis.getValue()); //adds head to temp set
            else
                pTemp = temp.addParamToSet(pTemp, pThis.getValue()); //adds value to the chain of temp
            pThis = pThis.getNext();
        }
        return temp;
    }
}