package lucene.demo;

import lucene.demo.search.Indexer;
import lucene.demo.search.SearchEngine;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
public class Main {

    /** Creates a new instance of Main */
    public Main() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            // build a lucene index
            System.out.println("rebuildIndexes");
            Indexer  indexer = new Indexer();
            indexer.rebuildIndexes();
            System.out.println("rebuildIndexes done");

            // perform search on "Notre Dame museum"
            // and retrieve the top 100 result
            System.out.println("performSearch");
            SearchEngine se = new SearchEngine();

            // start to search something
            TopDocs topDocs = se.performSearch("无所谓", 100);

            System.out.println("Results found: " + topDocs.totalHits);
            // find results
            ScoreDoc[] hits = topDocs.scoreDocs;
            for (int i = 0; i < hits.length; i++) {
                Document doc = se.getDocument(hits[i].doc);
                System.out.println(doc.get("name")
                        + " " + doc.get("city")
                        + " (" + hits[i].score + ")");
            }
            System.out.println("performSearch done");
        } catch (Exception e) {
            System.out.println("Exception caught.\n");
        }
    }
}
