package rank;

public class RankerFactory {
  public static IRanker createRanker(String type){
  
    if(type.equals("NgramRanker"))
      return new NgramRanker();
    else if(type.equals("RelevanceFeedbackRanker"))
      return new RelevanceFeedbackRanker();
    else if(type.equals("CompositeRanker"))
      return new CompositeRanker();
    else
      return null;
  }

}
