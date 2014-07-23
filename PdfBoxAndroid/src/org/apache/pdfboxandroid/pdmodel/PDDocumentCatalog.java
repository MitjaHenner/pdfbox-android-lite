package org.apache.pdfboxandroid.pdmodel;

import org.apache.pdfboxandroid.PDFBox;
import org.apache.pdfboxandroid.cos.COSBase;
import org.apache.pdfboxandroid.cos.COSDictionary;
import org.apache.pdfboxandroid.cos.COSName;
import org.apache.pdfboxandroid.pdmodel.common.COSObjectable;
import org.apache.pdfboxandroid.pdmodel.interactive.form.PDAcroForm;

import android.util.Log;

/**
 * This class represents the acroform of a PDF document.
 *
 * @author <a href="mailto:ben@benlitchfield.com">Ben Litchfield</a>
 * @version $Revision: 1.21 $
 */
public class PDDocumentCatalog implements COSObjectable {
	private COSDictionary root;
    private PDDocument document;
    
    private PDAcroForm acroForm = null;
    
    /**
     * Constructor.
     *
     * @param doc The document that this catalog is part of.
     */
    public PDDocumentCatalog( PDDocument doc )
    {
        document = doc;
        root = new COSDictionary();
        root.setItem( COSName.TYPE, COSName.CATALOG );
        document.getDocument().getTrailer().setItem( COSName.ROOT, root );
    }

    /**
     * Constructor.
     *
     * @param doc The document that this catalog is part of.
     * @param rootDictionary The root dictionary that this object wraps.
     */
    public PDDocumentCatalog( PDDocument doc, COSDictionary rootDictionary )
    {
        document = doc;
        root = rootDictionary;
    }

	/**
     * Convert this standard java object to a COS object.
     *
     * @return The cos object that matches this Java object.
     */
    public COSBase getCOSObject()
    {
        return root;
    }
    
    /**
     * This will get the documents acroform.  This will return null if
     * no acroform is part of the document.
     *
     * @return The documents acroform.
     */
    public PDAcroForm getAcroForm()
    {
			if( acroForm == null )
			{
				Log.e(PDFBox.LOG_TAG, "no acroform");
			    COSDictionary acroFormDic =
			        (COSDictionary)root.getDictionaryObject( COSName.ACRO_FORM );
			    
			    Log.e(PDFBox.LOG_TAG, "got dictionary");
			    if( acroFormDic != null )
			    {
			    	Log.e(PDFBox.LOG_TAG, "no dictionary");
			        acroForm = new PDAcroForm( document, acroFormDic );
			        Log.e(PDFBox.LOG_TAG, "create form");
			    }
			}
        return acroForm;
    }
}