
/* First created by JCasGen Sun Oct 18 15:12:09 EDT 2015 */
package type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** Passage with tokens
 * Updated by JCasGen Mon Oct 26 22:03:50 EDT 2015
 * @generated */
public class TokennizedPassage_Type extends Passage_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (TokennizedPassage_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = TokennizedPassage_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new TokennizedPassage(addr, TokennizedPassage_Type.this);
  			   TokennizedPassage_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new TokennizedPassage(addr, TokennizedPassage_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = TokennizedPassage.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("type.TokennizedPassage");
 
  /** @generated */
  final Feature casFeat_strTokens;
  /** @generated */
  final int     casFeatCode_strTokens;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getStrTokens(int addr) {
        if (featOkTst && casFeat_strTokens == null)
      jcas.throwFeatMissing("strTokens", "type.TokennizedPassage");
    return ll_cas.ll_getStringValue(addr, casFeatCode_strTokens);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setStrTokens(int addr, String v) {
        if (featOkTst && casFeat_strTokens == null)
      jcas.throwFeatMissing("strTokens", "type.TokennizedPassage");
    ll_cas.ll_setStringValue(addr, casFeatCode_strTokens, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public TokennizedPassage_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_strTokens = jcas.getRequiredFeatureDE(casType, "strTokens", "uima.cas.String", featOkTst);
    casFeatCode_strTokens  = (null == casFeat_strTokens) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_strTokens).getCode();

  }
}



    