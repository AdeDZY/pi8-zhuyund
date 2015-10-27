import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import myutil.QuestionComparator;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.fit.util.FSCollectionFactory;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import rank.AbstractRanker;
import rank.CompositeRanker;
import rank.IRanker;
import rank.NgramRanker;
import rank.RankerFactory;
import rank.RelevanceFeedbackRanker;
import type.Measurement;
import type.Passage;
import type.Question;
import type.TokennizedPassage;

/**
 * This CAS Consumer generates the report file with the method metrics
 */
public class PassageRankingWriter extends CasConsumer_ImplBase {
  final String PARAM_OUTPUTDIR = "OutputDir";

  final String OUTPUT_FILENAME = "ErrorAnalysis.csv";

  File mOutputDir;

  IRanker ngramRanker, otherRanker;

  CompositeRanker compositeRanker;

  @Override
  public void initialize() throws ResourceInitializationException {
    String mOutputDirStr = (String) getConfigParameterValue(PARAM_OUTPUTDIR);
    if (mOutputDirStr != null) {
      mOutputDir = new File(mOutputDirStr);
      if (!mOutputDir.exists()) {
        mOutputDir.mkdirs();
      }
    }

    // Initialize rankers
    compositeRanker = (CompositeRanker)RankerFactory.createRanker("CompositeRanker");
    ngramRanker = RankerFactory.createRanker("NgramRanker");
    otherRanker = RankerFactory.createRanker("RelevanceFeedbackRanker");
    compositeRanker.addRanker(ngramRanker);
    compositeRanker.addRanker(otherRanker);
  }

  @Override
  public void processCas(CAS arg0) throws ResourceProcessException {
    System.out.println(">> Passage Ranking Writer Processing");
    // Import the CAS as a aJCas
    JCas aJCas = null;
    File outputFile = null;
    PrintWriter writer = null;
    try {
      aJCas = arg0.getJCas();
      try {
        outputFile = new File(Paths.get(mOutputDir.getAbsolutePath(), OUTPUT_FILENAME).toString());
        outputFile.getParentFile().mkdirs();
        writer = new PrintWriter(outputFile);
      } catch (FileNotFoundException e) {
        System.out.printf("Output file could not be written: %s\n",
                Paths.get(mOutputDir.getAbsolutePath(), OUTPUT_FILENAME).toString());
        return;
      }
 
      writer.println("question_id,tp,fn,fp,precision,recall,f1");
      
      // read passages and put into a hahsmap according to their question id.
      FSIndex passageIndex = aJCas.getAnnotationIndex(TokennizedPassage.type);
      Iterator passageIter = passageIndex.iterator();
      HashMap<String, ArrayList<Passage>> questionPassages = new HashMap<String, ArrayList<Passage>>();
      while (passageIter.hasNext()) {
        Passage passage = (TokennizedPassage) passageIter.next();
        String qid = passage.getQuestionId();
        ArrayList<Passage> passages = questionPassages.get(qid);
        if (passages == null) {
          passages = new ArrayList<Passage>();
          questionPassages.put(qid, passages);
        }
        passages.add(passage);
      }

      // Retrieve all the questions for printout
      List<Question> allQuestions = UimaUtils.getAnnotations(aJCas, Question.class);    
      List<Question> hasAnswers = new ArrayList<Question>();
      for(Question question: allQuestions){
       for(Passage p: questionPassages.get(question.getId()))
         if(p.getLabel()){
           hasAnswers.add(question);
           break;
         }
      }
      List<Question> subsetOfQuestions = RandomUtils.getRandomSubset(hasAnswers, 10);

      Collections.sort(subsetOfQuestions, new QuestionComparator());
      
      for (Question question : subsetOfQuestions) {
        List<Passage> passages = questionPassages.get(question.getId());
        
        // for pi8, only use the composite ranker
       // List<Passage> compositeRankedPassages = compositeRanker.rank(question, passages);
        List<Passage> compositeRankedPassages = ngramRanker.rank(question, passages);
        List<List<Passage>> results = new ArrayList<List<Passage>>();      
        results.add(compositeRankedPassages);

        for (List<Passage> rankedPassages : results) {
          this.getMeasurement(rankedPassages, question);
          Measurement m = question.getMeasurement();

          // Calculate actual precision, recall and F1
          double precision = 0.0;
          double recall = 0.0;
          double f1 = 0.0;
          if (m.getTp() > 0) {
            precision = m.getTp() / (double) (m.getTp() + m.getFp());
            recall = m.getTp() / (double) (m.getTp() + m.getFn());
            f1 = 2 * precision * recall / (precision + recall);
          }

          writer.printf("%s,%d,%d,%d,%.3f,%.3f,%.3f\n", question.getId(), m.getTp(), m.getFn(),
                  m.getFp(), precision, recall, f1);
        }
      }
    } catch (CASException e) {
      try {
        throw new CollectionException(e);
      } catch (CollectionException e1) {
        e1.printStackTrace();
      }
    } finally {
      if (writer != null)
        writer.close();
    }
  }

  /**
   * Evaluate the measurements of a ranking
   * Write results into question.measurement
   * @param rankedPassages
   * @param question
   */
  protected void getMeasurement(List<Passage> rankedPassages, Question question) {
    int cutOff = 5; // use first 5 documents as class 1
    int tp = 0, fp = 0, fn = 0;
    for (int i = 0; i < rankedPassages.size() & i < cutOff; i++) {
      if (rankedPassages.get(i).getLabel())
        ++tp;
      else {
        ++fp;
      }
    }
    for (int i = cutOff; i < rankedPassages.size(); i++) {
      if (rankedPassages.get(i).getLabel()) {
        ++fn;
      }
    }

    Measurement m = question.getMeasurement();
    m.setFp(fp);
    m.setFn(fn);
    m.setTp(tp);
  }
}
