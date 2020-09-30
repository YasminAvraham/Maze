package algorithms.search;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
/**
 * class to create new search algorithm Breadth First Search Algorithm
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public class BreadthFirstSearch extends ASearchingAlgorithm {

    private Queue<AState> temp;

    public BreadthFirstSearch() {
        super();
        this.name="Breadth First Search Algorithm";
        temp=new ArrayDeque<AState>();
    }
    /**
     * This function solve maze
     * @param domain
     * @return all the solution to solve maze
     */
    @Override
    public Solution solve(ISearchable domain) {
        //Solution result=new Solution();
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
                    numOfNude++;
                    sol =solutionPath(neighbors.get(i),sol);

                    break;
                }
                neighbors.get(i).pervState=curr;
                temp.add(neighbors.get(i));
                numOfNude++;
            }
        }

        domain.isClear();
        return sol;
    }



}
