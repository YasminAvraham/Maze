package algorithms.search;

import java.util.ArrayList;
import java.util.PriorityQueue;
/**
 * class to create new search algorithm Best First Search Algorithm
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class BestFirstSearch extends ASearchingAlgorithm {
    private PriorityQueue<AState> temp;
    /**
     * constructor.
     */
    public BestFirstSearch() {
        super();
        this.name="Best First Search Algorithm";
        temp=new PriorityQueue<AState>(this::compareA);
    }
    /**
     * This function compare this state for Priority Queue
     * @param s1, s2
     * @return 1 if s1 is bigger -1 if s2 is bigger else 0
     */
    private int compareA(AState s1, AState s2) {
        if (s1.getPrice() > s2.getPrice())
            return 1;
        if (s1.getPrice()< s2.getPrice())
            return -1;
        else
            return 0;

    }

    /**
     * This function solve maze
     * @param domain
     * @return all the solution to solve maze
     */
    @Override
    public Solution solve(ISearchable domain) {
        if(domain==null)
            return null;
        Solution s2=domain.checkIfIsSmall();
        if(s2!=null){
            domain.isClear();
            return s2;
        }
        Solution sol = new Solution();
        temp.add(domain.getStartState());
        numOfNude++;
        domain.isVisit(domain.getStartState());
        ArrayList<AState> neighbors=new ArrayList<AState>();
        while(!temp.isEmpty()){
            AState curr=temp.poll();
            if(domain.isEqual(domain.getGoalState(),curr)){
                numOfNude++;
                sol =solutionPath(curr,sol);

                break;
            }
            neighbors=domain.getAllPossibleState(curr);
            for(int i=0;i<neighbors.size();i++){
                if(domain.isEqual(domain.getGoalState(),neighbors.get(i))){
                    neighbors.get(i).pervState=curr;
                    neighbors.get(i).setPrice(neighbors.get(i).getPrice()+curr.getPrice());
                    numOfNude++;
                    sol =solutionPath(neighbors.get(i),sol);
                    break;
                }
                neighbors.get(i).pervState=curr;
                neighbors.get(i).setPrice(neighbors.get(i).getPrice()+curr.getPrice());
                temp.add(neighbors.get(i));
                numOfNude++;
            }
        }

        domain.isClear();
        return sol;
    }


}
