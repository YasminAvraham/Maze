package algorithms.search;
/**
 * interface to solve general problem
 * @author  Yasmin Avraham & Neta Barel
 *
 */
public interface ISearchingAlgorithm {

    Solution solve(ISearchable domain);
    int getNumberOfNodesEvaluated();
    String getName();

}
