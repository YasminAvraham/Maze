package algorithms.search;
/**
 * abstract class to create new search algorithm
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public abstract class AState  {
    protected AState pervState;
    protected int id;
    private static int id_numbers=0;
    protected  int price;
    /**
     * constructor.
     */
    public AState() {
        this.pervState = null;
        this.id = id_numbers;
        id_numbers++;
    }
    /**
     * constructor.
     * @param cost
     */
    public AState(int cost) {
        this.pervState = null;
        this.id = id_numbers;
        id_numbers++;
        this.price=cost;
    }
    /**
     * This function get prev state
     * @return prev state
     */
    public AState getPervState() {
        return pervState;
    }

    /**
     * This function set prev state
     * @param pervState
     */
    public void setPervState(AState pervState) {
        this.pervState = pervState;
    }


    /**
     * This function get id
     * @return id
     */
    public int getId() {
        return id;
    }

    public int getPrice(){return  this.price;}
    public  void setPrice(int cost){this.price=cost;}


}
